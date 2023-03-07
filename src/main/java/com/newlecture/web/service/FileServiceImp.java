package com.newlecture.web.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.dao.FileDao;
import com.newlecture.web.entity.FileEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application.properties")
public class FileServiceImp implements FileService {

	private final FileDao fileDao;
	
	// 업로드 파일 로컬 저장 경로
	@Value("${file.dir}")
	private String fileDir;
	
	// 파일을 로컬&데이터베이스에 저장하는 Service 메소드
	@Override
	public int saveFile(MultipartFile files, String uuid) {
		
		// 파일이 비어있을 경우
		if(files.isEmpty()) {
			return 0;
		}
		
		// 원래 파일 이름 추출
		String origName = files.getOriginalFilename();
		
		// 파일을 저장할 때, 파일 이름으로 쓸 uuid 생성
		//String uuid = UUID.randomUUID().toString();
		
		// 확장자 추출
		String extension = origName.substring(origName.lastIndexOf("."));
		
		// uuid와 확장자 결합
		String savedName = uuid + extension;
		
		// 파일을 불러올 때, 사용할 파일 경로
		// new File("").getAbsolutePath() => 현재 절대경로를 반환
		String savedPath = new File("").getAbsolutePath() + fileDir + savedName;
		
		// 위에서 준비한 파일 정보들을 담는 FileEntity 객체 생성
		// (FileEntity에 @Builder를 붙여줬기 때문에,
		// 아래의 메소드들을 사용할 수 있음)
		FileEntity file = FileEntity.builder()
						.orgNm(origName)
						.savedNm(uuid)
						.savedPath(savedPath)
						.build();
		
		try {
			// 실제 로컬에 uuid를 파일명으로 저장
			files.transferTo(new File(savedPath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 데이터베이스에 파일 정보 저장
		int a = fileDao.save(file);
		
		// 데이터베이스에 저장된 파일의 id 반환
		return a;
	}

	@Override
	public FileEntity findByUUID(String uuid) {
		return fileDao.findByUUID(uuid);
	}

	@Override
	public String findFileNameByUUID(String uuid) {
		return fileDao.findFileNameByUUID(uuid);
	}
}
