<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>		
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>resource/css/createDtl.css">
		<script type="text/javascript"
			src="<%=basePath%>resource/script/showDetails.js" charset="gbk"></script>
		<script type="text/javascript"
			src="<%=basePath%>resource/script/util.js"></script>
		<style type="text/css">
.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE1 {
	font-size: 12px
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
</style>
	</head>

	<body>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="resource/tabimages/tab_05.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30">
								<img src="resource/tabimages/tab_03.gif" width="12" height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="46%" valign="middle">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="5%">
														<div align="center">
															<img src="resource/tabimages/tb.gif" width="16"
																height="16" />
														</div>
													</td>
													<td width="95%" class="STYLE1">
														<span class="STYLE3">当前位置</span>：[面试流程管理]-[筛选]
													</td>
												</tr>
											</table>
										</td>

									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>

				<td width="16">
					<img src="resource/tabimages/tab_07.gif" width="4" height="30" />
				</td>
			</tr>


			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8" background="resource/tabimages/tab_12.gif">
								&nbsp;
							</td>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									bgcolor="b5d6e6">
									<tr>
										<td width="3%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<input type="checkbox" name="checkbox" value="checkbox" />
											</div>
										</td>
										<td width="3%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">被推荐人姓名</span>
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">推荐姓名</span>
												
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">基本信息</span>
											</div>
										</td>
										<td width="15%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF"
											class="STYLE1">
											<div align="center">
												基本操作
											</div>
										</td>
									</tr>
									<c:forEach var="check" items="${requestScope.interlist}">
										<tr>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<input type="checkbox" name="checkbox2" value="checkbox" />
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center" class="STYLE1">
													<div align="center">
														${check.recoName }
													</div>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">
													<c:choose>
														<c:when test="${check.empFullName eq null}">
															${check.empId}
														</c:when>
														<c:otherwise>
															${check.empFullName}
														</c:otherwise>
													</c:choose>
													</span>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center" class="STYLE1">
													<div align="center">
														<a	onmouseover="startAjax('${check.recoId}')" onmouseout="closeWindow()">详情</a>
													</div>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE4"><a href="modifyInterviewStatus.do?recoId=${check.recoId}&currentstatus=${check.currStatus}"><img
																src="resource/tabimages/edt.gif" width="16" height="16" />通过</a>
														&nbsp; &nbsp;&nbsp;&nbsp;<img
															src="resource/tabimages/del.gif" width="16" height="16" />
														<a href="InterviewFailed.do?recoId=${check.recoId}">落选</a></span>
												</div>
											</td>
										</tr>
									</c:forEach>

								</table>
							</td>

							<td width="8" background="resource/tabimages/tab_15.gif">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
