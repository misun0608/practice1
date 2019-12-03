package com.spring.springmember;

import java.util.ArrayList;

public interface MemberService {
	public int insertMember(MemberVO memberVO) throws Exception;
	public int userCheck(MemberVO memberVO) throws Exception;
	public ArrayList<MemberVO> getMemberlist() throws Exception;
	public MemberVO selectMember(MemberVO memberVO) throws Exception;
	public MemberVO updateMemberForm(MemberVO memberVO) throws Exception;
	public int updateMember(MemberVO memberVO) throws Exception;
	public int deleteMember(MemberVO memberVO) throws Exception;
}
