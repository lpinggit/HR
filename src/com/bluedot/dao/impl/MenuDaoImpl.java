package com.bluedot.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.bluedot.dao.MenuDao;
import com.bluedot.po.Menu;
import com.bluedot.util.DBConnection;

public class MenuDaoImpl implements MenuDao {
	Connection conn;
	Statement st;
	PreparedStatement pstm;
	ResultSet rs;
	String sql;

	public Set<Menu> getAllMenu() {
		try {
			conn=DBConnection.getConn();
			sql="select * from menu_table";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			Set<Menu> setMenu=new HashSet<Menu>();
			while(rs.next()){
				Menu menu=new Menu();
				menu.setMenuId(rs.getInt("menu_id"));
				menu.setMenuName(rs.getString("menu_name"));
				setMenu.add(menu);
			}
			return setMenu;
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

}
