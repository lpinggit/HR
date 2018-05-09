package com.bluedot.controller;


import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bludeodt.dict.WordDict;
import com.bluedot.po.Menu;
import com.bluedot.po.Role;
import com.bluedot.service.MenuService;
import com.bluedot.service.RoleService;
import com.bluedot.service.impl.MenuServiceImpl;
import com.bluedot.service.impl.RoleServiceImpl;
import com.bluedot.util.SplitPage;

public class RoleController {

	public void userRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {
		try {
			if (WordDict.ADD_ROLE.equals(action)) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/role/addRole.jsp");
				rd.forward(request, response);
			} else if (WordDict.SAVE_ROLE.equals(action)) {
				//String roleId = request.getParameter("roleId");
				String roleName = request.getParameter("roleName");
				//System.out.println("添加的角色ID" + roleId);
				System.out.println("添加的角色名字" + roleName);
				Role role = new Role();
				//role.setRoleId(Integer.parseInt(roleId));
				role.setRoleName(new String(roleName.getBytes("ISO-8859-1"),"UTF-8"));
				// 接下来把页面获取的数据信息写到数据库去
				RoleService roleService = new RoleServiceImpl();
				if (roleService.addRole(role)) {

					System.out.println("添加角色成功····················");
					RequestDispatcher rd = request
							.getRequestDispatcher(WordDict.VIEW_ROLE);
					rd.forward(request, response);

				} else {
					request.setAttribute("info", "添加角色失败");
					RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsp/role/addRole.jsp");
			rd.forward(request, response);
					System.out.println("添加失败哈···············");
				}

			}else if(WordDict.VIEW_ROLE.equals(action)){
				String currentPage=request.getParameter("currentPage");
				RoleService roleService=new RoleServiceImpl();
				SplitPage splist=null;
				if(currentPage!=null){
					splist=roleService.getAllRole(Integer.parseInt(currentPage));
					request.setAttribute("curentPage", currentPage);
					request.setAttribute("splist", splist);
				}else{
				     splist=roleService.getAllRole(1);
				     System.out.println("role控制器"+splist.getTotalRows());
				     request.setAttribute("curentPage","1");
					 request.setAttribute("splist", splist);
				}	
			    request.getRequestDispatcher("/WEB-INF/jsp/role/allRole.jsp").forward(request, response);
			}else if(WordDict.GRANT_ROLE.equals(action)){
				RoleService roleService=new RoleServiceImpl();
				String roleId=request.getParameter("roleId");
				request.setAttribute("roleId", roleId);
				Set<Menu> currentMenus=roleService.getMenusByRoleId(Integer.parseInt(roleId));
				for(Menu menu:currentMenus){
					System.out.println("当前用户所拥有的菜单"+menu.getMenuName());
				}
				MenuService menuService=new MenuServiceImpl();
				Set<Menu> allMenus=menuService.getAllMenu();
				allMenus.removeAll(currentMenus);
				for(Menu menu:allMenus){
					System.out.println("集合运算的差集"+menu.getMenuName());
				}
				request.setAttribute("currentMenus", currentMenus);
				request.setAttribute("allMenus", allMenus);
				request.getRequestDispatcher("/WEB-INF/jsp/role/grantRole.jsp").forward(request, response);
				
			}else if(WordDict.SAVE_ROLE_GRANT.equals(action)){
				
				String roleId=request.getParameter("role_id");
				String []menuId=request.getParameterValues("menuId");
				for(String str:menuId){
					System.out.println("菜单Id"+str);
				}
				RoleService roleService=new RoleServiceImpl();
				if(roleService.grantRole(menuId, Integer.parseInt(roleId))){
					request.getRequestDispatcher("viewRole.do").forward(request, response);
				System.out.println("给角色授权成功");	
				}
			}else if(WordDict.DELETE_ROLE.equals(action)){
				
				String roleId=request.getParameter("roleId");
		     	//根据roleId去删除一个角色
				RoleService roleService=new RoleServiceImpl();
				if(roleService.deleteRole(Integer.parseInt(roleId))){
					System.out.println("删除一个角色成功");
					request.getRequestDispatcher("viewRole.do").forward(request, response);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
