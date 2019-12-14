package com.spring.springreboard;

public class CommentVO {
	private int board_num;
	private int comment_num;
	private String comment_name;
	private String comment_pass;
	private String comment_content;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_pass() {
		return comment_pass;
	}
	public void setComment_pass(String comment_pass) {
		this.comment_pass = comment_pass;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
}
