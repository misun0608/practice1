<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="com.spring.springsungjuk.SungjukVO" %>
 
<%
	if ((session.getAttribute("id")==null) || 
		  (!((String)session.getAttribute("id")).equals("admin"))) {
			out.println("<script>");
			out.println("location.href='loginform.me'");
			out.println("</script>");
	}

	SungjukVO vo = (SungjukVO)request.getAttribute("vo");
%>
<html>
<head>
<title>�������� �ý��� �����ڸ��(���� ���� �ϱ�)</title>
</head>
<body>
<form name="updateform" action="sungjukupdate.su" method="post">
<center>
<table border=1 width=300>
	<tr align=center>
		<td>���̵� : </td>
		<td><%=vo.getId() %></td>
		<input type="hidden" name="id" value="<%=vo.getId() %>">
	</tr>
	<tr align=center>
		<td>�й� : </td>
		<td><%=vo.getHakbun() %></td>
	</tr>
	<tr align=center>
		<td>�̸� : </td>
		<td><%=vo.getIrum() %></td>
	</tr>
	<tr align=center>
		<td>���� : </td>
		<td><input type="text" name="kor" value="<%=vo.getKor() %>"></td>
	</tr>
	<tr align=center>
		<td>���� : </td>
		<td><input type="text" name="eng" value="<%=vo.getEng() %>"></td>
	</tr>
	<tr align=center>
		<td>���� : </td>
		<td><input type="text" name="math" value="<%=vo.getMath() %>"></td>
	</tr>
	<tr>
		<td colspan="2" align=center>
			<a href="javascript:updateform.submit()">��������</a>&nbsp;&nbsp;
			<a href="javascript:updateform.reset()">�ٽ��ۼ�</a>
		</td>
	</tr>	
</table>
</center>
</body>
</html>
