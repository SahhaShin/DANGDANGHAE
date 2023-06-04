package com.shincha.ddh.model.service;

import java.util.List;

import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.One_day_blood_sugar;
import com.shincha.ddh.model.dto.One_day_exercise;
import com.shincha.ddh.model.dto.Users;

public interface UserService {
	// 회원등록
	int insertUser(Users user);

	// 회원 정보 전체 가져오기
	List<Users> selectAll();

	// 특정 아이디로 회원 정보 가져오기
	Users selectById(String user_id, String user_password);

	Users selectById2(String user_id);

	// 회원 정보 수정(닉네임, 이메일, 몸무게, 근육량, 당화혈색소)
	int updateUser(Users user);

	// 특정 회원의 매일 당 정보 기록하기
	int insertBloodSugar(One_day_blood_sugar blood);

	// 특정 회원의 매일 당 정보 기록 가져오기
	List<One_day_blood_sugar> selectSugarById(String user_id);

	// 특정 유저의 전체 운동 기록 가져오기
	List<One_day_exercise> selectExerciseById(String user_id);

	// 특정 유저의 전체 운동 기록 가져오기 (종료 일자 느린순 , 최신순)
	List<One_day_exercise> selectExerciseByIdNew(String user_id);

	// 종료를 눌렀을 때 타이머 시간 기록
	int insertExerciseTime(One_day_exercise time);

	// 나의 챌린지 정보 가져오기
	List<Challenge> selectMyChallenge(String userId);

	// 랭킹 변경 요청
	int updateRank(Users user);

}
