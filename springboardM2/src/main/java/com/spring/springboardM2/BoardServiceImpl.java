package com.spring.springboardM2;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList<BoardVO> getBoardList(int startRow, int endRow) {
		ArrayList<BoardVO> boardlist = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardlist = boardMapper.getBoardList(startRow, endRow);
		return boardlist;
	}

	@Override
	public BoardVO getDetail(int board_num) {
		BoardVO boardVO = null;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.setReadCountUpdate(board_num);
		boardVO = boardMapper.getDetail(board_num);
		return boardVO;
	}

	@Override
	public int boardInsert(BoardVO vo) {
		int res = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		
		int count = boardMapper.getCount();
		if(count != 0) {
			vo.setBOARD_NUM(count+1);
			vo.setBOARD_RE_REF(count+1);
		} else {
			vo.setBOARD_NUM(1);
			vo.setBOARD_RE_REF(1);
		}
		res = boardMapper.boardInsert1(vo);
		return res;
	}

	@Override
	public int boardReply(BoardVO vo) {
		int res = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		boardMapper.updateBoardReSeq(vo);
		int count = boardMapper.getCount();
		vo.setBOARD_NUM(count + 1);
		vo.setBOARD_RE_SEQ(vo.getBOARD_RE_SEQ() + 1);
		vo.setBOARD_RE_LEV(vo.getBOARD_RE_LEV() + 1);
		res = boardMapper.boardInsert2(vo);
		return res;
	}

	@Override
	public int boardUpdate(BoardVO vo) {
		int res = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		if(vo.getBOARD_PASS().equals(boardMapper.getBoardPass(vo.getBOARD_NUM()))) {
			res = boardMapper.boardUpdate(vo);
		} else {
			res = -1;
		}
		return res;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		int res = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		if(vo.getBOARD_PASS().equals(boardMapper.getBoardPass(vo.getBOARD_NUM()))) {
			res = boardMapper.boardDelete(vo.getBOARD_NUM());
		} else {
			res = -1;
		}
		return res;
	}

	@Override
	public int getBoardCount() {
		int count = 0;
		BoardMapper boardMapper = sqlSession.getMapper(BoardMapper.class);
		count = boardMapper.getCount();
		return count;
	}

}
