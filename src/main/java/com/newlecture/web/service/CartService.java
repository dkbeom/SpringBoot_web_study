package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Cart;
import com.newlecture.web.entity.CartView;

public interface CartService {

	// 기존 장바구니에 없는 상품 담기
	boolean putCart(Cart cart);
	
	// 기존 장바구니에 있는 상품 담기
	boolean updateCartQuantity(Cart cart);

	// 장바구니에 담은 특정 상품의 갯수 구하기
	Integer getQuantity(Integer item_id, String member_id);

	List<Cart> getCartList(String member_id);

	List<CartView> getCartViewList(String member_id);

	// 장바구니에 담은 상품 종류 갯수
	int getCount(String member_id);

	// 선택된 장바구니 상품들 삭제
	boolean deleteSelected(Integer[] select, String member_id);

	// 특정 장바구니 상품 삭제
	boolean delete(Integer delete, String member_id);
}
