package com.bluedot.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import oracle.jdbc.OracleTypes;

import com.bluedot.dao.EmployeeDao;
import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.po.Fun;
import com.bluedot.po.Menu;
import com.bluedot.po.Role;
import com.bluedot.util.DBConnection;
import com.bluedot.util.MyComparator;
import com.bluedot.util.SplitPage;

public class EmployeeDaoImpl implements EmployeeDao {
	Connection conn;
	PreparedStatement pstm;
	Statement st;
	ResultSet rs;
	String sql;
	private Statement pstm2;
	private ResultSet rs2;
	private  static int i;
	public Employee login(Employee employee) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// String
			// url="jdbc:oracle:thin:@192.168.111.128:1521:orcl";//连接oracle数据库的路径
			// conn=DriverManager.getConnection(jdbcInfo.getUrl(),
			// jdbcInfo.getUsername(), jdbcInfo.getPassword());
			conn = DBConnection.getConn();
			sql = "select role_id ,emp_account,emp_id ,emp_password from emp_table where emp_account=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, employee.getUsername());
			rs = pstm.executeQuery();
			int roleId = 0;
			Employee employ = new Employee();
			if (rs.next()) {
				System.out.println("登录验证");
				roleId = rs.getInt(1);
				System.out.println(roleId);
				employ.setUsername(rs.getString("emp_account"));
				employ.setPassword(rs.getString("emp_password"));
				employ.setEmpId(rs.getInt("emp_id"));

			}
			// 这个返回是登录对应的菜单
			Role role = new Role();
			role.setRoleId(roleId);

			employ.setRole(role);

