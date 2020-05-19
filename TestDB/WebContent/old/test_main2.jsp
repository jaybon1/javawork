<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	%>
<html>
<head>
<meta http-equiv="Content-Type" content = "text/html; charset = utf-8">
<title>Insert title here</title>
<script language = "JavaScript" type="text/JavaScript">
var xhrobject;

function creatXHR() {
    if (window.ActiveXObject) {
        xhrObject = new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) {
        xhrObject = new XMLHttpRequest();
    }
}

function getData() {

    var form_name = "form_main";
    var user_id = document.forms[form_name].elements["txt_user_id"].value;

    createXHR();

    var url = "./testFile.jsp";
    var reqparam = "user_id=" + user_id;
    xhrObject.onreadystatechange = resGetData;
    xhrObject.open("Post", url, "true");
    xhrObject.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
    xhrObject.send(reqparam);
}

function resGetData() {
    if (xhrObject.readyState == 4) {
        if (xhrObject.status == 200) {
            var result = xhrObject.responseText;
            var objRes = eval("(" + result + " )");
            var num = objRes.datas.length;
            var res = "<table cellpadding='3' cellspacing='0' border = '1' width='980'>";
            var resDiv = document.getElementById("div_res");

            if (num < 1) {
                res += "<tr><td width = '980' height = '100' align = 'center' style = 'font-size:50;'>검색결과가 없습니다.</td></tr>";
            } else {
                for (var i = 0; i < num; i++) {
                    var user_id = objRes.datas[i].ID;
                    var user_name = objRes.datas[i].NAME;
                    var user_phone = objRes.datas[i].PHONE;

                    res += "<tr>";
                    res += "<td width ='980' height= '100' align= 'center' style = 'font-size:50' bgcolor = #D0E6FC'>" + user_id + "</td>";
                    res += "</tr>";
                    res += "<tr>";
                    res += "<td width = '980' align = 'center' style = 'font-size:50' bgcolor='#FFFFCC'><br>" + user_name + "<br></td>";
                    res += "</tr>";
                    res += "<tr>";
                }
            }
            res += "</table>";
            resDiv.innerHTML = res;
        }
    }
}

function searchDate() {
    var form_name = "form_main";
    var user_id = document.forms[form_name].elements["txt_user_id"].value;

    if (user_id == "") {
        alert("user_id를 입력해주세여");
        document.forms[form_name].elements["txt_user_id"].focus();
        return;
    } else {
        getDate();
    }
}
</script>
</head>
<body>
<div id='div_main' width='980' height="300"
	style = "visibility: visible; position: absolute; left: 0px; top:115px; z-index:1;">
	<table border='0' width='980' cellpadding='0' cellspacing='0'>
	<form name = "form_main" onSubmit="javascript:return false;">
	<tr>
		<td width = '245'></td>
		<td width = '245' align='right'><input type='text' name='txt_user_id' size='10' value ='' maxlength='10' style='width: 240px; font-size:50; text-align: left;' onkeyup = 'javascript:searchData();'></td>
		<td width ='245'><img src='https://image.flaticon.com/icons/png/512/2919/2919803.png'  width='150' height ='70' onClick = 'javascript:searchData();'></td>
			<td width = '245'></td>
			</tr>
			</form>
			</table>
			</div>
		<div id = 'div_res' width='980'style="visibility:visible; position:absolute; left:0px top: 215px; z-index:1;">
		<table border='1' width='980' cellpadding='10' cellspacing='0'>
		<tr>
			<td align= 'center' style = 'width: 980px; font-size: 50' bgcolor=#FFFFCC>결과물</td>
			</tr>
			</table>
			 </div>
</body>
</html>