<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<String> list_title = (ArrayList<String>) request.getAttribute("list_title");
	ArrayList<String> list_point = (ArrayList<String>) request.getAttribute("list_point");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>네이버 영화 랭킹 크롤링</title>
</head>
<body>
<br />
	<table border="0" align="center">
	<tr>
		<td><a href="crawl.do?sel=cnt">
			<img src="https://ssl.pstatic.net/imgmovie/2007/img/super_db/tab_movie_1_off.gif" /></a></td>
		<td><a href="crawl.do?sel=cur">
			<img src="https://ssl.pstatic.net/imgmovie/2007/img/super_db/tab_movie_2_off.gif" /></a></td>
		<td><a href="crawl.do?sel=pnt">
			<img src="https://ssl.pstatic.net/imgmovie/2007/img/super_db/tab_movie_3_off.gif" /></a></td>
	</tr>
	</table>

	<table border="0" align="center">
		<tr>
			<th><font size="2">순위</font></th>
		
			<th><font size="2">영화제목</font></th>
			<% if(!(list_point.get(0).equals(""))) {%>
			<th><font size="2">평점</font></th>
			<%} %>
		</tr>
				<%
		for(int i = 0; i < list_title.size(); i++) {
		%>
		<tr>
			<td><font size="2">
				<%=i + 1 %></font>
			</td>
			<td><font size="2">
				<%=list_title.get(i).toString() %></font>
			</td>
			<td><font size="2">
				<%=list_point.get(i).toString() %></font>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	</table>

</body>
</html>