package com.spring.springmember;

import java.util.ArrayList;

// 인터클래스를 보면 어떤 메소드가 사용되고 있고 리턴타입은 어떻게되는지 확인할 수 있다. 한눈에 구조를 파악할 수 있다!
// 처음에는 이거 설계하기 힘드니까 하나하나 설계하고 Impl에 추가하고 이런식으로!
public interface MemberService {
	public int insertMember(MemberVO memberVO) throws Exception;
	public int userCheck(MemberVO memberVO) throws Exception;
	public ArrayList<MemberVO> getMemberlist() throws Exception;
	public MemberVO selectMember(MemberVO memberVO) throws Exception;	// 왜 memberVO로 설정했을까? 해당 아이디에 대한 값들이 memberVO로 되있기때문에
	// public MemberVO updateMemberForm(MemberVO memberVO) throws Exception;	// 선생님 추가
	public int deleteMember(MemberVO memberVO) throws Exception;
	public int updateMember(MemberVO memberVO) throws Exception;	// 수정이 잘됐는지 확인하기위해서 int로 설정
}
