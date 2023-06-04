package com.shincha.ddh.model.dao;

import java.util.List;

import com.shincha.ddh.model.dto.Board;
import com.shincha.ddh.model.dto.Comment;

public interface CommunityDao {
	
	//커뮤니티 게시판 전체 글 가져오기 (타입 상관없이)
	List<Board> selectBoardAll();

	// 커뮤니티 정보게시판 글 전체 가져오기 (최신순)
	List<Board> selectInfoBoardAllBynow();

	// 커뮤니티 정보게시판 글 전체 가져오기(인기순)
	List<Board> selectInfoBoardAllBypopular();

	// 커뮤니티 자유게시판 글 전체 가져오기(최신순)
	List<Board> selectFreeBoardAllBynow();

	// 커뮤니티 자유게시판 글 전체 가져오기(인기순)
	List<Board> selectFreeBoardAllBypopular();

	// 커뮤니티 글 등록하기
	int insertBoard(Board board);

	// 커뮤니티 특정 글 가져오기
	Board selectBoardByno(int board_no);

	// 특정 게시글 수정
	int updateBoard(Board board);

	// 특정 게시글 삭제
	int deleteBoard(int board_no);

	// 특정 게시글에 댓글 등록하기
	int insertComment(Comment comment);

	// 특정 게시글의 댓글 가져오기
	List<Comment> selectAllComment(int board_no);

	// 특정 게시글의 댓글 수정하기
	int updateComment(Comment comment);

	// 특정 게시글의 댓글 삭제하기
	int deleteComment(int no);

	// 조회수 증가
	int viewCnt(int board_no);

	// 검색기능 (정보/자유 게시글 검색 따로임)
	public List<Board> search(String title, String type);

}
