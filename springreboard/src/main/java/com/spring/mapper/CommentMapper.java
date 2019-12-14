package com.spring.mapper;

import java.util.ArrayList;

import com.spring.springreboard.CommentVO;

public interface CommentMapper {
	int getCommentcount();
	int getMaxnum();
	ArrayList<CommentVO> getCommentlist(CommentVO vo);
	int insertComment(CommentVO vo);
	int deleteComment(int num);
	String getPasswd(int num);
	CommentVO selectComment(int num);
	int updateComment(CommentVO vo);
}
