package com.newlecture.web.dao;

import com.newlecture.web.entity.Wish;

public interface WishDao {
	
	boolean insertWishlist(Wish wish);

	boolean deleteWishlist(Wish wish);

	Integer getWishId(int item_id, String member_id);
}
