package com.spring.springreboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="list.bo")
	public String list(@RequestParam(value="pageNum", required=false, defaultValue="1")int pageNum, Model model, HttpServletRequest request) {
		ArrayList<BoardVO> boardlist = new ArrayList();	//arrayList에 추가될 클래스타입이 생략되어있으면 어떤 타입이든 들어올 수 있음 / 대신! 객체를 가져올 때 캐스트연산을 꼭 해야함
		
		int page=1;
		int limit=10;	// 한 페이지에 뿌려줄 개수
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=boardService.getListcount();	// 총 리스트 수를 받아옴(글의 개수 구하는 작업)
		boardlist=boardService.getBoardlist(page, limit);
		//리스트를 받아옴
		
		// 총 페이지 수
		int maxpage=(int)((double)listcount/limit+0.95);
		//0.95를 더해서 올림 처리
		
		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)	/ 스타트 엔드 다른데 방식이 다른 것
		int startpage = (((int)((double)page/10+0.9))-1)*10+1;	// 10대신 limit써도 되고 / 이전 예제 공식으로 구해도 똑같은 결과 나옴
		
		//현재 페이지에 보여줄 마지막 페이지 수(10,20,30 등...)
		int endpage=startpage+10-1;
		
		if(endpage>maxpage)endpage=maxpage;
		
		model.addAttribute("page", page); // 현재 페이지
		model.addAttribute("maxpage", maxpage); // 최대 페이지
		model.addAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지 수
		model.addAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지 수
		model.addAttribute("listcount", listcount); // 글 수
		model.addAttribute("boardlist", boardlist);
		
		return "list";
	}
	
	@RequestMapping(value = "BoardWrite.bo")
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value = "insertboard.bo")
	public String writePro(MultipartHttpServletRequest request, BoardVO boardVO, Model model) throws Exception{
		try {
			MultipartFile mf = request.getFile("file");	// servlet xml 설정 확인잘하자...
			String uploadPath = "C:\\Project138\\upload\\";
			String originalFileExtension = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
			if(mf.getSize() != 0) {
				mf.transferTo(new File(uploadPath+storedFileName));
			}
			boardVO.setBoard_originfile(mf.getOriginalFilename());
			boardVO.setBoard_storedfile(storedFileName);
			boardService.boardInsert(boardVO);
			
		}catch(Exception e) {
			System.out.println("글쓰기 에러" + e.getMessage());
		}
		return "redirect:list.bo";
		// modelandview로 했더니 list가 null 떠서 오류 남...
	}
	
	@RequestMapping(value = "content.bo")
	public String content(HttpServletRequest request, BoardVO vo, Model model) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardVO boardVO = boardService.getDetail(num);
		
		model.addAttribute("fileName", boardVO.getBoard_originfile());
		model.addAttribute("storedFileName", boardVO.getBoard_storedfile());

		// fileName, storedFileName이 null이면 아래에서 오류남
		if(boardVO.getBoard_originfile() != null || boardVO.getBoard_storedfile() != null) {
			String downlink = "fileDownload.bo?of="+URLEncoder.encode(boardVO.getBoard_storedfile(),"utf-8") +"&of2=" + URLEncoder.encode(boardVO.getBoard_originfile(),"utf-8");
			// .bo 붙여야함 아니면 못찾음
			model.addAttribute("downlink", downlink);
		}
		
		model.addAttribute("boarddata", boardVO);
		
		return "view";
	}
	
	@RequestMapping("fileDownload.bo")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		
		String of = request.getParameter("of"); // 서버에 업로드된 변경된 실제 파일명
		String of2 = request.getParameter("of2"); // 오리지널 파일명
		
		/*
		 // 웹사이트 루트디렉토리의 실제 디스크상의 경로 알아내기.
		 String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		 String fullPath = uploadPath + "/" + of;
		 */
		
		String uploadPath = "C:\\Project138\\upload\\";
		// 직접 경로 지정
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		
		// 파일 다운로드를 위해 컨텐츠 타입을 application/download 설정
		response.setContentType("application/download;charset=UTF-8");
		// 파일 사이즈 지정
		
		response.setContentLength((int)downloadFile.length());
		// 다운로드 창을 띄우기 위한 헤더 조작
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(of2.getBytes(),"ISO8859_1"));
		
		response.setHeader("Content-Transfer-Encoding", "binary");
		/*
		 * Content-disposition 속성
		 * 1) "Content-disposition: attachment" 브라우저 인식 파일 확장자를 포함하여 모든 확장자의 파일들에 대해, 다운로드시 무조건 "파일 다운로드" 대화상자가 뜨도록 하는 헤더 속성이다.
		 * 2) "Content-disposition: inline" 브라우저 인식 파일 확장자를 가진 파일들에 대해서는 웹브라우저 상에서 바로 파일을 열고, 그외의 파일들에 대해서는 "파일 다운로드" 대화상자가 뜨도록 하는 헤더속성이다.
		 */
		
		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int size = -1;
		
		while((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}
	
	@RequestMapping(value ="replyform.bo")
	public String replyForm(BoardVO boardVO, Model model, HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		model.addAttribute("boarddata", boardService.getDetail(num));
		
		return "replyForm";
	}
	
	@RequestMapping(value="replyPro.bo")
	public String replyPro(BoardVO boardVO, Model model) {
		int num = boardService.replyInsert(boardVO);
		
		return "redirect:content.bo?num="+num;
	}
	
	@RequestMapping(value="deleteform.bo")
	public String deleteForm(HttpServletRequest request, BoardVO boardVO) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		return "deleteForm";
	}
	
	@RequestMapping(value="deletePro.bo")
	public String deletePro(HttpServletRequest request, BoardVO boardVO) {
		int num = Integer.parseInt(request.getParameter("num"));
		int res = boardService.deleteBoard(boardVO, num);
		
		if(res == 1) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("실패(비밀번호 확인)");
		}
		
		return "redirect:list.bo";
	}
	
	@RequestMapping(value="updateform.bo")
	public String deleteForm(HttpServletRequest request, BoardVO boardVO, Model model) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardVO vo = boardService.getDetail(num);
		model.addAttribute("boarddata", vo);
		
		return "updateForm";
	}
	
	@RequestMapping(value="updatePro.bo")
	public String updatePro(HttpServletRequest request, BoardVO boardVO) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int res = boardService.updateBoard(boardVO, num);
		
		if(res == 1) {
			System.out.println("수정 성공");
		}else {
			System.out.println("수정 실패(비밀번호 확인)");
		}
		
		return "redirect:list.bo";
	}
	
	
}
