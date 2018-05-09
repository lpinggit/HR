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
		<title>无标题文档</title>
		<link href="<%=basePath %>resource/css/recuDetail.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div id="content">
			<div id="side1">
				<div id="side1-1"></div>
				<div id="side1-2">
					职位需求描述：
				</div>
				<div id="side1-3"></div>
			</div>
			<div id="side2">
				<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse"
					bordercolor="#72d3d7">
					<tr>
						<td>
							招聘职位:
						</td>
						<td>
							技术支持工程师
						</td>
						<td>
							招聘人数:
						</td>
						<td>
							8
						</td>
						<td>
							部门:
						</td>
						<td>
							系统工程部
						</td>
						<td>
							工作地点:
						</td>
						<td>
							北京
						</td>
					</tr>
					<tr>
						<td>
							职能类别:
						</td>
						<td>
							翻译
						</td>
						<td>
							雇佣形式:
						</td>
						<td>
							全职
						</td>
						<td>
							截止日期:
						</td>
						<td>
							2012-9-1
						</td>
						<td>
							工作经验:
						</td>
						<td>
							6
						</td>
					</tr>
					<tr>
						<td>
							薪资待遇:
						</td>
						<td colspan="3">
							面议
						</td>
						<td>
							相关HR:
						</td>
						<td colspan="3">
							徐中原
						</td>
					</tr>
				</table>
			</div>
			<div id="side3">
				<H3>
					岗位描述:
				</H3>
				<div id="side3-1">
					1.最低学历资格:计算机及其相关专业，大专及以上学历； 
					<br />
					2.相关工作经验:2-3年以上系统维护或Helpdesk工作经验； 
					<br />
					3.所需的专业知识及证书：熟悉计算机软硬件系统、服务器操作系统和基本的计算机网络管理技术； 
					<br />
					4.个人素质要求：具备较强的沟通能力和服务意识、具备较强的学习能力和主动解决问题的能力。 
					<br />
					 
				</div>
			</div>
			<div id="side4">
				<H3>
					岗位要求：
				</H3>
				<div id="side3-1">
					1.负责项目中IT基础架构系统的营运 
					<br />
					2.应用系统实施及用户培训 
					<br />
					3.负责项目办公室的日常IT工作 
					<br />
					4.完成上级交办的其他工作任务
				</div>
			</div>
			<div id="side5">
				特殊需求：单身，未婚&nbsp;&nbsp;&nbsp;&nbsp;是否急招：
				<input type="radio" name="RadioGroup1" value="是" id="radio" />
				是
				<input type="radio" name="RadioGroup1" value="否" id="radio" />
				否&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="viewRecruitInfo.do">查看更多</a>
			</div>
		</div>
	</body>
</html>
