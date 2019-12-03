package com.spring.springsungjuck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.springmember.MemberVO;

@Controller
public class SungjuckController {
	
	@Autowired
	private SungjuckService sungjuckService;
	
	// 성적리스트
	@RequestMapping("/sungjucklist.sj")
	public String getSungjucklist(Model model)throws Exception{
		ArrayList<SungjuckVO> sungjuck_list = sungjuckService.getSungjucklist();
		model.addAttribute("sungjuck_list", sungjuck_list);
		
		return "sungjuck_list";
	}
	
	// 성적입력 폼 이동
	@RequestMapping("/insertsungjuckform.sj")
	public String insertSungjuckform()throws Exception{
		return "insertsungjuckform";
	}
	
	
	// 성적입력
	@RequestMapping("/insertsungjuck.sj")
	public String insertSungjuck(MemberVO memberVO, SungjuckVO sungjuckVO, HttpServletResponse response)throws Exception{
		int res = sungjuckService.insertSungjuck(memberVO, sungjuckVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('성적입력 성공!!');" + " location.href='./sungjucklist.sj';</script>");
		}else {
			
			writer.write("<script>alert('성적입력 실패!!');" +" location.href='./insertsungjuckform.sj?';</script>");
		}
		return null;
	}
	
	// 성적출력
	@RequestMapping("/selectsungjuck.sj")
	public String selectSungjuck(SungjuckVO sungjuckVO, Model model) throws Exception{
		SungjuckVO vo = sungjuckService.selectSungjuck(sungjuckVO);
		model.addAttribute("sungjuckVO", vo);
		
		return "selectsungjuck";
	}
	
//	// 성적출력 선생님 버전
//	@RequestMapping("selectsungjuck.sj")
//	public String selectSungjuck(SungjuckVO sungjuckVO, HttpSession session, Model model) throws Exception{
//		if(!session.getAttribute("id").toString().equals("admin")) {
//			sungjuckVO.setId((String)session.getAttribute("id"));
//		}
//		SungjuckVO vo = sungjuckService.selectSungjuck(sungjuckVO);
//		model.addAttribute("vo", vo);
//		
//		return "selectsungjuck";
//	}
	
	// 성적수정 폼 이동
	@RequestMapping("/updatesungjuckform.sj")
	public String updateSungjuckForm(SungjuckVO sungjuckVO, Model model) throws Exception{
		SungjuckVO vo = sungjuckService.selectSungjuck(sungjuckVO);
		model.addAttribute("sungjuckVO", vo);
		
		return "sungjuckupdateform";
	}
	
	// 성적수정
	@RequestMapping("/updatesungjuck.sj")
	public String updateSungjuck(SungjuckVO sungjuckVO, HttpServletResponse response) throws Exception {
		int res = sungjuckService.updateSungjuck(sungjuckVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('수정 성공!!');" +" location.href='./sungjucklist.sj';</script>");
		}
		else {
			writer.write("<script>alert('수정 실패!!');" +" location.href='./updatesungjuckform.sj';</script>");
		}
		return null;
	}
	
	// 성적삭제
	@RequestMapping("/deletesungjuck.sj")
	public String deleteSungjuck(SungjuckVO sungjuckVO, HttpServletResponse response) throws Exception{
		int res = sungjuckService.deleteSungjuck(sungjuckVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('삭제 성공!!');" +" location.href='./sungjucklist.sj';</script>");
		}
		else {
			writer.write("<script>alert('삭제 실패!!');" +" location.href='./sungjucklist.sj';</script>");
		}
		return null;
		
	}
}
