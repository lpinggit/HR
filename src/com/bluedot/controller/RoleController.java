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
				//System.out.println("��ӵĽ�ɫID" + roleId);
				System.out.println("��ӵĽ�ɫ����" + roleName);
				Role role = new Role();
				//role.setRoleId(Integer.parseInt(roleId));
				role.setRoleName(new String(roleName.getBytes("ISO-8859-1"),"UTF-8"));
				// ��������ҳ���ȡ��������Ϣд�����ݿ�ȥ
				RoleService roleService = new RoleServiceImpl();
				if (roleService.addRole(role)) {

					System.out.println("��ӽ�ɫ�ɹ�����������������������������������������");
					RequestDispatcher rd = request
							.getRequestDispatcher(WordDict.VIEW_ROLE);
					rd.forward(request, response);

				} else {
					request.setAttribute("info", "��ӽ�ɫʧ��");
					RequestDispatcher rd = request
					.getRequestDispatcher("/WEB-INF/jsp/role/addRole.jsp");
			rd.forward(request, response);
					System.out.println("���ʧ�ܹ�������������������������������");
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
				     System.out.println("role������"+splist.getTotalRows());
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
					System.out.println("��ǰ�û���ӵ�еĲ˵�"+menu.getMenuName());
				}
				MenuService menuService=new MenuServiceImpl();
				Set<Menu> allMenus=menuService.getAllMenu();
				allMenus.removeAll(currentMenus);
				for(Menu menu:allMenus){
					System.out.println("��������Ĳ"+menu.getMenuName());
				}
				request.setAttribute("currentMenus", currentMenus);
				request.setAttribute("allMenus", allMenus);
				request.getRequestDispatcher("/WEB-INF/jsp/role/grantRole.jsp").forward(request, response);
				
			}else if(WordDict.SAVE_ROLE_GRANT.equals(action)){
				
				String roleId=request.getParameter("role_id");
				String []menuId=request.getParameterValues("menuId");
				for(String str:menuId){
					System.out.println("�˵�Id"+str);
				}
				RoleService roleService=new RoleServiceImpl();
				if(roleService.grantRole(menuId, Integer.parseInt(roleId))){
					request.getRequestDispatcher("viewRole.do").forward(request, response);
				System.out.println("����ɫ��Ȩ�ɹ�");	
				}
			}else if(WordDict.DELETE_ROLE.equals(action)){
				
				String roleId=request.getParameter("roleId");
		     	//����roleIdȥɾ��һ����ɫ
				RoleService roleService=new RoleServiceImpl();
				if(roleService.deleteRole(Integer.parseInt(roleId))){
					System.out.println("ɾ��һ����ɫ�ɹ�");
					request.getRequestDispatcher("viewRole.do").forward(request, response);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
