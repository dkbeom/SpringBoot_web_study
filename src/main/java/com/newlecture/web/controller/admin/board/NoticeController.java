package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController {

	@Autowired
	private ServletContext ctx;

	@Autowired
	private NoticeService service;

	// 공지사항 목록 조회(관리자)(공지사항 목록 페이지)
	@GetMapping("list")
	public String list(String p, String f, String q, Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/customer/notice/list";
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

		List<NoticeView> list = service.getViewList(page, field, query, true);
		int count = service.getCount(field, query, true);

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("page", page);

		return "admin.board.notice.list";
	}

	// 공지사항 목록 조회(관리자)(공지사항 목록 페이지에서 공지사항 일괄공개/일괄삭제 누르면 나오는 페이지)
	@PostMapping("list")
	public String list(String[] open, String[] del, String ids, String cmd) {

		switch (cmd) {
		case "일괄공개":

			// 특정 페이지에서 나오는 공지사항의 id들
			String[] notice_idsArr = ids.trim().split(" ");

			// 일괄공개 요청한 공지사항 id들
			List<String> openIds = Arrays.asList(open);

			// 일괄비공개(공개 체크안함) 요청한 공지사항 id들
			List<String> closeIds = new ArrayList(Arrays.asList(notice_idsArr));
			closeIds.removeAll(openIds);

			int pubResult = service.updatePubAll(openIds, closeIds);

			break;
		case "일괄삭제":

			int delResult = service.deleteAll(del);

			break;
		}

		return "redirect:list";
	}

	// 자세한 공지사항 조회(관리자)(공지사항 목록 페이지에서 공지사항 하나 누르면 나오는 페이지)
	@GetMapping("detail")
	public String detail(int id, Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/customer/notice/detail?id="+id;
		}

		Notice notice = service.getNotice(id);
		Notice nextNotice = service.getNext(id);
		Notice prevNotice = service.getPrev(id);
		List<Comment> comments = service.getComment(id);

		model.addAttribute("n", notice);
		model.addAttribute("next", nextNotice);
		model.addAttribute("prev", prevNotice);
		model.addAttribute("cmt", comments);

		return "admin.board.notice.detail";
	}

	// detail 페이지에서 댓글 삭제 or 댓글 쓰기 버튼을 눌렀을 때
	@PostMapping("detail")
	public String detail(String content, String commentWriter, int noticeId, String delete) {

		// 댓글 삭제 버튼을 눌렀을 때(댓글 쓰기 버튼을 누르지 않았을 때)
		if (delete != null) {
			service.deleteComment(Integer.parseInt(delete));
		}
		// 댓글 삭제 버튼을 누르지 않았을 때(댓글 쓰기 버튼을 눌렀을 때)
		else {
			Comment comment = new Comment();
			comment.setContent(content);
			comment.setWriter_id(commentWriter);
			comment.setNotice_id(noticeId);
			service.insertComment(comment);
		}

		return "redirect:detail?id=" + noticeId;
	}

	// 공지사항 목록 페이지에서 "글쓰기" 버튼 누르면 나오는 페이지
	@GetMapping("reg")
	public String reg(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		return "admin.board.notice.reg";
	}

	// 글쓰기 버튼 누르면 나오는 페이지에서 글쓰기를 했을 때
	@PostMapping("reg")
	public String reg(String title, String content, MultipartFile file, boolean open) {

		// 실제 물리경로 얻기
		String webPath = "/upload";
		String realPath = ctx.getRealPath(webPath);

		// 업로드하기 위한 경로가 없을 경우
		File savePath = new File(realPath);
		if (!savePath.exists())
			savePath.mkdirs();

		// 실제 물리경로에 경로 구분자와 데이터로 받아온 파일의 이름 추가
		String fileName = file.getOriginalFilename();
		realPath += File.separator + fileName; // 운영체제에 따른 경로 구분자 추가
		// 받아온 파일의 실제 경로를 이용해서 File 객체 만듬
		File saveFile = new File(realPath);
		// 가져온 멀티파일 객체를 실제 물리경로에 저장
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// title, writer_id, file, content, pub 데이터베이스에 넣기
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter_id("newlec");
		notice.setFiles(fileName);
		notice.setContent(content);
		notice.setPub(open);

		service.insert(notice);

		return "redirect:list";
	}

	// 수정 페이지(공지사항 디테일 페이지에서 수정 버튼을 누르면 나오는 페이지)
	@GetMapping("edit")
	public String edit(int id, Model model, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		Notice notice = service.getNotice(id);

		model.addAttribute("n", notice);

		return "admin.board.notice.edit";
	}

	// 수정 페이지에서 수정을 마치고, 등록을 누른 후의 페이지
	@PostMapping("edit")
	public String edit(int id, int hit, String title, String content, MultipartFile file, boolean open) {

		// 실제 물리경로 얻기
		String webPath = "/upload";
		String realPath = ctx.getRealPath(webPath);

		// 업로드하기 위한 경로가 없을 경우
		File savePath = new File(realPath);
		if (!savePath.exists())
			savePath.mkdirs();

		// 실제 물리경로에 경로 구분자와 데이터로 받아온 파일의 이름 추가
		String fileName = file.getOriginalFilename();
		realPath += File.separator + fileName; // 운영체제에 따른 경로 구분자 추가
		// 받아온 파일의 실제 경로를 이용해서 File 객체 만듬
		File saveFile = new File(realPath);
		// 가져온 멀티파일 객체를 실제 물리경로에 저장
		try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// title, writer_id, file, content, pub 데이터베이스에 넣기
		Notice notice = new Notice();
		notice.setId(id);
		notice.setTitle(title);
		notice.setWriter_id("newlec");
		notice.setFiles(fileName);
		notice.setContent(content);
		notice.setHit(hit);
		notice.setPub(open);

		service.update(notice);

		return "redirect:list";
	}

	// detail 페이지에서 단일 공지사항 삭제
	@RequestMapping("del")
	public String del(int id, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		service.delete(id);

		return "redirect:list";
	}
}