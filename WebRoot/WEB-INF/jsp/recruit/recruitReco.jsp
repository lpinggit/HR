<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	    <base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<style>
#content h3 {
	margin-top: 0px;
	color: #30A5AB;
	margin-bottom: 10px;
}

#content textarea {
	background-color: #F0F0F0;
	border: 1px dashed #CCC;
	height: 117px;
}
#content #side5 textarea {
	background-color: #F0F0F0;
	border: 1px dashed #CCC;
	height: 20px;
}

#content #side3-1 {
	background-color: #FFF;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	margin-bottom: 15px;
}
#content  {
	width: 900px;
	margin-right: auto;
	margin-left: auto;
}
#content #side1 #side1-1 {	
	height: 44px;
	float: left;
	width: 15px;
}
#content #side1 #side1-2 {
	background-image: url(../../../resourse/../images/title3.png);
	background-repeat: repeat-x;
	height: 34px;
	float: left;
	width: 770px;
	color: #24999F;
	padding-top: 10px;
}
#content #side1 #side1-3 {
	background-image: url(resourse/../images/title2.png);
	background-repeat: no-repeat;
	float: right;
	width: 15px;
	height: 44px;
}
#content #side1 {
	background-color:#D0EAEF;
	height: 44px;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #00BDBD;
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
#content #side3-1  {
	height: 120px;
	width: 800px;
	border: 1px dashed #CCC;
	font-size: 12px;
	font-family: "黑体";
	line-height: 0.6cm;
	background-color: #F0F0F0;
}
#content #side3 {
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
#content #side4 {
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
#content #side5 {
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
#content #side2 table tr td {
	width: 120px;
}
#content #side2 table {
	margin-top: 15px;
	font-size: 12px;
}
#content h3  {
	margin-top: 0px;
	color: #30A5AB;
	margin-bottom: 10px;
}
#content #side2 {
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
#content #side2 table tr td input {
	width: 120px;
}
#content #side2 table tr td #radio {
	width: 25px;
}
#bgc {
	background-color: #F0F0F0;
}
#SYSF{font-size:13px;}
</style>
	</head>

	<body>
	<form action="immendiateRecruitReco.do?hrId=${recruitInfo.hrId}" method="POST">
		<div id="content">
			<div id="side1">
				<div id="side1-1"></div>
				<div id="side1-2" align="center"> 
					${recruitInfo.jobCate.cateName} 
					 
				</div>
				<div id="side1-3"></div>
			</div>
			
				<div id="side2">

					<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse"
						><!--bordercolor="#72d3d7"-->
						<tr>
							<td>
								招聘职位:
							</td>
							<td>
								${recruitInfo.jobCate.cateName}
							</td>
							<td>
								招聘人数:
							</td>
							<td>
								${recruitInfo.number }
							</td>
							<td>
								招聘部门:
							</td>
							<td>
								${recruitInfo.department.deptName}
							</td>
							<td>
								工作地点:
							</td>
							<td>
								${recruitInfo.workLoc}
							</td>
						</tr>
						<tr>
							<td>
								薪资待遇:
							</td>
							<td>
								面议
							</td>
							<td>
								雇佣形式:
							</td>
							<td>
								${recruitInfo.workshift}
							</td>
							<td>
								截止日期:
							</td>
							<td>
								${recruitInfo.deadLine }
								<div id="c" style="padding: 50px; display: none"></div>
							</td>
							<td>
								工作经验:
							</td>
							<td>
								${ recruitInfo.experience}年
							</td>
						</tr>
					
					</table>
				</div>
				<div id="side3">
					<h3	 >
						岗位描述:
					</h3>
					<div id="side4">
						<textarea rows="6" cols="110" name="jobDesc" style="font-size:13px;">${recruitInfo.jobDesc}</textarea>
					</div>
				</div>
				<div id="side4">
					<h3>
						岗位要求：
					</h3>
					<div id="bgc">
						<a id="SYSF">${recruitInfo.jobRequest}</a>
					</div>
				</div>
				<div id="side5">
					<h3>
						特殊要求：
						<br />
						<textarea cols="110" rows="2" style="font-size:13px;">${recruitInfo.specialRequest }</textarea>						
					</h3>
				</div>
				<div align="center"><input	type="submit" value="立即推荐"/>&nbsp;&nbsp;<input type="button" value="打印信息" onclick="window.print()"/></div>
		</div>
		
		</form>
	</body>
</html>

