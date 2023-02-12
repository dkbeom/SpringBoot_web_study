package com.newlecture.web.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FileEntity {
	
	private Long id;
	private String orgNm; // 파일의 원래 이름
	private String savedNm; // UUID
	private String savedPath; // UUID가 이름으로 된 파일의 경로
	
	@Builder
	public FileEntity(Long id, String orgNm, String savedNm, String savedPath) {
		this.id = id;
		this.orgNm = orgNm;
		this.savedNm = savedNm;
		this.savedPath = savedPath;
	}
}
