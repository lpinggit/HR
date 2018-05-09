package com.bluedot.service.impl;

import com.bluedot.dao.EmployeeDao;
import com.bluedot.dao.impl.EmployeeDaoImpl;
import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.service.EmployeeService;
import com.bluedot.util.SplitPage;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employDao;

	public EmployeeServiceImpl() {

		employDao = new EmployeeDaoImpl();
	}

	public Employee login(Employee employee) {

		return employDao.login(employee);
	}

	public boolean addUser(Employee employee) {

		return employDao.addUser(employee);
	}

	public SplitPage searchAllUser(int curentPage) {

		return employDao.searchAllUser(curentPage);
	}

	public boolean updateUserGrant(int empId, int roleId) {

		return employDao.updateUserGrant(empId, roleId);
	}

	public boolean deleteByEmpId(int []empId) {

		return employDao.deleteByEmpId(empId);
	}

	public boolean alterPassword(int empId, String password, String newPassword) {

		return employDao.alterPassword(empId, password, newPassword);
	}

	public Employee searchByEmpId(int empId) {

		return employDao.searchByEmpId(empId);
	}

	public boolean addUserDetails(EmpDetails empDetails) {

		return employDao.addUserDetails(empDetails);
	}

	public EmpDetails serachDetailsByEmpId(int empId) {

		return employDao.serachDetailsByEmpId(empId);
	}

	public boolean modifyDetails(EmpDetails empDetails) {

		return employDao.modifyDetails(empDetails);
	}
	public int[] getUserByRoleId(int roleId){
		return employDao.getUserByRoleId(roleId);
	}

}
