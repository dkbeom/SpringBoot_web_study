package com.newlecture.web.controller.file;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.newlecture.web.entity.FileEntity;
import com.newlecture.web.service.FileService;

@Controller
@RequestMapping("/file/")
public class FileController {

	@Autowired
	private FileService fileService;

	// 첨부파일 이미지 출력
	@GetMapping(value = "display/{UUID}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE })
	@ResponseBody
	public Resource imageAttach(@PathVariable("UUID") String uuid) throws MalformedURLException {

		FileEntity fileEntity = fileService.findByUUID(uuid);

		return new UrlResource("file:" + fileEntity.getSavedPath());
	}

	// 첨부파일 다운로드
	@GetMapping("attach/{UUID}")
	public ResponseEntity<Resource> downloadAttach(@PathVariable("UUID") String uuid) throws MalformedURLException {

		// 파일 id로 특정 파일 찾기
		FileEntity fileEntity = fileService.findByUUID(uuid);

		// 찾은 파일의 저장경로로 리소스 객체 생성
		UrlResource resource = new UrlResource("file:" + fileEntity.getSavedPath());

		// 파일명을 인코딩
		String encodedFileName = UriUtils.encode(fileEntity.getOrgNm(), StandardCharsets.UTF_8);

		// 응답 헤더에 "첨부파일"이라는 것과, "파일명"을 넣어줄 문자열 생성
		String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

		// 응답 헤더의 Content-Disposition 속성에 contentDisposition을 넣어주고,
		// 응답 바디에 해당 파일 리소스 객체를 넣어준다
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
	}

	// 이미지 출력(위에 위에 있는 메소드랑 기능이 사실 같음)
//	 @GetMapping("display/{UUID}")
//	 public ResponseEntity<byte[]> display(@PathVariable("UUID") String uuid){
//	  
//		 FileEntity fileEntity = fileService.findByUUID(uuid);
//	  
//		 File file = new File(fileEntity.getSavedPath());
//	  
//		 HttpHeaders header = new HttpHeaders();
//	  
//		 ResponseEntity<byte[]> result = null;
//	  
//		 try { // Files.probeContentType() - 해당 파일의 Content-type을 반환
//			 header.add("Content-type", Files.probeContentType(file.toPath()));
//			 // FileCopyUtils.copyToByteArray() - 해당 파일을 복사해서, byte[]로 변환
//			 result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//		 } catch (IOException e) { e.printStackTrace(); }
//	  
//		 return result;
//	} 
}
