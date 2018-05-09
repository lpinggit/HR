package com.bluedot.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import com.bluedot.dao.RecruitDao;
import com.bluedot.po.Categories;
import com.bluedot.po.Department;
import com.bluedot.po.Recommender;
import com.bluedot.po.RecruitInfo;
import com.bluedot.util.DBConnection;
import com.bluedot.util.SplitPage;

public class RecruitDaoImpl implements RecruitDao {

	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
    private static int i;
	// 保存发布的招聘信息
	public boolean saveRecruitInfo(RecruitInfo recruit) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			String sql = "INSERT INTO RECRUIT_REQUIRE_TABLE(job_id,dept_id,emp_id,work_cate_id"
					+ ",recruit_number,job_location,work_type,deadline_date,work_experience"
					+ ",job_description,job_skills,special_requirement,urgent_level,recruit_id)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recruit.getJobId());
			pstm.setInt(2, recruit.getDeptNo());
			pstm.setInt(3, recruit.getHrId());
			pstm.setInt(4, recruit.getWorkCateId());
			pstm.setInt(5, recruit.getNumber());
			pstm.setString(6, recruit.getWorkLoc());
			pstm.setString(7, recruit.getWorkshift());
			pstm.setDate(8, recruit.getDeadLine());
			pstm.setInt(9, recruit.getExperience());
			pstm.setString(10, recruit.getJobDesc());
			pstm.setString(11, recruit.getJobRequest());
			pstm.setString(12, recruit.getSpecialRequest());
			pstm.setString(13, recruit.getIsUrgent());
			//System.out.println("这里是否为空"+getRecruitIdFromSequence());
			pstm.setInt(14, getRecruitIdFromSequence());
			int num = pstm.executeUpdate();
			if (num > 0) {
				conn.commit();
				return true;
			}

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

	public int getRecruitIdFromSequence() {
		i++;
		return i;
	}

