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
		<title>添加角色</title>
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
				<form action="saveRole.do">
					<table width="350" border="1ex" bordercolor="#f0ffff"
						cellspacing="0" cellspacing="0" align="center">
						<tr>
							<td>
								输入角色名称：
							</td>
							<td>
								<input type="text" name="roleName" />
							</td>
						</tr>
						<tr>
						 <td >
						   <div id="submit">
						   <input type="submit" value="点击添加角色" />
					        </div>
					       </td>
					       <td>
					         <span>${info }</span>
					       </td>
					    </tr>
					</table>
				</form>
			</div>
			<div id="third"></div>
		</div>


	</body>
</html>
