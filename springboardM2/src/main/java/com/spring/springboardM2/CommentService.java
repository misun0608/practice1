package com.spring.springboardM2;

import java.util.ArrayList;

public interface CommentService {
	ArrayList<CommentVO> getCommentList(int BOARD_NUM);
	int insertComment(CommentVO vo);
	int updateComment(CommentVO vo);
	int deleteComment(CommentVO vo);
}
