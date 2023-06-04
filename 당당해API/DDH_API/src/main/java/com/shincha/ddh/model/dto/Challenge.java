package com.shincha.ddh.model.dto;

//Challenge가 만들어지면 팀이 자동 생성됨 2개 -> 방장이 선택한 팀으로 배정해야함 -> users_has_challenge_and_team에 팀과 챌린지 정보 기입해야함
public class Challenge {
	private int no; //auto_increment
	private String title;
	private String content;
	private String start_date; //datetime임 날짜 + 시간
	private String end_date; //datetime임 날짜 + 시간
	private long point;
	private int status;
	private String image;
	private String organizer; //users 외래키임
	private int organizer_team;
	private boolean modify;//수정여부
	
	//챌린지 생성
	public Challenge() {}
	
	public Challenge(int no, int status) {
		this.no=no;
		this.status=status;
	}

	public Challenge(int no, String title, String content, String start_date, String end_date, long point,
			String image, String organizer, int organizer_team) {
		super();
		this.no=no;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.end_date = end_date;
		this.point = point;
		this.image = image;
		this.organizer = organizer;
		this.organizer_team = organizer_team;
	}

	

	//setter getter
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public int getOrganizer_team() {
		return organizer_team;
	}

	public void setOrganizer_team(int organizer_team) {
		this.organizer_team = organizer_team;
	}

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public void setNo(int no) {
		this.no = no;
	}
	

	//toString
	@Override
	public String toString() {
		return "Challenge [no=" + no + ", title=" + title + ", content=" + content + ", start_date=" + start_date
				+ ", end_date=" + end_date + ", point=" + point + ", status=" + status + ", image=" + image
				+ ", organizer=" + organizer + ", organizer_team=" + organizer_team + ", modify=" + modify + "]";
	}
	

	
}
