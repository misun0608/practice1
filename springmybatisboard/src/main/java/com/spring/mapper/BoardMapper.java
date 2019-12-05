package com.spring.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.springmybatisboard.BoardVO;

public interface BoardMapper {
	int getBoardcount();
	public ArrayList<BoardVO> getBoardlist(@Param("startRow") int startRow, @Param("endRow") int endRow);
//	public HashMap<String, Integer> getBoardlist(int startRow, int endRow);
	public Integer writePro(BoardVO boardvo);
	public void writePro2(BoardVO boardvo);
	public int writePro3(BoardVO boardvo);
	public void selectBoard(BoardVO boardvo);
	public BoardVO selectBoard2(BoardVO boardvo);
	public BoardVO updatePro(BoardVO boardvo);
	public int updatePro2(BoardVO boardvo);
	public BoardVO deletePro(BoardVO boardvo);
	public int deletePro2(BoardVO boardvo);
}
