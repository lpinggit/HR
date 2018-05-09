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
		<title>无标题文档</title>
		<link href="<%=basePath%>resource/css/addUser.css" rel="stylesheet"
			type="text/css" />
		<link href="<%=basePath%>resource/css/grantRole.css" rel="stylesheet"
			type="text/css" />
			
		
	</head>

	<body>

		<div id="contenter">
			<span>角色管理&gt;&gt;授权角色</span>
			<div id="first">
				角色管理之授权角色
			</div>
			<form action="saveRoleGrant.do">
				<div id="second">
					角色ID：
					<input type="text" name="role_id" id="role" value="${roleId}" />
					<br />
					<div id="thi">
						<div id="thi1">
							<img src="<%=basePath%>resource/images/role2.png" width="27"
								height="21" />
						</div>
						<div id="thi2">
							该角色菜单显示：
						</div>
					</div>
					<div id="fourth">
						<div id="f-1">
							<div id="f-1-1">
								<img src="<%=basePath%>resource/images/role.png" width="21"
									height="21" />
							</div>
							<div id="f-1-2">
								该角色所拥有菜单
							</div>
						</div>
						<c:forEach var="currMenu" items="${currentMenus}"
							varStatus="status">

							<input type="checkbox" name="menuId" value="${currMenu.menuId}"
								checked="checked" />${currMenu.menuName}
						<c:if test="${((status.index+1) mod 2) ==0}">
								<br />
							</c:if>
						</c:forEach>

					</div>
					<div id="five">
						<div id="f-2">
							<div id="f-1-1">
								<img src="<%=basePath%>resource/images/role.png" width="21"
									height="21" />
							</div>
							<div id="f-1-2">
								其他菜单
							</div>
						</div>
						<c:forEach var="menu" items="${allMenus}">
							<input type="checkbox" name="menuId" value="${menu.menuId}" />${menu.menuName}
						<c:if test="${((status.index+1) mod 2) ==0}">
								<br />
							</c:if>
						</c:forEach>
					</div>
					<div id="side4">
						<input type="submit" value="点击进行授权" />
					</div>
				</div>
			</form>
			<div id="third"></div>
		</div>
	</body>
</html>