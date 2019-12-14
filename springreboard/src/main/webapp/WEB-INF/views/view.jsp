<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import ="com.spring.springreboard.BoardVO" %>
<%@ page import ="com.spring.springreboard.CommentVO" %>
<%
	BoardVO vo = (BoardVO)request.getAttribute("boarddata");
	CommentVO vo2 = (CommentVO)request.getAttribute("commentVO");
%>
<!DOCTYPE html>
<html>
<head>
<title>MVC 게시판</title>
<style>
a{
  text-decoration: none;
  color: blue;
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript">
    $(document).ready(function() {
        // 목록
        function selectData() {
           //table 내부 내용을 제거(초기화)
           $('#output').empty();
           var board_num = "board_num=";
           board_num += <%=vo.getBoard_num() %>;
           // alert(board_num);
           $.ajax({
              url:'/springreboard/getCommentJSON.bo',
              type:'POST',
              data: board_num,
              dataType : "json", // 서버에서 보내줄 데이터 타입
              contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
              success:function(data) {
                 $.each(data, function(index, item) {
                    var output = '';
                    output += '<tr>';
                    output += '<td>' + item.comment_name + '</td>';
                    output += '<td>' + item.comment_content + '</td>';
                    
                    //삭제 추가수정
                  /*  output += '<td><a href="/springajax2/deletePerson.do"class="del_data" '; //del_data"(공백 = 뒤에 붙이는 글자와 겹칠 수 있기에 공백이 필요) '
                 */
                 
                 output += '<td><a href="/springreboard/deleteComment.bo?comment_num='+item.comment_num+'" class="del_data" '
                   output += 'comment_num='+item.comment_num + '>삭제</a></td>';
                    //삭제 수정끝 
                    
                    // 수정 버튼 추가
                    output += '<td><a href="/springreboard/updateCommentForm.bo?comment_num='+item.comment_num+'" class="update_data" '
                    output += 'comment_num='+item.comment_num + '>수정</a></td>';
                    
                    output += '</tr>';
                    console.log("output:" + output);
                    $('#output').append(output);
                 });
                 },
                 error:function() {
                    alert("ajax통신 실패SELECT");
                 }
              });
           }
           
			// 댓글 삽입
           $('#input_data').on('click',function(event) {
              var params = $('#insert_form').serialize();
              // alert(params);
              jQuery.ajax({
                 url : '/springreboard/insertComment.bo',
                 type : 'POST',
                 data : params, // 서버로 보낼 데이터
                 contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
                 dataType : "json", // 서버에서 보내줄 타입
                 success: function (retVal) {
                    if (retVal.res == "OK") {
                       // 데이터 성공일떄 이벤트 작성
                       selectData();
                       // 초기화
                       $('#comment_name').val('');
                       $('#comment_pass').val('');
                       $('#comment_content').val('');
                    } 
                    else {
                       alert("Insert Fail!!!");
                    }
                 },error:function() {
                    alert("ajax통신 실패INSERT");
                 }
              });
              // 기본 이벤트 제거
              event.preventDefault();
           });
    
           // 댓글 삭제
           $(document).on('click', '.del_data',function(event){
        	   // 추가
        	   var passwd = "passwd=";
        		passwd += prompt('비밀번호를 입력하세요','');
        	   jQuery.ajax({
           url : $(this).attr("href"),
           type : 'GET',
           //data : {'id' : $(this).attr("id")},
           data: passwd,
           contentType: 'application/x-www-form-urlencoded;charset=utf-8',
           dataType:"json",
           success: function (retVal) {
              // alert("댓글 삭제중");
              if(retVal.res == "OK"){
                 //데이타 성공일 때 이벤트 작성
                 selectData();
              }else{
                 alert("Insert Fail! delete");
              }
           },
           error:function(){
              alert("ajax통신 실패~delete");
           }
           
        });
        //기본 이벤트 제거
        event.preventDefault();
        });
           
        // 댓글 수정 폼
        
        $(document).on('click', '.update_data',function(event){
        	$.ajax({
        		url: $(this).attr("href"),
        		type:'GET',
        		dataType:"json",
        		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
        		success:function(data){
        			$('#update').empty();
        			
       				var update ='';
       				// update += '<form id="update_form" method="get" action="./updateComment.bo">';
       	        	update += '<tr class="updateform">';
       	        	update += '<td>'+ '<input type="hidden" name="comment_num" value='+ data.comment_num + '>' + '<input type="text" name="comment_name" value='+ data.comment_name + '>'+ '<td>';
       	        	update += '<td>'+ '<textarea name="comment_content" rows="1">'+ data.comment_content +'</textarea>' + '<td>';
       	        	update += '<td>'+ '<input type="password" name="comment_pass" value='+ data.comment_pass + '>' + '<td>';
       	        	update += '<td>'+ '<input type="submit" value="수정" id="updatebtn">' + '<td>';
       	        	update += '<td>'+ '<input type="button" value="닫기" class="exit">' + '<td>';
       	        	update += '</tr>';
       	        	// update += '</form>'
       	        	$('#update').append(update);
        		},
        		error:function() {
                    alert("ajax통신 실패 updateForm");
        		}
        	});
            //기본 이벤트 제거	/ 이거 안쓰니까 다른 창으로 json 띄워서 넘어감....
            event.preventDefault();
        });
        
        // 댓글 수정
        $(document).on('click', '#updatebtn', function(event){
        	var params = $('#update_form').serialize();
        	// alert(params);
        	jQuery.ajax({
        		url : '/springreboard/updateComment.bo',
        		type : 'GET',
        		data : params,
                contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
                dataType : "json",
                success: function(retVal){
                	if(retVal.res == "OK"){
                		$('#update').empty();
                		selectData();
                	}
                	else{
                		alert("Update fail!")
                	}
                }, error:function(){
                	alert("ajax통신 실패 update");
                }
        	});
            // 기본 이벤트 제거
            event.preventDefault();
        });
        
        // 댓글 수정 닫기
        $(document).on('click', '.exit',function(event){
        	$('.updateform').toggle();
        });
           //다시 리스트 뽑아주기 
           selectData();
        });

    
    </script>
</head>
<body>
<!-- 게시판 수정 -->
	<table cellpadding="0" cellspacing="0">
		<tr align="center" valign="middle">
			<td colspan="5">MVC 게시판</td>
		</tr>
		<tr>
			<td style="font-faimly:돋음; font-size:12" height="16">
				<div align="center">제       목&nbsp;&nbsp;</div>
			</td>
			<td style="font-faimly:돋음; font-size:12">
				<%=vo.getBoard_subject() %>
			</td>
		</tr>
		<tr bgcolor="cccccc">
			<td colspan="2" style="height:1px;"></td>
		</tr>
		<tr>
			<td style="font-faimly:돋음; font-size:12">
				<div align="center">내       용</div>
			</td>
			<td style="font-faimly:돋음; font-size:12">
				<table border=0 width=490 height=250 style="table-layout:fixed">
					<tr>
						<td valign=top style="font-family:돋음; font-size:12">
							<%=vo.getBoard_content() %>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td style="font-faimly:돋음; font-size:12">
				<div align="center">첨부파일</div>
			</td>
			<td style="font-faimly:돋음; font-size:12">&nbsp;
				<%if(!(vo.getBoard_originfile() == null)) { %>
					<a href="${downlink }">
						<%=vo.getBoard_originfile() %>
					</a>
				<%} %>
			</td>
		</tr>
		<tr bgcolor="cccccc">
			<td colspan="2" style="height:1px;"></td>
		</tr>
		<tr><td colspan="2">&nbsp;</td></tr>
		<tr align="center" valign="middle">
			<td colspan="5">
				<font size=2>
					<a href="./replyform.bo?num=<%=vo.getBoard_num() %>">
					[답변]
					</a>&nbsp;&nbsp;
					<a href="./updateform.bo?num=<%=vo.getBoard_num() %>">
					[수정]
					</a>&nbsp;&nbsp;
					<a href="./deleteform.bo?num=<%=vo.getBoard_num() %>">
					[삭제]
					</a>&nbsp;&nbsp;
					<a href="./list.bo">
					[목록]
					</a>&nbsp;&nbsp;
				</font>
			</td>
		</tr>
	</table>
<form id="insert_form" method="post">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<label for="comment_name">이름</label>
			</td>
			<td>
				<label for="comment_content">내용</label>
			</td>
			<td>
				<label for="comment_pass">비밀번호</label>
			</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="board_num" value=<%=vo.getBoard_num() %>>
				<input type="text" name="comment_name" id="comment_name" value="">
			</td>
			<td>
				<textarea name="comment_content" id="comment_content"cols="20" rows="1" value=""></textarea>
			</td>
			<td>
				<input type="password" name="comment_pass" id="comment_pass" value="">
			</td>
			<td>
				<input type="button" value="추가" id="input_data">
			</td>
		</tr>
	</table>
</form>
	<table id="output"></table>
	
	<form id="update_form" method="get" action="./updateComment.bo">
		<table id="update"></table>
	</form>
</body>
</html>