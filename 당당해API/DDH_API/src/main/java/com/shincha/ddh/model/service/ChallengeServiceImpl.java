package com.shincha.ddh.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shincha.ddh.model.dao.ChallengeDao;
import com.shincha.ddh.model.dto.Challenge;
import com.shincha.ddh.model.dto.Team;
import com.shincha.ddh.model.dto.Users_has_challenge;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	ChallengeDao challengeDao;
	
	@Override
	public List<Challenge> selectChallengeAll() {
		return challengeDao.selectChallengeAll();
	}

	@Override
	public int insertChallenge(Challenge info) {
		return challengeDao.insertChallenge(info);
	}

	@Override
	public Challenge selectChallengeByNo(int no) {
		return challengeDao.selectChallengeByNo(no);
	}

	@Override
	public int updateChallenge(Challenge info) {
		return challengeDao.updateChallenge(info);
	}

	@Override
	public int deleteChallenge(int no) {
		return challengeDao.deleteChallenge(no);
	}

	@Override
	public List<Team> team(int no) {
		return challengeDao.team(no);
	}

	@Override
	public int insertTeam(Team team) {
		return challengeDao.insertTeam(team);
	}

	@Override
	public int registTeam(String users_id, int challenge_no, int team_no) {
		return challengeDao.registTeam(users_id, challenge_no, team_no);
	}

	@Override
	public int deleteTeam(String users_id, int challenge_no, int team_no) {
		return challengeDao.deleteTeam(users_id, challenge_no, team_no);
	}

	@Override
	public int changePoint(String users_id, long point) {
		return challengeDao.changePoint(users_id, point);

	}

	@Override
	public int changeCount(int team_no, int team_count) {
		return challengeDao.changeCount(team_no, team_count);
	}

	@Override
	public Team selectTeam(int team_no) {
		
		return challengeDao.selectTeam(team_no);
	}

	@Override
	public int changeStatus(int no, int status) {
		return challengeDao.changeStatus(no, status);
	}

	@Override
	public List<Users_has_challenge> challengeListByno(int challenge_no) {
		return challengeDao.challengeListByno(challenge_no);
	}

	@Override
	public List<Challenge> myChallenge(String users_id) {
		return challengeDao.myChallenge(users_id);
	}

}
