package com.spring.springcomment;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertComment(CommentVO vo) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		int res = commentmapper.insertComment(vo);
		return res;
	}
	
	@Override
	public ArrayList<CommentVO> getComment(int num) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		ArrayList<CommentVO> list = commentmapper.getComment(num);
		return list;
	}
	
	@Override
	public int commentDelete(int comment_num) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		int res = commentmapper.commentDelete(comment_num);
		return res;
	}
	
	@Override
	public String getPass(int comment_num) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		String dbpass = commentmapper.getPass(comment_num);
		return dbpass;
	}
	
	@Override
	public CommentVO detail(int comment_num) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		CommentVO detail = commentmapper.detail(comment_num);
		return detail;
	}
	
	@Override
	public int updateComment(CommentVO commentvo) {
		CommentMapper commentmapper = sqlSession.getMapper(CommentMapper.class);
		int res = commentmapper.updateComment(commentvo);
		return res;
	}
	
	
	

}
