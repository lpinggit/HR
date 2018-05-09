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
	// ��ѯ���еĽ�ɫ��ͨ�����ô洢����
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
			System.out.println("�ܵļ�¼��" + totalRows);
			System.out.println("�ܵ�ҳ��" + totalPage);
			
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
	 * �鿴��ɫ�����ƣ��û�����û�ʱ��ȡ��ɫ
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
	 * ���һ����ɫ
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
			System.out.println("�����"+i);
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
	 * ����ɫ��Ȩ
	 * 
	 * @param menuID
	 *            Ҫ��Ȩ�Ĳ˵�ID
	 * @param roleId
	 *            ���ĸ���ɫ��Ȩ
	 */
	public boolean grantRole(String[] menuId, int roleId) {
		try {
			// ����Ҫͳ�Ƹý�ɫIdӵ�м����˵�
			conn = DBConnection.getConn();
			sql = "select count(1) from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			rs = pstm.executeQuery();
			int num = 0;// num�����˸ý�ɫ��ӵ�ж��ٸ��˵�
			while(rs.next()){
				num = rs.getInt(1);
			}

			/****************** ɾ����ɫ��role_menu_table�ж�Ӧ�����в˵� *************/

			sql = "delete from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			int deleteNum = pstm.executeUpdate();

			/************** �����Ȩ�˵� ************************************/
			sql = "insert into role_menu_table values(?,?)";
			int insertNum = 0;
			for (int i = 0; i < menuId.length; i++) {
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, roleId);
				pstm.setInt(2, Integer.parseInt(menuId[i]));
				insertNum += pstm.executeUpdate();
			}
			System.out.println("�ý�ɫ��ӵ�еĲ˵���"+num);
			System.out.println("ɾ���Ĳ˵���"+deleteNum);
			System.out.println("��ӵĲ˵���"+insertNum);
			System.out.println("menuId�ĳ���"+menuId.length);
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
	 * ɾ��һ����ɫ*******************************
	 * ���ȸ���roleId�ҳ���Щ�û�ӵ���˸ý�ɫ��Ȼ����û���Ӧ�ĸý�ɫ�ֶ�role_Id����Ϊnull;
	 * ����ҳ�role_menu_table���������ĸý�ɫroleId,������ɫ��Ӧ��roleIdɾ��
	 * ������roleIdȥɾ��role_table�ж�Ӧ�ĸý�ɫ
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
				System.out.println("ͳ�Ƹý�ɫӵ�м����˵�" + roleNum);
			}
		

			/**************************** ɾ��role_menu_table�ж�Ӧ�Ľ�ɫId *****/
			sql = "delete from role_menu_table where role_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, roleId);
			int deleteNum = pstm.executeUpdate();
			System.out.println("ͳ��ɾ���Ĳ˵���" + deleteNum);

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
