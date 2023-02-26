package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.CartDao;
import com.newlecture.web.entity.Cart;
import com.newlecture.web.entity.CartView;

@Service
public class CartServiceImp implements CartService {
	
	@Autowired
	private CartDao cartDao;
	
	//////////////////////////////////////////////////////////
	
	@Override
	public boolean putCart(Cart cart) {
		return cartDao.insertCart(cart);
	}

	@Override
	public boolean updateCartQuantity(Cart cart) {
		return cartDao.updateCartQuantity(cart);
	}

	@Override
	public Integer getQuantity(Integer item_id, String member_id) {
		return cartDao.getQuantity(item_id, member_id);
	}

	@Override
	public List<Cart> getCartList(String member_id) {
		return cartDao.getCartList(member_id);
	}

	@Override
	public List<CartView> getCartViewList(String member_id) {
		return cartDao.getCartViewList(member_id);
	}

	@Override
	public int getCount(String member_id) {
		return cartDao.getCount(member_id);
	}

	@Override
	public boolean deleteSelected(Integer[] select, String member_id) {
		return cartDao.deleteSelected(select, member_id);
	}

	@Override
	public boolean delete(Integer delete, String member_id) {
		return cartDao.delete(delete, member_id);
	}
}
