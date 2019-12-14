package com.spring.springboardM2;

import org.springframework.web.multipart.MultipartFile;

public class RequestModel {
	private int board_num;
	private MultipartFile file;
	
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
