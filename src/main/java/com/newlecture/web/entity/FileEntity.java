package com.newlecture.web.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileEntity {
	
	private Long id;
	private String orgNm;
	private String savedNm;
	private String savedPath;
	
	@Builder
	public FileEntity(Long id, String orgNm, String savedNm, String savedPath) {
		this.id = id;
		this.orgNm = orgNm;
		this.savedNm = savedNm;
		this.savedPath = savedPath;
	}
}
