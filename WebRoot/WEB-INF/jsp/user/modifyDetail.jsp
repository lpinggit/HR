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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>个人信息</title>
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
															<span class="STYLE3">当前的位置</span>:[用户管理]-[保存资料]
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
				<form action="modifyUserDetails.do" method="post">
					<input type="hidden" name="empId"
						value="${sessionScope.user.empId}" />
					${userId}
					<table width="500" border="1ex" bordercolor="#f0ffff"
						cellspacing="0" cellspacing="0" align="center">
						<tr>
							<td width="100" align="center">
								全&nbsp;&nbsp;&nbsp;名:
							</td>
							<td>
								<input type="text" name="fullname" value="${empDetails.fullname }" />
							</td>
							<td width="100" align="center">
								性&nbsp;&nbsp;&nbsp;别:
							</td>
							<td>
								<c:choose>
								<c:when test="${empDetails.sex eq '男'}">
									<label>
										<input type="radio" name="sex" value="男" checked="checked" />
										男
									</label>
									<label>
										<input type="radio" name="sex" value="女" />
										女
									</label>
								</c:when>
								<c:otherwise>
								<label>
									<input type="radio" name="sex" value="男" />
									男
								</label>
									<label>
										<input type="radio" name="sex" value="女"  checked="checked"/>
										女
									</label>
								</c:otherwise>
							</c:choose>
							</td>
						</tr>
						<tr>
							<td width="100" align="center">
								身份证:
							</td>
							<td>
								<input type="text" name="id" value="${empDetails.idcard }"/>
							</td>
							<td width="100" align="center">
								电&nbsp;&nbsp;&nbsp;话:
							</td>
							<td>
								<input type="text" name="phone"	value="${empDetails.phone }" />
							</td>
						</tr>
						<tr>
							<td width="100" align="center">
								邮&nbsp;&nbsp;&nbsp;箱:
							</td>
							<td>
								<input type="text" name="mailbox" 	value="${empDetails.mailbox }" />
							</td>
							<td width="100" align="center">
								职&nbsp;&nbsp;&nbsp;业:
							</td>
							<td>
								<input type="text" name="job"	value="${empDetails.job }" />
							</td>
						</tr>
						<tr>
						  <td colspan="1">
						  <div id="submit">
						     <input type="submit" value="保存" />
					      </div>
					      <td colspan="3">
						  <span>${info}</span>
						 </td>
						</tr>
					</table>
					
				</form>
			</div>
			<div id="third"></div>
		</div>
	</body>
</html>

