package com.spring.springwebsocket;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

// 웹소켓에서 httpSession 객체를 사용할  수 있게 하기 위한 클래스 정의
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {
	
	// 동기화 전에 수행할 내용
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> map) throws Exception{
		// 위에 파라미터 중, attributes에 값을 저장하면 웹소켓 핸들러 클래스의 WebSocketSession에 전달된다.
		System.out.println("Before Handshake");
		ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
		System.out.println("URI:" + request.getURI());
		HttpServletRequest req = ssreq.getServletRequest();	// request 객체를 구하는 과정
		String id = (String)req.getParameter("id");	// *jsp에서 id=guest 뭐시기로 파라미터 전달할경우 이거 주석 해제
		// HttpSession 에 저장된 이용자의 아이디를 추출하는 경우
		// String id = (String)req.getSession().getAttribute("id");
//		String id = "admin";	// * jsp WebSocket에서 파라미터 안줄 경우
		map.put("userId", id);
		System.out.println("HttpSession에 저장된 id:"+id);	// id값 확인하는 작업
		
		return super.beforeHandshake(request, response, wsHandler, map);
	}
	
	// 동기화 후에 수행할 내용 / http프로토콜에서 ws프로토콜로 바뀐 후의 내용..????
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex){
		System.out.println("After Handshake");
		
		super.afterHandshake(request, response, wsHandler, ex);
	}
}
