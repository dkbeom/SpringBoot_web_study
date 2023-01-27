package com.newlecture.web.service;

import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.FileEntity;

public interface FileService {
	
	// 파일을 로컬,데이터베이스에 저장
	int saveFile(MultipartFile files, String uuid);

	// uuid를 이용해서 파일 객체를 찾음
	FileEntity findByUUID(String uuid);
	
	// uuid를 이용해서 파일 이름을 찾음
	String findFileNameByUUID(String uuid);
}