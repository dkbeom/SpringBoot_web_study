package com.newlecture.web.entity;

import java.util.Date;
import java.util.List;

public class NoticeView extends Notice {
	
	private int cmt_count;
	
	public NoticeView() {
		
	}

	public NoticeView(int id, String title, String writer_id, String content, Date regdate, int hit, String fileUUId, boolean pub, int cmt_count) {
		super(id, title, writer_id, "", regdate, hit, fileUUId, pub);
		
		this.cmt_count = cmt_count;
	}

	public int getCmt_count() {
		return cmt_count;
	}

	public void setCmt_count(int cmt_count) {
		this.cmt_count = cmt_count;
	}

	@Override
	public String toString() {
		return "NoticeView [cmt_count=" + cmt_count + ", getId()=" + getId() + ", getTitle()=" + getTitle()
				+ ", getwriter_id()=" + getWriter_id() + ", getContent()=" + getContent() + ", getRegdate()="
				+ getRegdate() + ", getHit()=" + getHit() + ", getFileUUID()=" + getFileUUID() + ", isPub()=" + isPub()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
}
