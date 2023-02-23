package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.WishDao;
import com.newlecture.web.entity.Wish;
import com.newlecture.web.entity.WishView;

@Service
public class WishServiceImp implements WishService {

	@Autowired
	private WishDao wishDao;
	
	/////////////////////////////////////////////////////////////
	
	@Override
	public boolean insertWish(Wish wish) {
		return wishDao.insertWish(wish);
	}
	@Override
	public boolean deleteWish(Wish wish) {
		return wishDao.deleteWish(wish);
	}
	@Override
	public boolean isWish(int item_id, String member_id) {
		if(wishDao.getWishId(item_id, member_id) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<Wish> getWishList(String member_id) {
		return wishDao.getWishList(member_id);
	}
	
	@Override
	public List<WishView> getWishViewList(String member_id) {
		return wishDao.getWishViewList(member_id);
	}

	@Override
	public boolean deleteSelectedWish(int[] item_ids, String member_id) {
		return wishDao.deleteAll(item_ids, member_id);
	}

	@Override
	public boolean deleteWish(Integer item_id, String member_id) {
		return wishDao.delete(item_id, member_id);
	}
	
	@Override
	public int getCount(String member_id) {
		return wishDao.getCount(member_id);
	}
}
