package com.spring.springmybatisboard;

import java.util.ArrayList;

public interface BoardService {
	public int getBoardcount();
	public ArrayList<BoardVO> getBoardlist(int startRow, int endRow);
	public int writePro(BoardVO boardVO);
	public BoardVO selectBoard(BoardVO boardVO);
	public int updatePro(BoardVO boardVO);
	public int deletePro(BoardVO boardVO);
}
