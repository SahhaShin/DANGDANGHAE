package com.shincha.ddh.model.dto;

//Challenge가 만들어지면 팀이 자동 생성됨 2개 -> 방장이 선택한 팀으로 배정해야함 -> users_has_challenge_and_team에 팀과 챌린지 정보 기입해야함
public class Team {
	private int no;//auto_increment
	private String team_name;
	private int team_count;
	private int challenge_no;
	
	//팀생성
	public Team() {}
	public Team(String team_name, int team_count, int challenge_no) {
		super();
		this.team_name = team_name;
		this.team_count = team_count;
		this.challenge_no = challenge_no;
	}
	
	//getter setter

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public int getTeam_count() {
		return team_count;
	}

	public void setTeam_count(int team_count) {
		this.team_count = team_count;
	}
	
	

	public int getChallenge_no() {
		return challenge_no;
	}

	public void setChallenge_no(int challenge_no) {
		this.challenge_no = challenge_no;
	}

	//toString
	@Override
	public String toString() {
		return "Team [no=" + no + ", team_name=" + team_name + ", team_count=" + team_count + ", challenge_no="
				+ challenge_no + "]";
	}

}
