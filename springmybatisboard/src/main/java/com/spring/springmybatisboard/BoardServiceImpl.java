package com.spring.springmybatisboard;

import java.sql.Timestamp;
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
	public int getBoardcount() {
		int count = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		try {
			count = boardMapper.getBoardcount();
		}
		catch (Exception e) { 
			System.out.println("카운트 실패." + e.getMessage());
		}
		return count;
	}

	@Override
	public ArrayList<BoardVO> getBoardlist(int startRow, int endRow) {
		ArrayList<BoardVO> list = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		try {
			list = boardMapper.getBoardlist(startRow, endRow);
			
		} 
		catch (Exception e) { 
			System.out.println("리스트 검색 실패." + e.getMessage());
		} 
		return list;
	}

	@Override
	public int writePro(BoardVO boardVO) {
		int num = boardVO.getNum();
		int ref = boardVO.getRef();
		int re_step = boardVO.getRe_step();
		int re_level = boardVO.getRe_level();
		int res = 0;
		int number = 0;
		Integer result;	// int로 하면 null값이면 오류가 나기때문에
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		
		try { 
			result = boardMapper.writePro(boardVO);
			System.out.println("result" + result);
			
			if(result != null) {	// num이 아니라 result로 ...
				number = (int)boardMapper.writePro(boardVO) + 1;
			}
			else {
				number = 1;
			}
			
			// 댓글쓰기
			if(num != 0) {
				boardMapper.writePro2(boardVO);
				
				re_step += 1;
				re_level += 1;
				
			// 원글쓰기
			}else {
				ref = number;
				re_step = 0;
				re_level = 0;
			}
			
		    boardVO.setRef(ref);
		    boardVO.setRe_step(re_step);
		    boardVO.setRe_level(re_level);
			boardVO.setReg_date(new Timestamp(System.currentTimeMillis()));
			res = boardMapper.writePro3(boardVO);
		} 
		catch (Exception e) { 
			System.out.println("등록 실패." + e.getMessage());
		} 
		return res;
	}

	@Override
	public BoardVO selectBoard(BoardVO boardVO) {
		BoardVO vo = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		try {
			boardMapper.selectBoard(boardVO);
			vo = boardMapper.selectBoard2(boardVO);
		} 
		catch (Exception e) { 
			System.out.println("검색 실패." + e.getMessage());
		} 
		return vo;
	}

	@Override
	public int updatePro(BoardVO boardVO) {
		int x = -1;
		BoardVO vo = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		try {
			vo = boardMapper.updatePro(boardVO);
			
			if(vo != null) {
				String dbpasswd = vo.getPasswd();
				if(dbpasswd.equals(boardVO.getPasswd())) {
					boardMapper.updatePro2(boardVO); 
					x=1;
				}else {
					x=0;
				}
			}
		} 
		catch (Exception e) { 
			System.out.println("수정 실패." + e.getMessage());
		} 
		return x;
	}

	@Override
	public int deletePro(BoardVO boardVO) {
		int x = -1;
		BoardVO vo = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		try {
			vo = boardMapper.deletePro(boardVO);
			if(vo != null) {
				String dbpasswd = vo.getPasswd();
				
				if(dbpasswd.equals(boardVO.getPasswd())) {
					boardMapper.deletePro2(boardVO);
					x = 1;
				}else {
					x=0;
				}
			}
		} 
		catch (Exception e) { 
			System.out.println("삭제 실패." + e.getMessage());
		} 
		return x;
	}

}
