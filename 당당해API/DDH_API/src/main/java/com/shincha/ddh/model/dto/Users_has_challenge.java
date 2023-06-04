package com.shincha.ddh.model.dto;

public class Users_has_challenge {
	private String users_id;
	private int challenge_no;
	private int team_no;
	
	public Users_has_challenge() {}
	public Users_has_challenge(String users_id, int challenge_no, int team_no) {
		super();
		this.users_id = users_id;
		this.challenge_no = challenge_no;
		this.team_no = team_no;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public int getChallenge_no() {
		return challenge_no;
	}
	public void setChallenge_no(int challenge_no) {
		this.challenge_no = challenge_no;
	}
	public int getTeam_no() {
		return team_no;
	}
	public void setTeam_no(int team_no) {
		this.team_no = team_no;
	}
	@Override
	public String toString() {
		return "Users_has_challenge [users_id=" + users_id + ", challenge_no=" + challenge_no + ", team_no=" + team_no
				+ "]";
	}
}
