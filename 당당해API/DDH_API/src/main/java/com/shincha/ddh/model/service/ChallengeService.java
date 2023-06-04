package com.shincha.ddh.model.service;

import java.util.List;

import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.Team;
import com.shincha.ddh.model.dto.Users_has_challenge;

public interface ChallengeService {
	// 챌린지 전체 정보 가져오기(최신순)
	List<Challenge> selectChallengeAll();

	// 특정 챌린지 정보 등록
	int insertChallenge(Challenge info);

	// 특정 챌린지 정보 가져오기
	Challenge selectChallengeByNo(int no);

	// 특정 챌린지 수정
	int updateChallenge(Challenge info);

	// 특정 챌린지 삭제
	int deleteChallenge(int no);

	// 특정 챌린지 팀 2개 정보 가져오기(챌린지, 팀, 유저)
	List<Team> team(int no); // no는 챌린지 번호

	// 챌린지 만들어지는 순간 팀 2개 추가하기
	int insertTeam(Team team);

	// 회원 챌린지&팀 가입
	int registTeam(String users_id, int challenge_no, int team_no);

	// 챌린지 가입 취소 / 팀 취소
	int deleteTeam(String users_id, int challenge_no, int team_no);

	// 포인트 차감/증가
	int changePoint(String users_id, long point);

	// 팀인원수 증가/감소
	int changeCount(int team_no, int team_count);

	// 특정 팀 정보 가져오기
	Team selectTeam(int team_no);

	// 챌린지 진행 상태 바꾸기
	// 0은 대기중 1은 시작 2는 끝
	// status는 프론트에서 setTimeout을 이용해 날아옴
	int changeStatus(int no, int status);

	// 특정 챌린지 가입한 사람들의 명단 가져오기
	List<Users_has_challenge> challengeListByno(int challenge_no);

	// 내 챌린지 가져오기
	List<Challenge> myChallenge(String users_id);

}
