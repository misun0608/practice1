package com.spring.springcomment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentservice;
	
	@PostMapping(value="/insertcomment.do", produces="application/json;charset=UTF-8")
	public Map<String, Object> insertComment(CommentVO commentvo) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		commentvo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		try {
			int res = commentservice.insertComment(commentvo);
			
			retVal.put("res", "OK");
		} catch(Exception e) {
			retVal.put("res", "Fail");
			retVal.put("message","Failure");
		}
		return retVal;
	}
	
	@PostMapping(value="/getcomment.do", produces="application/json;charset=UTF-8")
	public ArrayList<CommentVO> getComment(CommentVO commentvo) {
		ArrayList<CommentVO> list = commentservice.getComment(commentvo.getBoard_num());
		
		return list;
	}
	
	@GetMapping(value="/commentdelete.do", produces="application/json;charset=UTF-8")
 	public Map<String, Object> commentDelete(CommentVO commentvo) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		String dbpass = commentservice.getPass(commentvo.getComment_num());
		if(dbpass.equals(commentvo.getPass())) {
			int res = commentservice.commentDelete(commentvo.getComment_num());
				
			retVal.put("res", "OK");
		} else {
			retVal.put("res", "Fail");
			retVal.put("message","패스워드가 틀렸습니다");
		}
 		return retVal;
 	}
	
	@GetMapping(value="/commentupdateform.do", produces="application/json;charset=UTF-8")
	public Map<String, Object> commentUpdateForm(CommentVO commentvo) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		
		String dbpass = commentservice.getPass(commentvo.getComment_num());
		if(dbpass.equals(commentvo.getPass())) {
			CommentVO comment = commentservice.detail(commentvo.getComment_num());
			
			retVal.put("detail", comment);
			retVal.put("res", "OK");
		} else {
			retVal.put("res", "Fail");
			retVal.put("message","패스워드가 틀렸습니다");
		}
		return retVal;
	}
	
	@GetMapping(value="/updateprocess.do", produces="application/json;charset=UTF-8")
	public Map<String, Object> updateProcess(CommentVO commentvo) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		commentvo.setReg_date(new Timestamp(System.currentTimeMillis()));
		try {
			int res = commentservice.updateComment(commentvo);
			
			retVal.put("res", "OK");
		} catch(Exception e) {
			retVal.put("res", "Fail");
			retVal.put("message","Failure");
		}	
		return retVal;
	}
	
}
