package com.spring.springsungjuk;

import java.util.ArrayList;

import com.spring.springmember.MemberVO;

public interface SungjukService {
	public int insertSungjuk(SungjukVO sungjukvo) throws Exception;
	public int userCheck(MemberVO membervo) throws Exception;
	public ArrayList<SungjukVO> getSungjuklist() throws Exception;
	public SungjukVO selectSungjuk(SungjukVO sungjukvo) throws Exception;
	public int updateSungjuk(SungjukVO sungjukvo) throws Exception;
	public int deleteSungjuk(SungjukVO sungjukvo) throws Exception;
}
