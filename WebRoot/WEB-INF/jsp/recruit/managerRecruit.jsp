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
		<link href="<%=basePath%>resource/css/Calendar.css" rel="stylesheet"
			type="text/css" />
			
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fckeditor-replace-textarea-publish.js"></script>
		<script type="text/javascript">
		//下面的代码段如果你页面里有，可以去掉
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			function $(objID) {
				return document.getElementById(objID);
			}
	    </script>
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
	font-size: 14px;
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

</style>
	</head>

	<body>
	
	<script type="text/javascript" src="<%=basePath%>resource/script/calendar.js" charset="gbk"></script>
	<form action="udpateRecruit.do?recruitId=${recruitInfo.recruitId}"  method="POST">
		<div id="content">
			<div id="side1">
				<div id="side1-1"></div>
				<div id="side1-2">
					职位需求管理之发布招聘信息：
					<input type="button" value="打印信息" onclick="window.print()"/>
				</div>
				<div id="side1-3"></div>
			</div>
			
				<div id="side2">

					<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse">
						<tr>
							<td>
								招聘职位:
							</td>
							<td>
								<select name="job">
									<c:forEach var="job" items="${cateList}">
										<c:if test="${job.isWhatCate eq 'B'}">
											<option value="${job.cateId}" selected="selected">
												${job.cateName }
											</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td>
								招聘人数:
							</td>
							<td>
								<input type="text" name="number" value="${recruitInfo.number }" />
							</td>
							<td>
								部门:
							</td>
							<td>
								<select name="deptNo">
									<c:forEach var="dept" items="${deptList}">
										<option value="${dept.deptId}" selected="selected">
											${dept.deptName }
										</option>

									</c:forEach>

								</select>
							</td>
							<td>
								工作地点:
							</td>
							<td>
								<select name="workloc">
									<option value="大连" selected="selected">
										大连
									</option>
									<option value="北京">
										北京
									</option>
									<option value="上海">
										上海
									</option>
									<option value="广州">
										广州
									</option>
									<option value="深圳">
										深圳
									</option>
									<option value="杭州">
										杭州
									</option>
									<option value="成都">
										成都
									</option>
									<option value="苏州">
										苏州
									</option>
									<option value="武汉">
										武汉
									</option>
									<option value="厦门" selected="selected">
										厦门
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								职能类别:
							</td>
							<td>
								<select name="work_cate_id">
									<c:forEach var="job" items="${cateList}">
										<c:if test="${job.isWhatCate eq 'A'}">
											<option value="${job.cateId}">
												${job.cateName }
											</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td>
								雇佣形式:
							</td>
							<td>
								<select name="workshift">
									<option value="全职">
										全职
									</option>
									<option value="兼职">
										兼职
									</option>
								</select>
							</td>
							<td>
								截止日期:
							</td>
							<td>
								<input type="text" name="deadline" id="t"  
									value="2013-10-19" size="14"
		                           readonly onclick="showcalendar(event,this);"
		                           onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''"
								 />
								<div id="c" style="padding: 50px; display: none"></div>
							</td>
							<td>
								工作经验:
							</td>
							<td>
								<input type="text" name="experience" value="${recruitInfo.experience }" />
							</td>
						</tr>
						<tr>
							<td>
								薪资待遇:
							</td>
							<td>
								<input type="text" value="面议"/>
							</td>
							<td>
								<input type="hidden" name="hrId" value="${user.empId }" />

							</td>

							<td>
								紧急程度:
							</td>
							<td colspan="2">
								<input type="radio" name="isUrgent" value="yes"
									checked="checked" id="radio"/>急招
								
								<input type="radio" name="isUrgent" value="no" id="radio"/>不急
								
							</td>
							<td colspan="3">
								<input type="submit" value="点击修改" />
							</td>
						</tr>
					</table>
				</div>
				<div id="side3">
					<h3	 >
						岗位描述:
					</h3>
					<div id="side4">
						<textarea rows="5" cols="110" name="jobDesc" style="font-size:13px;">${recruitInfo.jobDesc}</textarea>
					</div>
				</div>
				<div id="side4">
					<h3>
						岗位要求：
					</h3>
					<div >
						<textarea  cols="110" rows="5" name="jobRequest">${recruitInfo.jobRequest }</textarea>
					</div>
				</div>
				<div id="side5">
					<h3>
						特殊要求：
						<br />
						<textarea name="specialRequest" cols="110" rows="2" style="font-size:13px;">${recruitInfo.specialRequest}</textarea>						
					</h3>
				</div>
		</div>
		</form>
	</body>
</html>

