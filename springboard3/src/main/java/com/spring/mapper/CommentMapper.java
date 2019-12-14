package com.spring.mapper;

import java.util.ArrayList;

import com.spring.springcomment.CommentVO;

public interface CommentMapper {
	int insertComment(CommentVO vo);
	ArrayList<CommentVO> getComment(int num);
	int commentDelete(int comment_num);
	String getPass(int comment_num);
	CommentVO detail(int comment_num);
	int updateComment(CommentVO commentvo);

}
