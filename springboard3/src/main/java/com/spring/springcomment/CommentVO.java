package com.spring.springcomment;

import java.sql.Timestamp;

public class CommentVO {
	private int comment_num;
	private int board_num;
	private String writer;
	private String pass;
	private String content;
	private Timestamp reg_date;
	private int reg_lev;
	private int parents_num;
	
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getComment_num() {
		return comment_num;
	}
	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}
	public int getReg_lev() {
		return reg_lev;
	}
	public void setReg_lev(int reg_lev) {
		this.reg_lev = reg_lev;
	}
	public int getParents_num() {
		return parents_num;
	}
	public void setParents_num(int parents_num) {
		this.parents_num = parents_num;
	}
}
