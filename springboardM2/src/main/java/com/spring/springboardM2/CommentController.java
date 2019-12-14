package com.spring.springboardM2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping(value="/getCommentList.do",produces="application/json;charset=UTF-8")
	public ArrayList<CommentVO> getCommentList(CommentVO vo) {
		ArrayList<CommentVO> commentlist = commentService.getCommentList(vo.getBOARD_NUM());
		return commentlist;
	}
	
	@PostMapping(value="/insertComment.do",produces="application/json;charset=UTF-8")
	public Map<String, Object> insertComment(CommentVO vo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); // 리턴값 저장
		try {
			int res = commentService.insertComment(vo);
			if(res > 0)
				retVal.put("res","OK");
			else
				retVal.put("res", "FAIL1");
		} catch(Exception e) {
			retVal.put("res", "FAIL2");
			retVal.put("message", "Failure");
		}
		
		return retVal;
	}
	
	@PostMapping(value="/updateComment.do",produces="application/json;charset=UTF-8")
	public Map<String, Object> updateComment(CommentVO vo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); // 리턴값 저장
		try {
			int res = commentService.updateComment(vo);
			if(res > 0)
				retVal.put("res","OK");
			else if(res == -1)
				retVal.put("res","PASSWORD ERROR");
			else
				retVal.put("res", "FAIL1");
		} catch(Exception e) {
			retVal.put("res", "FAIL2");
			retVal.put("message", "Failure");
		}
		return retVal;
	}
	
	@PostMapping(value="/deleteComment.do",produces="application/json;charset=UTF-8")
	public Map<String, Object> deleteComment(CommentVO vo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); // 리턴값 저장
		try {
			int res = commentService.deleteComment(vo);
			if(res > 0)
				retVal.put("res","OK");
			else if(res == -1)
				retVal.put("res","PASSWORD ERROR");
			else
				retVal.put("res", "FAIL1");
		} catch(Exception e) {
			retVal.put("res", "FAIL2");
			retVal.put("message", "Failure");
		}
		return retVal;
	}

}
