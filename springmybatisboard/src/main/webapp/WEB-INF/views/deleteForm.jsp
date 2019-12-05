<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file = "color.jsp" %> 

<%
	int num = (int)request.getAttribute("num");
	int pageNum = (int)request.getAttribute("pageNum");
%>

<html>
	<head> 
		<title> 게시판 </title> 
		
		<script language="JavaScript"> 
			function deleteSave()
			{
				if(document.delForm.passwd.value == '')
				{
					alert("비밀번호를 입력하십시요.");
					document.delForm.passwd.focus();
					return false;
				}
				return true;
			} 
		</script>
	</head>

	<body bgcolor="<%=bodyback_c%>">
		<center>
			<b> 글삭제 </b> <br>
			<form method="post" name="delForm" action="deletePro.bo?pageNum=<%=pageNum%>" onsubmit="return deleteSave()">
			<table border="1" align="center" cellspacing="0" cellpadding="0" width="360">
				<tr height="30">
					<td align="center" bgcolor="<%=value_c%>">
						<b> 비밀번호를 입력해 주세요. </b>
					</td>
				</tr>
				<tr height="30">
					<td align="center"> 
						비밀번호 :
						<input type="password" name="passwd" size="8" maxlength="12">
						<input type="hidden" name="num" value="<%=num%>"> 
					</td>
				</tr>
				<tr height="30">
					<td align="center" bgcolor="<%=value_c%>">
						<input type="submit" value="글삭제">
						<input type="button" value="글목록" 
						onclick="document.location.href='list.bo?pageNum=<%=pageNum%>'">
					</td>
				</tr>
			</table>
			</form>
		</center>
	</body>
</html>
			