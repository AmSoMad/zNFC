<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.body{margin: auto;}
@import url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff'); 
@font-face { font-family: 'CookieRun-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
body, div, ol, li, from, filedset, legend, input{font-family: 'CookieRun-Regular'}
	.span {
	margin: :auto;
	border: outset;
	background:linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
	font-family: 'CookieRun-Regular';
	margin: auto;

}
</style>
<body style="width: 347px;" class="body">
<form action="create.bo" method="post">
<fieldset class="a">
<legend class="span">사용자 신규등록</legend>
NFC 카드 ID : <input type="text" name="nfcid"><br>
이&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;름 : <input type="text" name="name"><br>
성&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;별 : <select name="gender"><option value="남자">남자</option><option value="여자">여자</option></select><br>
직&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;급 : <input type="text" name="position"><br>
사&nbsp;&nbsp;원&nbsp;&nbsp;번&nbsp;&nbsp;호 : <input type="text" name="employeeNumber"><br>
<input type="submit" value="등록"><a href="index.jsp"><input type="button" value="메인"></a>
</fieldset>
</form>
</body>
</html>