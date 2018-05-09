package com.bluedot.service;


import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.util.SplitPage;

public interface EmployeeService {
	Employee login(Employee employee);
	boolean addUser(Employee employee);
	SplitPage searchAllUser(int curentPage);
	boolean updateUserGrant(int empId,int roleId);
	boolean deleteByEmpId(int []empId);
	boolean alterPassword(int empId,String password ,String newPassword);
	Employee searchByEmpId(int empId);
	boolean addUserDetails(EmpDetails empDetails);
	boolean modifyDetails(EmpDetails empDetails);
	EmpDetails serachDetailsByEmpId(int empId);
	 int[] getUserByRoleId(int roleId);
}
