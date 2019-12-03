package com.spring.springmember;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.springsungjuck.SungjuckVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;	// MemberService가 MemberServiceImpl의 부모타입이라서 대입 가능

	@RequestMapping("/login.me")
	public String userCheck(MemberVO memberVO, HttpSession session, HttpServletResponse response) throws Exception {	// memberVO에는 입력받는 아이디와 패스워드 저장
		int res = memberService.userCheck(memberVO);

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if (res == 1) {
			session.setAttribute("id", memberVO.getId());
			writer.write("<script>alert('로그인 성공!!');</script>");
			if(memberVO.getId().equals("admin")) {
				writer.write("<script>location.href='./sungjucklist.sj';</script>");
			}else {
//				writer.write("<script>location.href='./selectsungjuck.sj';</script>"); // 이렇게하니 안됨...
				return "redirect:/selectsungjuck.sj?id="+session.getAttribute("id");
			}

		} else {
			writer.write("<script>alert('로그인 실패!!');" + "location.href='./loginform.me';</script>");
			// return "redirect:/loginform.me";
		}
		return null;
	}
	
	@RequestMapping("/main.me")
	public String mainPage() throws Exception{
		return "main";
	}
	
	@RequestMapping("/loginform.me")
	public String loginForm() throws Exception{
		return "loginForm";
	}
	
	@RequestMapping("/joinform.me")
	public String joinForm() throws Exception{
		return "joinForm";
	}
	
	// 회원가입 양식에 주어진 입력조건을 가지고 회원등록
	@RequestMapping("/joinprocess.me")
	public String insertMember(MemberVO memberVO, HttpServletResponse response)throws Exception{ // MemberVO memberVO 입력한 정보들 저장 / memberVO는 의존성주입이 일어나긴하지만 bean객체는 아님
		int res = memberService.insertMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		// redirect로 바로가서 경고창 안띄울거면 위에 세줄 필요없음!
		
		
		if(res != 0) {
			writer.write("<script>alert('회원가입 성공!!');" +" location.href='./loginform.me';</script>");
			// return "redirect:/loginform.me";
		}
		else {
			writer.write("<script>alert('회원가입 실패!!');" +" location.href='./joinform.me';</script>");
			// return "redirect:/joinform.me";
		}
		return null;
	}
	
	@RequestMapping("/memberlist.me")
	public String getMemberlist(Model model)throws Exception{
		ArrayList<MemberVO> member_list = memberService.getMemberlist();
		model.addAttribute("member_list", member_list);
		
		return "member_list";
	}
	
	@RequestMapping("/memberinfo.me")
	public String selectMember(MemberVO memberVO, Model model)throws Exception{
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "member_info";
	}
	
	@RequestMapping("/memberdelete.me")
	public String deleteMember(MemberVO memberVO, Model model) throws Exception{
		memberService.deleteMember(memberVO);
		
		return "redirect:/memberlist.me";
	}
	
	@RequestMapping("/memberupdate.me")
	public String updateMember(MemberVO memberVO, Model model) throws Exception{	// memberVO에는 아이디만 저장되있고 나머지 값은 필드이기때문에 디폴드값이 저장되어 있을 것
		MemberVO vo = memberService.selectMember(memberVO);
		model.addAttribute("memberVO", vo);
		
		return "memberupdateform";
	}
	
	@RequestMapping("/updateprocess.me")
	public String updateMemberProcess(MemberVO memberVO, HttpServletResponse response) throws Exception{	// memberVO에 수정폼에서 입력한 값들이 들어있음
		int res = memberService.updateMember(memberVO);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		if(res != 0) {
			writer.write("<script>alert('수정 성공!!');" +" location.href='./memberlist.me';</script>");
			// return "redirect:/memberlist.me";
		}
		else {
			writer.write("<script>alert('수정 실패!!');" +" location.href='./memberupdate.me';</script>");
			// return "redirect:/memberupdate.me";
		}
		return null;
	}	
}
