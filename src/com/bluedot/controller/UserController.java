package com.bluedot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bludeodt.dict.WordDict;
import com.bluedot.po.EmpDetails;
import com.bluedot.po.Employee;
import com.bluedot.po.Role;
import com.bluedot.service.EmployeeService;
import com.bluedot.service.RecoService;
import com.bluedot.service.RoleService;
import com.bluedot.service.impl.EmployeeServiceImpl;
import com.bluedot.service.impl.RecoServiceImpl;
import com.bluedot.service.impl.RoleServiceImpl;
import com.bluedot.util.SplitPage;

public class UserController {

	public void userRequest(HttpServletRequest request,
			HttpServletResponse response, String action) {
		try {
			if (WordDict.USER_LOGIN.equals(action)) {
				RequestDispatcher rd = request
						.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else if (WordDict.USER_LOGIN_OUT.equals(action)) {
				if (request.getSession(false) != null) {
					System.out.println("bbb: "
							+ request.getSession(false).getId());
					request.getSession(false).invalidate();
					System.out.println("ccc: " + request.getSession().getId());
				}
				RequestDispatcher rd = request
						.getRequestDispatcher("login.jsp");
				rd.forward(request, response);

			} else if (WordDict.USER_LOGIN_CHECK.equals(action)) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				System.out.println("登录输入的用户名字" + new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				Employee employee = new Employee();
				employee.setUsername(new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				// 发消息给业务逻辑层,控制层依赖与业务逻辑层
				EmployeeService employeeService = new EmployeeServiceImpl();
				HttpSession session = request.getSession();
				session.setAttribute("username", new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				session.setAttribute("user", employeeService.login(employee));
				if (employeeService.login(employee).getUsername() == null) {
					request.setAttribute("error","账号错误");
					RequestDispatcher rd = request
					.getRequestDispatcher("login.jsp");
			         rd.forward(request, response);
				} else if(!password.equals(employeeService.login(employee).getPassword())){
				    request.setAttribute("error","密码错误");
					RequestDispatcher rd = request
							.getRequestDispatcher("login.jsp");
					rd.forward(request, response);
				}
				else{
					request.setAttribute("error","");
					RequestDispatcher rd = request
					.getRequestDispatcher("main.html");
			rd.forward(request, response);
				}

				/*
				 * } else { RequestDispatcher rd = request
				 * .getRequestDispatcher("login.jsp"); rd.forward(request,
				 * response);
				 * 
				 * }
				 */

			} else if (WordDict.ADD_USER.equals(action)) {
				RoleService roleService = new RoleServiceImpl();
				/*SplitPage sp = roleService.getAllRole(1);*/
				List<Role> sprole = roleService.getByAllRole();
				
				RecoService recoService = new RecoServiceImpl();
				request.setAttribute("jobMap", recoService.getJobMap());
				request.setAttribute("sprole", sprole);
				// 跳转页面之前，就要把当前数据库的角色给查询出来，并且放入request scope
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/user/addUser.jsp");
				rd.forward(request, response);
			} else if (WordDict.VIEW_USER.equals(action)) {
				String curentPage = request.getParameter("currentPage");
				System.out.println("当前页" + curentPage);
				EmployeeService employeeService = new EmployeeServiceImpl();
				searchAllUser(request, response, employeeService, curentPage);

			} else if (WordDict.GRANT_USER.equals(action)) {
				String empId = request.getParameter("empId");
				String roleId = request.getParameter("roleId");
				System.out.println("授权角色Id" + roleId);

				RoleService roleService = new RoleServiceImpl();
				/*SplitPage sp = roleService.getAllRole(1);
				request.setAttribute("sprole", sp);*/
				List<Role> rolelist = roleService.getByAllRole();
				request.setAttribute("rolelist", rolelist);
				request.setAttribute("currentRoleId", roleId);
				request.setAttribute("empId", empId);
				request.getRequestDispatcher("/WEB-INF/jsp/user/grantUser.jsp")
						.forward(request, response);

			} else if (WordDict.SAVE_GRANT_USER.equals(action)) {
				String empId = request.getParameter("empId");
				String roleId = request.getParameter("roleId");
				System.out.println("员工号" + empId);
				System.out.println("角色号" + roleId);
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.updateUserGrant(Integer.parseInt(empId),
						Integer.parseInt(roleId))) {
					System.out.println("授权成功");
					searchAllUser(request, response, employeeService, "1");

				}

			} else if (WordDict.DELETE_USER.equals(action)) {
				/*String empId = request.getParameter("empId");
				System.out.println("删除员工的Id" + empId);
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.deleteByEmpId(Integer.parseInt(empId))) {

					System.out.println("删除用户成功");
					searchAllUser(request, response, employeeService, "1");
				 */
				String selectUser =request.getParameter("selectUser");
				String [] select =selectUser.split(",");
				int a[]=new int[select.length];
				for(int i  = 0;i<select.length;i++){
					a[i]=Integer.parseInt(select[i]);
					System.out.println("用户ID"+select[i]);
				}
				
				EmployeeService employeeService = new EmployeeServiceImpl();
				if(employeeService.deleteByEmpId(a)){
					
					searchAllUser(request, response, employeeService, "1");
				}
				
			} else if (WordDict.USER_ALTER_PASSWORD.equals(action)) {
				request.getRequestDispatcher(
						"/WEB-INF/jsp/user/alterPassword.jsp").forward(request,
						response);

			} else if (WordDict.SHOW_USER_DETAILS.equals(action)) {
				String empId = request.getParameter("empId");
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.serachDetailsByEmpId(Integer
						.parseInt(empId)) == null) {
					request.getRequestDispatcher(
							"/WEB-INF/jsp/user/details.jsp").forward(request,
							response);
				}

				/*
				 * 跳到修改自己的个人信息详情界面···········
				 */
				else {
					EmpDetails empDetails = employeeService
							.serachDetailsByEmpId(Integer.parseInt(empId));
					request.setAttribute("empDetails", empDetails);
					//request.getSession().setAttribute("empDetails", empDetails);
					request.getRequestDispatcher(
					"/WEB-INF/jsp/user/modifyDetail.jsp").forward(request,
					response);
					
				}
				
			}else if(WordDict.MODIFY_USER_DETAILS.equals(action)){
				
				System.out.println("修改用户个人信息详情");
				String empId = request.getParameter("empId");
				System.out.println("这是需要修改详情的用户Id"+empId);
				
				
				String fullname = request.getParameter("fullname");
				String sex = request.getParameter("sex");
				String id = request.getParameter("id");
				String phone = request.getParameter("phone");
				String mailbox = request.getParameter("mailbox");
				String job = request.getParameter("job");
				System.out.println(new String(fullname.getBytes("ISO-8859-1"),
						"UTF-8")
						+ "全名");
				if (empId != null) {
					EmpDetails empDetails = new EmpDetails();
					empDetails.setEmpId(Integer.parseInt(empId));
					empDetails.setFullname(new String(fullname
							.getBytes("ISO-8859-1"), "UTF-8"));
					empDetails.setIdcard(id);
					empDetails.setSex(new String(sex.getBytes("ISO-8859-1"),
							"UTF-8"));
					empDetails.setPhone(phone);
					empDetails.setMailbox(mailbox);
					empDetails.setJob(new String(job.getBytes("ISO-8859-1"),
							"UTF-8"));
					System.out.println("x性别" + sex);
					EmployeeService employeeService = new EmployeeServiceImpl();
					if (employeeService.modifyDetails(empDetails)) {
						
						System.out.println("修改详情成功");
						empDetails = employeeService
						.serachDetailsByEmpId(Integer.parseInt(empId));
						request.setAttribute("empDetails", empDetails);
						request.setAttribute("info", "保存成功");
						request.getRequestDispatcher("/WEB-INF/jsp/user/modifyDetail.jsp").forward(request, response);
						
					}

				}

				
				
			}
			else if (WordDict.SAVE_USER_DETAILS.equals(action)) {

				String empId = request.getParameter("empId");
				System.out.println(empId);

				String fullname = request.getParameter("fullname");
				String sex = request.getParameter("sex");
				String id = request.getParameter("id");
				String phone = request.getParameter("phone");
				String mailbox = request.getParameter("mailbox");
				String job = request.getParameter("job");
				System.out.println(new String(fullname.getBytes("ISO-8859-1"),
						"UTF-8")
						+ "全名");
				if (empId != null) {
					EmpDetails empDetails = new EmpDetails();
					empDetails.setEmpId(Integer.parseInt(empId));
					empDetails.setFullname(new String(fullname
							.getBytes("ISO-8859-1"), "UTF-8"));
					empDetails.setIdcard(id);
					empDetails.setSex(new String(sex.getBytes("ISO-8859-1"),
							"UTF-8"));
					empDetails.setPhone(phone);
					empDetails.setMailbox(mailbox);
					empDetails.setJob(new String(job.getBytes("ISO-8859-1"),
							"UTF-8"));
					System.out.println("x性别" + sex);
					EmployeeService employeeService = new EmployeeServiceImpl();
					if (employeeService.addUserDetails(empDetails)) {
						System.out.println("添加用户的详情成功");
					}

				}

			} else if (WordDict.USER_PASSWORD_UPDATED.equals(action)) {
				String empId = request.getParameter("empId");
				String password = request.getParameter("password");

				String newPassword = request.getParameter("newpassword");
				if (password == null || newPassword == null
						|| (password == null & newPassword == null)) {
					System.out.println("修改密码失败");
					request.getRequestDispatcher(
							"/WEB-INF/jsp/user/alterPassword.jsp").forward(
							request, response);

				} else {
					EmployeeService employeeService = new EmployeeServiceImpl();
					if (employeeService.alterPassword(Integer.parseInt(empId),
							password, newPassword)) {
						Employee employeeInfo = employeeService
								.searchByEmpId(Integer.parseInt(empId));
						request.setAttribute("employeeInfo", employeeInfo);
						request.setAttribute("info", "密码修改成功");
						request.getRequestDispatcher(
								"/WEB-INF/jsp/user/alterPassword.jsp").forward(
								request, response);
					} else {
						request.setAttribute("info", "密码修改失败");
						request.getRequestDispatcher(
								"/WEB-INF/jsp/user/alterPassword.jsp").forward(
								request, response);
					}

				}
			}

			else if (WordDict.SAVE_USER.equals(action)) {

				//String empId = request.getParameter("emp_id");
				String empusername = request.getParameter("emp_account");
				String emppassword = request.getParameter("emp_password");
				String roleId = request.getParameter("roles");
				String sex=request.getParameter("sex");
				String phone=request.getParameter("phone");
				String job=request.getParameter("job");
				// 封装添加用户表单的数据
				Employee employee = new Employee();
				EmpDetails empdetails=new EmpDetails();
				empdetails.setSex(new String(sex.getBytes("ISO-8859-1"), "UTF-8"));
				empdetails.setPhone(phone);
				empdetails.setJob(job);
				//employee.setEmpId(Integer.parseInt(empId));
				employee.setUsername(new String(empusername.getBytes("ISO-8859-1"),"UTF-8"));
				employee.setPassword(emppassword);
				employee.setRoleId(Integer.parseInt(roleId));
                employee.setEmpDetails(empdetails);
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.addUser(employee)) {
					System.out.println("添加用户成功");
					SplitPage empSp = new SplitPage();
					empSp = employeeService.searchAllUser(1);
					HttpSession session = request.getSession();
					if (empSp != null) {
						session.setAttribute("empSp", empSp);
						RequestDispatcher rd = request
								.getRequestDispatcher("/WEB-INF/jsp/user/empInfo.jsp");
						rd.forward(request, response);
					} 
					else {
						System.out.println("查询用户信息不成功");
						
					}
					
				}
				else{
					RoleService roleService = new RoleServiceImpl();
					/*SplitPage sp = roleService.getAllRole(1);*/
					List<Role> sprole = roleService.getByAllRole();
					request.setAttribute("sprole", sprole);
					
					RecoService recoService = new RecoServiceImpl();
					request.setAttribute("jobMap", recoService.getJobMap());
					
					request.setAttribute("info", "添加用户失败");
					RequestDispatcher rd = request
							.getRequestDispatcher("/WEB-INF/jsp/user/addUser.jsp");
					rd.forward(request, response);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchAllUser(HttpServletRequest request,
			HttpServletResponse response, EmployeeService employeeService,
			String curentPage) {

		if (curentPage != null) {
			SplitPage empSp = new SplitPage();
			empSp = employeeService.searchAllUser(Integer.parseInt(curentPage));
			HttpSession session = request.getSession();
			session.setAttribute("empSp", empSp);
			session.setAttribute("curentPage", curentPage);
		} else {
			SplitPage empSp = new SplitPage();
			empSp = employeeService.searchAllUser(1);
			HttpSession session = request.getSession();
			session.setAttribute("empSp", empSp);
			session.setAttribute("curentPage", "1");
		}
		RequestDispatcher rd = request
				.getRequestDispatcher("/WEB-INF/jsp/user/empInfo.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
