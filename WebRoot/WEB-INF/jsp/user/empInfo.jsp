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
		<link href="<%=basePath%>resource/css/recrInfo.css" rel="stylesheet"
			type="text/css" />
		<base href="<%=basePath%>">

		<title>My JSP 'empInfo.jsp' starting page</title>

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
	<script type="text/javascript">
		function delUser(){
			var str ="";
			var selectUser = document.getElementsByName("selectUser");
			for(var i = 0;i<selectUser.length;i++){
				if(selectUser[i].checked ==true){
					str = str +selectUser[i].value+",";
				}
			}
			if(str ==""){
			
				alert("至少选择一条记录删除");
			}
			window.location="deleteUser.do?selectUser="+str;
		}
		
	
		function checkAll(){
			var selectUser = document.getElementsByName("selectUser");
			if(document.getElementById("ifAll").checked==true){
				for(var i = 0;i<selectUser.length;i++){
					selectUser[i].checked=true;
				}
			}
			else {
			
			for(var i = 0;i<selectUser.length;i++){
					selectUser[i].checked=false;
				}
				
			}
		
		}
	</script>
	
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
													<td width="80%" class="STYLE1">
														<span class="STYLE3">当前位置</span>：[用户管理]-[查看用户]
													</td>
													<td><input type="button" value="批量删除"  onclick="delUser()"/></td>
													<td><input type="button" value="打印信息" onclick="window.print()"/></td>
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
											<input type="checkbox" id="ifAll" onclick="checkAll()"/>
										</div>
									</td>
									<td width="3%" height="22"
										background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE1">员工ID</span>
										</div>
									</td>
									<td width="12%" height="22"
										background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE1">员工帐号</span>
										</div>
									</td>
									<td width="14%" height="22"
										background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE1">密码</span>
										</div>
									</td>
									<td width="18%" background="resource/tabimages/bg.gif"
										bgcolor="#FFFFFF">
										<div align="center">
											<span class="STYLE1">角色身份(ID)</span>
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
								<c:forEach var="emp" items="${sessionScope.empSp.set}">
									<tr>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center">
												<input type="checkbox" name="selectUser"  value="${emp.empId}"/>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center" class="STYLE1">
												<div align="center">
													${emp.empId}
												</div>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">${emp.username}</span>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">${emp.password} </span>
											</div>
										</td>
										<td bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE1">${emp.role.roleName}</span>
											</div>
										</td>
										<td height="20" bgcolor="#FFFFFF">
											<div align="center">
												<span class="STYLE4"><a href="grantUser.do?empId=${emp.empId}&roleId=${emp.roleId}">
													<img
														src="resource/tabimages/edt.gif" width="16" height="16" />授权
												</a></span>
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
														<a href="viewUser.do?currentPage=${1}">
														<img src="resource/tabimages/first.gif" width="37"
																height="15" /> </a>
														<c:choose>
														<c:when test="${sessionScope.curentPage eq 1}">
														<img src="resource/tabimages/back.gif" width="37"
																	height="15" />
											
														</c:when>
														<c:otherwise>
															<a href="viewUser.do?currentPage=${sessionScope.curentPage-1}">
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
															<a href="viewUser.do?currentPage=${sessionScope.curentPage +1}">
															<img src="resource/tabimages/next.gif" width="37"
																	height="15" />
															</a>
														</c:otherwise>
													</c:choose>	
													<a href="viewUser.do?currentPage=${sessionScope.empSp.totalPage}">
													<img src="resource/tabimages/last.gif" width="37"
																height="15" /> </a>
														
														
																
														<!--<c:choose>
														<c:when test="${sessionScope.curentPage eq 1}">
															<a href="viewUser.do?currentPage=${1}"><img
																	src="resource/tabimages/first.gif" width="37"
																	height="15" />
															</a>
														</c:when>
														<c:otherwise>
															<a
																href="viewUser.do?currentPage=${sessionScope.curentPage-1}"><img
																	src="resource/tabimages/back.gif" width="43"
																	height="15" />
															</a>
															<%-- 需要在该JSP页面知道当前页 --%>
														</c:otherwise>
													</c:choose>

													<c:choose>
														<c:when
															test="${sessionScope.curentPage eq sessionScope.empSp.totalPage}">
															<a
																href="viewUser.do?currentPage=${sessionScope.curentPage}"><img
																	src="resource/tabimages/last.gif" width="37"
																	height="15" />
															</a>
														</c:when>
														<c:otherwise>
															<a
																href="viewUser.do?currentPage=${sessionScope.curentPage +1}"><img
																	src="resource/tabimages/next.gif" width="37"
																	height="15" />
															</a>
														</c:otherwise>
													</c:choose>
													--><!--<img src="resource/tabimages/first.gif" width="37"
														height="15" />
												</td>
												<td width="45">
													<img src="resource/tabimages/back.gif" width="43"
														height="15" />
												</td>
												<td width="45">
													<img src="resource/tabimages/next.gif" width="43"
														height="15" />
												</td>
												<td width="40">
													<img src="resource/tabimages/last.gif" width="37"
														height="15" />
												-->
												
												
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
