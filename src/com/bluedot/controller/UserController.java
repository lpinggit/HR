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
				System.out.println("��¼������û�����" + new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				Employee employee = new Employee();
				employee.setUsername(new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				// ����Ϣ��ҵ���߼���,���Ʋ�������ҵ���߼���
				EmployeeService employeeService = new EmployeeServiceImpl();
				HttpSession session = request.getSession();
				session.setAttribute("username", new String(username.getBytes("ISO-8859-1"),"UTF-8"));
				session.setAttribute("user", employeeService.login(employee));
				if (employeeService.login(employee).getUsername() == null) {
					request.setAttribute("error","�˺Ŵ���");
					RequestDispatcher rd = request
					.getRequestDispatcher("login.jsp");
			         rd.forward(request, response);
				} else if(!password.equals(employeeService.login(employee).getPassword())){
				    request.setAttribute("error","�������");
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
				// ��תҳ��֮ǰ����Ҫ�ѵ�ǰ���ݿ�Ľ�ɫ����ѯ���������ҷ���request scope
				RequestDispatcher rd = request
						.getRequestDispatcher("/WEB-INF/jsp/user/addUser.jsp");
				rd.forward(request, response);
			} else if (WordDict.VIEW_USER.equals(action)) {
				String curentPage = request.getParameter("currentPage");
				System.out.println("��ǰҳ" + curentPage);
				EmployeeService employeeService = new EmployeeServiceImpl();
				searchAllUser(request, response, employeeService, curentPage);

			} else if (WordDict.GRANT_USER.equals(action)) {
				String empId = request.getParameter("empId");
				String roleId = request.getParameter("roleId");
				System.out.println("��Ȩ��ɫId" + roleId);

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
				System.out.println("Ա����" + empId);
				System.out.println("��ɫ��" + roleId);
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.updateUserGrant(Integer.parseInt(empId),
						Integer.parseInt(roleId))) {
					System.out.println("��Ȩ�ɹ�");
					searchAllUser(request, response, employeeService, "1");

				}

			} else if (WordDict.DELETE_USER.equals(action)) {
				/*String empId = request.getParameter("empId");
				System.out.println("ɾ��Ա����Id" + empId);
				EmployeeService employeeService = new EmployeeServiceImpl();
				if (employeeService.deleteByEmpId(Integer.parseInt(empId))) {

					System.out.println("ɾ���û��ɹ�");
					searchAllUser(request, response, employeeService, "1");
				 */
				String selectUser =request.getParameter("selectUser");
				String [] select =selectUser.split(",");
				int a[]=new int[select.length];
				for(int i  = 0;i<select.length;i++){
					a[i]=Integer.parseInt(select[i]);
					System.out.println("�û�ID"+select[i]);
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
				 * �����޸��Լ��ĸ�����Ϣ������桤��������������������
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
				
				System.out.println("�޸��û�������Ϣ����");
				String empId = request.getParameter("empId");
				System.out.println("������Ҫ�޸�������û�Id"+empId);
				
				
				String fullname = request.getParameter("fullname");
				String sex = request.getParameter("sex");
				String id = request.getParameter("id");
				String phone = request.getParameter("phone");
				String mailbox = request.getParameter("mailbox");
				String job = request.getParameter("job");
				System.out.println(new String(fullname.getBytes("ISO-8859-1"),
						"UTF-8")
						+ "ȫ��");
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
					System.out.println("x�Ա�" + sex);
					EmployeeService employeeService = new EmployeeServiceImpl();
					if (employeeService.modifyDetails(empDetails)) {
						
						System.out.println("�޸�����ɹ�");
						empDetails = employeeService
						.serachDetailsByEmpId(Integer.parseInt(empId));
						request.setAttribute("empDetails", empDetails);
						request.setAttribute("info", "����ɹ�");
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
						+ "ȫ��");
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
					System.out.println("x�Ա�" + sex);
					EmployeeService employeeService = new EmployeeServiceImpl();
					if (employeeService.addUserDetails(empDetails)) {
						System.out.println("����û�������ɹ�");
					}

				}

			} else if (WordDict.USER_PASSWORD_UPDATED.equals(action)) {
				String empId = request.getParameter("empId");
				String password = request.getParameter("password");

				String newPassword = request.getParameter("newpassword");
				if (password == null || newPassword == null
						|| (password == null & newPassword == null)) {
					System.out.println("�޸�����ʧ��");
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
						request.setAttribute("info", "�����޸ĳɹ�");
						request.getRequestDispatcher(
								"/WEB-INF/jsp/user/alterPassword.jsp").forward(
								request, response);
					} else {
						request.setAttribute("info", "�����޸�ʧ��");
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
				// ��װ����û���������
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
					System.out.println("����û��ɹ�");
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
						System.out.println("��ѯ�û���Ϣ���ɹ�");
						
					}
					
				}
				else{
					RoleService roleService = new RoleServiceImpl();
					/*SplitPage sp = roleService.getAllRole(1);*/
					List<Role> sprole = roleService.getByAllRole();
					request.setAttribute("sprole", sprole);
					
					RecoService recoService = new RecoServiceImpl();
					request.setAttribute("jobMap", recoService.getJobMap());
					
					request.setAttribute("info", "����û�ʧ��");
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
