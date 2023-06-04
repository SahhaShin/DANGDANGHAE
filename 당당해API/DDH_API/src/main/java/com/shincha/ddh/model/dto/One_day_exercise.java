package com.shincha.ddh.model.dto;

public class One_day_exercise {
	private int no; //auto_increment
	private String total_time;//내가 운동한 시간은 몇 분?
	private String end_time;//날짜 + 시간 datetime 내가 인증한 타임
	private String users_id;//어떤 유저가 이 운동을 했는가 users가 외래키
	
	//하루 운동 기록 생성
	public One_day_exercise() {}

	public One_day_exercise(String total_time, String end_time, String users_id) {
		super();
		this.total_time = total_time;
		this.end_time = end_time;
		this.users_id = users_id;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTotal_time() {
		return total_time;
	}

	public void setTotal_time(String total_time) {
		this.total_time = total_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getUsers_id() {
		return users_id;
	}

	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}

	@Override
	public String toString() {
		return "One_day_exercise [no=" + no + ", total_time=" + total_time + ", end_time=" + end_time + ", users_id="
				+ users_id + "]";
	}
	
	
}
