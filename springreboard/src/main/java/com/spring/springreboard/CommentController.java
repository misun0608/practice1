package com.spring.springreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping(value = "/getCommentJSON.bo", produces = "application/json;charset=UTF-8")
	// RequestMapping와 method=RequestMethod.POST 결합한 형태 /GetMapping도 존재
	// 둘다 받고싶으면 원래처럼 RequestMapping 쓰고 method 지정안해주면 됨
	public ArrayList<CommentVO> getPeopleJSONGET(CommentVO vo) {
		ArrayList<CommentVO> list = commentService.getCommentjson(vo);

		return list;
	}

	@PostMapping(value = "/insertComment.bo", produces = "application/json;charset=UTF-8")
	public Map<String, Object> insertComment(CommentVO vo) {
		Map<String, Object> retVal = new HashMap<String, Object>(); // 리턴값 저장

		try {
			int res = commentService.insertComment(vo);

			retVal.put("res", "OK");

		} catch (Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal;
	}

	@GetMapping(value = "/deleteComment.bo", produces = "application/json;charset=UTF-8")
	public Map<String, Object> deleteComment(String passwd, CommentVO vo, HttpServletRequest request) {
		String dbpasswd = null;
		int num = vo.getComment_num(); // 제대로 나옴
		Map<String, Object> retVal = new HashMap<String, Object>(); // 리턴값 저장
		System.out.println("입력 비번" + passwd);

		dbpasswd = commentService.getpasswd(num);
		System.out.println("db비번" + dbpasswd);

		try {
			if (passwd.equals(dbpasswd)) {
				int res = commentService.deleteComment(num, vo);
				System.out.println("delete 컨트롤러 num=" + num);
				retVal.put("res", "OK");
			}

		} catch (Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal;
	}

	@GetMapping(value = "/updateCommentForm.bo", produces = "application/json;charset=UTF-8")
	public CommentVO updateCommentForm(CommentVO vo) {
//		Map<String, Object> retVal = new HashMap<String, Object>();
		int num = vo.getComment_num();
		System.out.println("수정 comment_num" + num);
		CommentVO commentVO = null;
		try {
			commentVO = commentService.selectComment(num);
//			retVal.put("res", "OK");

		} catch (Exception e) {
//			retVal.put("res", "FAIL");
//			retVal.put("message", "Failure");
		}
		return commentVO;
	}

	@GetMapping(value = "/updateComment.bo", produces = "application/json;charset=UTF-8")
	public Map<String, Object> updateComment(CommentVO vo, HttpServletRequest request) {
		Map<String, Object> retVal = new HashMap<String, Object>();
		String dbpasswd = null;
		String passwd = null;
		
		int num = vo.getComment_num();
		System.out.println("수정 comment_num" + num);
		
		dbpasswd = commentService.getpasswd(num);
		System.out.println("db비번" + dbpasswd);
		passwd = vo.getComment_pass();
		System.out.println("입력 비번" + passwd);
		
		try {
			if (passwd.equals(dbpasswd)) {
				int res = commentService.updateComment(vo);
				System.out.println("update 컨트롤러 comment_num=" + vo.getComment_num());
				retVal.put("res", "OK");
			}

		} catch (Exception e) {
			retVal.put("res", "FAIL");
			retVal.put("message", "Failure");
		}
		return retVal;
	}
}
