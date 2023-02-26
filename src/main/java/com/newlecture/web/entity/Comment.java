package com.newlecture.web.entity;

import java.util.Date;

public class Comment {
	
	private int comment_id;
	private String content;
	private Date regdate;
	private String writer_nickname;
	private int notice_id;
	
	public Comment() {
		
	}
	
	public Comment(int comment_id, String content, Date regdate, String writer_nickname, int notice_id) {
		this.comment_id = comment_id;
		this.content = content;
		this.regdate = regdate;
		this.writer_nickname = writer_nickname;
		this.notice_id = notice_id;
	}

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
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
	public String getWriter_nickname() {
		return writer_nickname;
	}
	public void setWriter_nickname(String writer_nickname) {
		this.writer_nickname = writer_nickname;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + comment_id + ", content=" + content + ", regdate=" + regdate + ", writer_nickname=" + writer_nickname
				+ ", notice_id=" + notice_id + "]";
	}
}
