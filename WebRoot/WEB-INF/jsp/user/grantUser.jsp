<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<title>给用户授权</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

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
-->
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
															<span class="STYLE3">当前的位置</span>:[用户管理]-[用户授权]
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
	
	
		<div id="contenter">
			<div id="second">
				<form action="saveGrantUser.do" method="POST">
					<table width="500" border="1ex" bordercolor="#f0ffff"
						cellspacing="0" cellspacing="0" align="center">
						<tr>
							<td width="100" id="id1">
								用户ID：
							</td>
							<td width="88">
								<input type="text" name="empId" value="${empId}"
									readonly="readonly" />
							</td>
							
							<tr>
							   <td width="100" id="id1">
								授权的角色：
							</td>
							<td width="88">
								<select name="roleId">
									<c:forEach var="role" items="${rolelist}">
										<c:choose>
											<c:when test="${currentRoleId eq role.roleId}">
												<option value="${role.roleId}" selected="selected">
													${role.roleName}
												</option>
											</c:when>
											<c:otherwise>
												<option value="${role.roleId}">
													${role.roleName}
												</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</td>
							</tr>
							<tr>
							  <td colspan="2">
							     <div id="submit">
						           <input type="submit" value="授权" />
					             </div>
							  </td>
							</tr>
					</table>
					
				</form>
			</div>
			<div id="third"></div>
		</div>
	</body>
</html>