	/*
	 * 查看发布的招聘信息
	 * 
	 * @see com.bluedot.dao.RecruitDao#viewRecruitInfo()
	 */
	public List<RecruitInfo> viewRecruitInfo() {
		try{
			conn =DBConnection.getConn();
			String sql = "select rrt.recruit_id,ct.cate_name,dt.dept_name,rrt.job_location, rrt.DEADLINE_DATE from recruit_require_table rrt " +
					    "left outer join CATEGORIES_TABLE ct on (rrt.job_id=ct.cate_id) and ct.is_what_cate ='B' " +
					   "join department_table dt on (rrt.dept_id=dt.dept_id)";
			pstm = conn.prepareStatement(sql);
			rs =pstm.executeQuery();
			List<RecruitInfo> list  = new ArrayList<RecruitInfo>();
			while(rs.next()){
				RecruitInfo recruitInfo =new RecruitInfo();
				recruitInfo.setRecruitId(rs.getInt(1));
				recruitInfo.setJobCate(new  Categories(rs.getString(2),"B"));
				recruitInfo.setDepartment(new Department(rs.getString(3)));
				recruitInfo.setWorkLoc(rs.getString(4));
				recruitInfo.setDeadLine(rs.getDate(5));
				list.add(recruitInfo);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}
	
	
	public List<RecruitInfo> viewRecruitInfoByRole(int empId) {
		try{
			conn =DBConnection.getConn();
			String sql = "select role_id from emp_table where emp_id  = ?";
			pstm =  conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			int roleId = 0;
			while(rs.next()){
				roleId = rs.getInt(1);
			}
			if(roleId == 8888){
			sql = "select rrt.recruit_id,ct.cate_name,dt.dept_name,rrt.job_location, rrt.DEADLINE_DATE from recruit_require_table rrt " +
					    "left outer join CATEGORIES_TABLE ct on (rrt.job_id=ct.cate_id) and ct.is_what_cate ='B' " +
					   "join department_table dt on (rrt.dept_id=dt.dept_id)";
			pstm = conn.prepareStatement(sql);
			rs =pstm.executeQuery();
			List<RecruitInfo> list  = new ArrayList<RecruitInfo>();
			while(rs.next()){
				RecruitInfo recruitInfo =new RecruitInfo();
				recruitInfo.setRecruitId(rs.getInt(1));
				recruitInfo.setJobCate(new  Categories(rs.getString(2),"B"));
				recruitInfo.setDepartment(new Department(rs.getString(3)));
				recruitInfo.setWorkLoc(rs.getString(4));
				recruitInfo.setDeadLine(rs.getDate(5));
				list.add(recruitInfo);
			}
			return list;
			}else{
				
				sql = "select rrt.recruit_id,ct.cate_name,dt.dept_name,rrt.job_location, rrt.DEADLINE_DATE from recruit_require_table rrt " +
			    "left outer join CATEGORIES_TABLE ct on (rrt.job_id=ct.cate_id) and ct.is_what_cate ='B' " +
			   "join department_table dt on (rrt.dept_id=dt.dept_id) where rrt.emp_id = ?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs =pstm.executeQuery();
				List<RecruitInfo> list  = new ArrayList<RecruitInfo>();
				while(rs.next()){
					RecruitInfo recruitInfo =new RecruitInfo();
					recruitInfo.setRecruitId(rs.getInt(1));
					recruitInfo.setJobCate(new  Categories(rs.getString(2),"B"));
					recruitInfo.setDepartment(new Department(rs.getString(3)));
					recruitInfo.setWorkLoc(rs.getString(4));
					recruitInfo.setDeadLine(rs.getDate(5));
					list.add(recruitInfo);
				}
				return list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}


	/*
	 * 发招聘信息前台页面需要获取的部门，工作职位等信息
	 * 
	 * @see com.bluedot.dao.RecruitDao#getRecruitInfo()
	 */
	public List getRecruitInfo() {
		try {
			conn = DBConnection.getConn();
			String sql = "select * from categories_table where is_what_cate='B'OR is_what_cate='A'";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			List list = new ArrayList();
			while (rs.next()) {
				Categories categories = new Categories();
				categories.setCateId(rs.getInt(1));
				categories.setCateName(rs.getString(2));
				categories.setIsWhatCate(rs.getString(3));
				list.add(categories);
			}
			sql = "select dept_id,dept_name from department_table";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Department dept = new Department();
				dept.setDeptId(rs.getInt(1));
				dept.setDeptName(rs.getString(2));
				list.add(dept);

			}
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	
	/*
	 * 根據Id查詢具體的招聘信息
	 */
	public RecruitInfo viewRecruitById(int recruitId) {
		try{
			conn =DBConnection.getConn();
			String sql = "select rrt.recruit_id,ct.cate_name,dt.dept_name,rrt.job_location, rrt.DEADLINE_DATE ,rrt.recruit_number,rrt.work_experience,rrt.JOB_DESCRIPTION,rrt.JOB_SKILLS,rrt.work_type,rrt.SPECIAL_REQUIREMENT ,rrt.emp_id from recruit_require_table rrt " +
					    "left outer join CATEGORIES_TABLE ct on (rrt.job_id=ct.cate_id) and ct.is_what_cate ='B' " +
					   "join department_table dt on (rrt.dept_id=dt.dept_id) where recruit_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recruitId);
			rs =pstm.executeQuery();
			RecruitInfo recruitInfo = null;
			while(rs.next()){
				recruitInfo =new RecruitInfo();
				recruitInfo.setRecruitId(rs.getInt(1));
				recruitInfo.setJobCate(new  Categories(rs.getString(2),"B"));
				recruitInfo.setDepartment(new Department(rs.getString(3)));
				recruitInfo.setWorkLoc(rs.getString(4));
				recruitInfo.setDeadLine(rs.getDate(5));
				recruitInfo.setNumber(rs.getInt(6));
				recruitInfo.setExperience(rs.getInt(7));
				recruitInfo.setJobDesc(rs.getString(8));
				recruitInfo.setJobRequest(rs.getString(9));
				recruitInfo.setWorkshift(rs.getString(10));
				recruitInfo.setSpecialRequest(rs.getString(11));
				recruitInfo.setHrId(Integer.parseInt(rs.getString(12)));
			}
			return recruitInfo;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}
	
	/*
	 * 根据recruitId和员工身份进行删除招聘信息
	 */

	public boolean deleteRecruitInfo(String[] recruitId) {
		try{
			conn = DBConnection.getConn();
			String sql  = "delete from recruit_require_table where recruit_id = ?";
			int num = 0;
			for(int i = 0;i<recruitId.length;i++){
				pstm =  conn.prepareStatement(sql);
				pstm.setInt(1,Integer.parseInt(recruitId[i]));
				pstm.executeUpdate();
				num++;
			}
			if(num == recruitId.length){
				return true;
				}
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, null);
		}
		return false;
	}
	
	
	/*
	 *查看详情表的所有邮箱号码
	 */
	public List<String> getAllmailBox() {
		try{
			conn = DBConnection.getConn();
			String sql = "select emp_mailbox from emp_details_table ";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			List<String> list = new ArrayList<String>();
			String mailBox = null;
			while(rs.next()){
				mailBox = rs.getString(1);
				list.add(mailBox);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	
	/*
	 * 根据招聘ＩＤ更新招聘信息
	 */
	public boolean updateRecruitInfo(RecruitInfo recruit) {
		try{
			conn = DBConnection.getConn();
			String sql = "update recruit_require_table set job_id =?,dept_id = ?, work_cate_id = ?," +
					"recruit_number = ?,job_location =?,work_type = ?,deadline_date = ?,work_experience = ?," +
					"job_description = ?,job_skills = ?,special_requirement = ?,URGENT_LEVEL = ? where recruit_id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, recruit.getJobId());
			pstm.setInt(2, recruit.getDeptNo());
			pstm.setInt(3, recruit.getWorkCateId());
			pstm.setInt(4, recruit.getNumber());
			pstm.setString(5, recruit.getWorkLoc());
			pstm.setString(6, recruit.getWorkshift());
			pstm.setDate(7, recruit.getDeadLine());
			pstm.setInt(8, recruit.getExperience());
			pstm.setString(9, recruit.getJobDesc());
			pstm.setString(10, recruit.getJobRequest());
			pstm.setString(11, recruit.getSpecialRequest());
			pstm.setString(12, recruit.getIsUrgent());
			pstm.setInt(13, recruit.getRecruitId());
			int num = pstm.executeUpdate();
			if(num ==1){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			DBConnection.close(pstm, conn, null);
		}
		return false;
	}
	/*
	 *修改员工表的标识（消息）,只需要修改不是指定的处理人的标识
	 */
	public boolean modifyMark() {
		try{
			conn = DBConnection.getConn();
			String	sql = "update emp_table set message =0	where message >=1";//and message<100 
			pstm = conn.prepareStatement(sql);
			pstm.execute();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, null);
		}
		return false;
	}

	Statement st;
	//分页实现
	public SplitPage getAllRecruit(int curentPage) {
		try {
			Connection conn;
			int totalRows = 0;
			conn = DBConnection.getConn();
			String sql = "select count(*) from RECRUIT_REQUIRE_TABLE";
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
