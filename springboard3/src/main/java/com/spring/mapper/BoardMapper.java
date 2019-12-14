package com.spring.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.springboard3.BoardVO;

public interface BoardMapper {
	ArrayList<BoardVO> getArticles(@Param("start") int start, @Param("end") int end);
	int getArticleCount();
	int insertArticle(BoardVO boardvo);
	BoardVO getArticle(String num);
	String selectPass(int num);
	int updateArticle(BoardVO boardvo);
	void updateReadCount(BoardVO boardvo);
	void deleteArticle(String num);
}
