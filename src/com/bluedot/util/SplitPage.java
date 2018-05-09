package com.bluedot.util;

import java.util.List;
import java.util.Set;

import com.bluedot.po.Credit;
import com.bluedot.po.Employee;
import com.bluedot.po.Role;

public class SplitPage {
	int totalRows;
	int totalPage;
	Set<Employee> set;
	List<Role> roleList;
	List<Credit> creditlist;
	


	public final List<Role> getRoleList() {
		return roleList;
	}

	public final void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Credit> getCreditlist() {
		return creditlist;
	}

	public void setCreditlist(List<Credit> creditlist) {
		this.creditlist = creditlist;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Set<Employee> getSet() {
		return set;
	}

	public void setSet(Set<Employee> set) {
		this.set = set;
	}

}
