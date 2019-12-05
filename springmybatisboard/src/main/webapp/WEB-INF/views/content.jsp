<%@ page contentType ="text/html; charset=utf-8"%>
<%@ page import = "com.spring.springmybatisboard.BoardVO" %>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ include file = "color.jsp" %>

<html>
	<head> 	<title> 게시판 </title> </head> 
<%
	BoardVO vo = (BoardVO)request.getAttribute("boardvo");
	int pageNum = (int)request.getAttribute("pageNum");
	int number = (int)request.getAttribute("number");

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	try
	{
		int ref = vo.getRef();
		int re_step = vo.getRe_step();
		int re_level = vo.getRe_level(); 
%>

		<body bgcolor="<%=bodyback_c%>">
			<center>
				<b> 글 내용 보기 </b> <br>

				<form>
					<table width="555" border="1" cellspacing="0" cellpadding="0" 
						bgcolor="<%=bodyback_c%>" align="center">
						<tr height="30" align="center" width="125">
							<td bgcolor="<%=value_c%>"> 글 번호 </td>
							<td> <%=number%> </td>
							<td bgcolor="<%=value_c%>"> 조회수 </td>
							<td width="180"> <%=vo.getReadcount()%> </td>
						</tr>
						<tr height="30" align="center"  width="125">
							<td bgcolor="<%=value_c%>"> 작성자 </td>
							<td> <%=vo.getWriter()%> </td>
							<td bgcolor="<%=value_c%>"> 작성일 </td>
							<td width="180"> <%=sdf.format(vo.getReg_date())%> </td>
						</tr>
						<tr height="30" >
							<td align="center" width="125" bgcolor="<%=value_c%>"> 글제목 </td>
							<td align="left" colspan="3">
								&nbsp;<%=vo.getSubject()%>
							</td>
						</tr>
						<tr>
							<td align="center" width="125" bgcolor="<%=value_c%>"> 글내용 </td>
							<td align="left" colspan="3">
								<pre> <%=vo.getContent()%> </pre> 
							</td>
						</tr>
						<tr height="30">
							<td colspan="4" bgcolor="<%=value_c%>" align="center">
								<input type="button" value="글수정" onclick="document.location.href='updateForm.bo?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'"><!-- pageNum을 주는 이유는 수정/삭제 후에 어떤 페이지로 가야할지 알아야하기 때문에 -->
								&nbsp;&nbsp;
								<input type="button" value="글삭제" onclick="document.location.href='deleteForm.bo?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
								&nbsp;&nbsp;
								<input type="button" value="답글쓰기" onclick="document.location.href='writeForm.bo?num=<%=vo.getNum()%>&ref=<%=ref%>&re_step=<%=re_step%>&re_level=<%=re_level%>'"><!-- 원글에 대한 ref / re_step/ re_level -->
								&nbsp;&nbsp;
								<input type="button" value="글목록" onclick="document.location.href='list.bo?pageNum=<%=pageNum%>'">
							</td>
						</tr>
					</table>
				</form>
			</center>
		</body> 
<%
	}
	catch(Exception ex) {} 
%>
</html>
