package com.shincha.ddh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shincha.ddh.model.dto.Board;
import com.shincha.ddh.model.dto.Comment;
import com.shincha.ddh.model.service.CommunityService;

@RestController
@RequestMapping("/api-community")
public class CommunityRestController {
	@Autowired
	CommunityService communityService;

	// 1. 커뮤니티 정보게시판 글 가져오기 (최신글 중 인기글 - 메인)
	@GetMapping("/communityInfoPop")
	public ResponseEntity<?> doCommunityInfoPop() {
		List<Board> boardList = communityService.selectInfoBoardAllBypopular();

		if (boardList != null && boardList.size() > 0) {
			return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 2. 커뮤니티 자유게시판 글 가져오기 (최신글 중 인기글 - 메인)
	@GetMapping("/communityfreePop")
	public ResponseEntity<?> doCommunityfreePop() {
		List<Board> boardList = communityService.selectFreeBoardAllBypopular();

		if (boardList != null && boardList.size() > 0) {
			return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 3. 커뮤니티 정보게시판 글 가져오기 (최신순)
	@GetMapping("/communityInfo")
	public ResponseEntity<?> doCommunityInfo() {
		List<Board> boardList = communityService.selectInfoBoardAllBynow();

		if (boardList != null && boardList.size() > 0) {
			return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 4. 커뮤니티 자유게시판 글 가져오기 (최신순)
	@GetMapping("/communityfree")
	public ResponseEntity<?> doCommunityfree() {
		List<Board> boardList = communityService.selectFreeBoardAllBynow();

		if (boardList != null && boardList.size() > 0) {
			return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 5. 게시판 검색 결과 가져오기
	@GetMapping("/searchReview/{word}/{type}")
	public ResponseEntity<?> searchReview(@PathVariable String word, @PathVariable String type) {
		List<Board> searchList = communityService.search(word, type);

		if (searchList != null && searchList.size() > 0)
			return new ResponseEntity<List<Board>>(searchList, HttpStatus.OK);
		else
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	// 6. 게시판 상세보기
	@GetMapping("/board/{no}")
	public ResponseEntity<?> detail(@PathVariable int no) {
		Board board = communityService.selectBoardByno(no);
		if(board!=null) return new ResponseEntity<Board>(board, HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	// 7. 등록
	@PostMapping("/board")
	public ResponseEntity<?> write(@RequestBody Board board) {
		int result = communityService.insertBoard(board);
		
		if(result>0)return new ResponseEntity<Board>(board, HttpStatus.CREATED);
		else return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}

	// 8. 삭제
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Void> delete(@PathVariable int no) {
		int result = communityService.deleteBoard(no);
		if(result > 0) return new ResponseEntity<Void>(HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}

	// 9. 수정
	@PutMapping("/board")
	public ResponseEntity<Void> update(@RequestBody Board board) {
		int result = communityService.updateBoard(board);
		if(result > 0) return new ResponseEntity<Void>(HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
	}
	
	//10. 특정 게시물 댓글 불러오기
	@GetMapping("/comment/{board_no}") 
	public ResponseEntity<?> commentRead(@PathVariable int board_no) {
		List<Comment> list = communityService.selectAllComment(board_no);
		if(list != null && list.size() > 0) return new ResponseEntity<List<Comment>>(list,HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//11. 특정 게시물 댓글 쓰기
	@PostMapping("/comment") 
	public ResponseEntity<?> commentWrite(@RequestBody Comment comment) {
		int result = communityService.insertComment(comment);
		if(result > 0) return new ResponseEntity<Void>(HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//12. 특정 게시물 댓글 수정하기
	@PutMapping("/comment") 
	public ResponseEntity<?> commentUpdate(@RequestBody Comment comment) {
		int result = communityService.updateComment(comment);
		if(result > 0) return new ResponseEntity<Void>(HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//13. 특정 게시물 댓글 삭제하기
	@DeleteMapping("/comment/{no}") 
	public ResponseEntity<?> commentDelete(@PathVariable int no) {
		int result = communityService.deleteComment(no);
		if(result > 0) return new ResponseEntity<Void>(HttpStatus.OK);
		else return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	//14. 전체 게시물 불러오기
	@GetMapping("/communityAll")
	public ResponseEntity<?> docommunityAll() {
		List<Board> boardList = communityService.selectBoardAll();

		if (boardList != null && boardList.size() > 0) {
			return new ResponseEntity<List<Board>>(boardList, HttpStatus.OK);
		} else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	

}
