<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>성적입력 페이지</title>
</head>
<body>
	<form action="./insertsungjuck.sj" name="sungjuckform" method="post">
	<center>
		<table border=1>
			<tr>
				<td colspan=2><b><font size="5">성적입력 페이지</font></b></td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
					<input type="text" name="id">
				</td>
			</tr>
			<tr>
				<td>학번</td>
				<td>
					<input type="text" name="hakbun">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="name">
				</td>
			</tr>
			<tr>
				<td>국어</td>
				<td>
					<input type="text" name="kor">
				</td>
			</tr>
			<tr>
				<td>영어</td>
				<td>
					<input type="text" name="eng">
				</td>
			</tr>
			<tr>
				<td>수학</td>
				<td>
					<input type="text" name="math">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="javascript:sungjuckform.submit()">성적입력</a>
					&nbsp;&nbsp;
					<a href="javascript:sungjuckform.reset()">다시작성</a>
				</td>
			</tr>
		</table>
		</center>
	</form>
</body>
</html>