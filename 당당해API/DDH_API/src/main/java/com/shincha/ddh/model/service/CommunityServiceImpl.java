package com.shincha.ddh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shincha.ddh.model.dao.CommunityDao;
import com.shincha.ddh.model.dto.Board;
import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.Comment;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired
	CommunityDao communityDao;

	@Override
	public List<Board> selectInfoBoardAllBynow() {
		return communityDao.selectInfoBoardAllBynow();
	}

	@Override
	public List<Board> selectInfoBoardAllBypopular() {
		return communityDao.selectInfoBoardAllBypopular();
	}

	@Override
	public List<Board> selectFreeBoardAllBynow() {
		return communityDao.selectFreeBoardAllBynow();
	}

	@Override
	public List<Board> selectFreeBoardAllBypopular() {
		return communityDao.selectFreeBoardAllBypopular();
	}

	@Override
	public int insertBoard(Board board) {
		return communityDao.insertBoard(board);
	}

	@Override
	public Board selectBoardByno(int board_no) {
		viewCnt(board_no);
		return communityDao.selectBoardByno(board_no);
	}

	@Override
	public int updateBoard(Board board) {
		return communityDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int board_no) {
		return communityDao.deleteBoard(board_no);
	}

	@Override
	public int insertComment(Comment comment) {
		return communityDao.insertComment(comment);
	}

	@Override
	public List<Comment> selectAllComment(int board_no) {
		return communityDao.selectAllComment(board_no);
	}

	@Override
	public int updateComment(Comment comment) {
		return communityDao.updateComment(comment);
	}

	@Override
	public int deleteComment(int no) {
		return communityDao.deleteComment(no);
	}

	@Override
	public int viewCnt(int board_no) {
		return communityDao.viewCnt(board_no);
	}

	@Override
	public List<Board> search(String title, String type) {
		return communityDao.search(title, type);
	}

	@Override
	public List<Board> selectBoardAll() {
		return communityDao.selectBoardAll();
	}

	
}
