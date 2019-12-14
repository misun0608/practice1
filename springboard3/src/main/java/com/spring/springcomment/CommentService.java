package com.spring.springcomment;

import java.util.ArrayList;

public interface CommentService {
	public int insertComment(CommentVO vo);
	public ArrayList<CommentVO> getComment(int num);
	public int commentDelete(int comment_num);
	public String getPass(int comment_num);
	public CommentVO detail(int comment_num);
	public int updateComment(CommentVO commentvo);
}
