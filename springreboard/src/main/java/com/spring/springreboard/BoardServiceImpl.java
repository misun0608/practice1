package com.spring.springreboard;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int getListcount() {
		int x = 0;
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			x = boardMapper.getListcount();
			System.out.println(x);
		}catch(Exception e) {
			System.out.println("카운트 실패." + e.getMessage());
		}
		return x;
	}

	@Override
	public ArrayList<BoardVO> getBoardlist(int page, int limit) {
		ArrayList<BoardVO> list = new ArrayList();
		
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			list = boardMapper.getBoardList(startrow, endrow);
			
		}catch(Exception e) {
			System.out.println("리스트 실패." + e.getMessage());
		}
		
		return list;
	}
	
	@Override
	public int boardInsert(BoardVO boardVO) {
		int num = 0;
		int count = 0;
		int res = 0;
		
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			count = boardMapper.getListcount();
			if(count == 0) {
				num = 1;
			}else {
				num = boardMapper.getMaxnum() + 1;
			}
			boardVO.setBoard_num(num);
			res = boardMapper.boardInsert(boardVO);
			
		}catch(Exception e) {
			System.out.println("글쓰기 실패." + e.getMessage());
		}
		return res;
	}
	
	@Override
	public BoardVO getDetail(int num) {
		BoardVO vo = null;
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			boardMapper.setReadCountUpdate(num);
			vo = boardMapper.getDetail(num);
			
		}catch(Exception e) {
			System.out.println("글보기 실패."+ e.getMessage());
		}
		return vo;
	}
	
	@Override
	public int replyInsert(BoardVO boardVO) {
		int num = 0;
		int count = 0;
		
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			count = boardMapper.getMaxnum();
			
			if(count == 0) {
				num = 1;
			}else {
				num = count + 1;
			}
			boardVO.setBoard_num(num);
			boardMapper.updateReply(boardVO);
			
			boardVO.setBoard_re_seq(boardVO.getBoard_re_seq() + 1);
			boardVO.setBoard_re_lev(boardVO.getBoard_re_lev() + 1);
			
			boardMapper.replyInsert(boardVO);
			
		}catch(Exception e) {
			System.out.println("댓글쓰기 실패." + e.getMessage());
		}
		return num;
	}
	
	@Override
	public int deleteBoard(BoardVO boardVO, int num) {
		int res = 0;
		String dbpasswd = null;
		
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			dbpasswd = boardMapper.selectPasswd(num);
			
			if(dbpasswd.equals(boardVO.getBoard_pass())) {
				boardMapper.deleteBoard(num);
				res = 1;
			}
			
		}catch(Exception e) {
			System.out.println("글 삭제 실패." + e.getMessage());
		}
		return res;
	}
	
	@Override
	public int updateBoard(BoardVO boardVO, int num) {
		int res = 0;
		String dbpasswd = null;
		
		try {
			BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
			dbpasswd = boardMapper.selectPasswd(num);
			
			if(dbpasswd.equals(boardVO.getBoard_pass())) {
				boardMapper.updateBoard(boardVO);
				res = 1;
			}
			
		}catch(Exception e) {
			System.out.println("글 수정 실패." + e.getMessage());
		}
		return res;
	}

}
