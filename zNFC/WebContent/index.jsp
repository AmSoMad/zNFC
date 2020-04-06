
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작페이지</title>
<%	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date time = new Date();
	String currentDatetime = format.format(time);
%>

<style type="text/css">
@import url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff'); 
@font-face { font-family: 'CookieRun-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }

legend{ margin:auto; padding:0}
.a {
	background-image: url("img/1111.jpg");
	background-size: contain;
}

	
	.btn-1{
	overflow: hidden;
	-webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1); 
    -o-transform:scale(1);  
    transform:scale(1);
    -webkit-transition:.5s;
    -moz-transition:.5s;
    -ms-transition:.5s;
    -o-transition:.5s;
    transition:.5s;
	position: relative;
    width: 7em;
    height: 7em;
    margin-bottom: 2em;
    border-radius: 70%;
    color: #252537;
    font-family: 'CookieRun-Regular'; 
    font-weight: normal; 
	font-style: normal;
   
}
	.btn-1:hover{
	background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	font-family: 'CookieRun-Regular'; 
	font-weight: normal; 
	font-style: normal;
	transform:scale(1.2);
}
	.btn-2{
	position:absolute;
    width: 7em;
    height: 7em;
    margin-bottom: 2em;
    border-radius: 70%;
    background-image: url("img/bit1.jpg");
    background-size: cover;
    overflow: hidden;
    -webkit-transform:scale(1);
    -moz-transform:scale(1);
    -ms-transform:scale(1);
    transform:scale(1);
    -webkit-transition:.5s;
    -moz-transition:.5s;
    -ms-transition:.5s;
    -o-transition:.5s;
    transition:.5s;
 
    
}
	.btn-2:hover{
	background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	background-image: url("img/bit2.jpg");
    background-size: cover;
    transform:scale(1.2);
    -webkit-transform:scale(1.2);
    -moz-transform:scale(1.2);
    -ms-transform:scale(1.2);   
    -o-transform:scale(1.2);
}
	.btn-2:active{
	background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	background-image: url("img/bit3.jpg");
    background-size: cover;
    transform:scale(1.4);
}
	.span {
	margin: :auto;
	border: outset;
	background:linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	font-family: 'CookieRun-Regular';

}
	.f {font-family: 'CookieRun-Regular';
}
	#sound{
	position: absolute;
	background-color: black;
	visibility: visible; 
	
	 
	}
</style>
<!-- 시간 실시간 -->
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {setInterval(realTimer, 500);});
     // 시간을 딜레이 없이 나타내기위한 선 실행
       // DOMContentLoaded는 문서에서 스크립트 작업을 할 수 있을 때 실행되기 때문에 이미지 다운로드를 기다릴 필요가 없다. 
    // 시간을 출력
    function realTimer() {
		const nowDate = new Date();
		const year = nowDate.getFullYear();
		const month= nowDate.getMonth() + 1;
		const date = nowDate.getDate();
		const hour = nowDate.getHours();
		const min = nowDate.getMinutes();
		const sec = nowDate.getSeconds();
		document.getElementById("Times").innerHTML = 
                  year + "-" + addzero(month) + "-" + addzero(date) + "&nbsp;" + hour + ":" + addzero(min) + ":" + addzero(sec);
	}
        // 1자리수의 숫자인 경우 앞에 0을 붙여준다.
	function addzero(num) {
		if(num < 10) { num = "0" + num; }
 		return num;
	}
</script>
<!-- //시간 실시간 -->
</head>


<body>
<input type="hidden" class="sound">

<fieldset class="a" style="width: 347px; margin: auto;">
<legend><span id="Times" class="span" style="margin: auto;"></span></legend>
<form method="post" style="width: 347px; height: 530px; margin: auto; ">
<br>
			<a href="createView.bo"><input type="button" value="신규등록" name="create_Attend" class="btn-1" size="30px;"></a> &nbsp;&nbsp;
			<a href="admin.bo"><input type="button" value="관리자모드" name="admin_Mode" class="btn-1"></a> &nbsp;&nbsp;
			<a href="list.bo"><input type="button" value="조회" name="list" class="btn-1"></a><br><br>
			<a href="attendance.bo"><input type="button" value="출근" name="attendance" class="btn-1"></a> &nbsp;&nbsp; 
			<a href="leave_Work.bo"><input type="button" value="퇴근" name="leave_Work" class="btn-1"></a> &nbsp;&nbsp;
									<input type="button" value="" name="bit" class="btn-2"><a href="index.jsp"></a><br>
</form>
</fieldset>
</body>
</html>