package com.spring.springmember;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")	// 괄호 안에 이름은 bean객체를 생성할 때 객체이름 지정해준것 / 객체이름이 memberService로 만들어진다
public class MemberServiceImpl implements MemberService {
	
	@Autowired(required=false)
	// bean으로 만들어진 요소를 MemberDAO에 대입해주고싶을때(의존성주입) Autowired 사용
	// 이름이 중요한게 아니라 타입이 중요함
	// required=true일때 MemberDAO 타입이 없으면 오류난다. false면 대입할 객체가 없을 시 자체적으로 에러가 발생하진 않는다. 하지만 뒤에선 에러나겠지...
	private MemberDAO memberDAO = null;

	@Override
	public int insertMember(MemberVO memberVO) throws Exception {
		try {
			int res = memberDAO.insertMember(memberVO);
			return res;
		}catch(Exception e) {
			throw new Exception("회원 등록 실패.", e);
		}
	}

	@Override
	public int userCheck(MemberVO memberVO) throws Exception {
		try {
			int res = memberDAO.userCheck(memberVO);
			return res;
		}catch(Exception e) {
			throw new Exception("회원 확인 실패.", e);
		}
	}

	@Override
	public ArrayList<MemberVO> getMemberlist() throws Exception {
		try {
			ArrayList<MemberVO> member_list = new ArrayList<MemberVO>();
			member_list = memberDAO.getMemberlist();
			return member_list;
		}catch(Exception e) {
			throw new Exception("회원 리스트 검색 실패.", e);
		}
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) throws Exception {
		try {
			MemberVO vo = memberDAO.selectMember(memberVO);
			return vo;
		}catch(Exception e) {
			throw new Exception("회원 검색 실패.", e);
		}
	}

	@Override
	public int deleteMember(MemberVO memberVO) throws Exception {
		try {
			int res = memberDAO.deleteMember(memberVO);
			return res;
		}catch(Exception e) {
			throw new Exception("회원 삭제 실패.", e);
		}
	}
	
	
//	@Override
//	public MemberVO updateMemberForm(MemberVO memberVO) throws Exception{	// 선생님부분 추가
//		try {
//			MemberVO vo = memberDAO.selectMember(memberVO);
//			return vo;
//		}catch(Exception e) {
//			throw new Exception("회원 수정 폼", e);
//		}
//	}
//	
	@Override
	public int updateMember(MemberVO memberVO) throws Exception{
		try {
			int res = memberDAO.updateMember(memberVO);	// 1 or 0 이 반환
			return res;
			
		}catch(Exception e) {
			throw new Exception("회원 수정 실패.", e);
		}
	}

}
