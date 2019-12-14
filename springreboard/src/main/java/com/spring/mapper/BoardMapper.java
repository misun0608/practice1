package com.spring.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.springreboard.BoardVO;

public interface BoardMapper {
	int getListcount();
	ArrayList<BoardVO> getBoardList(@Param("startrow")int startrow, @Param("endrow")int endrow);
	BoardVO getDetail(int num);
	int getMaxnum();
	int boardInsert(BoardVO boardVO);
	void updateReply(BoardVO boardVO);
	void replyInsert(BoardVO boardVO);
	void setReadCountUpdate(int num);
	void deleteBoard(int num);
	String selectPasswd(int num);
	void updateBoard(BoardVO boardVO);
}
