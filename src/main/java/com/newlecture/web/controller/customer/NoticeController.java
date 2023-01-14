package com.newlecture.web.controller.customer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/customer/notice/")
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
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
		if(p != null && !p.equals(""))
			page = Integer.parseInt(p);
		
		String field = "title";
		// f가 제대로 왔다면(f가 null이 아니고, 빈문자열이 아니라면)
		if(f != null && !f.equals(""))
			field = f;
		
		String query= "";
		// q가 제대로 왔다면(q가 null이 아니고, 빈문자열이 아니라면)
		if(q != null && !q.equals(""))
			query = q;
		
		List<NoticeView> list = service.getViewList(page, field, query, false);
		int count = service.getCount(field, query, false);
		
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
			return "redirect:/admin/board/notice/detail?id="+id;
		}
		
		int hit = service.hitUp(id);
		Notice notice = service.getNotice(id);
		Notice nextNotice = service.getNext(id);
		Notice prevNotice = service.getPrev(id);
		List<Comment> comment = service.getComment(id);
		
		model.addAttribute("n", notice);
		model.addAttribute("next", nextNotice);
		model.addAttribute("prev", prevNotice);
		model.addAttribute("cmt", comment);

		return "customer.notice.detail";
	}
	
	// 일반 회원이 댓글 달기
	@PostMapping("detail")
	public String detail(String content, String commentWriter, int noticeId) {
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setWriter_id(commentWriter);
		comment.setNotice_id(noticeId);
		
		service.insertComment(comment);
		
		return "redirect:detail?id="+noticeId;
	}
}