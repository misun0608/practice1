<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="com.spring.springmember.MemberVO"%>

<%
	if ((session.getAttribute("id") == null) || (!((String) session.getAttribute("id")).equals("admin"))) {
		out.println("<script>");
		out.println("location.href='./loginform.me'");
		out.println("</script>");
	}
	MemberVO memberVO = (MemberVO)request.getAttribute("memberVO");	// object형태라서 캐스팅해줘야함
		
%>
<!DOCTYPE html>
<html>
<head>
<title>회원관리 시스템 회원가입 페이지</title>
</head>
<body>
	<form action="./updateprocess.me" name="updateform" method="post">
		<center>
			<table border=1 width=300>
				<tr align=center>
					<td>아이디:</td>
					<td>${memberVO['id']}</td>
					<input type="hidden" name="id" value=${memberVO.getId()} />
				</tr>
				<tr align=center>
					<td>비밀번호:</td>
					<td><input type="password" name="password" value=${memberVO.getPassword()}></td>
				</tr>
				<tr align=center>
					<td>이름:</td>
					<td><input type="text" name="name" value=${memberVO.getName()}></td>
				</tr>
				<tr align=center>
					<td>나이:</td>
					<td><input type="text" name="age" value=${memberVO.getAge()}></td>
				</tr>
				<tr align=center>
					<td>성별:</td>
					<td>
						<%
							if (memberVO.getGender().equals("남")) {
						%> 
						<input type="radio" name="gender" value="여" />여자 
						<input type="radio" name="gender" value="남" checked />남자  
						<% 
						 	} else {
						 %> 
 						<input type="radio" name="gender" value="여" checked />여자 
 						<input type="radio" name="gender" value="남" />남자 
						<%
						 	}
						%>
					</td>
				</tr>
				<tr align=center>
					<td>이메일 주소:</td>
					<td><input type="text" name="email"
						value=${memberVO.getEmail()}></td>
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