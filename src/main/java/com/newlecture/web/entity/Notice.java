package com.newlecture.web.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String writer_nickname;
	private String content;
	private Date regdate;
	private int hit;
	private String fileUUID;
	private boolean pub;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public Notice(int id, String title, String writer_nickname, String content, Date regdate, int hit, String fileUUID,
			boolean pub) {
		this.id = id;
		this.title = title;
		this.writer_nickname = writer_nickname;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.fileUUID = fileUUID;
		this.pub = pub;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter_nickname() {
		return writer_nickname;
	}

	public void setWriter_nickname(String writer_nickname) {
		this.writer_nickname = writer_nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getFileUUID() {
		return fileUUID;
	}

	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writer_nickname=" + writer_nickname + ", content=" + content
				+ ", regdate=" + regdate + ", hit=" + hit + ", fileUUID=" + fileUUID + ", pub=" + pub + "]";
	}
}
