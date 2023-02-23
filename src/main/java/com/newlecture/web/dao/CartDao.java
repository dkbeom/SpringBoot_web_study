package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Cart;
import com.newlecture.web.entity.CartView;

public interface CartDao {
	
	boolean insertCart(Cart cart);

	boolean updateCart(Cart cart);

	Integer getQuantity(Integer item_id, String member_id);

	List<Cart> getCartList(String member_id);

	List<CartView> getCartViewList(String member_id);

	int getCount(String member_id);
}
