package com.newlecture.web.controller.item;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.web.entity.Cart;
import com.newlecture.web.entity.CartView;
import com.newlecture.web.entity.Item;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Review;
import com.newlecture.web.entity.Wish;
import com.newlecture.web.entity.WishView;
import com.newlecture.web.service.CartService;
import com.newlecture.web.service.FileService;
import com.newlecture.web.service.ItemService;
import com.newlecture.web.service.ReviewService;
import com.newlecture.web.service.WishService;

@Controller
@RequestMapping("/item/")
public class ItemController {

	@Autowired
	private FileService fileService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private WishService wishService;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ReviewService reviewService;

	////////////////////////////////////////////////////////////////////
	
	// 상품 등록하는 페이지를 열 때
	@GetMapping("regItem")
	public String regItem(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자(code == 0)가 아니면, /index 로 이동
		if (loginMember == null || loginMember.getCode() != 0) {
			return "redirect:/index";
		}

		return "item.regItem";
	}

	// 상품 등록하고 난 후
	@PostMapping("regItem")
	public String regItem(MultipartFile file, @ModelAttribute Item item, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 관리자(code == 0)가 아니면, /index 로 이동
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
		
		// 로그인한 상태가 아니면, 로그인 페이지로 이동
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// 해당 아이템 가져오기
		Item item = itemService.getItem(id);
		model.addAttribute("item", item);

		// 찜한 상품인지 여부 확인
		boolean isWish = wishService.isWish(id, loginMember.getId());
		model.addAttribute("isWish", isWish);
		
		// 리뷰 리스트 가져오기
		List<Review> reviewList = reviewService.getReviewList(id);
		model.addAttribute("reviewList", reviewList);

		return "item.detail";
	}
	
	// 상품 디테일 페이지에서 리뷰를 삭제하거나, 리뷰를 등록했을 때
	@PostMapping("detail")
	public String detail(Integer deleteReviewId, String writerNickname, Integer score, String content, Integer itemId) {
		
		// 리뷰 삭제 버튼을 눌렀을 때
		if(deleteReviewId != null) {
			// 여기서, 상품 DB에도 리뷰 평점 삭제해줌
			reviewService.deleteReview(deleteReviewId);
		}
		// 리뷰를 등록했을 때
		else {
			Review review = new Review();
			review.setWriter_nickname(writerNickname);
			review.setContent(content);
			review.setItem_id(itemId);
			review.setScore(score);
			
			// 여기서, 상품 DB에도 리뷰 평점 저장해줌
			reviewService.insertReview(review);
		}
		
		return "redirect:detail?id="+itemId;
	}

	// 찜 리스트 페이지를 열 때
	@GetMapping("wishlist")
	public String wishlist(Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태가 아니면, 로그인 페이지로 이동
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// 현재 사용자의 찜 리스트 가져오기
		List<WishView> wishViewlist = wishService.getWishViewList(loginMember.getId());

		// 현재 사용자의 찜 리스트 상품 종류 갯수 가져오기
		int count = wishService.getCount(loginMember.getId());

		model.addAttribute("wishViewList", wishViewlist);
		model.addAttribute("count", count);

		return "item.wishlist";
	}

	// 찜 리스트 페이지에서 "장바구니" 또는 "삭제" 버튼을 눌렀을 때
	@PostMapping("wishlist")
	public String wishlist(boolean deleteSelected, int[] select, Integer cart, Integer delete, Model model,
			HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태가 아니면, 로그인 페이지로 이동
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// "선택삭제" 버튼을 눌렀을 때
		if (deleteSelected == true) {
			// 선택한 상품들을 찜 리스트에서 일괄삭제
			wishService.deleteSelectedWish(select, loginMember.getId());
		}
		// "삭제" 버튼을 눌렀을 때
		else if (delete != null) {
			// 해당 상품을 찜 리스트에서 삭제
			wishService.deleteWish(delete, loginMember.getId());
		}

		return "redirect:wishlist";
	}

	// 장바구니 담기 버튼을 눌렀을 때
	@PostMapping("putCart")
	@ResponseBody
	public void putCart(Integer item_id, String member_id, Integer quantity) {

		// 해당 상품을 장바구니에 담기
		Cart cart = new Cart();
		cart.setItem_id(item_id);
		cart.setMember_id(member_id);

		// 장바구니에 해당 상품이 기존에 없었던 경우
		if (cartService.getQuantity(item_id, member_id) == null || cartService.getQuantity(item_id, member_id) < 1) {
			cart.setQuantity(quantity == null ? 1 : quantity);
			cartService.putCart(cart);
		}
		// 장바구니에 해당 상품이 기존에 있었던 경우
		else {
			cart.setQuantity(cartService.getQuantity(item_id, member_id) + (quantity == null ? 1 : quantity));
			cartService.updateCartQuantity(cart);
		}
	}
	
	// 상품 디테일에서 해당 상품이 찜 리스트에 있는 상품인지 확인
	@GetMapping("isWish")
	@ResponseBody
	public boolean isWish(int itemId, String memberId) {
		
		return wishService.isWish(itemId, memberId);
	}

	// 상품 디테일에서 찜 버튼을 눌러서 찜할 때
	@GetMapping("insertWishlist")
	@ResponseBody
	public void insertWishlist(int itemId, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		Wish wish = new Wish();
		wish.setItem_id(itemId);
		wish.setMember_id(loginMember.getId());

		wishService.insertWish(wish);
	}

	// 상품 디테일에서 찜 버튼을 눌러서 찜을 해제할 때
	@GetMapping("deleteWishlist")
	@ResponseBody
	public void deleteWishlist(int itemId, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		Wish wish = new Wish();
		wish.setItem_id(itemId);
		wish.setMember_id(loginMember.getId());

		wishService.deleteWish(wish);
	}

	// 장바구니 페이지를 열 때
	@GetMapping("cart")
	public String cart(Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태가 아니면, 로그인 페이지로 이동
		if (loginMember == null) {
			return "redirect:/member/login";
		}

		// 현재 사용자의 장바구니 가져오기
		List<CartView> cartViewList = cartService.getCartViewList(loginMember.getId());

		// 현재 사용자의 장바구니 상품 종류 갯수 가져오기
		int count = cartService.getCount(loginMember.getId());

		model.addAttribute("cartViewList", cartViewList);
		model.addAttribute("count", count);

		return "item.cart";
	}

	@PostMapping("cart")
	public String cart(@RequestParam(name="deleteSelected", defaultValue="false") boolean deleteSelected,
					Integer[] select, Integer delete, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginSession");
		
		// 로그인한 상태가 아니면, 로그인 페이지로 이동
		if (loginMember == null) {
			return "redirect:/member/login";
		}
		
		// 선택삭제 버튼을 눌렀을 때
		if(deleteSelected) {
			cartService.deleteSelected(select, loginMember.getId());
		}
		// 개별 삭제 버튼을 눌렀을 때
		else {
			cartService.delete(delete, loginMember.getId());
		}
		
		return "redirect:cart";
	}
	
	@GetMapping("updateCartQuantity")
	@ResponseBody
	public void updateCartQuantity(int itemId, String memberId, int quantity) {
		
		Cart cart = new Cart(null, itemId, memberId, quantity);
		
		cartService.updateCartQuantity(cart);
	}
}
