<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.spring.springmember.MemberVO" %>
<%
MemberVO vo = (MemberVO)request.getAttribute("vo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="updateform" action="./memberupdate.me" method="post">
<center>
<table border=1>
	<tr>
		<td colspan="2" align=center>
			<b><font size=5>회원 수정 페이지</font></b>
		</td>
	</tr>
	<tr><td>아이디 : </td><td><%=vo.getId() %></td></tr>
			<input type="hidden" name="id" value="<%=vo.getId() %>" />
	<tr><td>비밀번호 : </td><td><input type="password" name="password" value="<%=vo.getPassword() %>" /></td></tr>
	<tr><td>이름 : </td><td><input type="text" name="name" value="<%=vo.getName() %>" /></td></tr>
	<tr><td>나이 : </td><td><input type="text" name="age" maxlength=2 size=5 value="<%=vo.getAge() %>" /></td></tr>
	<tr>
		<td>성별 : </td>
		<td>
		<%
			if (vo.getGender().equals("남")){
		%>
			<input type="radio" name="gender" value="남" checked/>남자
			<input type="radio" name="gender" value="여"/>여자
		<%
			}else{
		%>
			<input type="radio" name="gender" value="남"/>남자
			<input type="radio" name="gender" value="여" checked/>여자
		<%
			}	
		%>
		</td>
	</tr>
	<tr><td>이메일 주소 : </td><td><input type="text" name="email" size=30 value="<%=vo.getEmail() %>" /></td></tr>
	<tr>
		<td colspan="2" align=center>
			<a href="javascript:updateform.submit()">수정</a>&nbsp;&nbsp;
			<a href="javascript:updateform.reset()">다시작성</a>
		</td>
	</tr>
</table>
</center>
</form>
</body>
</html>