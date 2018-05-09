package com.bluedot.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import com.bluedot.dao.RecoDao;
import com.bluedot.po.Categories;
import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.po.Recommender;
import com.bluedot.util.DBConnection;
import com.bluedot.util.SplitPage;

public class RecoDaoImpl implements RecoDao {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ResultSet rs1;
	ResultSet rs2;
	
	public Map<Integer, String> getJobMap() {// 在插入初始数据的时候记得要提交数据，否则则无法查询到数据
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			conn = DBConnection.getConn();
			String sql = "select cate_id,cate_name from CATEGORIES_TABLE where IS_WHAT_CATE=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "B");
			// int num=pstm.executeUpdate();用来测试查询出的数据有多少行
			// System.out.println("查询出的职能数"+num);
			rs = pstm.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt("cate_id"), rs.getString("cate_name"));
				System.out.println("用户的职能" + rs.getString("cate_name"));
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	public Map<Integer, String> getMajorMap() {
		try {
			Map<Integer, String> map = new HashMap<Integer, String>();
			conn = DBConnection.getConn();
			String sql = "select cate_id,cate_name from categories_table where IS_WHAT_CATE=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "C");
			rs = pstm.executeQuery();
			while (rs.next()) {
				map.put(rs.getInt("cate_id"), rs.getString("cate_name"));
				System.out.println("用户的专业" + rs.getString("cate_name"));
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	/*
	 * 该方法用于保存用户推进的被推荐人的信息
	 */
	public boolean saveReco(Recommender reco) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			String sql="SELECT reco_id_seq.nextval FROM DUAL ";
			int i = 0;
     		pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
					i=rs.getInt(1);
				   }
			 sql = "INSERT INTO recommender_table (reco_id,reco_name,emp_id,job_id,major_id,"
					+ "reco_sex,reco_pic,reco_degree,reco_graduated_from,reco_is_graduated,"
					+ "reco_graduated_time,reco_phone,reco_mailbox,reco_skills,reco_resume,"
					+ "reco_is_recommended,reco_current_status)"
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			int reco_id=i;
			pstm.setInt(1,reco_id);
			pstm.setString(2, reco.getRecoName());
			pstm.setInt(3, reco.getEmpId());
			pstm.setInt(4, reco.getJobId());
			pstm.setInt(5, reco.getMajorId());
			pstm.setString(6, reco.getSex());
			pstm.setString(7, reco.getPic());
			pstm.setString(8, reco.getRecoDegree());
			pstm.setString(9, reco.getGraduatedFrom());
			pstm.setString(10, reco.getIsGraduated());
			pstm.setDate(11, reco.getGraduatedTime());
			pstm.setString(12, reco.getPhone());
			pstm.setString(13, reco.getMail());
			pstm.setString(14, reco.getSkills());
			pstm.setString(15, reco.getResume());
			pstm.setString(16, reco.getIsRecommended());
			pstm.setString(17, reco.getCurrStatus());
			while (pstm.executeUpdate() == 1) {
				sql = "select reco_id from reco_id_remark";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				int recoId = 0;
				while (rs.next()) {
					recoId = rs.getInt(1);
				}
				System.out.println(recoId);
				// 当被推荐人保存成功的时候，则向历史推荐表记录该被推荐的人的记录。
				sql = "insert into reco_history_table(reco_id) values(?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, reco_id);
				while (pstm.executeUpdate() == 1) {
	                           
						sql = "delete from reco_id_remark";
						pstm = conn.prepareCall(sql);
						pstm.execute();
						//给所有的Hr或者admin发消息提示
						sql ="select emp_id from emp_table where (role_id =1000 or role_id =8888) AND emp_id!=?" ;
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1, reco.getEmpId());
						rs2 =pstm.executeQuery();
						while(rs2.next()){
							System.out.println(rs2.getInt("emp_id"));
							sql ="update emp_table set message =message+1 where emp_id=? ";
							pstm = conn.prepareStatement(sql);
							pstm.setInt(1, rs2.getInt("emp_id"));
							pstm.execute();
						}
						System.out.println("看dao层有没有结束");
						conn.commit();
						return true;
					

				}

			}

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return false;
	}

	
	
