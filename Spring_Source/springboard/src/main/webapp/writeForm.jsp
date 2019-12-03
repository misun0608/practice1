<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "color.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>게시판</title>
</head>
<%
   int num = 0, ref = 1, re_step = 0, re_level = 0;
	// 글쓰기는 두가지루트를 통해서 올 수 있음 / 1) 원글쓰기 2) 답글쓰기
   try 
   {
      if(request.getParameter("num") != null) // 답글 쓰기
      {
         num = Integer.parseInt(request.getParameter("num"));	// num : boardtable의 num column
         ref = Integer.parseInt(request.getParameter("ref"));	// ref : 원글과 댓글을 하나로 묶기위한 용도
         re_step = Integer.parseInt(request.getParameter("re_step"));	// 댓글의 순서
         re_level = Integer.parseInt(request.getParameter("re_level"));	// 댓글 들여쓰기
      }
%>
<body bgcolor ="<%=bodyback_c%>">
   <center><b>글쓰기</b> <b>
   <form method = "post" name = "writeform" action = "./writeprocess.bo">
      <input type = "hidden" name = "num" value = "<%=num %>" />	<!-- 원글이면 0이 넘어옴 / 답글이면 원글에 대한 num -->
      <input type = "hidden" name = "ref" value = "<%=ref %>" />
      <input type = "hidden" name = "re_step" value = "<%=re_step %>" />
      <input type = "hidden" name = "re_level" value = "<%=re_level %>" />
      
      <table width = "400" border = "1" cellspacing = "0" cellpadding = "0" bgcolor = "<%=bodyback_c %>" align = "center">
         <tr>
            <td align = "right" colspan = "2" bgcolor = "<%=value_c %>">
               <a href = "list.jsp">글목록</a>
            </td>
         </tr>
         <tr>
            <td width = "70" bgcolor = "<%=value_c %>" align = "center"> 이름 </td>
            <td width = "330">
               <input type = "text" size = "10" maxlength = "10" name = "writer">
            </td>
         </tr>
         <tr>
            <td width = "70" bgcolor = "<%=value_c %>" align = "center"> 제목 </td>
            <td width = "330">
               <%
                  if(request.getParameter("num") == null) {
               %>
                  <input type = "text" size = "40" maxlength = "50" name = "subject">
               <%
                  } else {
               %>
                  <input type = "text" size = "40" maxlength = "50" name = "subject" value = "[답변]" />
               <%
                  }
               %>
            </td>
         </tr>
         <tr>
            <td width = "70" bgcolor = "<%=value_c%>" align = "center"> Email </td>
            <td width = "330">
               <input type = "text" size = "40" maxlength = "30" name = "email">
            </td>
         </tr>
         <tr>
            <td width = "70" bgcolor = "<%=value_c %>" align = "center"> 내용 </td>
            <td width = "330">
               <textarea name = "content" rows = "13" cols = "40"></textarea>
            </td>
         </tr>
         <tr>
            <td width = "70" bgcolor = "<%=value_c %>" align = "center"> 비밀번호 </td>
            <td width = "330">
               <input type = "password" size = "8" maxlength = "12" name = "passwd">
            </td>
         </tr>
         <tr>
            <td colspan = "2" bgcolor = "<%=value_c %>" align = "center">
               <input type = "submit" value = "글쓰기" />
               <input type = "reset" value ="다시작성" />
               <input type = "button" value = "목록보기" onclick="window.loaction='list.jsp'"  />
            </td>
         </tr>
      </table>
   </form>
   </center>
</body>
<% }
   catch(Exception ex) {} 
%>
</html>