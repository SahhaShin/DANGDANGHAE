package com.shincha.ddh.model.dto;

public class Comment {
	private int no; //auto_increment
	private String comment_content;
	private String write_date;
	private boolean modify;
	private int board_no;
	private String users_id;
	
	//유저가 하나의 댓글 씀
	public Comment() {}
	public Comment(String comment_content, int board_no, String users_id) {
		super();
		this.comment_content = comment_content;
		this.board_no = board_no;
		this.users_id = users_id;
	}

	
	//getter setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getWrite_date() {
		return write_date;
	}

	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}

	public boolean isModify() {
		return modify;
	}

	public void setModify(boolean modify) {
		this.modify = modify;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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
		return "Comment [no=" + no + ", comment_content=" + comment_content + ", write_date=" + write_date + ", modify="
				+ modify + ", board_no=" + board_no + ", users_id=" + users_id + "]";
	}


}
