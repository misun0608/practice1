<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.springform.DeptVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	DeptVO deptvo = (DeptVO)request.getAttribute("deptvo");
%>
	<table border="1" align="center">
		<tr>
			<td>부서번호</td>
			<td>&nbsp;<%=deptvo.getDeptno() %></td>
			<%--${deptvo{['deptno']} --%>
		</tr>
		<tr>
			<td>부서명</td>
			<td><%=deptvo.getDname() %></td>
		</tr>
		<tr>
			<td>위치</td>
			<td><%=deptvo.getLoc() %></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><a href="selectProcess.me">사원정보</a></td>
		</tr>
	</table>
</body>
</html>
<%-- 진이코드
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.springform.DeptVO" %>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>Department Information</title>
</head>
<body>
<%
   DeptVO dept_info = (DeptVO)request.getAttribute("dept_info");
%>
   <br><br>
   <table border="1" align="center">
      <tr>
         <td width="100">부서번호</td>
         <td width="120"><%=dept_info.getDeptno() %></td>
      </tr>
      <tr>
         <td width="100">부서명</td>
         <td width="120"><%=dept_info.getDname() %></td>
      </tr>
      <tr>
         <td width="100">위치</td>
         <td width="120"><%=dept_info.getLoc() %></td>
      </tr>
      <tr>
         <td colspan="2" align="center"><a href="selectProcess.me">사원정보</a></td>
      </tr>
   </table>
</body>
</html>
--%>