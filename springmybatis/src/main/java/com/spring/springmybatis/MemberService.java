package com.spring.springmybatis;

import java.util.ArrayList;
import java.util.HashMap;

// MemberMapper 메소드 이름과 service 메소드 이름은 상관없는 것! 이름이 같은 건 그냥 헷갈리지않기위해서 같게 설계해준 것일뿐
// service에서는 controller로 결과를 리턴
public interface MemberService {
	public ArrayList<MemberVO> getMembers();
	public MemberVO getMember(String id);
	public void insertMember(MemberVO member);
	public void insertMember2(HashMap<String, String> map);
	public void updateMember(MemberVO member);
	public void deleteMember(String id);
}
