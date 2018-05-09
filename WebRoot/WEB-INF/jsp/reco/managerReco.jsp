<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>	
		<link href="<%=basePath%>resource/css/addPerson.css" rel="stylesheet" type="text/css" />
		<base href="<%=basePath%>"> 
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<div id="neirong">
			<form name="saveRecommender.do" enctype="multipart/form-data" method="post">
				<div id="first">
					<div id="img">
						<img src="<%=basePath%>resource/images/personPic.png" width="52" height="38" />
					</div>
					<div id="head">
						管理被推荐人信息
					</div>
					<div id="submit">
						<input type="submit" value="点击修改&gt;&gt;" />
					</div>
				</div>
				<div id="second">
					<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse"
						bordercolor="#72d3d7">
						<tr>
							<td>
								被推荐人ID：
							</td>
							<td>
								<input name="recoId" type="text" />
							</td>
							<td>
								被推荐人姓名：
							</td>
							<td>
								<input name="recoName" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								职位：
							</td>
							<td>
								<select name="job">
								<c:forEach var="job" items="${jobMap}">									
										<option value="${job.key}">${job.value}</option>																	
									</c:forEach>
								</select>
							</td>
							<td>
								专业：
							</td>
							<td>
								<select name="major">
									<c:forEach var="major" items="${majorMap}">
    								<option value="${major.key}">${major.value}</option>
    								</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td>
								被推荐人性别：
							</td>
							<td>
								<input type="radio" name="sex" value="男" id="radio" />
								男
								<input type="radio" name="sex" value="女" id="radio" />
								女
							</td>
							<td>
								被推荐人照片：
							</td>
							<td>
								<input name="pic" type="file" />
							</td>
						</tr>
						<tr>
							<td>
								被推荐人学位：
							</td>
							<td>
								<select name="degree">
					    			<option value="学士">学士</option>
					    			<option value="硕士">硕士</option>
					    			<option value="博士">博士</option>
					    			<option value="博士后">博士后</option>
    							</select>
							</td>
							<td>
								毕业院校：
							</td>
							<td>
								<input name="motherSchool" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								是否再读？
							</td>
							<td>
								<input type="radio" name="isGraguated" value="是" id="radio" />
								是
								<input type="radio" name="isGraguated" value="否" id="radio" />
								否
							</td>
							<td>
								毕业时间：
							</td>
							<td>
								<input name="graduatedDate" type="text" />
							</td>
						</tr>
						<tr>
							<td>
								手机号码：
							</td>
							<td>
								<input name="phone" type="text" />
							</td>
							<td>
								电子邮箱：
							</td>
							<td>
								<input name="mailbox" type="text" />
							</td>
						</tr>
						<tr><td>简历：</td><td><input type="file" name="resume"/></td></tr>
						<tr>
							<td>
								专业技能：
							</td>
							<td colspan="3" id="zy">
								<textarea name="skills" cols="45" rows="8"></textarea>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>

