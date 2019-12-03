package com.spring.springsungjuck;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.spring.springmember.MemberVO;

// SungjuckDAO 참고해서 구현해주면된다
public interface SungjuckService {
	public ArrayList<SungjuckVO> getSungjucklist() throws Exception;
	public int insertSungjuck(MemberVO memberVO, SungjuckVO sungjuckVO) throws Exception;
	public SungjuckVO selectSungjuck(SungjuckVO sungjuckVO) throws Exception;
	public SungjuckVO updateSungjuckForm(SungjuckVO sungjuckVO, Model model) throws Exception;
	public int updateSungjuck(SungjuckVO sungjuckVO) throws Exception;
	public int deleteSungjuck(SungjuckVO sungjuckVO) throws Exception;
}
