package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Wish;
import com.newlecture.web.entity.WishView;

public interface WishService {

	boolean insertWish(Wish wish);

	boolean deleteWish(Wish wish);

	boolean isWish(int item_id, String member_id);
	
	List<Wish> getWishList(String member_id);
	
	List<WishView> getWishViewList(String member_id);

	boolean deleteSelectedWish(int[] item_ids, String member_id);

	boolean deleteWish(Integer item_id, String member_id);
	
	int getCount(String member_id);
}
