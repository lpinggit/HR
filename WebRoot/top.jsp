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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title></title>
		<script type="text/javascript" src="<%=basePath%>resource/script/util.js"></script>
		<script type="text/javascript">
		//disptime();
		function disptime() {
			var time = new Date(); //获得当前时间
			var year = time.getFullYear();//获得年、月、日
			var month = time.getMonth() + 1;
			var day = time.getDate();
			var hour = time.getHours(); //获得小时、分钟、秒
			var minute = time.getMinutes();
			var second = time.getSeconds();
			if (minute < 10) //如果分钟只有1位，补0显示
				minute = "0" + minute;
			if (second < 10) //如果秒数只有1位，补0显示
				second = "0" + second;
			/*设置文本框的内容为当前时间*/
			document.getElementById("myclock").innerHTML = year + "年" + month + "月"
					+ day + "日   " + hour + ":" + minute + ":" + second;
			/*设置定时器每隔1秒（1000毫秒），调用函数disptime()执行，刷新时钟显示*/
			var myTime = setTimeout("disptime()", 1000);
		}
	
	</script>
	<script type="text/javascript" >
		function secondLoad(){
			showbar=setInterval("startAjax()",1000*60);
		}
		function startAjax(){
				var name = "sanbao";
				var url = "recommenderMessage.do?recoName="+name;
				var method = "GET";
				request = createRequest();
				request.open(method, url, true);
				request.onreadystatechange = backFun;
				request.send(null);
			}
	//回调函数
	function backFun(){
		if(request.readyState == 4){
			if(request.status == 200){
				var message = request.responseText;
				if(message =="true")
					{
						//showbar=setInterval("setbar()",100);
							document.getElementById("bar").innerHTML="最新公告：你有新的消息待处理！";
						
					}
					else {
					document.getElementById("bar").innerHTML="";
					}
			}
		}
	}
	
	</script>
<style type="text/css">
  body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image:url(resource/images/naozlp.gif);
	background-repeat:no-repeat;
}
#bar{display:block;font-family:Arial, Helvetica, sans-serif;font-size:12px;text-align:center;}
ul{list-style: none;}
.clear{display: block;overflow: hidden;}
a:link{color: #333;}
a:visited{color: #333;}
a:hover{color: red;}
a:active{color: red;}
a{text-decoration: none;}

.top{line-height: 34px;}
.top div{float: left;}
.top ul{float: left;margin-left:400px;}
.top ul li{float: left;margin-left: 8px;padding: 0 8px 0 16px;}
#gh span{background-color:rgb(67,67,210);color:white;font-size:15px;line-height: 40px;border-radius:5px;margin-left:9px;}
  </style>
</head>

<body onload="disptime();startAjax();secondLoad();">
   <div class="top clear">
				<div><span>欢迎您！${user.username}</span>&nbsp;<a href="userlogout.do" target="_top"> 退出</a></div>
				<ul class="clear">
					<li class="icon_01"><a href="tab.html" target="right">首页</a></li>
					<li class="icon_02"><a href="userpasswordUpdated.do" target="right">修改密码</a></li>
					<li class="icon_03"><a href="showUserDetails.do?empId=${user.empId }" target="right">个人信息查看及修改</a></li>
					<li class="icon_01"><span id="myclock"> </span></li>
				</ul>
			</div>
			
			<div id="gh">
			<span>功 &nbsp;能 &nbsp;菜 &nbsp;单</span>
			   <MARQUEE  style="FONT-WEIGHT: normal; FONT-SIZE: 12px;WIDTH:800px; LINE-HEIGHT: normal; FONT-STYLE: normal; HEIGHT: 15px; FONT-VARIANT: normal" scrollAmount=1 scrollDelay=30 direction=left 
								onMouseOver=this.stop()	onMouseOut=this.start()  >
						<a href="viewRecruitBar.do" target="right" onclick="startAjax();"><strong id="bar" ></strong></a>
						</MARQUEE>
			<div>
	</body>
</html>
