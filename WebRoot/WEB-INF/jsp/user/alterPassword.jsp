<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="<%=basePath%>resource/css/addUser.css" rel="stylesheet"
			type="text/css" />
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style>
#contenter #second table {
	margin-left: 70px
}
</style>
<script src="<%=basePath%>resource/script/jquery-1.3.2.min.js"></script>
</head>

<body>

		<div id="contenter">
			<span>用户管理&gt;&gt;修改密码</span>
			<div id="first">
				用户管理之修改密码
			</div>

			<div id="second">
				<form action="userpasswordUpdated.do" method="post">
					<table>
						<tr>
							<td>
								用户名：
							</td>
							<td>
								<input type="text" name="username" value="${user.username}"
									readonly="readonly" />
							</td>
							<td>
								用户ID：
							</td>
							<td >
								<input type="text" name="empId" value="${user.empId}"
									readonly="readonly" />
							</td>
							<td></td>
						</tr>
						<tr>
							<td>
								原密码：
							</td>
							<td>
								<input type="password" name="password" id="password"/>
							</td>
							<td>
								新密码：
							</td>
							<td>
								<input type="password" name="newpassword" id="newpassword"/>
							</td>
							<td><span class="e">${info}</span></td>
						</tr>
						<tr>
						<td colspan="5">
						 <div id="submit">
						   <button type="submit" onclick="a()">点击修改密码</button>
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

