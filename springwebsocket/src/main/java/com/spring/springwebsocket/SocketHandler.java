package com.spring.springwebsocket;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler {
	
	HttpServletRequest request;
	
	// 접속하는 사용자에 대한 세션을 보관하기 위해 정의
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	// Set은 집합 / 동일한 객체를 중복저장하지 않는다
	
	// 생성자
	public SocketHandler() {
		super();
		System.out.println("create SocketHandler instance!");
	}

	// 아래의 메소드들 눈여겨보기!!!
	// 클라이언트에서 연결을 종료할 경우 발생하는 이벤트
	// websocket 연결됐을때 유지관리를 해주는 게 WebSocketSession session / HttpSession과 비슷하다고 생각하면 됨
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		super.afterConnectionClosed(session, status);
		
		sessionSet.remove(session);
		System.out.println("remove session!");
	}
	
	// 클라이언트에서 접속을 하여 성공할 경우 발생하는 이벤트
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		super.afterConnectionEstablished(session);	// 웹소켓이 연결되면 호출되는 함수
		
		sessionSet.add(session);
		System.out.println("add session!");
	}
	
	// 웹소켓을 통해서 클라이언트에서 메세지를 보내면 자동으로 이 메소드가 호출됨
	// 클라이언트에서 send를 이용해서 메세지 발송을 한 경우 이벤트 핸들링
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception{
		super.handleMessage(session, message);
		
		// session.getAttribute() : HandshakeInterceptor의 beforeHandshake() 메소드에서 저장한 map을 가져온다.
		
		//map은 handshake 클래스에서 return super.beforeHandshake(request, response, wsHandler, map) 했던 map 객체
		Map<String,Object> map = session.getAttributes();
		String userId = (String)map.get("userId");
		// handshake에서 map.put("userId", id); 했던 userId임 / 연결관계 잘 이해하기
		System.out.println("전송자 아이디:" + userId);
		
		// his.sessionSet에는 웹소켓에 접속했을 때 session들이 저장돼있음
		for(WebSocketSession client_session: this.sessionSet) {
			if(client_session.isOpen()) { // 연결된 상태인지 검사
				try {
					client_session.sendMessage(message);
				}catch(Exception ignored) {
					System.out.println("fail to send message!" + ignored);
				}
			}
		}
	}
	
	// 전송 에러 발생할 때 호출
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)throws Exception{
		System.out.println("web socket error!" + exception);
	}
	
	// 메세지가 긴 경우 분할해서 보낼 수 있는지 여부를 결정하는 메소드
	// 쓸일 거의 없음 / 무시해도되고 오버라이딩만 해놓기
	@Override
	public boolean supportsPartialMessages() {
		System.out.println("call method!");
		
		return false;
	}
}
