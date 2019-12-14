<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file ="color.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
</head>
<body>
<center>

		<body bgcolor="<%=bodyback_c %>">
			<b> 글쓰기 </b><br>
			<form method="post" name="writeform" action="./writeprocess.do" enctype="multipart/form-data">
				
				<table width="400" border="1" cellspacing="0" cellpadding="0" 
					bgcolor="<%=bodyback_c %>" align="center">
					<tr>
						<td align="right" colspan="2" bgcolor="<%=value_c %>">
							<a href="./list.do"> 글목록 </a>
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center" > 이   름  </td>
						<td width="330"><input type="text" size="10" maxlength="10" name="writer"></td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center"> 제   목 </td>
						<td width="330">
<%
							if(request.getParameter("num") == null) {
%>	
								<input type="text" size="40" maxlength="50" name="subject">
<%
							} else {	
%>
								<input type="text" size="40" maxlength="50" name="subject" value="[답변]">
<%
							}
%>
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center"> Email </td>
						<td width="330"><input type="text" size="40" maxlength="30" name="email"></td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center"> 내   용 </td>
						<td width="330"><textarea name="content" rows="13" cols="40"></textarea></td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center" value="파일첨부"> 파일첨부 </td>
						<td width="330"><input type="file" name="file"></td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c %>" align="center"> 비밀번호 </td>
						<td width="330"><input type="password" size="8" maxlength="12" name="pass"></td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="<%=value_c %>" align="center">
							<input type="submit" value="글쓰기">
							<input type="reset" value="다시작성">
							<input type="button" value="목록보기" Onclick="javascript:history.go(-1)">
						</td>
					</tr>
				</table>
			</form>
		</body>

</html>