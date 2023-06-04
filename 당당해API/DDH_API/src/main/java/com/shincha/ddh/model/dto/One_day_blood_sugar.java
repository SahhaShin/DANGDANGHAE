package com.shincha.ddh.model.dto;

public class One_day_blood_sugar {
	private int no; //auto_increment
	private String cur_date;
	private float blood_sugar;
	private String users_id;
	
	//혈당 체크 생성
	public One_day_blood_sugar() {}
	public One_day_blood_sugar(int no, String cur_date, float blood_sugar, String users_id) {
		super();
		this.no = no;
		this.cur_date = cur_date;
		this.blood_sugar = blood_sugar;
		this.users_id = users_id;
	}

	
	//getter setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getCur_date() {
		return cur_date;
	}

	public void setCur_date(String cur_date) {
		this.cur_date = cur_date;
	}

	public float getBlood_sugar() {
		return blood_sugar;
	}

	public void setBlood_sugar(float blood_sugar) {
		this.blood_sugar = blood_sugar;
	}

	public String getUsers_id() {
		return users_id;
	}

	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}


	//toString
	@Override
	public String toString() {
		return "One_day_blood_sugar [no=" + no + ", cur_date=" + cur_date + ", blood_sugar=" + blood_sugar
				+ ", users_id=" + users_id + "]";
	}
	
	
	
}
