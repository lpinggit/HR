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
		<link href="<%=basePath%>resource/css/Calendar.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/fckeditor-replace-textarea.js"></script>
		<script type="text/javascript" src="<%=basePath%>resource/script/jquery-1.3.2.min.js" ></script>
		<script type="text/javascript" src="<%=basePath%>resource/script/email.js"></script>
		<style type="text/css">
		.STYLE3 {
			font-size: 12px;
			font-weight: bold;
		}
		
		.STYLE1 {
			font-size: 12px
		}
		#validEmail
		{
			margin-top: 4px;
			margin-left: 9px;
			position: absolute;
			width: 16px;
			height: 16px;
		}
		
		.text
		{
			font-family: Arial, Tahoma, Helvetica;
		}
	</style>
	<script language="javascript">
		//下面的代码段如果你页面里有，可以去掉
		var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
		function $(objID) {
			return document.getElementById(objID);
		}
	</script>
		</head>
		<body>
	<script type="text/javascript" src="<%=basePath%>resource/script/calendar.js" charset="gbk"></script>
	<table width="79%" border="0" cellspacing="0" cellpadding="0" align="center">
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
														<span class="STYLE3">当前位置</span>：[被推荐人管理]-[添加被推荐人]
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
		</table>


		<form action="saveRecommender.do" enctype="multipart/form-data"
			method="post">
			<div>
				<table cellspacing="0" border=1 style="BORDER-COLLAPSE: collapse"
					bordercolor="#72d3d7" align="center">

					<tr>
						<td>
							推荐人：
						</td>
						<td>
							<input name="recoId" type="text" value="${user.username}"
									readonly="readonly"/>
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
									<option value="${job.key}">
										${job.value}
									</option>
								</c:forEach>
							</select>
						</td>
						<td>
							专业：
						</td>
						<td>
							<select name="major">
								<c:forEach var="major" items="${majorMap}">
									<option value="${major.key}">
										${major.value}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							被推荐人性别：
						</td>
						<td>
							<input type="radio" name="sex" value="男" id="radio"
								checked="checked" />
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
								<option value="学士">
									学士
								</option>
								<option value="硕士">
									硕士
								</option>
								<option value="博士">
									博士
								</option>
								<option value="博士后">
									博士后
								</option>
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
							是否再读
						</td>
						<td>
							<input type="radio" name="isGraguated" value="是" id="radio" />
							是
							<input type="radio" name="isGraguated" value="否" id="radio"
								checked="checked" />
							否
						</td>
						<td>
							毕业时间：
						</td>
						<td>
							<input name="graduatedDate" type="text"  value="2013-10-19" size="14"
		                           readonly onclick="showcalendar(event,this);"
		                           onfocus="showcalendar(event, this);if(this.value=='0000-00-00')this.value=''"/>
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
							<input name="mailbox" type="text"  id="validate" width="30" ><span id="validEmail"></span>
						</td>
					</tr>
					<tr>
						<td>
							简历：
						</td>
						<td>
							<input type="file" name="resume" />
						</td>
						<td>
							推荐:
						</td>
						<td>
							<input type="submit" value="点击添加&gt;&gt;" />
						</td>
					</tr>
					<tr>
						<td>
							专业技能：
						</td>
						<td colspan="3">
							<textarea name="skills" cols="90" rows="8"></textarea>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>

