package com.bluedot.service.impl;

import java.util.List;
import java.util.Set;

import com.bluedot.dao.RoleServiceDao;
import com.bluedot.dao.impl.RoleServiceDaoImpl;
import com.bluedot.po.Menu;
import com.bluedot.po.Role;
import com.bluedot.service.RoleService;
import com.bluedot.util.SplitPage;

public class RoleServiceImpl implements RoleService {

	RoleServiceDao roleServiceDao;

	public RoleServiceImpl() {
		roleServiceDao = new RoleServiceDaoImpl();
	}

	public SplitPage getAllRole(int curentPage) {
		return roleServiceDao.getAllRole(curentPage);
	}

	public boolean addRole(Role role) {
		
		return roleServiceDao.addRole(role);
	}

	public Set<Menu> getMenusByRoleId(int roleId) {
		return roleServiceDao.getMenusByRoleId(roleId);
	}

	public boolean grantRole(String[] menuId, int roleId) {
		
		return roleServiceDao.grantRole(menuId, roleId);
	}

	public boolean deleteRole(int roleId) {
		
		return roleServiceDao.deleteRole(roleId);
	}

	public List<Role> getByAllRole() {
		// TODO Auto-generated method stub
		return roleServiceDao.getByAllRole();
	}

}
