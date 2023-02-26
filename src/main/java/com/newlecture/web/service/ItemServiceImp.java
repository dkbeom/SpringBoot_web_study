package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.ItemDao;
import com.newlecture.web.entity.Item;

@Service
public class ItemServiceImp implements ItemService {
	
	@Autowired
	private ItemDao itemDao;
	
	/////////////////////////////////////////////////////////////////////
	
	@Override
	public List<Item> getItemList(boolean isAdmin) {
		return getItemList("", isAdmin);
	}
	@Override
	public List<Item> getItemList(String query, boolean isAdmin) {
		return itemDao.getItemList(query, isAdmin);
	}
	
	@Override
	public int getCount(boolean isAdmin) {
		return getCount("", isAdmin);
	}
	@Override
	public int getCount(String query, boolean isAdmin) {
		return itemDao.getCount(query, isAdmin);
	}
	
	@Override
	public Item getItem(int id) {
		return itemDao.getItem(id);
	}
	
	@Override
	public boolean insertItem(Item item) {
		return itemDao.insertItem(item);
	}
	
	@Override
	public boolean updateItem(Item item) {
		return itemDao.updateItem(item);
	}

	@Override
	public boolean deleteItem(int id) {
		return itemDao.deleteItem(id);
	}

	@Override
	public boolean deleteAllItem(int[] ids) {
		return itemDao.deleteAllItem(ids);
	}
	
	@Override
	public boolean updatePubAllItem(int[] pubIds, int[] closeIds) {
		return itemDao.updatePubAllItem(pubIds, closeIds);
	}

	@Override
	public boolean scoreItem(int id, Integer score) {
		
		Double sum_score = itemDao.getSumOfScore(id);
		Integer num_score = itemDao.getNumOfScore(id);
		
		Double new_sum_score = null;
		if(sum_score != null) {
			new_sum_score = sum_score + score;
		} else {
			new_sum_score = (double)score;
		}
		
		Integer new_num_score = null;
		if(num_score != null) {
			new_num_score = num_score + 1;
		} else {
			new_num_score = 1;
		}
		
		return itemDao.updateItemScore(id, new_sum_score, new_num_score);
	}
	@Override
	public boolean cancelScore(int id, Integer score) {
		
		Double sum_score = itemDao.getSumOfScore(id);
		Integer num_score = itemDao.getNumOfScore(id);
		
		Double new_sum_score = null;
		if(sum_score != null && sum_score >= score) {
			new_sum_score = sum_score - score;
		} else {
			new_sum_score = (double) 0;
		}
		
		Integer new_num_score = null;
		if(num_score != null && num_score >= 1) {
			new_num_score = num_score - 1;
		} else {
			new_num_score = 0;
		}
		
		return itemDao.updateItemScore(id, new_sum_score, new_num_score);
	}
}
