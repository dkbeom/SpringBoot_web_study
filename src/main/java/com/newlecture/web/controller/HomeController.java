package com.newlecture.web.controller;

import java.net.MalformedURLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlecture.web.entity.FileEntity;
import com.newlecture.web.entity.Item;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.NoticeView;
import com.newlecture.web.service.FileService;
import com.newlecture.web.service.ItemService;
import com.newlecture.web.service.NoticeService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private FileService fileService;
	

	@GetMapping("index")
	public String index(HttpSession session, Model model) {

		// 보여질 상품 리스트
		List<Item> itemList = itemService.getItemList(false);
		model.addAttribute("itemList", itemList);
		// 보여질 상품 갯수
		int numOfItem = itemService.getCount(false);
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

	// 상품 이미지 파일 출력
	@GetMapping(value = "images", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE })
	@ResponseBody
	public Resource image(@RequestParam("UUID") String uuid) throws MalformedURLException {

		System.out.println("file 꺼내기 직전인데, uuid => "+uuid);
		FileEntity fileEntity = fileService.findByUUID(uuid);
		System.out.println("file의 아이디는 => "+fileEntity.getId());

		return new UrlResource("file:" + fileEntity.getSavedPath());
	}
}
