package com.spring.springsungjuck;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.springmember.MemberVO;

@Service("sungjuckService")
public class SungjuckServiceImpl implements SungjuckService {

	@Autowired(required=false)	// 의존성 주입발생
	private SungjuckDAO sungjuckDAO = null;
	
	@Override
	public ArrayList<SungjuckVO> getSungjucklist() throws Exception {
		try {
			ArrayList<SungjuckVO> sungjuck_list = new ArrayList<SungjuckVO>();
			sungjuck_list = sungjuckDAO.getSungjucklist();
			return sungjuck_list;
			
		}catch(Exception e) {
			throw new Exception("성적 리스트 검색 실패.", e);
		}
		
	}
	
	@Override
	public int insertSungjuck(MemberVO memberVO, SungjuckVO sungjuckVO) throws Exception{
		try {
			int res = sungjuckDAO.insertSungjuck(memberVO, sungjuckVO);
			return res;
		}catch(Exception e) {
			throw new Exception("성적 입력 실패.", e);
		}
	}
	
	@Override
	public SungjuckVO selectSungjuck(SungjuckVO sungjuckVO) throws Exception{
		try {
			SungjuckVO vo = sungjuckDAO.selectSungjuck(sungjuckVO);
			return vo;
		}catch(Exception e) {
			throw new Exception("성적 출력 실패.", e);
		}
	}
	
	@Override
	public SungjuckVO updateSungjuckForm(SungjuckVO sungjuckVO, Model model) throws Exception{
		try {
			SungjuckVO vo = sungjuckDAO.selectSungjuck(sungjuckVO);
			return vo;
		}catch(Exception e) {
			throw new Exception("성적 수정 폼", e);
		}
	}
	
	@Override
	public int updateSungjuck(SungjuckVO sungjuckVO)throws Exception{
		try {
			int res = sungjuckDAO.updateSungjuck(sungjuckVO);
			return res;
			
		}catch(Exception e) {
			throw new Exception("성적 수정 실패.", e);
		}
	}
	
	@Override
	public int deleteSungjuck(SungjuckVO sungjuckVO)throws Exception{
		try {
			int res = sungjuckDAO.deleteSungjuck(sungjuckVO);
			return res;
			
		}catch(Exception e) {
			throw new Exception("성적 삭제 실패.", e);
		}
	}
}
