package com.spring.mapper;

import java.util.ArrayList;

import com.spring.springboardM2.CommentVO;

public interface CommentMapper {
	int getCommentCount();
	int getMaxCommentNum();
	ArrayList<CommentVO> getCommentList(int BOARD_NUM);
	int insertComment(CommentVO vo);
	String getCommentPassword(int comment_num);
	int updateComment(CommentVO vo);
	int deleteComment(int comment_num);
}
