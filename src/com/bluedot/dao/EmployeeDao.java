package com.bluedot.dao;

import java.util.List;

import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.util.SplitPage;

public interface EmployeeDao {
	Employee login(Employee employee);
	boolean addUser(Employee employee);
	SplitPage searchAllUser(int curentPage);
	boolean updateUserGrant(int empId,int roleId);
	boolean deleteByEmpId(int[] empId);
	boolean alterPassword(int empId,String password,String newPassword);
	Employee searchByEmpId(int empId);
	boolean addUserDetails(EmpDetails empDetails);
	EmpDetails serachDetailsByEmpId(int empId);
	int [] getUserByRoleId(int roleId);
	boolean modifyDetails(EmpDetails empDetails);
	
}
