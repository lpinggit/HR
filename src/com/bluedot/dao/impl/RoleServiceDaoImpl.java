package com.bluedot.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import oracle.jdbc.OracleTypes;

import com.bluedot.dao.EmployeeDao;
import com.bluedot.dao.RoleServiceDao;
import com.bluedot.po.Menu;
import com.bluedot.po.Role;
import com.bluedot.service.EmployeeService;
import com.bluedot.service.impl.EmployeeServiceImpl;
import com.bluedot.util.DBConnection;
import com.bluedot.util.MyComparator;
import com.bluedot.util.SplitPage;

public class RoleServiceDaoImpl implements RoleServiceDao {
	Connection conn;
	Statement st;
	PreparedStatement pstm;
	ResultSet rs;
	String sql;
	private Statement pstm2;
	private ResultSet rs2;
	// 查询所有的角色，通过调用存储过程
	public SplitPage getAllRole(int curentPage) {
		try {
			conn = DBConnection.getConn();
			// sql = "select * from role_table";
			int totalRows = 0;
			String sql1 = "select count(*) from role_table";
			String sql2 = "select * from (select e.*,rownum r from(select * from role_table)e) where r>(?-1)*5 and r<=?*5";
			PreparedStatement pstm1 = conn.prepareStatement(sql1);
			ResultSet rs1=pstm1.executeQuery();
			if(rs1.next()){ totalRows = rs1.getInt(1);}
			 rs1.close();
			 pstm1.close();
			int totalPage =(totalRows-1)/5+1;
			System.out.println("总的记录数" + totalRows);
			System.out.println("总的页数" + totalPage);
			
			PreparedStatement pstm2= conn.prepareStatement(sql2);
			pstm2.setInt(1,curentPage);
			pstm2.setInt(2,curentPage);
			ResultSet rs2=pstm2.executeQuery();
			// Set<Role> roleSet=new TreeSet<Role>(new MyComparator());
			List<Role> roleList = new ArrayList<Role>();
			while (rs2.next()) {
				Role role = new Role();
				role.setRoleId(rs2.getInt("role_id"));
				role.setRoleName(rs2.getString("role_name"));
				roleList.add(role);
			}
			SplitPage sp = new SplitPage();
			sp.setTotalRows(totalRows);
			sp.setTotalPage(totalPage);
			sp.setRoleList(roleList);
			return sp;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnection.close(pstm2, conn,rs2);

		}
		return null;
	}

	/*
	 * 查看角色的名称，用户添加用户时读取角色
	 */

	public List<Role> getByAllRole() {
		try {
			List<Role> roleList = new ArrayList<Role>();
			conn = DBConnection.getConn();
			sql = "select * from role_table";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setRoleId(rs.getInt(1));
				role.setRoleName(rs.getString(2));
				roleList.add(role);
			}
			return roleList;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;

	}

	/*
	 * 添加一个角色
	 */
	public boolean addRole(Role role) {
			conn = DBConnection.getConn();
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			String sql="SELECT role_id_seq.nextval FROM DUAL ";
			int i = 0;
			try {
				pstm=conn.prepareStatement(sql);
				rs=pstm.executeQuery();
				while(rs.next()){
					i=rs.getInt(1);
				    }
			System.out.println("输出是"+i);
		    String sql1="insert into role_table values (?,?)";
			pstm=conn.prepareStatement(sql1);
			pstm.setInt(1, i);
			pstm.setString(2, role.getRoleName());
			int num = pstm.executeUpdate();
			if (num > 0) {
				conn.commit();
				return true;
			   } 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return false;
	}

	
	public Set<Menu> getMenusByRoleId(int roleId) {
		try {
			Set<Menu> setMenu = new HashSet<Menu>();
			conn = DBConnection.getConn();
			String sql = "SELECT menu_id,menu_name,parent_id FROM menu_table "
					+ "WHERE menu_id IN(SELECT menu_id FROM role_menu_table WHERE role_id=?)";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Menu menu = new Menu();
				menu.setMenuId(rs.getInt("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				if (rs.getString("parent_id") != null) {
					menu.setParentId(Integer
							.parseInt(rs.getString("parent_id")));
				}
				setMenu.add(menu);
			}
			return setMenu;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	/**
	 * 给角色授权
	 * 
	 * @param menuID
	 *            要授权的菜单ID
	 * @param roleId
	 *            给哪个角色授权
	 */
	public boolean grantRole(String[] menuId, int roleId) {
		try {
			// 首先要统计该角色Id拥有几个菜单
			conn = DBConnection.getConn();
			sql = "select count(1) from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			int num = 0;// num代表了该角色的拥有多少个菜单
			while(rs.next()){
				num = rs.getInt(1);
			}

			/****************** 删除角色在role_menu_table中对应的所有菜单 *************/

			sql = "delete from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			int deleteNum = pstm.executeUpdate();

			/************** 添加授权菜单 ************************************/
			sql = "insert into role_menu_table values(?,?)";
			int insertNum = 0;
			for (int i = 0; i < menuId.length; i++) {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, roleId);
				pstm.setInt(2, Integer.parseInt(menuId[i]));
				insertNum += pstm.executeUpdate();
			}
			System.out.println("该角色所拥有的菜单数"+num);
			System.out.println("删除的菜单数"+deleteNum);
			System.out.println("添加的菜单数"+insertNum);
			System.out.println("menuId的长度"+menuId.length);
			if ((num == deleteNum) && (insertNum == menuId.length)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
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

	/***********************
	 * 删除一个角色*******************************
	 * 首先根据roleId找出那些用户拥有了该角色，然后该用户对应的该角色字段role_Id设置为null;
	 * 其次找出role_menu_table中所关联的该角色roleId,并将角色对应的roleId删除
	 * 最后根据roleId去删除role_table中对应的该角色
	 * 
	 * */
	public boolean deleteRole(int roleId) {
		try {
			EmployeeService employeeService = new EmployeeServiceImpl();
			int[] empId = employeeService.getUserByRoleId(roleId);
			conn = DBConnection.getConn();
			conn.setAutoCommit(false);
			employeeService.deleteByEmpId(empId);
			sql = "select count(*) from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			int roleNum=0;
			if(rs.next())
			{
				 roleNum = rs.getInt(1);
				System.out.println("统计该角色拥有几个菜单" + roleNum);
			}
		

			/**************************** 删除role_menu_table中对应的角色Id *****/
			sql = "delete from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			int deleteNum = pstm.executeUpdate();
			System.out.println("统计删除的菜单数" + deleteNum);

			/**********************************************/

			sql = "delete from role_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			if ((roleNum == deleteNum)&& (pstm.executeUpdate() == 1)) {
				conn.commit();
				return true;
			} else {
				conn.rollback();
				return false;
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

	public String getRoleNameBy(int roleId) {
		try {
			conn = DBConnection.getConn();
			sql = "select role_name from role_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			/*
			 * rs.next(); String roleName=rs.getString(1);
			 */
			String roleName = null;
			while (rs.next()) {
				roleName = rs.getString(1);
			}
			return roleName;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBConnection.close(pstm, conn, rs);

		}
	}
}
