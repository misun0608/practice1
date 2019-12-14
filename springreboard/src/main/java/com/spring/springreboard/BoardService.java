package com.spring.springreboard;

import java.util.ArrayList;

public interface BoardService {
	public int getListcount();
	public ArrayList<BoardVO> getBoardlist(int page, int limit);
	public int boardInsert(BoardVO boardVO);
	public BoardVO getDetail(int num);
	public int replyInsert(BoardVO boardVO);
	public int deleteBoard(BoardVO boardVO, int num);
	public int updateBoard(BoardVO boardVO, int num);
//	public void updateReply(BoardVO boardVO);
}
