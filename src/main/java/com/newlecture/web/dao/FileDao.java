package com.newlecture.web.dao;

import com.newlecture.web.entity.FileEntity;

public interface FileDao {

	// 데이터베이스에 파일 정보 저장
	int save(FileEntity file);

	FileEntity findByUUID(String uuid);

	String findFileNameByUUID(String uuid);
}
