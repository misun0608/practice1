package com.spring.springreboard;

import java.util.ArrayList;

public interface CommentService {
	ArrayList<CommentVO> getCommentjson(CommentVO vo);
	int insertComment(CommentVO vo);
	int deleteComment(int num, CommentVO vo);
	String getpasswd(int num);
	CommentVO selectComment(int num);
	int updateComment(CommentVO vo);
}
