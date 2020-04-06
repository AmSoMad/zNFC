<%@page import="vo.SecurityBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.listBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	@SuppressWarnings("unchecked")
	ArrayList<listBean> arraylist=(ArrayList<listBean>)request.getAttribute("list");
	System.out.println(request.getAttribute("list"));  
	SecurityBean securityBean = new SecurityBean();
%>
<html>
<head>
<meta charset="UTF-8">
<title>관리자모드</title>
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
		document.getElementById("Times").innerHTML = 
			year + "-" + addzero(month) + "-" + addzero(date) + "&nbsp;" + hour + ":" + addzero(min) + ":" + addzero(sec);
		document.getElementById("Times2").innerHTML = 
			year + "-" + addzero(month) + "-" + addzero(date) + "&nbsp;" + hour + ":" + addzero(min)
	}
        // 1자리수의 숫자인 경우 앞에 0을 붙여준다.
	function addzero(num) {
		if(num < 10) { num = "0" + num; }
 		return num;
	}
</script>
<style type="text/css">
@import url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff'); 
@font-face { font-family: 'CookieRun-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
body, div, ol, li, from, filedset, legend, input{font-family: 'CookieRun-Regular'}
.body{margin: auto;}
.type {
	    border-collapse: collapse;
	    text-align: left;
	    line-height: 1.5;
	    border-left: 1px solid #ccc;
	    margin: 20px 10px;

	}
	
	.type thead th {
	    padding: 10px;
	    font-weight: bold;
	    border-top: 1px solid #ccc;
	    border-right: 1px solid #ccc;
	    border-bottom: 2px solid #c00;
	    background: #dcdcd1;
	
	}
	.type tbody th {
	    width: 150px;
	    padding: 10px;
	    font-weight: bold;
	    vertical-align: top;
	    border-right: 1px solid #ccc;
	    border-bottom: 1px solid #ccc;
	    background: #ececec;
	  
	}
	.type td {
	    width: 350px;
	    padding: 10px;
	    vertical-align: top;
	    border-right: 1px solid #ccc;
	    border-bottom: 1px solid #ccc;
	 
	}
</style>

</head>
<body class="body" style="width: 700px;">
<% if(request.getAttribute("Commuting") != null){
		if(request.getAttribute("Commuting").equals("A_adminQ")){
							if(request.getAttribute("security").equals(securityBean.getVar2())){
							%>
							<script type="text/javascript">
							alert("정상접속되었습니다.")
							</script>
							<%}else{%>
								<script type="text/javascript">
									alert("비정상적 접근입니다.")
									document.location.href="index.jsp" ;
							</script>
							<%} %>
			<%}else if(request.getAttribute("Commuting").equals("admin_leave")){ %>
				<script type="text/javascript">
				alert("<%= request.getAttribute("nfcid")+" 카드 "+request.getAttribute("name") + " 님을 "+ request.getAttribute("time") + "으로 강제퇴근처리 됬습니다."  %>")					
				</script>
			<%}else{%>
			<script type="text/javascript">
				alert("비정상적 접근입니다.")
				document.location.href="index.jsp" ;
			</script>
			<%} %>
	<%}else{   %>
	<script type="text/javascript">
			alert("비정상적 접근입니다.")
			document.location.href="index.jsp" ;
	</script>
	<%}%>

<form action="admin_img_add.bo" enctype="multipart/form-data">
	
<input type="submit" value="사진첨부">
</form>


<form action="A_admin.bo" method="post" style="width: 700px;">
<fieldset>
<legend><span id="Times"></span></legend>
NFC 아이디 : <input type="text" name="nfcid" value="<%=request.getAttribute("nfcid") %>"><br>
<input type="submit" value="조회">
<a href="index.jsp"><input type="button" value="메인"></a>
</fieldset>
</form><br>
<table class="type">
<thead>
<tr><th>NFC아이디</th><th>날짜</th><th>출근</th><th>퇴근</th><th>근무시간</th></thead>
<% 
	for(int i = 0; i < arraylist.size(); i++){%>
	<tr><td>
		<%= arraylist.get(i).getNfcid() %></td>
		<td><%= arraylist.get(i).getDay() %> </td>
		<td><%= arraylist.get(i).getAttend() %> </td>
		<td><%= arraylist.get(i).getLeave_Work() %> 
		<% if(arraylist.get(i).getLeave_Work().equals("")){%>
			<form action="Admin_leave_Work.bo">
			<input type="hidden" name="nfcid" value="<%=arraylist.get(i).getNfcid() %>">
			<input type="submit" value="강제퇴근처리">
			</form>
			<%} %>
		</td>
		<td><%= arraylist.get(i).getWorking() %> </td>
	</tr>	
	<% }%>	
</table>
</body>
</html>