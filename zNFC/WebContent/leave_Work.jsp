<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.text.SimpleDateFormat" %>
	<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<%	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	Date time = new Date();
	String currentDatetime = format.format(time);
%>

<html>
<head>
<meta charset="UTF-8">
<title>퇴근</title>

<style type="text/css">
.body{margin: auto;}
@import url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff'); 
@font-face { font-family: 'CookieRun-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
body, div, ol, li, from, filedset, legend, input{font-family: 'CookieRun-Regular'}
</style>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        // 시간을 딜레이 없이 나타내기위한 선 실행
        realTimer();
        // 이후 0.5초에 한번씩 시간을 갱신한다.
        setInterval(realTimer, 500);
    });
    // 시간을 출력
    function realTimer() {
		const nowDate = new Date();
		const year = nowDate.getFullYear();
		const month= nowDate.getMonth() + 1;
		const date = nowDate.getDate();
		const hour = nowDate.getHours();
		const min = nowDate.getMinutes();
		const sec = nowDate.getSeconds();
		document.getElementById("nowTimes").innerHTML = 
                  year + "-" + addzero(month) + "-" + addzero(date) + "&nbsp;" + hour + ":" + addzero(min) + ":" + addzero(sec);
	}
        // 1자리수의 숫자인 경우 앞에 0을 붙여준다.
	function addzero(num) {
		if(num < 10) { num = "0" + num; }
 		return num;
	}
</script>

</head>
<body class="body" style="width: 347px;">
<form action="A_leave_Work.bo" method="get">
<fieldset>
<legend>퇴근등록 <span id="nowTimes"></span></legend>
NFC 아이디 : <input type="text" name="nfcid"><br>
<input type="hidden" value="<%=currentDatetime %>" name="time">
<input type="submit" value="퇴근"><a href="index.jsp"><input type="button" value="메인"></a>
</fieldset>
</form>
</body>
</html>