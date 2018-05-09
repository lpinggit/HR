<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<script type="text/javascript">
        function selectbox(indexbox)
        {
            parent.right.location.href = indexbox;
        }
    </script>
		<script type="text/javascript" src="js/dtree/dtree.js"></script>
		<script type="text/javascript" src="js/java-like.util.js"></script>
		<link rel="stylesheet" href="js/dtree/dtree.css" type="text/css">


		<title>My JSP 'menu.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
     <style type="text/css">
     body{
     background-image:url(resource/images/leftlp.jpg);
     width:100%;
     }
 </style>
	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="28" background="resource/images/main_40.gif"></td>
				<td align="center" valign="top">
					<table width="90%" border="0" align="center" cellpadding="1"
						cellspacing="1">
						<tr>
							<td>
								<SCRIPT LANGUAGE="JavaScript">
                                d = new dTree('d');
                                d.config.target = "right";
                                d.config.imageDir = 'js/dtree/img';
                                d.reSetImagePath();
                                d.config.folderLinks = false;
                                d.config.closeSameLevel = true;
                                var isOpen ;
                                //根节点
                                d.add(0, -1, 'HR人才推荐系统');
                                
                                <c:forEach var="menu" items="${user.role.menuSet}">
                               		<c:choose>
                               			<c:when test="${empty menu.parentId}">
                               				d.add(${menu.menuId},0,'${menu.menuName}');
                               				<c:forEach var="fun" items="${menu.funSet}">
                               					d.add(${fun.funId},${menu.menuId}, '${fun.funName}', '${fun.funUri}', '', 'right');
                               				</c:forEach>
                               			</c:when>
                               			<c:otherwise>
                               				d.add(${menu.menuId},${menu.parentId},'${menu.menuName}');
                               				<c:forEach var="fun" items="${menu.funSet}">
                               					d.add(${fun.funId},${menu.menuId}, '${fun.funName}', '${fun.funUri}', '', 'right');
                               				</c:forEach>
                               			</c:otherwise>
                               		</c:choose>
                               	</c:forEach>
                                
                               
                                document.write(d);
                            </script>
							</td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
	</body>
</html>
