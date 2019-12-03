package com.spring.springsungjuk;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SungjukController {
	
	@Autowired
	private SungjukService sungjukService;
	
	@RequestMapping("/sungjuklist.su") 
	public String getSungjuklist(Model model) throws Exception { 
		
		ArrayList<SungjukVO> sungjuk_list = sungjukService.getSungjuklist();
		
		model.addAttribute("sungjuk_list", sungjuk_list);
		
		return "sungjuk_list";
	}
	
	@RequestMapping("/sungjukinfo.su") 
	public String getSungjukinfo(SungjukVO sungjukvo, HttpSession session, Model model) throws Exception { 
		if (!session.getAttribute("id").toString().equals("admin")) {
			sungjukvo.setId((String)session.getAttribute("id"));
		}
		
		SungjukVO vo = sungjukService.selectSungjuk(sungjukvo);
		
		model.addAttribute("vo", vo);
		
		return "sungjuk_info";
	}
	
	@RequestMapping("/sungjukinputform.su") 
	public String sungjukinputform(SungjukVO sungjukvo, Model model) throws Exception { 
		SungjukVO vo = sungjukService.selectSungjuk(sungjukvo);
		
		model.addAttribute("vo", vo);
		
		return "sungjuk_inputForm";
	}
	
	@RequestMapping("/sungjukinput.su") 
	public String sungjukinput(SungjukVO sungjukvo, HttpServletResponse response) throws Exception { 
		int res = sungjukService.insertSungjuk(sungjukvo);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1)
		{
			writer.write("<script>alert('성적 입력 성공!!');location.href='./sungjuklist.su';</script>");
		}
		else
		{
			writer.write("<script>alert('성적 입력 실패!!');location.href='./sungjuklist.su';</script>");
		}
		return null;
	}
	
	@RequestMapping("/sungjukupdateform.su") 
	public String sungjukupdateform(SungjukVO sungjukvo, Model model) throws Exception { 
		SungjukVO vo = sungjukService.selectSungjuk(sungjukvo);
		
		model.addAttribute("vo", vo);
		
		return "sungjuk_updateForm";
	}
	
	@RequestMapping("/sungjukupdate.su") 
	public String sungjukupdate(SungjukVO sungjukvo, HttpServletResponse response) throws Exception { 
		int res = sungjukService.updateSungjuk(sungjukvo);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1)
		{
			writer.write("<script>alert('성적 수정 성공!!');location.href='./sungjuklist.su';</script>");
		}
		else
		{
			writer.write("<script>alert('성적 수정 실패!!');location.href='./sungjuklist.su';</script>");
		}
		return null;
	}
	
	@RequestMapping("/sungjukdelete.su") 
	public String sungjukdelete(SungjukVO sungjukvo, HttpServletResponse response) throws Exception { 
		int res = sungjukService.deleteSungjuk(sungjukvo);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == 1)
		{
			writer.write("<script>alert('성적 삭제 성공!!');location.href='./sungjuklist.su';</script>");
		}
		else
		{
			writer.write("<script>alert('성적 삭제 실패!!');location.href='./sungjuklist.su';</script>");
		}
		return null;
	}
}
