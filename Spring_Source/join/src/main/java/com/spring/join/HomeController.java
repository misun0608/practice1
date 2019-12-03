package com.spring.join;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.join.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "join.me", method = RequestMethod.POST)
		public String res(MemberVO vo, Model model) {	// 파라미터를 통해 전달된 데이터들을 vo를 통해서 한번에 처리할 수 있다 / 원래라면 일일히 request불러와야하는데 spring에서 한번에 처리
			String id = vo.getId();
			String pw1 = vo.getPw1();
			String ju1 = vo.getJu1();
			String ju2 = vo.getJu2();
			String gender = vo.getGender();
			String phone1 = vo.getPhone1();
			String phone2 = vo.getPhone2();
			String phone3 = vo.getPhone3();
			String email1 = vo.getEmail1();
			String email2 = vo.getEmail2();
			String hobby[] = vo.getHobby();
			String hobby_res = "";
		      int i;
		      for (i=0; i<hobby.length-1; i++)
		         hobby_res += hobby[i] + ", ";
		      hobby_res += hobby[i];
			String intro = vo.getIntro();
			
			model.addAttribute("id", id);
			model.addAttribute("pw", pw1);
			model.addAttribute("idnum", ju1 + "-" +ju2);
			model.addAttribute("gender", gender);
			model.addAttribute("phone", phone1 + "-" + phone2 + "-" + phone3);
			model.addAttribute("email", email1 + "@" + email2);
		    model.addAttribute("hobby", hobby_res);
			model.addAttribute("intro", intro);
			
			// model.addAttribute("vo", vo);
			
			return "res";
		}
}
