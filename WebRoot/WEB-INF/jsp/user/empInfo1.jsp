<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'empInfo.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<table border="1">
		<tr>
			<th>员工ID</th>
			<th>员工帐号</th>
			<th>员工密码</th>
			<th>员工角色</th>
			<th colspan="4">操作</th>
		</tr>
	<c:forEach var="emp" items="${sessionScope.empSp.set}">
	  <tr>
			<th>${emp.empId}</th>
			<th>${emp.username}</th>
			<th>${emp.password }</th>
			<th>${emp.roleId}</th>
	        <th><a href="deleteUser.do?empId=${emp.empId}">删除</a></th>
	        <th><a href="grantUser.do?empId=${emp.empId}&roleId=${emp.roleId}">授权</a></th>
		</tr>
	</c:forEach>
	<tr>
	<th>共${sessionScope.empSp.totalRows }用户</th>
	<th>${sessionScope.curentPage}/${sessionScope.empSp.totalPage }</th>
	
	<th>
	<c:choose>
    		<c:when test="${sessionScope.curentPage eq 1}">
    			<a href="viewUser.do?currentPage=${1}">首页</a>
    		</c:when>
    		<c:otherwise>
    			<a href="viewUser.do?currentPage=${sessionScope.curentPage-1}">上一页</a><%-- 需要在该JSP页面知道当前页 --%>	
    		</c:otherwise>
    	</c:choose>
    	
    	<c:choose>
    		<c:when test="${sessionScope.curentPage eq sessionScope.empSp.totalPage}">
    			<a href="viewUser.do?currentPage=${sessionScope.curentPage}">尾页</a>
    		</c:when>
    		<c:otherwise>
    			<a href="viewUser.do?currentPage=${sessionScope.curentPage +1}">下一页</a>
    		</c:otherwise>
    	</c:choose>
	</th>
	
	</tr>
	</table>
	
	<body>

	</body>
</html>
