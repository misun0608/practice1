<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.spring.springboardM2.BoardVO" %>
<%@ page import="java.net.URLEncoder" %>
<%
	BoardVO board = (BoardVO)request.getAttribute("boardVO");
	String downlink = null;
	if(board.getBOARD_STORED_FILE() != null)
		downlink = "fileDownload.do?of=" + URLEncoder.encode(board.getBOARD_STORED_FILE(),"UTF-8") + "&of2=" + URLEncoder.encode(board.getBOARD_ORI_FILE(),"UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
	<title>MVC 게시판</title>
	<style>
		a {
			text-decoration: none;
		}
		
		.small {
			font-size: small;
		}
		
		.p_more {
			position: absolute;
			float: right;
		}
		
		.c_more {
			display: none;
			background-color: #fff;
			border: 1px solid gray;
			padding: 3px;
			vertical-align: middle;
			float: right;
			margin: 0 10px;
		}
		
		.hide {
			display: none;
		}
		
		.comment_content {
			width:400px;
			margin: 1px 5px;
		}
		
		.border {
                border: 1px solid gray;
                float: right;
                margin: 10px 0;
                padding: 5px;
            }
	</style>
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script>
	    $(document).ready(function(){
	        $(document).on('click','.more',function(){
	            $(this).siblings().css({'display':'block'});
	        });
	        $(document).on('mouseleave','.p_more',function(){
	            $('.c_more').css({'display':'none'});
	        });
	        
	        $('.comment_btn').toggle(function(){
	            $('.comment').removeClass('hide');
	        },function(){
	            $('.comment').addClass('hide');
	        });
	        
            $(document).on('click','.c_update_btn1',function(){
                $('.c_delete').addClass('hide');
                $('.c_update').addClass('hide');
                var index = $(this).attr('name');
                $('[name='+index+']').eq(2).removeClass('hide');
            });
            $(document).on('click','.c_update_cancel',function(){
            	var index = $(this).attr('name');
            	$('[name='+index+']').eq(2).addClass('hide');
            });
            
            $(document).on('click','.c_delete_btn1',function(){
                $('.c_update').addClass('hide');
                $('.c_delete').addClass('hide');
                var index = $(this).attr('name');
                $('[name='+index+']').eq(5).removeClass('hide');
            });
            $(document).on('click','.c_delete_cancel',function(){
            	var index = $(this).attr('name');
            	$('[name='+index+']').eq(5).addClass('hide');
            });
            
	        
	        function selectComment(){
	        	$('.commentarea').empty();
	        	
	        	$.ajax({
	        		url:'/springboardM2/getCommentList.do',
	        		type:'POST',
	        		data:{'BOARD_NUM':$('#BOARD_NUM').attr("value")},
	        		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					dataType : "json",
					success : function (data) {
						$.each(data, function(index,item){
							var output='';
							output += '<tr>';
							output += '<td>' + item.comment_name + '</td>';
							output += '<td align="left" class="comment_content">';
							output += item.comment_content;
							output += '</td>';
							output += '<td class="p_more"><a href="#" font-size=2 class="small more">…</a>';
							output += '<table class="c_more">';
							output += '<tr>';
							output += '<td><a href="#" class="c_update_btn1 small" name="index'+index+'">[수정]</a>';
							output += '<a href="#" class="c_delete_btn1 small" name="index'+index+'">[삭제]</a></td>';
							output += '</tr>';
							output += '</table>';
							output += '</td>';
							output += '</tr>';
							output += '<tr><td colspan=2>';
							output += '<form id="c_updateform" name="update_index'+index+'" method="post">';
							output += '<table class="c_update border hide" name="index'+index+'">';
							output += '<input type="hidden" id="comment_num" name="comment_num" value="'+item.comment_num+'" />';
							output += '<tr><td>덧글</td>';
							output += '<td colspan=2>';
							output += '<textarea cols="50" rows="3" name="comment_content">';
							output += item.comment_content + '</textarea></td></tr>';
							output += '<tr><td>비밀번호</td>';
							output += '<td><input type="password" name="comment_password" /></td>';
							output += '<td><input type="button" class="c_update_btn2" name="index'+index+'" value="수정" />';
							output += '<input type="button" class="c_update_cancel" name="index'+index+'" value="취소" />';
							output += '</td></tr></table></form></td></tr>';
							output += '<tr><td colspan=2>';
							output += '<form id="c_deleteform" name="delete_index'+index+'" method="post">';
							output += '<table class="c_delete border hide" name="index'+index+'">';
							output += '<input type="hidden" id="comment_num" name="comment_num" value="'+item.comment_num+'" />';
							output += '<tr><td>비밀번호</td>';
							output += '<td><input type="password" name="comment_password" /></td>';
							output += '<td>';
							output += '<input type="button" class="c_delete_btn2" name="index'+index+'" value="삭제" />';
							output += '<input type="button" class="c_delete_cancel" name="index'+index+'" value="취소" />';
							output += '</td></tr></table></form></td></tr>';
							$('.commentarea').append(output);
						});
					},
					error : function(){
						alert("ajax 통신 실패");
					}
	        	});
	        }
	        
	        $(document).on('click','#input_comment',function(){
	        	var params=$('#commentform').serialize();
	        	alert(params);
	        	jQuery.ajax({
	        		url : '/springboardM2/insertComment.do',
	        		type : 'POST',
	        		data : params,
	        		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					dataType : "json",
					success : function (retVal) {
						if(retVal.res == "OK") {
							// 데이터 성공일 때 이벤트 작성
							selectComment();
							// 초기화
							$('#comment_name').val('');
							$('#comment_password').val('');
							$('#comment_content').val('');
						}
						else {
							alert('Insert Fail : ' + retVal.res);
						}
					},
					error:function(){
						alert("ajax통신 실패!!!");
					}
	        	});
	        	event.preventDefault();
	        });
	        
	        $(document).on('click','.c_update_btn2',function(){
	        	var index = $(this).attr('name');
	        	var params = $('form[name=update_'+index+']').serialize();
	        	alert(params);
	        	jQuery.ajax({
	        		url:'/springboardM2/updateComment.do',
	        		type:'POST',
	        		data:params,
	        		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					dataType : "json",
					success : function (retVal) {
						if(retVal.res == "OK") {
							// 데이터 성공일 때 이벤트 작성
							selectComment();
						}
						else {
							alert('Update Fail : ' + retVal.res);
						}
					},
					error:function(){
						alert("ajax통신 실패!!!");
					}
	        	})
	        });
	        
	        $(document).on('click','.c_delete_btn2',function(){
	        	var index = $(this).attr('name');
	        	var params = $('form[name=delete_'+index+']').serialize();
	        	alert(params);
	        	jQuery.ajax({
	        		url:'/springboardM2/deleteComment.do',
	        		type:'POST',
	        		data:params,
	        		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					dataType : "json",
					success : function (retVal) {
						if(retVal.res == "OK") {
							// 데이터 성공일 때 이벤트 작성
							selectComment();
						}
						else {
							alert('Delete Fail : ' + retVal.res);
						}
					},
					error:function(){
						alert("ajax통신 실패!!!");
					}
	        	})
	        });
	        
	        
	        selectComment();
	    });
	    
	</script>

</head>
<body>
	<%-- 게시판 수정 --%>
	
	<table cellpadding="0" cellspacing="0" align="center">
		<tbody align="center">
			<tr valign="middle">
				<td colspan="5">MVC 게시판</td>
			</tr>
		
			<tr>
				<td style="font-family:돋움; font-size:12" height="16">
					<div align="center">제 목&nbsp;&nbsp;</div>
				</td>
				<td style="font-family:돋움; font-size:12">
					<%=board.getBOARD_SUBJECT() %>
				</td>
			</tr>
			
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			
			<tr>
				<td style="font-family:돋움; font-size:12">
					<div align="center">내 용</div>
				</td>
				<td style="font-family:돋움; font-size:12">
					<table border=0 width=400 height=250 style="table-layout:fixed">
						<tr>
							<td valign=top style="font-family:돋움; font-size:12">
								<%=board.getBOARD_CONTENT() %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td style="font-family:돋움; font-size:12">
					<div align="center">첨부파일</div>
				</td>
				<td style="font-family:돋움; font-size:12">
					&nbsp;
					<% if(!(board.getBOARD_ORI_FILE() == null)) { %>
						<a href="<%=downlink%>">
							<%=board.getBOARD_ORI_FILE() %>
						</a>
					<% } %>
				</td>
			</tr>
			
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px;"></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			
			<tr valing="middle">
				<td colspan="5" align="right">
					<font size=2>
						<a href="./BoardReplyForm.do?num=<%=board.getBOARD_NUM() %>">[답변]</a>&nbsp;&nbsp;
						<a href="./BoardUpdateForm.do?num=<%=board.getBOARD_NUM() %>">[수정]</a>&nbsp;&nbsp;
						<a href="./BoardDeleteForm.do?num=<%=board.getBOARD_NUM() %>">[삭제]</a>&nbsp;&nbsp;
						<a href="./list.do">[목록]</a>&nbsp;&nbsp;
					</font>
				</td>
			</tr>
			<tr><td colspan=2><hr/></td></tr>
	        <tr>
	            <td><a href="#" class="comment_btn">덧글 작성하기</a></td>
	        </tr>
	        <form id="commentform" method="post">
	        <input type="hidden" id="BOARD_NUM" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %>" />
			<tr class="comment hide">
				<td colspan=2>
					<table>
						<tr>
							<td>작성자</td>
							<td><input type="text" name="comment_name" id="comment_name" /></td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="comment_password" id="comment_password" /></td>
						</tr>
						<tr>
							<td>덧글</td>
							<td><textarea cols="50" rows="3" name="comment_content" id="comment_content"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><input type="button"
								value="덧글 등록" id="input_comment" /></td>
						</tr>
					</table>
				</td>
			</tr>
			</form>
			<tr>
				<td colspan=2><hr /></td>
			</tr>
			<tr>
				<td clspan=2>덧글 목록</td>
			</tr>
			<tr>
				<td colspan=2><hr /></td>
			</tr>
			<tr>
				<td colspan="2">
					<table class="commentarea">
					<%-- 
						
						<tr>
							<td>작성자</td>
							<td align="left" class="comment_content">덧글 내용</td>
							<td class="p_more"><a href="#" font-size=2 class="small more">…</a>
								<table class="c_more">
									<tr>
										<td><a href="#" class="small" id="r_update">[수정]</a>
										<a href="#" class="small" id="r_delete">[삭제]</a></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
		                    <td colspan=2>
		                        <form id="c_updateform" method="post">
		                            <table class="c_update border hide">
		                            	<input type="hidden" id="comment_num" name="comment_num" value="item.comment_num" />
		                                <tr>
		                                    <td>덧글</td>
		                                    <td colspan=2>
		                                        <textarea cols="50" rows="3" name="comment_content"></textarea>
		                                    </td>
		                                </tr>
		                                <tr>
		                                    <td>비밀번호</td>
		                                    <td><input type="password" name="comment_password" /></td>
		                                    <td>
		                                        <input type="button" value="수정" id="c_update_btn2" />
		                                        <input type="button" value="취소" id="c_update_cancel" />
		                                    </td>
		                                </tr>
		                            </table>
		                        </form>
		                    </td>
		                </tr>
					--%>
		                <tr>
		                    <td colspan=2>
		                        <form id="c_deleteform" method="post">
		                            <table class="c_delete border hide">
		                                <tr>
		                                    <td>비밀번호</td>
		                                    <td><input type="password" name="comment_password" /></td>
		                                    <td>
		                                        <input type="button" value="삭제" id="c_delete_btn2" />
		                                        <input type="button" value="취소" id="c_delete_cancel" />
		                                    </td>
		                                </tr>
		                            </table>
		                        </form>
		                    </td>
		                </tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	<%-- 게시판 수정 --%>
</body>
</html>