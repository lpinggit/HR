<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	font-size: 18px;
}

.sizefont {
	font-size: 14px;
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
														<span class="STYLE3">当前位置</span>：[积分管理制度]-[查看积分]
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
												<span class="STYLE1">用户名</span>
											</div>
										</td>
										<td width="12%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">积分等级</span>
											</div>
										</td>
										<td width="15%" height="22"
											background="resource/tabimages/bg.gif" bgcolor="#FFFFFF"
											class="STYLE1">
											<div align="center">
												总积分
											</div>
										</td>
									</tr>
									<c:forEach var="credit" items="${requestScope.creditlist}">
										<tr>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<input type="checkbox" name="checkbox2" value="checkbox" />
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center" class="STYLE1">
													<div align="center">
														<c:choose>
															<c:when test="${credit.empFullName == null}">${credit.empAccount}</c:when>
															<c:otherwise>
																${credit.empFullName}
															</c:otherwise>
														</c:choose>

													</div>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">${credit.creditGrade}</span>
												</div>
											</td>
											<td height="20" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">${credit.totalCredit}</span>
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

		<hr color="b5d6e6" size="4" />
		<font class="STYLE4">一·积分制度明细</font>
		<br />
		<p class="sizefont">
			1.积分等级为1，则你的积分范围0-500分。
			<br />
			2.积分等级为2，则你的积分范围500-1000分。
			<br />
			3.积分等级为3，则你的积分范围1000-unlimit分。
			<br />
		</p>
		<font class="STYLE4">二·积分的获取途径</font>
		<br />
		<p class="sizefont">
			1.员工为公司推荐一个人才，则对应的推荐人的积分+10分。
			<br />
			2.被推荐人通过筛选，则对应的推荐人的积分+10分。
			<br />
			3.被推荐人通过初试，则对应的推荐人的积分+10分。
			<br />
			4.被推荐人通过终试，则对应的推荐人的积分+10分。
			<br />
			5.被推荐人拿到Offer，则对应的推荐人的积分+10分。
			<br />
		</p>
		<font class="STYLE4">三·积分奖励制度</font>
		<br />
		<p class="sizefont">
			1.积分等级为1，无奖励。
			<br />
			2.积分等级为2，则对应的推荐人可领取100元的现金。
			<br />
			3.积分等级为3，则对应的推荐人可领取5000元的现金。
			<br />
		</p>
	</body>
</html>
