package com.spring.springboard3;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	@RequestMapping("list.do")
	public String getArticles(HttpServletRequest request, Model model) throws Exception {
		int pageSize = 10;
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;	
		int endRow = startRow + pageSize - 1;
		int count = 0;
		int number = 0;
		
		ArrayList<BoardVO> articleList = null;
		count = boardservice.getArticleCount();
		
		if(count < startRow) { 
			currentPage = currentPage - 1;
			startRow = (currentPage - 1) * pageSize + 1;
			endRow = startRow + pageSize - 1;
		}
		if(count > 0) {
			//BoardVO boardvo = new BoardVO();
			//boardvo.setStart(startRow);
			//boardvo.setEnd(endRow);
			articleList = boardservice.getArticles(startRow, endRow);
			number = count - (currentPage - 1) * pageSize;
		}
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("startRow", startRow);
		model.addAttribute("endRow", endRow);
		model.addAttribute("count", count);
		model.addAttribute("number", number);
		model.addAttribute("articleList", articleList);
		model.addAttribute("pageSize", pageSize);
		
		return "list";
	}
	
	@RequestMapping("writeform.do")
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping("writeprocess.do")
	public String writeProcess(MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter writer = response.getWriter();
		String downlink = null;		
		MultipartFile mf = request.getFile("file");
	
		if(!mf.isEmpty()) {
			String uploadPath = "C:\\Project138\\upload\\";
			String originalFileExtension = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf("."));
			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + originalFileExtension;
			
			if(mf.getSize() != 0) {
				mf.transferTo(new File(uploadPath+storedFileName));
			}
			
			downlink = "fileDownload.do?of="+ URLEncoder.encode(storedFileName, "utf-8")
						+ "&of2=" + URLEncoder.encode(mf.getOriginalFilename(), "utf-8");
		} else {
			downlink = "첨부파일 없음";
		}
	
		BoardVO boardvo = new BoardVO();
		boardvo.setPass(request.getParameter("pass"));
		boardvo.setWriter(request.getParameter("writer"));
		boardvo.setSubject(request.getParameter("subject"));
		boardvo.setEmail(request.getParameter("email"));
		boardvo.setContent(request.getParameter("content"));
		boardvo.setReg_date(new Timestamp(System.currentTimeMillis()));
		boardvo.setReadcount(0);
		boardvo.setFilename(mf.getOriginalFilename());
		boardvo.setFileloc(downlink);
		
		int x = boardservice.insertArticle(boardvo);
		if(x == 0) {
			writer.write("<script>alert('글작성 실패');location.href='./writeform.do';</script>");
		} else {
			writer.write("<script>location.href='./list.do';</script>");
		}
		
		return null;
	}
	
	@RequestMapping("content.do")
	public String getArticle(HttpServletRequest request, Model model) {
		int currentPage = Integer.parseInt(request.getParameter("pageNum"));
		int number = Integer.parseInt(request.getParameter("number"));
		BoardVO boardvo = boardservice.getArticle(request.getParameter("num"));
		boardservice.updateReadCount(boardvo);
		
		model.addAttribute("article", boardvo);
		model.addAttribute("pageNum", currentPage);
		model.addAttribute("number", number);
		return "content";
	}
	
	@RequestMapping("fileDownload.do")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		
		String of = request.getParameter("of");
		String of2 = request.getParameter("of2");
		
		String uploadPath = "C:\\Project138\\upload\\";
		String fullPath = uploadPath + of;
		File downloadFile = new File(fullPath);
		
		response.setContentType("application/download; charset=utf-8");
		response.setContentLength((int)downloadFile.length());
		response.setHeader("Content-Disposition", "attachment;filename=" + new String(of2.getBytes(), "ISO8859_1"));
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fin = new FileInputStream(downloadFile);
		ServletOutputStream sout = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int size = -1;
		
		while((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0 , size);
		}
		
		fin.close();
		sout.close();
	}
	
	@RequestMapping("updateform.do")
	public String updateForm(HttpServletRequest request, Model model) {
		BoardVO boardvo = boardservice.getArticle(request.getParameter("num"));
		model.addAttribute("article", boardvo);
		return "updateForm";
	}
	
	@RequestMapping("update.do")
	public String update(BoardVO boardvo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter writer = response.getWriter();
		String pageNum = request.getParameter("pageNum");
		
		String dbpass = boardservice.selectPass(boardvo.getNum());
		if(dbpass.equals(boardvo.getPass())) {
		int x = boardservice.updateArticle(boardvo);
		if(x==0) {
			writer.write("<script>alert('글수정 실패');location.href='./updateform.do?pageNum="+pageNum+"';</script>");
		}
			writer.write("<script>location.href='./list.do?pageNum="+pageNum+"';</script>");
		} else {
			writer.write("<script>alert('패스워드가 틀렸습니다');location.href='./updateform.do?pageNum="+pageNum+"';</script>");
		}
		
		return null;
	}
	
	@RequestMapping("deleteform.do")
	public String deleteForm(HttpServletRequest request) {
		return "deleteForm";
	}
	
	@RequestMapping("delete.do")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8"); 
		String pageNum = request.getParameter("pageNum");
		PrintWriter writer = response.getWriter();
		
		String dbpass = boardservice.selectPass(Integer.parseInt(request.getParameter("num")));
		if(dbpass.equals(request.getParameter("pass"))) {
			boardservice.deleteArticle(request.getParameter("num"));
			writer.write("<script>location.href='./list.do?pageNum="+pageNum+"';</script>");
		} else {
			writer.write("<script>alert('패스워드가 틀렸습니다');location.href='./deleteform.do?pageNum="+pageNum+"';</script>");
		}
		
		return null;
	}
}
