
//�����������������ڴ����������ֵ
var site_x,site_y;

//��ȡ�����������е�λ��
function mousePos(e){
	var x,y;
	var e = e||window.event;
	return {
		x:e.clientX+document.body.scrollLeft+document.documentElement.scrollLeft,
		y:e.clientY+document.body.scrollTop+document.documentElement.scrollTop
	};
}

//��ֵ��������ʹ��
function site(e){
	site_x = mousePos(e).x;
	site_y = mousePos(e).y;
}

//�رգ��������Ϣ��ʾ����
function closeWindow(){
	if(document.getElementById('mesWindow')!=null){
		document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
	}
}

//ʹ��AJAX����ȡ���ݲ������ֲ�������ˢ����ʾ
function startAjax(id){
	var url = "showRecommenderDtls.do?recoId="+id;
	var method = "GET";
	request = createRequest();
	request.open(method, url, true);
	request.onreadystatechange = backFun;
	request.send(null);
}

//�ص�����
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
			
			//ʹ��JS����DOM��������Ϣ��ʾ�Ĵ���
			var infoW = document.createElement("div");
			infoW.id = "mesWindow";
			infoW.className="mesWindow";
			var strHTML = "<div id='mesWindowTop'><table width='100%' height='100%'><tr><td><font color=red>["+recoName+"]</font>�Ļ�����Ϣ</td><td style='width:1px;'><input type='button' onclick='closeWindow();' title='�رմ���' id='close' value='�ر�' /></td></tr></table></div><div id='mesWindowContent'><table><tr><td><�Ա�></td><td>"+sex+"</td><td rowspan=5><img width='100px' height='120px' src=\"http://localhost:8080/HR/pic/"+pic+"\"/></td></tr><tr><td><ѧλ></td><td>"+recoDegree+"</td></tr><tr><td><����></td><td>"+mailbox+"</td></tr><tr><td><�ֻ�����></td><td>"+phone+"</td></tr><tr><td><��ҵѧУ></td><td>"+graduatedFrom+"</td></tr></table><table width='100%' height='100%'><tr><td style='width:1px;'><רҵ����></td></tr><tr><td>"+skills+"</td></tr></table></div><div id='mesWindowBottom'></div>";
			infoW.innerHTML = strHTML;
			styleStr = "left:"+(site_x-400)+"px;top:"+(site_y-10)+"px;position:absolute;width:400px;";
			infoW.style.cssText = styleStr;
			document.body.appendChild(infoW);
		}
	}
}