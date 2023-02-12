package com.newlecture.web.controller.customer;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.FileEntity;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.FileService;
import com.newlecture.web.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customer/notice/")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;
	private final FileService fileService;

	// 일반 회원이 공지사항 목록 보기
	@GetMapping("list")
	public String list(String p, String f, String q, Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자라면, /index 로 보내기
		if (loginMember != null && loginMember.getCode() == 0) {
			return "redirect:/admin/board/notice/list";
		}

		int page = 1;
		// p가 제대로 왔다면(p가 null이 아니고, 빈문자열이 아니라면)
		if (p != null && !p.equals(""))
			page = Integer.parseInt(p);

		String field = "title";
		// f가 제대로 왔다면(f가 null이 아니고, 빈문자열이 아니라면)
		if (f != null && !f.equals(""))
			field = f;

		String query = "";
		// q가 제대로 왔다면(q가 null이 아니고, 빈문자열이 아니라면)
		if (q != null && !q.equals(""))
			query = q;

		List<NoticeView> list = noticeService.getViewList(page, field, query, false);
		int count = noticeService.getCount(field, query, false);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("page", page);

		return "customer.notice.list";
	}

	// 일반 회원이 공지사항 보기
	@GetMapping("detail")
	public String detail(int id, Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자라면, /index 로 보내기
		if (loginMember != null && loginMember.getCode() == 0) {
			return "redirect:/admin/board/notice/detail?id=" + id;
		}

		noticeService.hitUp(id);
		Notice notice = noticeService.getNotice(id);
		Notice nextNotice = noticeService.getNext(id);
		Notice prevNotice = noticeService.getPrev(id);
		List<Comment> comment = noticeService.getComment(id);

		model.addAttribute("n", notice);
		model.addAttribute("next", nextNotice);
		model.addAttribute("prev", prevNotice);
		model.addAttribute("cmt", comment);

		if (notice.getFileUUID() != null) {
			String fileUUID = notice.getFileUUID();
			String fileName = fileService.findFileNameByUUID(fileUUID);
			model.addAttribute("fileName", fileName);
		}

		return "customer.notice.detail";
	}

	// 일반 회원이 댓글 달거나, 댓글 삭제 버튼을 눌렀을 때
	@PostMapping("detail")
	public String detail(String content, String commentWriter, int noticeId, String delete) {

		// 댓글 삭제 버튼을 눌렀을 때(댓글 쓰기 버튼을 누르지 않았을 때)
		if (delete != null) {
			noticeService.deleteComment(Integer.parseInt(delete));
		}
		// 댓글 삭제 버튼을 누르지 않았을 때(댓글 쓰기 버튼을 눌렀을 때)
		else {
			Comment comment = new Comment();
			comment.setContent(content);
			comment.setWriter_nickname(commentWriter);
			comment.setNotice_id(noticeId);
			noticeService.insertComment(comment);
		}

		return "redirect:detail?id=" + noticeId;
	}

	// 첨부파일 이미지 출력
	@GetMapping(value = "images/{UUID}")//, produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE })
	@ResponseBody
	public Resource image(@PathVariable("UUID") String uuid) throws MalformedURLException {

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
	@GetMapping("display/{UUID}")
	public ResponseEntity<byte[]> display(@PathVariable("UUID") String uuid){
		
		FileEntity fileEntity = fileService.findByUUID(uuid);
		
		File file = new File(fileEntity.getSavedPath());
		
		HttpHeaders header = new HttpHeaders();
		
		ResponseEntity<byte[]> result = null;
		
		try {
			// Files.probeContentType() - 해당 파일의 Content-type을 반환
			header.add("Content-type", Files.probeContentType(file.toPath()));
			// FileCopyUtils.copyToByteArray() - 해당 파일을 복사해서, byte[]로 변환
			result = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}