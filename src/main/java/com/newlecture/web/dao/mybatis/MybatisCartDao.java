package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.CartDao;
import com.newlecture.web.entity.Cart;
import com.newlecture.web.entity.CartView;

@Repository
public class MybatisCartDao implements CartDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private CartDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisCartDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(CartDao.class);
	}

	///////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean insertCart(Cart cart) {
		return mapper.insertCart(cart);
	}

	@Override
	public boolean updateCartQuantity(Cart cart) {
		return mapper.updateCartQuantity(cart);
	}

	@Override
	public Integer getQuantity(Integer item_id, String member_id) {
		return mapper.getQuantity(item_id, member_id);
	}

	@Override
	public List<Cart> getCartList(String member_id) {
		return mapper.getCartList(member_id);
	}

	@Override
	public List<CartView> getCartViewList(String member_id) {
		return mapper.getCartViewList(member_id);
	}

	@Override
	public int getCount(String member_id) {
		return mapper.getCount(member_id);
	}

	@Override
	public boolean deleteSelected(Integer[] select, String member_id) {
		return mapper.deleteSelected(select, member_id);
	}

	@Override
	public boolean delete(Integer delete, String member_id) {
		return mapper.delete(delete, member_id);
	}

}
