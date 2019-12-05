<%@ page contentType = "text/html; charset=utf-8"%>
<%@ page import = "com.spring.springmybatisboard.BoardVO" %>
<%@ include file = "color.jsp" %>

<html>
	<head> <title> 게시판 </title> </head> 

<%
	BoardVO vo = (BoardVO)request.getAttribute("boardvo");
	int num = Integer.parseInt(request.getParameter("num"));	// 위에 boardvo에 들어있는 num과 같은 num
	System.out.println("1111:num=" + num);
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
%>

	<body bgcolor="<%=bodyback_c%>">
		<center> <b> 글수정 </b> <br>
			<form method="post" name="writeform" action="updatePro.bo?pageNum=<%=pageNum%>" >
				<table width="400" border="1" cellspacing="0" cellpadding="0" bgcolor="<%=bodyback_c%>" align="center">
					<tr>
						<td width="70" bgcolor="<%=value_c%>" align="center"> 이 름 </td>
						<td align="left" width="330">
							<input type="text" size="10" maxlength="10" name="writer" value="<%=vo.getWriter()%>">
							<input type="hidden" name="num" value="<%=vo.getNum()%>">	<!-- 글 번호는 수정할 화면에 뿌려줄 필요없지만 수정에 필요하기때문에 hidden -->
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c%>" align="center"> 제 목 </td>
						<td align="left" width="330">
							<input type="text" size="40" maxlength="50" name="subject" value="<%=vo.getSubject()%>">
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c%>" align="center"> Email </td>
						<td align="left" width="330">
							<input type="text" size="40" maxlength="30" name="email" value="<%=vo.getEmail()%>">
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c%>" align="center"> 내 용 </td>
						<td align="left" width="330">
							<textarea name="content" rows="13" cols="40"><%=vo.getContent()%></textarea>
						</td>
					</tr>
					<tr>
						<td width="70" bgcolor="<%=value_c%>" align="center"> 비밀번호 </td>
						<td align="left" width="330">
							<input type="password" size="8" maxlength="12" name="passwd">
						</td>
					</tr>
					<tr>
						<td colspan="2" bgcolor="<%=value_c%>" align="center">
							<input type="submit" value="글수정">
							<input type="reset" value="다시작성">
							<input type="button" value="목록보기" onclick="document.location.href='list.bo?pageNum=<%=pageNum%>'">
						</td>
					</tr>
				</table>
			</form>
		</center>
	</body> 
</html>
