package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Item;

public interface ItemDao {
	
	List<Item> getItemList(String query, boolean isAdmin);
	
	int getCount(String query, boolean isAdmin);

	Item getItem(int id);

	boolean insertItem(Item item);

	boolean updateItem(Item item);

	boolean deleteItem(int id);

	boolean deleteAllItem(int[] ids);
	
	boolean updatePubAllItem(int[] pubIds, int[] closeIds);

	boolean scoreItem(int id, double new_sum_score, int new_num_score);
	
	double getSumOfScore(int id);

	int getNumOfScore(int id);
}
