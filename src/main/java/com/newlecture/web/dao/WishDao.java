package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Wish;
import com.newlecture.web.entity.WishView;

public interface WishDao {
	
	boolean insertWish(Wish wish);
	
	boolean deleteWish(Wish wish);
	
	Integer getWishId(int item_id, String member_id);

	List<Wish> getWishList(String member_id);
	
	List<WishView> getWishViewList(String member_id);
	
	boolean deleteAll(int[] item_ids, String member_id);
	
	boolean delete(Integer item_id, String member_id);

	int getCount(String member_id);
}
