package com.newlecture.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.FileDao;
import com.newlecture.web.entity.FileEntity;

@Repository("fileDao")
public class MybatisFileDao implements FileDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private FileDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisFileDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(FileDao.class);
	}
	
	////////////////////////////////////////////////////////////////
	
	// 특정 파일 정보 저장
	@Override
	public int save(FileEntity file) {
		return mapper.save(file);
	}

	@Override
	public FileEntity findByUUID(String uuid) {
		return mapper.findByUUID(uuid);
	}

	@Override
	public String findFileNameByUUID(String uuid) {
		return mapper.findFileNameByUUID(uuid);
	}
}
