package com.shincha.ddh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shincha.ddh.model.dao.UserDao;
import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.One_day_blood_sugar;
import com.shincha.ddh.model.dto.One_day_exercise;
import com.shincha.ddh.model.dto.Users;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	// 회원등록
	@Override
	public int insertUser(Users user) {
		return userDao.insertUser(user);
	}

	
	@Override
	public List<Users> selectAll() {
		return userDao.selectAll();
	}

	// 특정 아이디로 회원 정보 가져오기
	@Override
	public Users selectById(String user_id, String user_password) {
		Users user = userDao.selectById(user_id);
		if(user!=null && user.getPassword().equals(user_password)) {
			return user;
		}
		
		else return null;
	}
	
	@Override
	public Users selectById2(String user_id) {
		Users user = userDao.selectById2(user_id);
		if(user!=null) {
			return user;
		}
		else return null;
	}

	//회원 정보 수정
	@Override
	public int updateUser(Users user) {
		return userDao.updateUser(user);
	}

	//특정 회원의 데일리 당 기록 -> 하루에 한 번
	@Override
	public int insertBloodSugar(One_day_blood_sugar blood) {
		return userDao.insertBloodSugar(blood);
	}

	//차트 정보를 위한 특정 회원의 당 기록 가져오기
	@Override
	public List<One_day_blood_sugar> selectSugarById(String user_id) {
		return userDao.selectSugarById(user_id);
	}

	//특정 회원 운동 정보 가져오기
	@Override
	public List<One_day_exercise> selectExerciseById(String user_id) {
		return userDao.selectExerciseById(user_id);
	}

	//데일리 운동 기록
	@Override
	public int insertExerciseTime(One_day_exercise time) {
		return userDao.insertExerciseTime(time);
	}


	//나의 챌린지 정보 가져오기
	@Override
	public List<Challenge> selectMyChallenge(String userId) {
		return userDao.selectMyChallenge(userId);
	}


	@Override
	public int updateRank(Users user) {
		return userDao.updateRank(user);
	}


	//특정 유저 운동 기록 최신순
	@Override
	public List<One_day_exercise> selectExerciseByIdNew(String user_id) {
		return userDao.selectExerciseByIdNew(user_id);
	}


	
	
}
