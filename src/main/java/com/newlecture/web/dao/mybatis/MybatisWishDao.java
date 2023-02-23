package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.WishDao;
import com.newlecture.web.entity.Wish;
import com.newlecture.web.entity.WishView;

@Repository
public class MybatisWishDao implements WishDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private WishDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisWishDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(WishDao.class);
	}

	///////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean insertWish(Wish wish) {
		return mapper.insertWish(wish);
	}

	@Override
	public boolean deleteWish(Wish wish) {
		return mapper.deleteWish(wish);
	}

	@Override
	public Integer getWishId(int item_id, String member_id) {
		return mapper.getWishId(item_id, member_id);
	}

	@Override
	public List<Wish> getWishList(String member_id) {
		return mapper.getWishList(member_id);
	}
	
	@Override
	public List<WishView> getWishViewList(String member_id) {
		return mapper.getWishViewList(member_id);
	}

	@Override
	public boolean deleteAll(int[] item_ids, String member_id) {
		return mapper.deleteAll(item_ids, member_id);
	}

	@Override
	public boolean delete(Integer item_id, String member_id) {
		return mapper.delete(item_id, member_id);
	}

	@Override
	public int getCount(String member_id) {
		return mapper.getCount(member_id);
	}
}
