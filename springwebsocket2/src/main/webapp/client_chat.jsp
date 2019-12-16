<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%
	request.setCharacterEncoding("utf-8");
	if (session.getAttribute("id") == null) {
		out.println("<script>");
		out.println("location.href='loginForm.do'");
		out.println("</script>");
	}
	
	String id = (String)session.getAttribute("id");
	String name = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket</title>
<script>
	var log = function(s) { // s 서버에서 받아오는 데이터
		document.getElementById("output").textContent += (s + "\n");
	}

	// 통신을 위해서 http프로토콜에서 ->ws프로토콜로 변환작업(handshake)
	w = new WebSocket("ws://localhost:8080/springwebsocket2/broadcasting.do");

	w.onopen = function() {
		alert("WebSocket Connected!!!");
	}

	// SocketHandler의 handleMessage 반응
	// e는 이벤트 객체 / data에는 메세지는 client_session.sendMessage(message);
	// 서버에서 클라이언트로 메세지를 뿌려줄 때
	w.onmessage = function(e) {
		log(e.data.toString());
	}

	// 웹소켓 연결종료됐을때
	w.onclose = function(e) {
		log("WebSocket closed!!!");
	}

	// 웹소켓 연결시도했는데 실패했을때
	w.onerror = function(e) {
		log("WebSocket error!!!");
	}

	window.onload = function() {
		document.getElementById("send_button").onclick = function() {
				var input = document.getElementById("input").value;
				w.send("<%=id%>" + ">" + input);
				document.getElementById("input").value = ""; // 메세지 창 리셋되게 만드는 코드 추가
		}
	}
</script>
</head>
<body>
	<input type="text" id="input" placeholder="input message..." size="55">
	<button id="send_button">Send</button>
	대화명: <%=id %>
	<p />
	<textarea id="output" readonly rows="30" cols="80"></textarea>
</body>
</html>