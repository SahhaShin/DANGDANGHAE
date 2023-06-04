package com.shincha.ddh.model.dto;

public class Board {
	private int no; //auto_increment
	private String title;
	private String content;
	private String type; //정보 or 자유
	private String write_date; //자동
	private int view_count;
	private int like;
	private boolean modify;
	private String users_id; //users 외래키
	
	public Board() {}
	//어떤 유저가 게시글을 썼다. 생성!
	public Board(String title, String content, String type, String users_id) {
		super();
		this.title = title;
		this.content = content;
		this.type = type;
		this.users_id = users_id;
	}
	
	//getter setter

	public int getNo() {
		return no;
	}



	public void setNo(int no) {
		this.no = no;
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



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getWrite_date() {
		return write_date;
	}



	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}



	public int getView_count() {
		return view_count;
	}



	public void setView_count(int view_count) {
		this.view_count = view_count;
	}



	public int getLike() {
		return like;
	}



	public void setLike(int like) {
		this.like = like;
	}



	public boolean isModify() {
		return modify;
	}



	public void setModify(boolean modify) {
		this.modify = modify;
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
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", type=" + type + ", write_date="
				+ write_date + ", view_count=" + view_count + ", like=" + like + ", modify=" + modify + ", users_id="
				+ users_id + "]";
	}
	
}
