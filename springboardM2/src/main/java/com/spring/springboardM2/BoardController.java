package com.spring.springboardM2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam(value="page", required=false, defaultValue="1") int page) {
		ModelAndView mav = new ModelAndView();
		ArrayList<BoardVO> boardlist = null;
		
		int limit = 10;
		int startrow = (page-1)*10 + 1; // 읽기 시작할 row 번호
		int endrow = startrow + limit - 1; // 읽을 마지막 row 번호
		
		boardlist = boardService.getBoardList(startrow, endrow);
		
		int listcount = boardService.getBoardCount();
		int maxpage=(int)((double)listcount/limit + 0.95); // 총페이지 수
		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if(endpage > maxpage) endpage = maxpage;
		
		mav.addObject("boardlist", boardlist);
		mav.addObject("listcount", listcount);
		mav.addObject("page", page);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("maxpage", maxpage);
		mav.setViewName("qna_board_list");
		
		return mav;
	}
	
	@RequestMapping(value="/BoardWriteForm.do", method=RequestMethod.GET)
	public String boardwriteform() {
		return "qna_board_write";
	}
	
	@RequestMapping(value="/BoardWrite.do", method=RequestMethod.POST)
	public String boardwrite(BoardVO boardVO, RequestModel model) throws Exception {
		MultipartFile mf = model.getFile();
		String uploadPath = "C:\\Project138\\upload\\";
		String originalFileExtension = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
		String storedFileName = UUID.randomUUID().toString().replace("-", "") + originalFileExtension;
		
		if(mf.getSize() != 0) {
			mf.transferTo(new File(uploadPath + storedFileName));
		}
		
		boardVO.setBOARD_ORI_FILE(mf.getOriginalFilename());
		boardVO.setBOARD_STORED_FILE(storedFileName);
		
		boardService.boardInsert(boardVO);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping(value="BoardDetail.do", method=RequestMethod.GET)
	public ModelAndView boarddetail(int num) {
		ModelAndView mav = new ModelAndView();
		BoardVO boardVO = boardService.getDetail(num);
		mav.addObject("boardVO", boardVO);
		mav.setViewName("qna_board_view");
		return mav;
	}
	
	@RequestMapping(value="/fileDownload.do", method=RequestMethod.GET)
	public void filedownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		String of = request.getParameter("of");
		String of2 = request.getParameter("of2");
		String uploadPath = "C:\\Project138\\upload\\";
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		response.setContentType("application/download; charset=UTF-8");
		response.setContentLength((int)downloadFile.length());
		response.setHeader("Content-Disposition", "attachment;filename="+new String(of2.getBytes(),"ISO8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = -1;
		while ((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}
	
	@RequestMapping(value="/BoardReplyForm.do", method=RequestMethod.GET)
	public ModelAndView boardreplyform(int num) throws Exception {
		ModelAndView mav = new ModelAndView();
		BoardVO boardVO = boardService.getDetail(num);
		mav.addObject("boardVO", boardVO);
		mav.setViewName("qna_board_reply");
		return mav;
	}
	
	@RequestMapping(value="/BoardReply.do", method=RequestMethod.POST)
	public String boardreply(BoardVO vo) throws Exception {
		boardService.boardReply(vo);
		
		return "redirect:list.do";
	}
	
	@RequestMapping(value="BoardUpdateForm.do", method=RequestMethod.GET)
	public ModelAndView boardupdateform(int num) throws Exception {
		ModelAndView mav = new ModelAndView();
		BoardVO boardVO = boardService.getDetail(num);
		mav.addObject("boardVO", boardVO);
		mav.setViewName("qna_board_update");
		return mav;
	}
	
	@RequestMapping(value="BoardUpdate.do", method=RequestMethod.POST)
	public String boardupdate(BoardVO vo) throws Exception {
		int res = boardService.boardUpdate(vo);
		
		if(res == -1) {
			System.out.println("수정 실패 : 비밀번호 불일치");
		} else if(res > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("수정 실패");
		}
		
		String url = "redirect:/BoardDetail.do?num=" + vo.getBOARD_NUM();
		return url;
	}
	
	@RequestMapping(value="BoardDeleteForm.do", method=RequestMethod.GET)
	public ModelAndView boarddeleteform(int num) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", num);
		mav.setViewName("qna_board_delete");
		
		return mav;
	}
	
	@RequestMapping(value="BoardDelete.do")
	public String boarddelete(int num, BoardVO vo) throws Exception {
		vo.setBOARD_NUM(num);
		int res = boardService.boardDelete(vo);
		
		if(res == -1) {
			System.out.println("삭제 실패 : 비밀번호 불일치");
		} else if(res > 0) {
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		return "redirect:/list.do";
	}

}
