package com.bluedot.po;

import java.util.Set;
import java.util.TreeSet;

import com.bluedot.util.MyComparator;

public class Role {
	private int roleId;
	private String roleName;
	private Set<Menu> menuSet=new TreeSet<Menu>(new MyComparator());
	

	public Set<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

}
