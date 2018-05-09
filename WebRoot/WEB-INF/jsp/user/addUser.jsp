<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加用户</title>
		<link href="<%=basePath%>resource/css/addUser.css" rel="stylesheet"
			type="text/css" />
		<style type="text/css">

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
</style>
	</head>

	<body>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" background="resource/tabimages/tab_05.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="12" height="30">
									<img src="resource/tabimages/tab_03.gif" width="12" height="30" />
								</td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="46%" valign="middle">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="5%">
															<div align="center">
																<img src="resource/tabimages/tb.gif" width="16"
																	height="16" />
															</div>
														</td>
														<td width="95%" class="STYLE1">
															<span class="STYLE3">当前的位置</span>:[用户管理]-[添加用户]
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		
				<form action="saveUser.do" method="POST">
				<table width="350" border="1ex" bordercolor="#f0ffff"
						cellspacing="0" cellspacing="0" align="center">
					<tr>
						<td>
							输入员工账号：
						</td>
						<td>
							<input type="text" name="emp_account" />
						</td>
					</tr>
					<tr>
						<td>
							输入员工密码：
						</td>
						<td>
							<input type="password" name="emp_password" />
						</td>
					</tr>
					<tr>
					   <td>
							输入员工角色：
						</td>
						<td>
							<select name="roles">
								<c:forEach var="roles" items="${sprole}">
									<option value="${roles.roleId}">
										${roles.roleName}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							员工性别：
						</td>
						<td>
							<input type="text" name="sex" />
						</td>
					</tr>
					<tr>
						<td>
							员工手机号码：
						</td>
						<td>
							<input type="text" name="phone" />
						</td>
					</tr>
					
					<tr>
					  <td>
							员工职位：
						</td>
						<td>
							<select name="job">
								<c:forEach var="job" items="${jobMap}">
									<option value="${job.key}">
										${job.value}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						 <td>
						   <div id="submit">
					           <input type="submit" value="提交" />
				            </div>
					       </td>
					       <td>
					        <span>${info }</span>
					       </td>
					  </tr>
				</table>
				
				</form>
		
		
	</body>
</html>

