<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket</title>
<script>
	var log = function(s){	// s 서버에서 받아오는 메세지?
		document.getElementById("output").textContent += (s + "\n");
	}
	
	// 통신을 위해서 http프로토콜에서 ->ws프로토콜로 변환작업
	// <websocket:mapping path="/broadcasting" handler="socketHandler" /> 과 같게 아래 경로 지정해주면 됨
	// w = new WebSocket("ws://localhost:8080/springwebsocket/broadcasting");
	w = new WebSocket("ws://localhost:8080/springwebsocket/broadcasting?id=guest");
	
	w.onopen = function(){
		alert("WebSocket Connected!!!");
	}
	
	// e는 이벤트 객체 / data에는 메세지는 client_session.sendMessage(message);
	// 서버에서 클라이언트로 메세지를 뿌려줄 때
	w.onmessage = function(e){
		log(e.data.toString());
	}
	
	// 웹소켓 연결종료됐을때
	w.onclose = function(e){
		log("WebSocket closed!!!");
	}
	
	// 웹소켓 연결시도했는데 실패했을때
	w.onerror = function(e){
		log("WebSocket error!!!");
	}
	
	window.onload = function(){
		document.getElementById("send_button").onclick = function(){
			if(document.getElementById("nicname").value == ""){
				alert("별명을 입력하세요!!!");
			}else{
				var nicname = document.getElementById("nicname").value;
				var input = document.getElementById("input").value;
				// w 웹소켓 객체 / 웹소켓을 통해서 클라이언트에서 서버로 메세지를 전송할 때 send() 메소드 사용
				// 그러면 handleMessage 호출
				w.send(nicname + "> " + input);
				document.getElementById("input").value=""; // 메세지 창 리셋되게 만드는 코드 추가
			}
		}
	}
	
</script>
</head>
<body>
<input type="text" id="input" placeholder="input message..." size="55">
<button id="send_button">Send</button>
대화명<input type="text" id="nicname" placeholder="대화명 입력" size="10">
<p/>
<textarea id="output" readonly rows="30" cols="80"></textarea>
</body>
</html>