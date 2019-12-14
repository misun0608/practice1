package com.spring.springboard3;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<BoardVO> getArticles(@Param("start") int start, @Param("end") int end) {
		ArrayList<BoardVO> articleList = new ArrayList<BoardVO>();
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		articleList = boardmapper.getArticles(start, end);
		return articleList;
	}
	
	@Override
	public int getArticleCount() {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardmapper.getArticleCount();
		return res;
	}
	
	@Override
	public int insertArticle(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardmapper.insertArticle(boardvo);
		return res;
	}
	
	@Override
	public BoardVO getArticle(String num) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO boardvo = boardmapper.getArticle(num);
		return boardvo;
	}
	
	@Override
	public String selectPass(int num) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		String dbpass = boardmapper.selectPass(num);
		return dbpass;
	}
	
	@Override
	public int updateArticle(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		int res = boardmapper.updateArticle(boardvo);
		return res;
	}
	
	@Override
	public void updateReadCount(BoardVO boardvo) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		boardmapper.updateReadCount(boardvo);
	}
	
	@Override
	public void deleteArticle(String num) {
		BoardMapper boardmapper = sqlSession.getMapper(BoardMapper.class);
		boardmapper.deleteArticle(num);
	}

}
