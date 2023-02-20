package com.newlecture.web.controller.item;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.Item;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Wish;
import com.newlecture.web.service.FileService;
import com.newlecture.web.service.ItemService;

@Controller
@RequestMapping("/item/")
public class ItemController {

	@Autowired
	private FileService fileService;

	@Autowired
	private ItemService itemService;
	
	////////////////////////////////////////////////////////////////////

	// 상품 등록하는 페이지를 열 때
	@GetMapping("regItem")
	public String regItem(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자(code == 0)가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		return "item.regItem";
	}

	// 상품 등록하고 난 후
	@PostMapping("regItem")
	public String regItem(MultipartFile file, @ModelAttribute Item item, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자(code == 0)가 아니면, /index 로 보내기
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		// 파일이 넘어온 경우
		if (file != null) {
			String uuid = UUID.randomUUID().toString();
			fileService.saveFile(file, uuid);
			item.setImageUUID(uuid);
		}
		itemService.insertItem(item);

		return "redirect:/index";
	}

	// 상품 디테일 페이지를 열 때
	@GetMapping("detail")
	public String detail(int id, Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");
		
		// 해당 아이템 가져오기
		Item item = itemService.getItem(id);
		model.addAttribute("item", item);
		
		// 찜한 상품인지 여부 확인
		boolean isWish = itemService.isWish(id, loginMember.getId());
		model.addAttribute("isWish", isWish);

		return "item.detail";
	}

	// 찜 리스트 페이지를 열 때
	@GetMapping("wishlist")
	public String wishlist() {
		return "item.wishlist";
	}

	// 상품 디테일에서 찜 버튼을 눌러서 찜할 때
	@GetMapping("insertWishlist")
	@ResponseBody
	public void insertWishlist(int itemId, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		Item item = itemService.getItem(itemId);
		
		Wish wish = new Wish();
		wish.setItem_id(itemId);
		wish.setItem_name(item.getName());
		wish.setMember_id(loginMember.getId());

		itemService.insertWishlist(wish);
	}

	// 상품 디테일에서 찜 버튼을 눌러서 찜을 해제할 때
	@GetMapping("deleteWishlist")
	@ResponseBody
	public void deleteWishlist(int itemId, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		Wish wish = new Wish();
		wish.setItem_id(itemId);
		wish.setMember_id(loginMember.getId());

		itemService.deleteWishlist(wish);
	}

	// 장바구니 페이지를 열 때
	@GetMapping("cart")
	public String cart() {
		return "item.cart";
	}

}
