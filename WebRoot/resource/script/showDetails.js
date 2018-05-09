
//声明两个变量，用于存放鼠标的坐标值
var site_x,site_y;

//获取鼠标在浏览器中的位置
function mousePos(e){
	var x,y;
	var e = e||window.event;
	return {
		x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
	};
}

//赋值，给下面使用
function site(e){
	site_x = mousePos(e).x;
	site_y = mousePos(e).y;
}

//关闭（清除）信息显示窗口
function closeWindow(){
	if(document.getElementById('mesWindow')!=null){
		document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
	}
}

//使用AJAX来获取数据并且做局部的数据刷新显示
function startAjax(id){
	var url = "showRecommenderDtls.do?recoId="+id;
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
			var recoName,mailbox,graduatedFrom,phone,sex,recoDegree,skills,pic;
			var gson_code = eval("("+request.responseText+")");
			recoName = gson_code["recoName"];
			mailbox = gson_code["mail"];
			graduatedFrom = gson_code["graduatedFrom"];
			phone =gson_code["phone"];
			sex  = gson_code["sex"];
			recoDegree = gson_code["recoDegree"];
			skills	= gson_code["skills"];
			pic = gson_code["pic"];
			
			//使用JS操作DOM来创建信息显示的窗口
			var infoW = document.createElement("div");
			infoW.id = "mesWindow";
			infoW.className="mesWindow";
			var strHTML = "<div id='mesWindowTop'><table width='100%' height='100%'><tr><td><font color=red>["+recoName+"]</font>的基本信息</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='关闭窗口' id='close' value='关闭' /></td></tr></table></div><div id='mesWindowContent'><table><tr><td><性别></td><td>"+sex+"</td><td rowspan=5><img width='100px' height='120px' src=\"http://localhost:8080/HR/pic/"+pic+"\"/></td></tr><tr><td><学位></td><td>"+recoDegree+"</td></tr><tr><td><邮箱></td><td>"+mailbox+"</td></tr><tr><td><手机号码></td><td>"+phone+"</td></tr><tr><td><毕业学校></td><td>"+graduatedFrom+"</td></tr></table><table width='100%' height='100%'><tr><td style='width:1px;'><专业技能></td></tr><tr><td>"+skills+"</td></tr></table></div><div id='mesWindowBottom'></div>";
			infoW.innerHTML = strHTML;
			styleStr = "left:"+(site_x-400)+"px;top:"+(site_y-10)+"px;position:absolute;width:400px;";
			infoW.style.cssText = styleStr;
			document.body.appendChild(infoW);
		}
	}
}