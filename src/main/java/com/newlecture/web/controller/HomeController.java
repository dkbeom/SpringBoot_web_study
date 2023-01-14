package com.newlecture.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, Model model) {
		
		// 공지사항 가져오기
		List<NoticeView> list = service.getViewList(false);
		model.addAttribute("list", list);
		
		// 세션이 있다면 세션 반환, 세션이 없다면 null 반환
		HttpSession session = request.getSession(false);
		
		// 로그인을 안한 상태라면 (세션이 존재하지 않는다면)
		if(session == null) {
			return "home.index";
		}
		
		Member loginMember = (Member)session.getAttribute("loginSession");
		
		// 여기서 의문? loginMember에 null 값이 들어갈 수도 있는데
		
		// loginMember 라는 세션 아이디가 존재하면
		if(loginMember != null) {	
			// 로그인은 한 상태지만, 회원 코드가 없을 때
			if(loginMember.getCode() == null) {
				return "home.index";
			}
			// 관리자일 경우
			else if(loginMember.getCode() == 0) {
				System.out.println("관리자 로그인 성공");
				list = service.getViewList(true);
				model.addAttribute("list", list);
				return "home.index";
			}
			// 일반 회원일 경우
			else {
				System.out.println("일반 회원 로그인 성공");
				return "home.index";
			}
		}
		return "home.index";
	}
	
	@RequestMapping("help")
	public String help() {
		return "home.help";
	}
}
