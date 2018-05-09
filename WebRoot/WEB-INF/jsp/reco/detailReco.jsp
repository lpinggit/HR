<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
<head>
<link href="<%=basePath%>resource/css/viewInfo.css" rel="stylesheet" type="text/css" />
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
<div id="content">
<div id="first">被推荐人信息表<hr/></div>
<div id="second">
<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse" bordercolor="#72d3d7" cellpadding="5">
<tr id="sigle"><td>被推荐人ID：</td><td></td><td>被推荐人姓名：</td><td>xxxxxx</td><td colspan="2"  rowspan="3" id="alon"><input name="" type="image" width="60" height="80" /></td></tr>
<tr id="double"><td>被推荐人姓名：</td><td>xxxxxxww</td><td>当前处理HR：</td><td>xxxx</td></tr>
<tr id="sigle"><td>推荐职位：</td><td>xxxxxx</td><td>专业：</td><td>xxxx</td></tr>
<tr id="double"><td>被推荐人性别：</td><td> </td><td>毕业院校：</td><td>xxxx</td><td>联系电话：</td><td>xxxx</td></tr>
<tr id="sigle"><td>电子邮箱：</td><td>xxxxxx</td><td>简历：</td><td>xxxx</td><td>是否被推荐过？</td><td>xxxx</td></tr>
<tr id="double"> <td>专业技能：</td><td colspan="5">xxxxxx</td></tr>
</table>
</div>
</div>
</body>
</html>
