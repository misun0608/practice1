package com.spring.springwebsocket2;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	SqlSession sqlSession;

	// 회원
	@Override
	public int insertMember(MemberVO vo) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.insertMember(vo);
		System.out.println(res);

		return res;
	}

	// 로그인할때 확인
	@Override
	public int userCheckMember(MemberVO vo) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		int res = memberMapper.userCheckMember(vo.getId());
		System.out.println(res);
		
		return res;
	}

	// 회원정보 추출
	@Override
	public String pickNameMember(MemberVO vo) {
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		String name = memberMapper.pickNameMember(vo.getId());
		
		return name;
	}

}
