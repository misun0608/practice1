package com.spring.springreboard;

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
	public ArrayList<CommentVO> getCommentjson(CommentVO vo){
		System.out.println("vo num" + vo.getBoard_num());
		ArrayList<CommentVO> commentList = null;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		commentList = commentMapper.getCommentlist(vo);
		System.out.println("commentList=" + commentList); // null...
		return commentList;
	}
	
	@Override
	public int insertComment(CommentVO vo) {
		int num = 0;
		int count = 0;
		int res = 0;
		
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			
			count = commentMapper.getCommentcount();
			if(count == 0) {
				num = 1;
			}else {
				num = commentMapper.getMaxnum() + 1;
			}
			vo.setComment_num(num);
			res = commentMapper.insertComment(vo);
			
		}catch(Exception e) {
			System.out.println("글쓰기 실패." + e.getMessage());
		}
		
		return res;
	}
	
	@Override
	public int deleteComment(int num, CommentVO vo) {
		int res = 0;
		
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			res = commentMapper.deleteComment(num);
			
		}catch(Exception e) {
			System.out.println("댓글 삭제 실패." + e.getMessage());
		}
		return res;
	}
	
	@Override
	public String getpasswd(int num) {
		String dbpasswd = null;
		CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
		dbpasswd = commentMapper.getPasswd(num);
		
		return dbpasswd;
	}
	
	@Override
	public CommentVO selectComment(int num) {
		CommentVO vo = null;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			vo = commentMapper.selectComment(num);
		}catch(Exception e) {
			System.out.println("수정 데이터 불러오기 실패." + e.getMessage());
		}
		
		return vo;
	}
	
	@Override
	public int updateComment(CommentVO vo) {
		int res = 0;
		try {
			CommentMapper commentMapper = sqlSession.getMapper(CommentMapper.class);
			res = commentMapper.updateComment(vo);
		}catch(Exception e) {
			System.out.println("수정 데이터 불러오기 실패." + e.getMessage());
		}
		return res;
	}
	
}