			sql = "SELECT mt.* FROM role_menu_table rmt,menu_table mt WHERE role_id=? and rmt.menu_id=mt.menu_id";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				menu.setParentId(rs.getInt("parent_id"));
				role.getMenuSet().add(menu);

			}
			for (Menu menu : employ.getRole().getMenuSet()) {
				sql = "select ft.* from menu_fun_table mft,fun_table ft where menu_id=? and mft.fun_id=ft.fun_id";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, menu.getMenuId());
				rs = pstm.executeQuery();
				while (rs.next()) {
					Fun fun = new Fun();
					if(menu.getMenuId()==1&&(roleId!=1000&&roleId!=8888))
					{
						fun.setFunId(2002);
						fun.setFunName("查看招聘信息");
						fun.setFunUri("viewRecruitInfo.do");
						menu.getFunSet().add(fun);
						break;
					}
					if(menu.getMenuId()==7&&(roleId==1000||roleId==8888))
					{
						fun.setFunId(2011);
						fun.setFunName("查看积分");
						fun.setFunUri("viewAllCredit.do");
						menu.getFunSet().add(fun);
						break;
					}
					fun.setFunId(rs.getInt("fun_id"));
					fun.setFunName(rs.getString("fun_name"));
					fun.setFunUri(rs.getString("fun_uri"));
					menu.getFunSet().add(fun);
				}
			}

			for (Menu menu : employ.getRole().getMenuSet()) {
				System.out.println("菜单名字:" + menu.getMenuName());
				for (Fun fun : menu.getFunSet()) {

					System.out.println("菜单功能:" + fun.getFunName());
				}
			}
			return employ;
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);

		}

		return null;
	}

	// 添加一个新的用户
	public boolean addUser(Employee employee) {
		try {
			conn = DBConnection.getConn();
			String sql = "INSERT INTO emp_table	VALUES(?,?,?,?,?)";
			conn.setAutoCommit(false);
			pstm = conn.prepareStatement(sql);
			int empid=getUserIdFromSequence();
			//System.out.println(getUserIdFromSequence(conn)+"角色Id正在插入···············");
			pstm.setInt(1,empid);
			
			pstm.setInt(2, employee.getRoleId());// ``````````````````
			pstm.setString(3, employee.getUsername());
			pstm.setString(4, employee.getPassword());
			pstm.setInt(5, 0);

			int num = pstm.executeUpdate();
			if (num > 0) {		
				System.out.println("影响的行数" + num);
				// 当添加一个员工成功之后，它就应该就有一个积分等级表。
				sql = "INSERT INTO credit_grade_table values(?,?,?)";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1,empid);
				pstm.setInt(2,1);
				pstm.setInt(3,0);
				num = pstm.executeUpdate();
				if (num == 1) {
					
				    sql = "select cate_name from CATEGORIES_TABLE where cate_id=?";
					pstm = conn.prepareStatement(sql);
					pstm.setInt(1, Integer.parseInt(employee.getEmpDetails().getJob()));
					ResultSet rs=pstm.executeQuery();
					if(rs.next()){
						sql="INSERT INTO emp_details_table(emp_id,emp_sex,emp_phone,emp_job) values(?,?,?,?)";
						pstm = conn.prepareStatement(sql);
						pstm.setInt(1,empid);
						pstm.setString(2,employee.getEmpDetails().getSex());
						pstm.setString(3,employee.getEmpDetails().getPhone());
						pstm.setString(4,rs.getString(1));
						num = pstm.executeUpdate();
						if(num==1)
						{
							conn.commit();
						}
					}
				}
				return true;
			}

		} catch (SQLException e) {
			System.out.println("异常··········");
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

	
	public int getUserIdFromSequence() {
		i++;
		return i;
	}
	
	// 查询用户信息

	public SplitPage searchAllUser(int curentPage) {

		try {
			Connection conn;
			int totalRows = 0;
			conn = DBConnection.getConn();
			System.out.println("23456"+curentPage);
			String sql1 = "select count(*) from emp_table";
			String sql2 = "select * from (select e.*,rownum r from(select * from emp_table)e) where r>(?-1)*5 and r<=?*5";
			PreparedStatement pstm1 = conn.prepareStatement(sql1);
			ResultSet rs1=pstm1.executeQuery();
			Set<Employee> empSet = new TreeSet<Employee>(new MyComparator());
			RoleServiceDaoImpl rsdi = new RoleServiceDaoImpl();
			if(rs1.next()){ totalRows = rs1.getInt(1);}
			 rs1.close();
			 pstm1.close();
			PreparedStatement pstm2= conn.prepareStatement(sql2);
			pstm2.setInt(1,curentPage);
			pstm2.setInt(2,curentPage);
			ResultSet rs2=pstm2.executeQuery();
			while (rs2.next()) {	
				Employee emp = new Employee();
				emp.setEmpId(rs2.getInt("emp_id"));
				String roleName = rsdi.getRoleNameBy(rs2.getInt("role_id"));
				Role role = new Role();
				role.setRoleName(roleName);
				emp.setRole(role);
				emp.setRoleId(rs2.getInt("role_id"));
				emp.setUsername(rs2.getString("emp_account"));
				emp.setPassword(rs2.getString("emp_password"));
				empSet.add(emp);
			}
			int totalPage =(totalRows-1)/5+1;
			System.out.println("总的用户数" + empSet.size());
			SplitPage sp = new SplitPage();
			sp.setTotalRows(totalRows);
			sp.setTotalPage(totalPage);
			sp.setSet(empSet);

			return sp;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			DBConnection.close(pstm2, conn,rs2);
		}
		return null;
	}

	// 给用户授权
	public boolean updateUserGrant(int empId, int roleId) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "UPDATE emp_table SET role_id=? WHERE emp_id=?";
			pstm = conn.prepareCall(sql);
			pstm.setInt(1, roleId);
			pstm.setInt(2, empId);
			int num = pstm.executeUpdate();
			if (num > 0) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
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

	// 删除用户首先的删除详情表的内容，然后在删除积分等级表
	public boolean deleteByEmpId(int[] empId) {
		try {
			System.out.println("得到删除员工的ID" + empId);
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			// 删除员工的关联的积分等级表
			for(int i = 0;i<empId.length;i++){
			String sql = "delete from credit_grade_table where emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId[i]);
			int CreNum = pstm.executeUpdate();
			System.out.println("删除员工对应的积分表" + CreNum);
			// 首先查询该用户是否详情表，有则把详情表该用户的删除掉
			sql = "DELETE FROM emp_details_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId[i]);
			int num = pstm.executeUpdate();
			System.out.println("删除员工对应的详情表:" + num);
			// 然后删除掉用户表的该用户
			sql = "DELETE FROM emp_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId[i]);
			int num1 = pstm.executeUpdate();
			System.out.println("从员工表删除某个用户：" + num1);
			if ((num == num1 || num == 0) && (CreNum == num1)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
			}
			}
		} catch (SQLException e) {
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

	public boolean alterPassword(int empId, String password, String newPassword) {

		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "UPDATE emp_table SET emp_password=? WHERE emp_id=? and emp_password=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, newPassword);
			pstm.setInt(2, empId);
			pstm.setString(3, password);
			int num = pstm.executeUpdate();
			if (num > 0) {
				conn.commit();
				return true;
			}

		} catch (SQLException e) {
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

	public Employee searchByEmpId(int empId) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "select * from emp_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			Employee employee;
			if (rs.next()) {
				conn.commit();
				employee = new Employee();
				employee.setEmpId(rs.getInt("emp_id"));
				employee.setUsername(rs.getString("emp_account"));
				employee.setRoleId(rs.getInt("role_id"));
				employee.setPassword(rs.getString("emp_password"));
				return employee;
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	public boolean addUserDetails(EmpDetails empDetails) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "insert into emp_details_table(emp_id,emp_fullname,emp_sex,emp_id_card,emp_phone,emp_mailbox,emp_job)"
					+ "values(?,?,?,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empDetails.getEmpId());
			pstm.setString(2, empDetails.getFullname());
			pstm.setString(3, empDetails.getSex());
			pstm.setString(4, empDetails.getIdcard());
			pstm.setString(5, empDetails.getPhone());
			pstm.setString(6, empDetails.getMailbox());
			pstm.setString(7, empDetails.getJob());
			int num = pstm.executeUpdate();
			if (num > 0) {
				conn.commit();
				return true;
			}
		} catch (SQLException e) {

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

	public int[] getUserByRoleId(int roleId) {
		try {
			conn = DBConnection.getConn();
			sql = "select count(emp_id) from emp_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			if(rs.next())
			{
				int num = rs.getInt(1);
				int[] empId = new int[num];
				sql = "SELECT emp_id FROM emp_table WHERE role_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, roleId);
				rs = pstm.executeQuery();
				int i = 0;
				while (rs.next()) {
					empId[i] = rs.getInt(1);
					i++;
				}
				return empId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	public EmpDetails serachDetailsByEmpId(int empId) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "select * from emp_details_table WHERE emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			if (rs.next()) {
				EmpDetails empDetails = new EmpDetails();
				empDetails.setEmpId(rs.getInt(1));
				empDetails.setFullname(rs.getString(2));
				empDetails.setSex(rs.getString(3));
				empDetails.setIdcard(rs.getString(4));
				empDetails.setPhone(rs.getString(5));
				empDetails.setMailbox(rs.getString(6));
				empDetails.setJob(rs.getString(7));
				conn.commit();
				return empDetails;
			}

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}
	
	
	

	public boolean modifyDetails(EmpDetails empDetails) {
		try {
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			sql = "update emp_details_table set EMP_FULLNAME=?, EMP_SEX=?,EMP_ID_CARD=?,EMP_PHONE=?,EMP_MAILBOX=?, EMP_JOB =? where emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, empDetails.getFullname());
			pstm.setString(2, empDetails.getSex());
			pstm.setString(3, empDetails.getIdcard());
			pstm.setString(4, empDetails.getPhone());
			pstm.setString(5, empDetails.getMailbox());
			pstm.setString(6, empDetails.getJob());
			pstm.setInt(7, empDetails.getEmpId());
			int num = pstm.executeUpdate();
			if (num == 1) {
				conn.commit();
				return true;
			}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			DBConnection.close(pstm, conn, null);
		}
		return false;
	}

}
