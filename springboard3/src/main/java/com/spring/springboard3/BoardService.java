package com.spring.springboard3;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface BoardService {
	public ArrayList<BoardVO> getArticles(@Param("start") int start, @Param("end") int end);
	public int getArticleCount();
	public int insertArticle(BoardVO boardvo);
	public BoardVO getArticle(String num);
	public String selectPass(int num);
	public int updateArticle(BoardVO boardvo);
	public void updateReadCount(BoardVO boardvo);
	public void deleteArticle(String num);
}
