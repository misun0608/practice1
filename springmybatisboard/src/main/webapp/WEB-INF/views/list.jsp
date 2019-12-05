<%@ page contentType= "text/html; charset=utf-8" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="com.spring.springmybatisboard.BoardVO" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%><%--사용자가 정의한 tag를 쓰기위해... --%>
<%@ include file = "color.jsp" %> 

<%
ArrayList<BoardVO> list = (ArrayList<BoardVO>)request.getAttribute("list");
int count = (int)request.getAttribute("count");
int currentPage = (int)request.getAttribute("currentPage");
int number = (int)request.getAttribute("number");
int pageCount = (int)request.getAttribute("pageCount");
int pageBlock = (int)request.getAttribute("pageBlock");
int startPage = (int)request.getAttribute("startPage");

SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm");
int i;
%>

<html>
	<head> <title> 게시판 </title> </head>

	<body bgcolor="<%=bodyback_c%>">
		<center> 
			<b> 글목록 </b> 
			<table width="600">
				<tr>
					<td align="right" bgcolor="<%=value_c%>">
						<a href="writeForm.bo"> 글쓰기 </a>	<!-- 원글쓰기 -->
					</td>
				</tr>
			</table> 
<%

			if (count  == 0 )
			{ 
%>
				<table width="600" border="1" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center"> 게시판에 저장된 글이 없습니다. </td>
					</tr>
				</table> 
<%
			}
			else
			{ 
%>
				<table border="1" width="600" cellpadding="0" cellspacing="0" align="center">
					<tr height="30" bgcolor="<%=value_c%>">
						<td align="center" width="50"> 번 호 </td>
						<td align="center" width="250"> 제 목 </td>
						<td align="center" width="100"> 작성자 </td>
						<td align="center" width="150"> 작성일 </td>
						<td align="center" width="50"> 조 회 </td>
					</tr> 
<%
					for(i=0; i<list.size(); i++)
					{
						BoardVO vo = list.get(i); 
%>
						<tr height="30">
							<td align="center" width="50"> <%=number%> </td><!-- 일련번호를 붙기위해 number사용 -->
							<td width="250"> 
<%
								if (vo.getRe_level() > 0 )
								{
									for(int level = 0; level < vo.getRe_level(); level++)
									{
%>
										&nbsp;
<%
									} 
%>
									<img src="${pageContext.request.contextPath}/resources/image/re.gif" />
									<!-- 8000/springboard/ 를 의미-->
<%
								}
								else
								{ 
%>
									&nbsp;
<%
								} 
%>
								<a href="content.bo?num=<%=vo.getNum()%>&pageNum=<%=currentPage%>&number=<%=number %>">
									<%=vo.getSubject()%> </a> 
<%
								if(vo.getReadcount() >= 10) 
								{ 
%>
									<img src="${pageContext.request.contextPath}/resources/image/hot.gif" /> 
									<!--  ${pageContext.request.contextPath} 는 /springboard를 의미
									<img src="${pageContext.request.contextPath}/resources/image/hot.gif" />
									<img src="/springboard/image/hot.gif" border="0" height="16"> 
									<img src="<spring:url value='/image/hot.gif' />" />
									-->
<%
								} 
%>
							</td>
							<td align="center" width="100">
							<a href="mailto:<%=vo.getEmail()%>"> <%=vo.getWriter()%> </a> 
							</td>

							<td align="center" width="150">
								<%=sdf.format(vo.getReg_date())%>
							</td>

							<td align="center" width="50"> <%=vo.getReadcount()%> </td>
						</tr> 
<%
						number--;
					} 
%>
				</table> 
				<br>
<%
			} 
			
			if(count > 0 )
			{
				if (startPage > 10)
				{ 
%>
					<a href="list.bo?pageNum= <%=startPage - 10 %>">[이전]</a> 
<%
				}

				for(i=startPage; (i<=startPage+9) && (i<=pageCount) ; i++) 
				{ 
%>
					<a href="list.bo?pageNum=<%=i%>">[<%=i%>]</a> 
<%
				}

				if(i < pageCount) 
				{ 
%>
					<a href="list.bo?pageNum=<%=startPage+10%>">[다음]</a> 
<%
				}
			} 
%>
		</center>
	</body>
</html>