package com.spring.springboardM2;

import java.util.ArrayList;

public interface BoardService {
	int getBoardCount();
	ArrayList<BoardVO> getBoardList(int startRow, int endRow);
	BoardVO getDetail(int board_num);
	int boardInsert(BoardVO vo);
	int boardReply(BoardVO vo);
	int boardUpdate(BoardVO vo);
	int boardDelete(BoardVO vo);
}
