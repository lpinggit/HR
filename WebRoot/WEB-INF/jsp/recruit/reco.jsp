<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	System.out.println("ddd: " + basePath);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>推荐页面</title>
		<base href="<%=basePath%>">
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
</style>
	<script type="text/javascript">
	function Recommender()
		{
			var str="";
			var sel=document.getElementsByName("selectReco");
			var hrId =document.getElementById("hrId").value;
			for(var i=0;i<sel.length;i++)
				{
				if(sel[i].checked==true)
					str+=sel[i].value+",";
				}
			if(str=="")
				{
				alert(hrId);
				alert("请至少选择一条记录");
				return false;
				}
			if(window.confirm("确定？"))
				{
				window.location.href="changeRecruitStatus.do?selectReco="+str+"&hrId="+hrId ;
				//+"&hrId ="+hrId
				}
		}
	
		function checkAll()
		{
			var select =document.getElementsByName("selectReco");
			if(document.getElementById("ifAll").checked==true)
				{
				for(var i=0;i<select.length;i++)
					{
					var check=select[i];
					check.checked=true;
					}
				}
			else 
				{
				for(var i=0;i<select.length;i++)
					{
					var check=select[i];
					check.checked=false;
					
					}
				}
	}
	
	function addReco(){
		window.location="addRecommender.do";
	}
	</script>
	</head>
	<body>
		<div onmousemove=site(event);>
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
															<span class="STYLE3">当前位置</span>：[推荐]
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
						<img src="resource/tabimages/tab_07.gif" width="16" height="30" />
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
											<td width="8%" height="22"
												background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
												<div align="center">
													<input type="checkbox" id="ifAll"  onclick="checkAll()"/><span class="STYLE1">全选</span>
												</div>
											</td>
											<td width="12%" height="22"
												background="resource/tabimages/bg.gif" bgcolor="#FFFFFF">
												<div align="center">
													<span class="STYLE1">被推荐人姓名</span>
												</div>
											</td>
											<td width="15%" height="22"
												background="resource/tabimages/bg.gif" bgcolor="#FFFFFF"
												class="STYLE1">
												<div align="center">
													被推荐职位
												</div>
											</td>
											<td width="10%" height="22"
												background="resource/tabimages/bg.gif" bgcolor="#FFFFFF"
												class="STYLE1">
												<div align="center">
													专	业
												</div>
											</td>
											<td width="10%" height="22"
												background="resource/tabimages/bg.gif" bgcolor="#FFFFFF"
												class="STYLE1">
												<div align="center">
													基本操作
												</div>
											</td>
										</tr>
										<c:forEach var="reco" items="${requestScope.recoList}">
											<tr>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<input type="checkbox" name="selectReco" value="${reco.recoId}" />
													</div>
												</td>
												<td height="10" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1">${reco.recoName}</span>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1">${reco.jobCate.cateName}</span>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE1"><a>${reco.majorCate.cateName}</a></span>
													</div>
												</td>
												<td height="20" bgcolor="#FFFFFF">
													<div align="center">
														<span class="STYLE4"><a href="changeRecruitStatus.do?selectReco=${reco.recoId }&hrId=${hrId}"> <img
																	src="resource/tabimages/edt.gif" width="16" height="16" />推荐</a>
														<input type="hidden" value="${hrId }" id="hrId"/>
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
			</table>
			<p align="center"> <input  type="button" value="批量推荐" onClick="Recommender();">&nbsp;&nbsp;<input type="button" value="打印信息" onclick="window.print()"/>&nbsp;&nbsp;<input type="button" value="添加推荐人" onclick="addReco();"/></p>
		</div>
	</body>
</html>
