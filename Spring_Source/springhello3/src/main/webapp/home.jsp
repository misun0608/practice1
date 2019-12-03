<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
<title>Home</title>
</head>
<body>
<h1>
	Hello world! 결제했습니다... ^^>.. 노트북이요... ㅎㅎ 부럽죠..? ^^  아닙니다ㅜ ㅎ 여덟 손가락ㅇ,ㅡ로도 타자 가능합니다 ㅎㅎㅎ ㅎㅎ ㅎ ㅎ 
</h1>
<p> The time on the server is <%=request.getAttribute("serverTime") %>. </p><!-- 모델 객체에 전달된 데이터를 받아오는 두가지 방법 / request를 통해 읽어올 수 있다 /  달러 표시...도 가능 / 주석에 달러표시 괄호쓰면 오류나니 조심ㅠㅠ  -->
<p> The time on the server is ${serverTime}. </p>	<!-- 위 아래 둘다 같은 결과 / 두가지 방법 모두 기억해놓기-->
</body>
</html>