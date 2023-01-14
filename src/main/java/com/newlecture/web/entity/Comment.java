package com.newlecture.web.entity;

import java.util.Date;

public class Comment {
	private int comment_id;
	private String content;
	private Date regdate;
	private String writer_id;
	private int notice_id;
	
	public Comment() {
		
	}
	
	public Comment(int comment_id, String content, Date regdate, String writer_id, int notice_id) {
		this.comment_id = comment_id;
		this.content = content;
		this.regdate = regdate;
		this.writer_id = writer_id;
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
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + comment_id + ", content=" + content + ", regdate=" + regdate + ", writer_id=" + writer_id
				+ ", notice_id=" + notice_id + "]";
	}
}
