<%@page import="vo.SecurityBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%SecurityBean securityBean = new SecurityBean(); %>
<html>
<head>
<meta charset="UTF-8">
<title>결과</title>
<style type="text/css">
@import url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff'); 
@font-face { font-family: 'CookieRun-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/CookieRun-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
body, div, ol, li, from, filedset, legend, input{font-family: 'CookieRun-Regular'}
</style>

</head>
<body>

	<%if(request.getAttribute("Commuting").equals("attend")){%>
	<script type="text/javascript">
		alert("<%= request.getAttribute("nfcid")+" 카드 소유자 "+request.getAttribute("name") + " 님 "+ request.getAttribute("time") + "에 출근하셨습니다."  %>")
		document.location.href="index.jsp" ;
	</script>
	<%}else if(request.getAttribute("Commuting").equals("leave")){%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<script type="text/javascript">
		alert("<%= request.getAttribute("nfcid")+" 카드 소유자 "+request.getAttribute("name") + " 님 "+ request.getAttribute("time") + "에 퇴근하셨습니다. 수고하셨습니다."  %>")
		document.location.href="index.jsp" ;
		</script>
	<%}else if(request.getAttribute("Commuting").equals("empty")){%>
	<script type="text/javascript">
		alert("<%= request.getAttribute("nfcid") + "은 없는 카드정보 입니다."%>")
		document.location.href="index.jsp" ;
		</script>
	<%}else if(request.getAttribute("Commuting").equals("already")){%>
	<script type="text/javascript">
		alert("<%= request.getAttribute("nfcid") + "  이미 출근처리되었습니다."%>")
		document.location.href="index.jsp" ;
		</script>
	<%}else if(request.getAttribute("Commuting").equals("already2")){%>
	<script type="text/javascript">
		alert("<%= request.getAttribute("nfcid") + "  이미 퇴근처리되었습니다."%>")
		document.location.href="index.jsp" ;
		</script>
	<%}else if(request.getAttribute("Commuting").equals("create")){%>
	<script type="text/javascript">
		alert("<%=request.getAttribute("name") + " 고객님은" + request.getAttribute("nfcid")+ " 카드번호로 신규등록되었습니다."%>")
		document.location.href="index.jsp" ;
	</script>
	<%}else if(request.getAttribute("Commuting").equals("adminQ")){%>
	<script type="text/javascript">

		userInput = prompt("관리자 비밀번호를 입력해주세요."+"");
		if(userInput == <%=request.getAttribute("pass") %>){
			document.location.href="A_admin.bo" ;
		}else{
			alert("비밀번호가 틀렸습니다. 메인으로 돌아갑니다.")
			document.location.href="index.jsp" ;
		}
	</script>
<%	}else if(request.getAttribute("Commuting").equals("error")){%>
	<script type="text/javascript">

		
			alert("잘못된 접근방식 입니다.")
			document.location.href="index.jsp" ;
		
	</script>
<%	}%>%>




</body>
</html>