	/*
	 * 根据用户的登录的身份查询被推荐的信息，用户为admin可以查询所有的被推荐人的信息 ，其他用户只能查看到自己所推荐的被推荐人的信息。
	 */
	public List<Recommender> getRecommender(int empId) {
		try {
			conn = DBConnection.getConn();
			// 首先根据empId判断他的角色，如果是管理员则可以查看所有被推荐人的信息，否则只能查看自己做推荐的人
			String sql = "SELECT role_id FROM emp_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			int roleId = 0;
			if (rs.next())// 万一没有数据，会提示异常（结果集耗尽）
			{
				roleId = rs.getInt(1);
			}
			List<Recommender> list = new ArrayList<Recommender>();
			if (roleId == 8888) {// 管理员身份
				sql = "select rt.*,edt.emp_fullname as fullname,ct.cate_name as jobName,ctt.cate_name as majorName from recommender_table rt "
						+ "left outer join emp_details_table edt on(rt.emp_id=edt.emp_id)"
						+ "join categories_table ct on(rt.job_id=ct.cate_id) and ct.is_what_cate='B'"
						+ "join categories_table ctt on (ctt.cate_id=rt.major_id) and ctt.is_what_cate='C'";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					Recommender reco = new Recommender();
					reco.setRecoId(rs.getInt(1));
					reco.setRecoName(rs.getString(2));
					reco.setEmpId(rs.getInt(3));
					reco.setCurrentHrId(rs.getInt(4));
					// reco.setJobId(rs.getInt(5));// 职位
					reco
							.setJobCate(new Categories(rs.getString("jobName"),
									"B"));
					// reco.setMajorId(rs.getInt(6));// 专业
					reco.setMajorCate(new Categories(rs.getString("majorName"),
							"C"));
					reco.setSex(rs.getString(7));
					reco.setPic(rs.getString(8));
					reco.setRecoDegree(rs.getString(9));
					reco.setGraduatedFrom(rs.getString(10));
					reco.setIsGraduated(rs.getString(11));
					reco.setGraduatedTime(rs.getDate(12));
					reco.setPhone(rs.getString(13));
					reco.setMail(rs.getString(14));
					reco.setSkills(rs.getString(15));
					reco.setResume(rs.getString(16));
					reco.setIsRecommended(rs.getString(17));
					reco.setCurrStatus(rs.getString(18));

					// 当前处理的HR的ID进行查询姓名
					sql = "select emp_fullname from  emp_details_table where emp_id=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, rs.getInt(4));
					rs1 = pstm.executeQuery();
					String empFullName = null;
					if (rs1.next()) {
						empFullName = rs1.getString("emp_fullname");
					}
					System.out.println("当前处理HR的姓名：" + empFullName);
					reco.setCurrentHrName(empFullName);

					/*******************************/
					String fullname = rs.getString("fullname");
					EmpDetails empdt = new EmpDetails();
					empdt.setFullname(fullname);
					Employee emp = new Employee();
					emp.setEmpDetails(empdt);
					reco.setEmployee(emp);
					list.add(reco);

				}
				return list;
			} else {// 其他身份

				// 首先判断该用户的详情表是否为空
				sql = "select emp_fullname from emp_details_table where emp_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				EmpDetails empdt = null;
				Employee emp = null;
				String fullname = null;
				if (rs.next()) {
					fullname = rs.getString(1);
				}
				if (fullname != null) {
					empdt = new EmpDetails();
					empdt.setFullname(fullname);
					emp = new Employee();
					emp.setEmpDetails(empdt);
				}

				sql = "select tt.* from (select rt.*,edt.emp_fullname as fullname,ct.cate_name as jobName,ctt.cate_name as majorName "
						+ "from recommender_table rt left outer join emp_details_table edt on(rt.emp_id=edt.emp_id)  "
						+ "join categories_table ct on(rt.job_id=ct.cate_id) and ct.is_what_cate='B' "
						+ "join categories_table ctt on (ctt.cate_id=rt.major_id) and ctt.is_what_cate='C') tt "
						+ "where tt.emp_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				int currentHrId = 0;
				while (rs.next()) {
					Recommender reco = new Recommender();
					reco.setRecoId(rs.getInt(1));
					reco.setRecoName(rs.getString(2));
					reco.setEmpId(rs.getInt(3));
					reco.setCurrentHrId(rs.getInt(4));
					// reco.setJobId(rs.getInt(5));// 职位
					reco.setJobCate(new Categories(rs.getString("jobName"),
									"B"));
					// reco.setMajorId(rs.getInt(6));// 专业
					reco.setMajorCate(new Categories(rs.getString("majorName"),
							"C"));
					reco.setSex(rs.getString(7));
					reco.setPic(rs.getString(8));
					reco.setRecoDegree(rs.getString(9));
					reco.setGraduatedFrom(rs.getString(10));
					reco.setIsGraduated(rs.getString(11));
					reco.setGraduatedTime(rs.getDate(12));
					reco.setPhone(rs.getString(13));
					reco.setMail(rs.getString(14));
					reco.setSkills(rs.getString(15));
					reco.setResume(rs.getString(16));
					reco.setIsRecommended(rs.getString(17));
					reco.setCurrStatus(rs.getString(18));
					if (emp != null) {
						reco.setEmployee(emp);
						currentHrId = rs.getInt(4);
						sql = "select emp_fullname from  emp_details_table where emp_id=?";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1, currentHrId);
						rs1 = pstm.executeQuery();
						String empFullName = null;
						if (rs1.next()) {
							empFullName = rs1.getString("emp_fullname");
						}
						System.out.println("当前处理HR的姓名：" + empFullName);
						reco.setCurrentHrName(empFullName);

					}
					list.add(reco);
				}
				return list;

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs1);
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}
	
		/*
		 * 根绝empId查询出自己的推荐的人
		 * 
		 */
	

	public List<Recommender> getReco(int empId) {
		try{
			conn = DBConnection.getConn();
			List<Recommender> list = new ArrayList<Recommender>();
			String sql = "select tt.* from (select rt.*,ct.cate_name as jobName,ctt.cate_name as majorName "
					+ "from recommender_table rt left outer "
					+ "join categories_table ct on(rt.job_id=ct.cate_id) and ct.is_what_cate='B' "
					+ "join categories_table ctt on (ctt.cate_id=rt.major_id) and ctt.is_what_cate='C') tt "
					+ "where tt.emp_id=? and tt.RECO_IS_RECOMMENDED='F' and tt.current_hr_id is null" ;
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Recommender reco = new Recommender();
				reco.setRecoId(rs.getInt(1));
				reco.setRecoName(rs.getString(2));
				reco.setEmpId(rs.getInt(3));
				reco.setCurrentHrId(rs.getInt(4));
				// reco.setJobId(rs.getInt(5));// 职位
				reco.setJobCate(new Categories(rs.getString("jobName"),
								"B"));
				// reco.setMajorId(rs.getInt(6));// 专业
				reco.setMajorCate(new Categories(rs.getString("majorName"),
						"C"));
				reco.setIsRecommended(rs.getString(17));
				list.add(reco);
			}
			return list;
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 员工查看自己推荐的被推荐人的当前状态
	 */
	public List<Recommender> getRecommenderCurrStatus(int empId) {
		try {
			conn = DBConnection.getConn();
			// 首先根据empId判断他的角色，如果是管理员则可以查看所有被推荐人的信息，否则只能查看自己做推荐的人
			String sql = "SELECT role_id FROM emp_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			int roleId = 0;
			if (rs.next())// 万一没有数据，会提示异常（结果集耗尽）
			{
				roleId = rs.getInt(1);
			}
			List<Recommender> list = new ArrayList<Recommender>();
			if (roleId == 8888) {
				sql = "select reco_id,reco_name,current_hr_id,reco_sex,reco_current_status from recommender_table";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				int currentHrId = 0;
				while (rs.next()) {
					Recommender reco = new Recommender();
					reco.setRecoId(rs.getInt("reco_id"));
					reco.setRecoName(rs.getString("reco_name"));
					currentHrId = rs.getInt("current_hr_id");
					reco.setCurrentHrId(currentHrId);
					reco.setCurrStatus(rs.getString("reco_current_status"));
					// 得到当前推 荐人的id,就可以知道该推荐人 的名字。
					sql = "select emp_fullname from emp_details_table where emp_id =?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, currentHrId);
					rs1 = pstm.executeQuery();
					while (rs1.next()) {
						reco.setCurrentHrName(rs1.getString("emp_fullname"));
					}
					list.add(reco);
				}
				return list;
			} else {
				sql = "select reco_id,reco_name,current_hr_id,reco_sex,reco_current_status from recommender_table where emp_id =?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				int currentHrId = 0;
				while (rs.next()) {
					Recommender reco = new Recommender();
					reco.setRecoId(rs.getInt("reco_id"));
					reco.setRecoName(rs.getString("reco_name"));
					currentHrId = rs.getInt("current_hr_id");
					reco.setCurrentHrId(currentHrId);
					reco.setCurrStatus(rs.getString("reco_current_status"));
					// 得到当前推 荐人的id,就可以知道该推荐人 的名字。
					sql = "select emp_fullname from emp_details_table where emp_id =?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, currentHrId);
					rs1 = pstm.executeQuery();
					while (rs1.next()) {
						reco.setCurrentHrName(rs1.getString("emp_fullname"));
					}
					list.add(reco);
				}
				return list;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs1);
			DBConnection.close(pstm, conn, rs);

		}
		return null;
	}

	/*
	 * 
	 * 首先要根据recoId删除被推荐人在历史推荐表的信息记录，然后在根据recoId删除推荐表中被推荐人的信息。
	 */
	public boolean deleteRecommender(String [] recoId) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			for(int i = 0;i<recoId.length;i++){
			String sql = "delete from reco_history_table where reco_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, Integer.parseInt(recoId[i]));
			int upNum = pstm.executeUpdate();
			if (upNum >= 0) {
				conn.commit();
				sql = "delete from recommender_table where reco_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, Integer.parseInt(recoId[i]));
				int num = pstm.executeUpdate();
				if (num == 1) {
					conn.commit();
				}
			}
		}
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, null);
		}
		return false;
	}
	
	//AJAX 根据recoId查看被推荐人的详细信息
	public Recommender getByRecoId(int recoId) {
		try {
			conn = DBConnection.getConn();
			String sql = "select * from recommender_table where reco_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recoId);
			rs = pstm.executeQuery();
			Recommender reco = new Recommender();
			while (rs.next()) {
				reco.setRecoName(rs.getString("reco_name"));
				reco.setMail(rs.getString("reco_mailbox"));
				reco.setSex(rs.getString("RECO_SEX"));
				reco.setRecoDegree(rs.getString("reco_degree"));
				reco.setPhone(rs.getString("reco_phone"));
				reco.setGraduatedFrom(rs.getString("RECO_GRADUATED_FROM"));
				reco.setSkills(rs.getString("reco_skills"));
				reco.setPic(rs.getString("reco_pic"));
			}
			return reco;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 推荐了自己的推荐人就得修改他的状态，以及他的处理HR, 
	 */
	public boolean updateRecommender(String[] recoId,int hrId) {
		try{
			conn = DBConnection.getConn();
			String sql ="select emp_id from recruit_require_table where recruit_id =?";
			for(int i = 0;i<recoId.length;i++){
				pstm =conn.prepareStatement(sql);
				pstm .setInt(1, Integer.parseInt(recoId[i]));
				rs = pstm.executeQuery();
				int emp_id =0;
				while(rs.next()){
					emp_id = rs.getInt(1);
				}
				if(emp_id == hrId){//发布的招聘信息是自己发布的，那么指定其他HR处理自己推荐的被推荐人
			
					sql ="update recommender_table set RECO_IS_RECOMMENDED = ?,RECO_CURRENT_STATUS =? where reco_id=?";
					pstm = conn.prepareStatement(sql);
					pstm.setString(1, "T");
					pstm.setString(2, "step");
					pstm.setInt(3, Integer.parseInt(recoId[i]));
					int num =pstm.executeUpdate();
					if(num >0){
						return true;
					}
				}
				else{
					sql ="update recommender_table set CURRENT_HR_ID = ?,RECO_IS_RECOMMENDED = ?,RECO_CURRENT_STATUS =? where reco_id=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, hrId);
					pstm.setString(2, "T");
					pstm.setString(3, "step");
					pstm.setInt(4, Integer.parseInt(recoId[i]));
					int num =pstm.executeUpdate();
					if(num >0){
						sql ="update emp_table	set message=message+100	where emp_id=?";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1, hrId);
						pstm.execute();
						return true;
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
			/*
			 * 判断是否有消息处理
			 */
	public boolean Message(int	empId) {
		try{
			conn = DBConnection.getConn();
			String sql = "select message from emp_table where emp_id =?";
			pstm =conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("message"));
				if(rs.getInt(1)>0){
					return true;
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	Statement st;
	//分页
	public SplitPage getAllReco(int curentPage) {
		try {
			Connection conn;
			int totalRows = 0;
			conn = DBConnection.getConn();
			String sql = "select count(*) from RECOMMENDER_TABLE";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet rs=pstm.executeQuery();
			if(rs.next()){
				 totalRows = rs.getInt(1);// 一共有多少条记录
			}
			int totalPage =(totalRows-1)/5+1 ;// 一共有几页
			System.out.println("总的记录数" + totalRows);
			System.out.println("总的页数" + totalPage);
			// Set<Role> roleSet=new TreeSet<Role>(new MyComparator());
			
			SplitPage sp = new SplitPage();
			sp.setTotalRows(totalRows);
			sp.setTotalPage(totalPage);
			return sp;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnection.close(pstm, conn, rs);

		}
		return null;
	}


}
