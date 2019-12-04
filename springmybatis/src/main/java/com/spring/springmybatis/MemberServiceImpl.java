package com.spring.springmybatis;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private SqlSession sqlSession; // Mybatis(ibatis) 라이브러리가 제공하는 클래스
	// bean 객체 중에 SqlSession 타입이 여기에 주입 
	// SqlSessionFactory은 SqlSession을 만들어진다. 그리고 SqlSession은 sql문을 호출하는데 사용
	
	@Override
	public ArrayList<MemberVO> getMembers() {
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();	// null로 해도 상관없음
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		//getMembers()의 메소드명과 mapper.xml의 id는 동일해야한다.
		// MemberMapper 클래스 이름 지정? 기억해야함 반드시 이 작업을 거처야한다
		
		memberList = memberMapper.getMembers();
		// MemberMapper.java에 정의되어있던...
		System.out.println(memberMapper.getCount());	// 테스트 용
		//memberList = memberMapper.getMembers("tab_mybatis");
		
		return memberList;
	}

	@Override
	public MemberVO getMember(String id) {
		MemberVO member = new MemberVO();	// MemberVO 사용할 경우
		HashMap<String, String> vo = new HashMap<String, String>();
		// HashMap 이용시 추가 부분
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		
		/*
		HashMap<String, String> vo = new HashMap<String, String>();
		// HashMap 이용시 추가 부분
		vo = memberMapper.getMember(id);
			// HashMap 이용시 추가부분
		System.out.println("vo.id=" + vo.get("id"));
			// HashMap 이용시 추가부분
		member.setId(vo.get("id"));
			// HashMap 이용시 추가부분
		member.setName(vo.get("name"));
			// HashMap 이용시 추가부분
		member.setEmail(vo.get("email"));
			// HashMap 이용시 추가부분
		member.setPhone(vo.get("phone"));
			// HashMap 이용시 추가부분
		 */
		
		// member = memberMapper.getMember(id);
		vo = memberMapper.getMember(id);
			// HashMap 이용시 추가부분
		System.out.println("vo.id=" + vo.get("id"));
			// HashMap 이용시 추가부분
		member.setId(vo.get("id"));
			// HashMap 이용시 추가부분
		member.setName(vo.get("name"));
			// HashMap 이용시 추가부분
		member.setEmail(vo.get("email"));
			// HashMap 이용시 추가부분
		member.setPhone(vo.get("phone"));
			// HashMap 이용시 추가부분
		
		return member;
	}


	@Override
	public void insertMember(MemberVO member) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		// MemberMapper 타입의 객체 생성
		
		int res = memberMapper.insertMember(member);
		// 삽입후 삽입한 결과 상태 반환하기 위해 반환값을 int로 정해줌
		System.out.println("res=" + res);	// 간단하게 확인하는 작업
	}

	@Override
	public void insertMember2(HashMap<String, String> map) {
		System.out.println("hashmap");
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.insertMember2(map);
	}

	@Override
	public void updateMember(MemberVO member) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.updateMember(member);
	}

	@Override
	public void deleteMember(String id) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		memberMapper.deleteMember(id);
	}

}
