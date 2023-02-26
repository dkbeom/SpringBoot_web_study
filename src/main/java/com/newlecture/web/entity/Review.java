package com.newlecture.web.entity;

import java.util.Date;

public class Review {
	
	private int id;
	private String writer_nickname;
	private String content;
	private int item_id;
	private int score;
	private Date regdate;
	
	public Review() {
		
	}

	public Review(int id, String writer_nickname, String content, int item_id, int score, Date regdate) {
		this.id = id;
		this.writer_nickname = writer_nickname;
		this.content = content;
		this.item_id = item_id;
		this.score = score;
		this.regdate = regdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", writer_nickname=" + writer_nickname + ", content=" + content + ", item_id="
				+ item_id + ", score=" + score + ", regdate=" + regdate + "]";
	}
}
