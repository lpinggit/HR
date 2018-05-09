<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>		
		<link href="<%=basePath%>resource/css/viewInfo.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>resource/css/selectInfo.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	</head>

	<body>
		<div id="content">
			<div id="first">
				审核信息之拿到Offer
				<hr />
			</div>
			<div id="second">
				<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse"
					bordercolor="#72d3d7" cellpadding="5">
					<tr id="sigle">
						
						<td>
							被推荐人姓名：
						</td>
						<td>
							徐小凤
						</td>
						<td>
							推荐人姓名：
						</td>
						<td>
							邓晓明
						</td>
						<td>
							当前处理HR
						</td>
						<td>
							许小雨
						</td>
						<td>
							<a href="#">通过</a>
						</td>
					
					</tr>
					<tr id="double">
					
						<td>
							被推荐人姓名：
						</td>
						<td>
							杨晓
						</td>
						<td>
							推荐人姓名：
						</td>
						<td>
							廖世杰
						</td>
						<td>
							当前处理HR
						</td>
						<td>
							周大伟
						</td>
						<td>
							<a href="#">通过</a>
						</td>
					</tr>
					<tr id="sigle">
						
						<td>
							被推荐人姓名：
						</td>
						<td>
							李晨分
						</td>
						<td>
							推荐人姓名：
						</td>
						<td>
							李世明
						</td>
						<td>
							当前处理HR
						</td>
						<td>
							许小雨
						</td>
						<td>
							<a href="#">通过</a>
						</td>
					</tr>
					<tr id="double">
						
						<td>
							被推荐人姓名：
						</td>
						<td>
							黄小弟
						</td>
						<td>
							推荐人姓名：
						</td>
						<td>
							李世明
						</td>
						<td>
							当前处理HR
						</td>
						<td>
							邓晓明
						</td>
						<td>
							<a href="#">通过</a>
						</td>
					</tr>
					<tr id="sigle">
						
						<td>
							被推荐人姓名：
						</td>
						<td>
							张子栋
						</td>
						<td>
							推荐人姓名：
						</td>
						<td>
							徐晓燕
						</td>
						<td>
							当前处理HR
						</td>
						<td>
							姚晨防
						</td>
						<td>
							<a href="">通过</a>
						</td>
					</tr>
					<tr>
					<td>总共12用户</td>
					<td colspan="2">首页</td>
					<td>上一页</td>
					<td>下一页</td>
					<td  colspan="2">尾页</td>
					<td></td>
					</tr>																		
				</table>
			</div>
		</div>
	</body>
</html>
