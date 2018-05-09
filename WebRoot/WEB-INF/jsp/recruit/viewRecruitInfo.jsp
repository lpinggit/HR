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
		<link href="<%=basePath%>resource/css/recrInfo.css" rel="stylesheet"
			type="text/css" />
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
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
														<span class="STYLE3">当前位置</span>：[招聘管理]-[查看招聘信息]
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
										
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">职位名称</span>
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">所在部门</span>
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">工作地点</span>
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">截止日期</span>
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
									<c:forEach var="recruit" items="${requestScope.recruitlist}">
										<tr>
										
											<td height="20" bgcolor="#FFFFFF">
												<div align="center" class="STYLE1">
													<div align="center">
														${recruit.jobCate.cateName}
													</div>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">${recruit.department.deptName}</span>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">${recruit.workLoc}</span>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">${recruit.deadLine}</span>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE4"><a href="recoRecruit.do?recruitId=${recruit.recruitId }"><img
																src="resource/tabimages/edt.gif" width="16" height="16" />查看详情</a>
													</span>
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
			
			
			<tr>
			<td height="35" background="resource/tabimages/tab_19.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12" height="35">
							<img src="resource/tabimages/tab_18.gif" width="12" height="35" />
						</td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="STYLE4">
										&nbsp;&nbsp;共有${sessionScope.empSp.totalRows }
										条记录数${sessionScope.curentPage}/${sessionScope.empSp.totalPage
										} 页
									</td>
									<td>
										<table border="0" align="right" cellpadding="0"
											cellspacing="0">
											<tr>
												<td>
														<a href="viewRecruitInfo.do?currentPage=${1}">
														<img src="resource/tabimages/first.gif" width="37"
																height="15" /> </a>
														<c:choose>
														<c:when test="${sessionScope.curentPage eq 1}">
														<img src="resource/tabimages/back.gif" width="37"
																	height="15" />
											
														</c:when>
														<c:otherwise>
															<a href="viewRecruitInfo.do?currentPage=${sessionScope.curentPage-1}">
															<img src="resource/tabimages/back.gif" width="43" height="15" />
															</a>
														</c:otherwise>
													</c:choose>	
													
													<c:choose>
														<c:when
															test="${sessionScope.curentPage eq sessionScope.empSp.totalPage}">
															<img src="resource/tabimages/next.gif" width="37"
																	height="15" />
														</c:when>
														<c:otherwise>
															<a href="viewRecruitInfo.do?currentPage=${sessionScope.curentPage +1}">
															<img src="resource/tabimages/next.gif" width="37"
																	height="15" />
															</a>
														</c:otherwise>
													</c:choose>	
													<a href="viewRecruitInfo.do?currentPage=${sessionScope.empSp.totalPage}">
													<img src="resource/tabimages/last.gif" width="37"
																height="15" /> </a>
														
													
												
												</td>
												<td width="60"></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="16">
							<img src="resource/tabimages/tab_20.gif" width="16" height="35" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</body>
</html>
