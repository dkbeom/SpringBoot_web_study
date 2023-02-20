package com.newlecture.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.WishDao;
import com.newlecture.web.entity.Wish;

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
	public boolean insertWishlist(Wish wish) {
		return mapper.insertWishlist(wish);
	}

	@Override
	public boolean deleteWishlist(Wish wish) {
		return mapper.deleteWishlist(wish);
	}

	@Override
	public Integer getWishId(int item_id, String member_id) {
		return mapper.getWishId(item_id, member_id);
	}
}
