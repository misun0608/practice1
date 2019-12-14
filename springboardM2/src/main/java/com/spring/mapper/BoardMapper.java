package com.spring.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.springboardM2.BoardVO;

public interface BoardMapper {
	int getCount();
	ArrayList<BoardVO> getBoardList(@Param("startrow") int start, @Param("endrow") int end);
	BoardVO getDetail(int board_num);
	int selectMaxBoardNum();
	int boardInsert1(BoardVO vo);
	void updateBoardReSeq(BoardVO vo);
	int boardInsert2(BoardVO vo);
	int boardUpdate(BoardVO vo);
	String getBoardPass(int board_num);
	int boardDelete(int board_num);
	void setReadCountUpdate(int board_num);
}
