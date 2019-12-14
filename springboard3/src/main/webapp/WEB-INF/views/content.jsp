<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>
<%@ page import = "com.spring.springboard3.BoardVO" %>
<%@ include file = "color.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 게시판 </title>
<%

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	try {
		BoardVO article = (BoardVO)request.getAttribute("article");
		String file = article.getFileloc();
			
%>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	
	function check() {
		var writer = comment_form.writer.value;
		var content = comment_form.content.value;
		var pass = comment_form.pass.value;
		
		if(writer=="") {
			alert('이름을 입력해주세요');
			return false;
		} else if(content=="") {
			alert('내용을 입력해주세요');
			return false;
		} else if(pass=="") {
			alert('패스워드를 입력해주세요');
			return false;
		}
		return true;
	}

	function date_format(format) {
		var year = format.getFullYear();
	    var month = format.getMonth() + 1;
	    if(month<10) {
	    	month = '0' + month;
	    }
	    var date = format.getDate();
	    if(date<10) {
	    	date = '0' + date;
	    }
	    var hour = format.getHours();
	    if(hour<10) {
	    	hour = '0' + hour;
	    }
	    var min = format.getMinutes();
	    if(min<10) {
	    	min = '0' + min;
	    }

		return year + "-" + month + "-" + date + " " + hour + ":" + min;
	}

	$(document).ready(function() {
		function selectData() {	
			$('#output').empty();
			
			$.ajax({
				url:'/springboard3/getcomment.do',
				type:'POST',
				data : {'board_num':<%=article.getNum() %>},
				tadaType:"json",
				contentType:'application/x-www-form-urlencoded; charset=utf-8',
				success:function(data) {
					$.each(data, function(index, item) {
						var output = '';
						var reg_date = new Date(item.reg_date);
						var date = date_format(reg_date);
						output += '<tr>';
						output += '<td width="125" colspan="1" align="center" bgcolor="#b0e0e6">' + item.writer + '<br>' + date + '</td>';
						output += '<td colspan="2" align="center">' + item.content + '</td>';
						output += '<td width="125" colspan="1" align="center" bgcolor="#b0e0e6"><p><a href="/springboard3/commentupdateform.do?comment_num='+item.comment_num+'" id="update" class="update">[수정]</a><br><a href="/springboard3/commentdelete.do?comment_num='+ item.comment_num +'" id="delete" class="delete">[삭제]</a><br><a href="#" id="reply" class="reply">[답글]</a></p></td>';
						output += '</tr>';
						console.log("output:" + output);
						$('#output').prepend(output);
					});
				},
				error:function() {
					alert("ajax통신 실패!!!");
				}
			});
		}
		
		$(document).on('click', '.reply', function(event) {
			jQuery.ajax({
				
				
			})
		});
		
		$(document).on('click', '.update', function(event) {
			var pass2 = prompt('패스워드를 입력하세요');
			var url = $(this).attr("href") + "&pass=" + pass2;
			if(pass2!=null) {
			jQuery.ajax({
				url:url,
				type:'GET',
				contentType:'application/x-www-form-urlencoded; charset=utf-8', dataType:"json",
				success:function(retVal) {
					if(retVal.res == "OK") {
						var detail = retVal.detail;
						var content = prompt('댓글수정', detail.content);
						var url2 = '/springboard3/updateprocess.do?comment_num='+ detail.comment_num +'&content='+ content;
						$.ajax({
							url:url2,
							type:'GET',
							contentType:'application/x-www-form-urlencoded; charset=utf-8', dataType:"json",
							success:function(retVal) {
								if(retVal.res == "OK") {
									selectData();
								} else {
									alert("댓글수정 실패");
								}
							},
							error:function(request, error) {
								alert("message:"+request.responseText);
							}
						});
						
					} else {
						alert("패스워드가 틀렸습니다.");
					}
				},
				error:function() {
					alert("ajax통신 실패!!!");
				}
			});
			}
			event.preventDefault();
		});
		
		$(document).on('click', '.delete', function(event) {
			var pass = prompt('패스워드를 입력하세요');
			var url = $(this).attr("href") + "&pass=" + pass;
			if(pass!=null) {	
			jQuery.ajax({
				url:url,
				type:'GET',
				contentType:'application/x-www-form-urlencoded; charset=utf-8', dataType:"json",
				success:function(retVal) {
					if(retVal.res == "OK") {
						selectData();
					}
					else 
						{
						alert("패스워드가 틀렸습니다.");
						}
				},
				error:function() {
					alert("ajax통신 실패!!!");
				}
			});
			}
			event.preventDefault();
		});
					
		$('#input_comment').click(function(event) {
			if(check()) {
			var params = $("#comment_form").serialize();
			params += "&board_num="+<%=article.getNum() %>;
			params += "&reg_lev=0";
			params += "&parents_num=0";
			$.ajax({
				url:'/springboard3/insertcomment.do', 
				type:'POST',
				data:params,
				contentType:'application/x-www-form-urlencoded;charset=utf-8', 
				dataType:'json',
				success:function(retVal) {				
					if(retVal.res == "OK") {
						selectData();
						$('#writer').val(''); 
						$('#content').val('');
						$('#pass').val('');
					}
					else
						{
						alert("Insert Fail!!!");
						}
				},
				error:function(request, error) {
					alert("message:"+request.responseText);
				}
			});
			}
			event.preventDefault();
		});	
		selectData();
	});
	
</script>
</head>
		<body bgcolor="<%=bodyback_c%>">
			<center>
				<b> 글 내용 보기 </b><br>
				<form>
					<table width="555" border="1" cellspacing="0" cellpadding="0"
						bgcolor="<%=bodyback_c %>" align="center">
							<tr height="30" align="center" width="125">
								<td bgcolor="<%=value_c %>"> 글 번호 </td>
								<td> ${number } </td>
								<td bgcolor="<%=value_c %>"> 조회수 </td>
								<td width="180"><%=article.getReadcount() %></td>
							</tr>
							<tr height="30" align="center" width="125">
								<td bgcolor="<%=value_c %>"> 작성자 </td>
								<td><%=article.getWriter() %></td>
								<td bgcolor="<%=value_c %>"> 작성일 </td>
								<td width="180"><%=sdf.format(article.getReg_date()) %></td>
							</tr>
							<tr height="30">
								<td align="center" width="125" bgcolor="<%=value_c %>"> 글제목 </td>
								<td align="left" colspan="3"> &nbsp;<%=article.getSubject() %> </td>
							</tr>
							<tr>
								<td align="center" bgcolor="<%=value_c %>"> 글내용 </td>
								<td align="left" colspan="3"><pre><%=article.getContent() %></pre></td>
							</tr>
							<tr>
								<td align="center" bgcolor="<%=value_c %>"> 첨부파일 </td>
								<%if(!(file.equals("첨부파일 없음"))) { %>
									<td align="left" colspan="3"><a href="<%=file %>"><%=article.getFilename() %></a></td>
								<% } else {%>
									<td align="left" colspan="3">첨부파일 없음</td>
								<% } %>
							</tr>
							<tr height="30">
								<td colspan="4" bgcolor="<%=value_c %>" align="center">
									<input type="button" value="글수정" onclick="document.location.href='updateform.do?num=<%=article.getNum() %>&pageNum=${pageNum }'">
										&nbsp;&nbsp;
									<input type="button" value="글삭제" onclick="document.location.href='deleteform.do?num=<%=article.getNum() %>&pageNum=${pageNum }'">
										&nbsp;&nbsp;
									<input type="button" value="글목록" onclick="document.location.href='./list.do?pageNum=${pageNum }'">
								</td>
							</tr>
					</table>
			 	</form>
			 	<br>
			 	
			 	<table width="555" border="1" cellspacing="0" cellpadding="0"
						bgcolor="<%=bodyback_c %>" align="center" id="output">
				</table>
				<form id="comment_form" method="post">
			 	<table width="555" border="1" cellspacing="0" cellpadding="0"
						bgcolor="<%=bodyback_c %>" align="center" id="commentwrite">
						<tr>
							<td align="center" bgcolor="<%=value_c %>" width="125" rowspan="1"> 작 성 자  <input type="text" name="writer" id="writer" size="10"></td>
							<td colspan="2" align="center" bgcolor="<%=value_c %>" rowspan="2">
								<textarea name="content" id="content" rows="7" cols="38"></textarea>
							</td>
							<td align="center" bgcolor="<%=value_c %>" width="125" rowspan="2">
								<!-- <input type="button" id="input_comment" value="댓글등록"> -->
								<p><a href="#" id="input_comment">[댓글등록]</a>
							</td>
						</tr>
						<tr>
							<td align="center" bgcolor="<%=value_c %>" width="125" rowspan="1"> 패스워드 <input type="password" name="pass" id="pass" size="10"></td>	
						</tr>
				</table>
				</form>
	</body>
<%
	} catch(Exception ex) {}
%>
</html>