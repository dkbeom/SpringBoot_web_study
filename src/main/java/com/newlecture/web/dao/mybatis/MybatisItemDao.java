package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.ItemDao;
import com.newlecture.web.entity.Item;
import com.newlecture.web.entity.Wish;

@Repository
public class MybatisItemDao implements ItemDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private ItemDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisItemDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(ItemDao.class);
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	@Override
	public List<Item> getItemList(String query, boolean isAdmin) {
		return mapper.getItemList(query, isAdmin);
	}
	
	@Override
	public int getCount(String query, boolean isAdmin) {
		return mapper.getCount(query, isAdmin);
	}

	@Override
	public Item getItem(int id) {
		return mapper.getItem(id);
	}

	@Override
	public boolean insertItem(Item item) {
		return mapper.insertItem(item);
	}

	@Override
	public boolean updateItem(Item item) {
		return mapper.updateItem(item);
	}

	@Override
	public boolean deleteItem(int id) {
		return mapper.deleteItem(id);
	}

	@Override
	public boolean deleteAllItem(int[] ids) {
		return mapper.deleteAllItem(ids);
	}
	
	@Override
	public boolean updatePubAllItem(int[] pubIds, int[] closeIds) {
		return mapper.updatePubAllItem(pubIds, closeIds);
	}

	@Override
	public boolean scoreItem(int id, double new_sum_score, int new_num_score) {
		return mapper.scoreItem(id, new_sum_score, new_num_score);
	}
	
	@Override
	public double getSumOfScore(int id) {
		return mapper.getSumOfScore(id);
	}

	@Override
	public int getNumOfScore(int id) {
		return mapper.getNumOfScore(id);
	}
}
