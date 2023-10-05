package com.yomuka.yomuka.main;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int boardaid;
	private String notice;
	private String memberid;
	private String title;
	private String img;
	private String content;
	private String date;
	private MultipartFile imgFile;
	public int getBoardaid() {
		return boardaid;
	}
	public void setBoardaid(int boardaid) {
		this.boardaid = boardaid;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public MultipartFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(MultipartFile imgFile) {
		this.imgFile = imgFile;
	}
	
	
}
