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

import com.bluedot.dao.CreditDao;
import com.bluedot.po.Credit;
import com.bluedot.po.EmpDetails;
import com.bluedot.po.Role;
import com.bluedot.util.DBConnection;
import com.bluedot.util.SplitPage;

public class CreditDaoImpl implements CreditDao {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	/*
	 * 
	 * 查看自己的积分
	 * 
	 * @see com.bluedot.dao.CreditDao#viewCredit(int)
	 */
	public Credit viewCredit(int empId) {
		try {
			conn = DBConnection.getConn();
			String sql = "select emp_fullname ,emp_account from emp_details_table d join emp_table e on d.emp_id=e.emp_id where e.emp_id=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, empId);
			rs = pstm.executeQuery();
			String empFullName = null;
			String empAccount = null;
			while (rs.next()) {
				empFullName = rs.getString(1);
				empAccount = rs.getString(2);
			}
			if (empFullName != null) {
				sql = "SELECT credit_grade,total_credit FROM credit_grade_table where emp_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				while (rs.next()) {
					Credit credit = new Credit();
					credit.setCreditGrade(rs.getInt(1));
					credit.setEmpFullName(empFullName);
					credit.setTotalCredit(rs.getInt(2));
					return credit;
				}
			} else {
				sql = "SELECT credit_grade,total_credit FROM credit_grade_table where emp_id=?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, empId);
				rs = pstm.executeQuery();
				while (rs.next()) {
					Credit credit = new Credit();
					credit.setCreditGrade(rs.getInt(1));
					credit.setEmpAccount(empAccount);
					credit.setTotalCredit(rs.getInt(2));
					return credit;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstm, conn, rs);
		}
		return null;
	}

	

	Statement st;
	private ResultSet rs2;
	private Statement pstm2;
	//分页实现
	public SplitPage getAllCredit(int curentPage) {
		try {
			System.out.println("看有没有进入这个方法");
			Connection conn;
			int totalRows = 0;
			conn = DBConnection.getConn();
			String sql1 = "select count(*) from CREDIT_GRADE_TABLE";
			PreparedStatement pstm1 = conn.prepareStatement(sql1);
			ResultSet rs1=pstm1.executeQuery();
			System.out.println("看有没有到这里");
			if(rs1.next()){
				 totalRows = rs1.getInt(1);// 一共有多少条记录
			}
			rs1.close();
			pstm1.close();
			int totalPage =(totalRows-1)/5+1 ;// 一共有几页
			System.out.println("总的记录数" + totalRows);
			System.out.println("总的页数" + totalPage);
			// Set<Role> roleSet=new TreeSet<Role>(new MyComparator());
			List<Credit> creditList = new ArrayList<Credit>();
			String sql2 = "select * from (select e1.*,rownum r from(SELECT e.emp_account,c.credit_grade,c.total_credit FROM credit_grade_table c,emp_table e where c.emp_id=e.emp_id)e1) where r>(?-1)*5 and r<=?*5";
			PreparedStatement pstm2= conn.prepareStatement(sql2);
			pstm2.setInt(1,curentPage);
			pstm2.setInt(2,curentPage);
			ResultSet rs2= pstm2.executeQuery();
			while(rs2.next()) {
				
				Credit credit=new Credit();
				credit.setEmpAccount(rs2.getString("emp_account"));
				
				credit.setCreditGrade(rs2.getInt("credit_grade"));
				credit.setTotalCredit(rs2.getInt("total_credit"));
				creditList.add(credit);
			}
			System.out.println("执行完了吗");
			SplitPage sp = new SplitPage();
			sp.setTotalRows(totalRows);
			sp.setTotalPage(totalPage);
			sp.setCreditlist(creditList);
			return sp;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			DBConnection.close(pstm2, conn, rs2);

		}
		return null;
	}

}
