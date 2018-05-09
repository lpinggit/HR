package com.bluedot.dao;

import java.util.List;
import java.util.Set;

import com.bluedot.po.Menu;
import com.bluedot.po.Role;
import com.bluedot.util.SplitPage;

public interface RoleServiceDao {
 SplitPage getAllRole(int curentPage);
 boolean addRole(Role role);
 Set<Menu> getMenusByRoleId(int roleId);
 boolean grantRole(String []menuId,int roleId);
 boolean deleteRole(int roleId); 
 List<Role> getByAllRole();
}
