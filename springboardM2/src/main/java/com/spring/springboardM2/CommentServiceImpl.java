package com.spring.springboardM2;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.CommentMapper;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<CommentVO> getCommentList(int BOARD_NUM) {
		ArrayList<CommentVO> commentlist = null;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		commentlist = commentMapper.getCommentList(BOARD_NUM);
		return commentlist;
	}

	@Override
	public int updateComment(CommentVO vo) {
		int res = 0;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		String dbpasswd = commentMapper.getCommentPassword(vo.getComment_num());
		if(dbpasswd.equals(vo.getComment_password())) {
			res = commentMapper.updateComment(vo);
		} else {
			res = -1;
		}
		return res;
	}

	@Override
	public int deleteComment(CommentVO vo) {
		int res = 0;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		String dbpasswd = commentMapper.getCommentPassword(vo.getComment_num());
		if(dbpasswd.equals(vo.getComment_password())) {
			res = commentMapper.deleteComment(vo.getComment_num());
		} else {
			res = -1;
		}
		return res;
	}

	@Override
	public int insertComment(CommentVO vo) {
		int res = 0;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		if(commentMapper.getCommentCount() != 0) {
			int maxnum = commentMapper.getMaxCommentNum();
			vo.setComment_num(maxnum + 1);
		} else {
			vo.setComment_num(1);
		}
		res = commentMapper.insertComment(vo);
		return res;
	}

}
