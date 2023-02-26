package com.newlecture.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.Item;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.ItemService;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private ItemService itemService;

	//////////////////////////////////////////////////////////////////////////////

	@GetMapping("index")
	public String index(String query, HttpSession session, Model model) {

		// 보여질 상품 리스트
		List<Item> itemList = itemService.getItemList(query, false);
		model.addAttribute("itemList", itemList);
		// 보여질 상품 갯수
		int numOfItem = itemService.getCount(query, false);
		model.addAttribute("numOfItem", numOfItem);

		//////////////////////////////////////////////////////////////////////////
		// 로그인 상태에 따라, 다른 상황(보이는 공지사항이 다름)
		Member loginMember = (Member) session.getAttribute("loginSession");

		List<NoticeView> noticeList;
		// 관리자일 경우
		if (loginMember != null && loginMember.getCode() == 0) {
			System.out.println("관리자 로그인 성공");
			// 공지사항 가져오기
			noticeList = noticeService.getViewList(true);
			model.addAttribute("noticeList", noticeList);
			return "home.index";
		}
		// 일반 회원일 경우
		else if (loginMember != null && loginMember.getCode() != 0) {
			System.out.println("일반 회원 로그인 성공");
			// 공지사항 가져오기
			noticeList = noticeService.getViewList(false);
			model.addAttribute("noticeList", noticeList);
			return "home.index";
		}
		// 로그인이 안된 경우
		else {
			System.out.println("로그인 실패");
			// 공지사항 가져오기
			noticeList = noticeService.getViewList(false);
			model.addAttribute("noticeList", noticeList);
			return "home.index";
		}
	}

	@GetMapping("help")
	public String help() {
		return "home.help";
	}
}
