package com.spring.springmybatisboard;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "list.bo") // @RequestParam은 파라메터값이 없으면 오류가 발생할수 있으므로 defaultValue값 설정
	public String list(@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum, Model model) {	// pageNum 파라미터가 존재하면 int pageNum에 그 값을 대입하고 아니면 말고
		int pageSize = 10;
		ArrayList<BoardVO> list = null;
		
		int count = boardService.getBoardcount();
				
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		
		if (count < startRow)	// 언제 이런상황인지 알아야함
		{
			currentPage = currentPage - 1;
			startRow = (currentPage - 1) * pageSize + 1;
			endRow = startRow + pageSize - 1;
		}
		if(count > 0)
			list = boardService.getBoardlist(startRow, endRow);
		
		int number = count - (currentPage - 1) * pageSize; 
		int pageCount = ((count-1) / pageSize)+1;
		int startPage = 1;

		int pageBlock = 10;
		if(currentPage%pageBlock != 0)
			startPage = (int)(currentPage/pageBlock)*pageBlock+1;
		else
			startPage = currentPage - 9;

		model.addAttribute("list", list );
		model.addAttribute("count", count );
		model.addAttribute("currentPage", currentPage );
		model.addAttribute("number", number );
		model.addAttribute("pageCount", pageCount );
		model.addAttribute("pageBlock", pageBlock );
		model.addAttribute("startPage", startPage );
		
		return "list";
	}
	
	@RequestMapping(value = "writeForm.bo")
	public String writeForm() {
	
		return "writeForm";
	}
	
	@RequestMapping(value = "writePro.bo")
	public String writePro(BoardVO vo, HttpServletResponse response) {
		try {
			boardService.writePro(vo);
		}
		catch(Exception e) {
			System.out.println("글쓰기 에러 : " + e.getMessage());
		}

		return "redirect:list.bo";
	}
	
	@RequestMapping(value = "content.bo")
	public String content(HttpServletRequest request, BoardVO vo, Model model) {
		// list에서 처럼 Requestparam으로 받는 방법도 있고 지금처럼 request로 받는 방법이 있다
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		int number = Integer.parseInt(request.getParameter("number"));

		BoardVO boardvo = boardService.selectBoard(vo);	// num만 저장돼있음
		
		model.addAttribute("boardvo", boardvo);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
		
		return "content";
	}
	
	@RequestMapping(value = "updateForm.bo")
	public String updateForm(BoardVO vo, Model model) {
		BoardVO boardvo = boardService.selectBoard(vo);	// vo에 num저장
		
		model.addAttribute("boardvo", boardvo);

		return "updateForm";
	}
	
	@RequestMapping(value = "updatePro.bo")
	public String updatePro(HttpServletRequest request, HttpServletResponse response, BoardVO vo, Model model) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		try {
			int res = boardService.updatePro(vo);
			
			if (res == 1)	// 수정성공
			{
				return "redirect:list.bo";
			}
			else	// 수정실패
			{
				System.out.println("vo.getNum()=" + vo.getNum());
				PrintWriter writer = response.getWriter();
				writer.write("<script>location.href='updateForm.bo?num=" + vo.getNum() + "&pageNum=" + pageNum + "';</script>");
				return null;	// 리턴값 필요해서 형식상 null로
			}
		}
		catch(Exception e) {
			System.out.println("에러 : " + e.getMessage());
		}
		
		return null;
	}
	
	@RequestMapping(value = "deleteForm.bo")
	public String deleteForm(HttpServletRequest request, BoardVO vo, Model model) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		model.addAttribute("num", vo.getNum());
		model.addAttribute("pageNum", pageNum);
		
		return "deleteForm";
	}
	
	@RequestMapping(value = "deletePro.bo")
	public String deletePro(HttpServletRequest request, BoardVO vo, Model model) {
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		int res = boardService.deletePro(vo);	// vo에 num, pageNum 저장돼있음
		
		if (res == 1)
		{
			return "redirect:list.bo";
		}
		else
		{
			model.addAttribute("boardvo", vo);
			model.addAttribute("num", vo.getNum());
			model.addAttribute("pageNum", pageNum);
			return "redirect:deleteForm.bo";
		}
	}
}
