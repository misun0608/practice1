<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.sql.*" %>
 <%@page import="javax.sql.*" %>
 <%@page import="javax.naming.*" %>
 <%@page import="com.spring.springsungjuck.SungjuckVO"%>

<%
	
%>
<!DOCTYPE html>
<html>
<head>
<title>회원관리 시스템 회원가입 페이지</title>
</head>
<body>
	<form action="./updatesungjuck.sj" name="updateform" method="post">
		<center>
			<table border=1 width=400>
				<tr>
					<td colspan="2" align=center><b><font size=5>회원가입
								페이지</font></b></td>
				</tr>
				<tr>
					<td>아이디 :</td>
					<td>${sungjuckVO.getId() }</td>
					<input type="hidden" name="id" value=${sungjuckVO.getId() } />
					<!-- 아이디는 수정안되지만 나중에 값을 넘겨줘야하기때문에 -->
				</tr>
				<tr>
					<td>학번 :</td>
					<td><input type="text" name="hakbun"
						value=${sungjuckVO.getHakbun() } /></td>
				</tr>
				<tr>
					<td>이름 :</td>
					<td><input type="text" name="name"
						value=${sungjuckVO.getName() } /></td>
				</tr>
				<tr>
					<td>국어 :</td>
					<td><input type="text" name="kor" maxlength=2 size=5
						value=${sungjuckVO.getKor() } /></td>
				</tr>
				<tr>
					<td>영어 :</td>
					<td><input type="text" name="eng" maxlength=2 size=5
						value=${sungjuckVO.getEng() } /></td>
				</tr>
				<tr>
					<td>수학 :</td>
					<td><input type="text" name="math" maxlength=2 size=5
						value=${sungjuckVO.getMath() } /></td>
				</tr>
				
				<tr>
					<td colspan="2" align=center><a
						href="javascript:updateform.submit()">수정</a>&nbsp;&nbsp; <a
						href="javascript:updateform.reset()">다시 작성</a></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>