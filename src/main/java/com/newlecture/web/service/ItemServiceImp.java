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
	public boolean scoreItem(int id, int score) {
		
		double sum_score = itemDao.getSumOfScore(id);
		int num_score = itemDao.getNumOfScore(id);
		
		double new_sum_score = sum_score + score;
		int new_num_score = num_score + 1;
		
		return itemDao.scoreItem(id, new_sum_score, new_num_score);
	}
}
