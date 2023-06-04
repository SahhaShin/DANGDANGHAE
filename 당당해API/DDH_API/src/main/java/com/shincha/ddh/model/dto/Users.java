package com.shincha.ddh.model.dto;

public class Users {
	private String id;
	private String password;
	private String name;
	private String nick_name;
	private String email;//프론트에서 input type email로 해줘서 들어옴
	
	private float weight;
	private float muscle;
	private float blood_sugar;
	private float HbA1c;
	private long point;
	
//	브론즈(0~5000)
//	골드( 5000~100000)
//	플레티넘(100000~)
	private String rank;
	
	//모든 정보를 기입한 경우
	//weight, muscle, blood_sugar, HbA1c는 null값 가능
	public Users() {}
	public Users(String id, String password, String name, String nick_name, String email, float weight, float muscle,
			float blood_sugar, float hbA1c, long point) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.nick_name = nick_name;
		this.email = email;
		this.weight = weight;
		this.muscle = muscle;
		this.blood_sugar = blood_sugar;
		this.HbA1c = hbA1c;
		this.point = point;
	}

	
	//setter getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getMuscle() {
		return muscle;
	}

	public void setMuscle(float muscle) {
		this.muscle = muscle;
	}

	public float getBlood_sugar() {
		return blood_sugar;
	}

	public void setBlood_sugar(float blood_sugar) {
		this.blood_sugar = blood_sugar;
	}

	public float getHbA1c() {
		return HbA1c;
	}

	public void setHbA1c(float hbA1c) {
		HbA1c = hbA1c;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return "Users [id=" + id + ", password=" + password + ", name=" + name + ", nick_name=" + nick_name + ", email="
				+ email + ", weight=" + weight + ", muscle=" + muscle + ", blood_sugar=" + blood_sugar + ", HbA1c="
				+ HbA1c + ", point=" + point + ", rank=" + rank + "]";
	}


}
