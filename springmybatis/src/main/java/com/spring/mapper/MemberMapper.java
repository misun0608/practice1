package com.spring.mapper;
//mapper.xml과 동일한 패키지에 소스파일 생성할것

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.springmybatis.MemberVO;

public interface MemberMapper {
	// xml과 이 인터페이스 파일을 연결할 때 패키지 경로가 반.드.시 같아야 할 것
	// 인터페이스의 메소드명과 MemberMapper.xml의 id와 반.드.시 동일하게 작성해야 한다
	// 리턴타입이 ResultMap이나 ResultType으로 사용된다 / 반드시 타입이 같아야한다
	// xml의 parameterType과 여기의 파라미터 타입은 반드시 일치해야 한다
	// 이 인터페이스와 xml 문서 DAO 역할을 대신함!
	
	ArrayList<MemberVO> getMembers();	// 설계만해주면 ArrayList타입으로 알아서 반환해줌 / 타입지정이 굉장히 중요
	//MemberVO getMember(String id);	// MemberVO로 할 경우
	HashMap<String,String> getMember(String id);	//HashMap 이용시 추가부분
	int insertMember(MemberVO member);
	// 삽입 후 삽입한 결과 상태 반환하기 위해 반환값을 int로 정해줌
	// 굳이 MemberMapper에서 굳이 resultMap으로 지정해주진 않았지만
	// 명시되지 않았지만 삽입한 레코드 개수를 반환받고싶으면 int로 하면 됨
	void insertMember2(HashMap<String, String> map);
	void updateMember(MemberVO member);
	void deleteMember(String id);
	int getCount();
	
}
