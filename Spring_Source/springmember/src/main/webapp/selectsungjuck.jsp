<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="com.spring.springsungjuck.SungjuckVO" %>

<%
	SungjuckVO sungjuckVO = (SungjuckVO)request.getAttribute("sungjuckVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<%
	if(sungjuckVO != null){
	%>
		<table border=1 width=300>
			<tr align=center>
				<td>아이디</td>
				<td>${sungjuckVO.getId()}</td>
			</tr>
			<tr align=center>
				<td>학번</td>
				<td>${sungjuckVO.getHakbun()}</td>
			</tr>
			<tr align=center>
				<td>이름</td>
				<td>${sungjuckVO.getName()}</td>
			</tr>
			<tr align=center>
				<td>국어</td>
				<td>${sungjuckVO.getKor()}</td>
			</tr>
			<tr align=center>
				<td>영어</td>
				<td>${sungjuckVO.getEng()}</td>
			</tr>
			<tr align=center>
				<td>수학</td>
				<td>${sungjuckVO.getMath()}</td>
			</tr>
			<tr align=center>
				<td>총점</td>
				<td>${sungjuckVO.getTot()}</td>
			</tr>
			<tr align=center>
				<td>평균</td>
				<td>${sungjuckVO.getAvg()}</td>
			</tr>
			<tr align=center>
				<td>등급</td>
				<td>${sungjuckVO.getGrade()}</td>
			</tr>
	<%
	if(session.getAttribute("id").equals("admin")){
	%>
	<tr align=center>
		<td colspan=2><a href="./sungjucklist.sj">리스트로 돌아가기</a></td>
		</table>
		<%}
	}else{
	%>
	성적 입력 전입니다!
	<%
	}
	%>
	</center>
</body>
</html>