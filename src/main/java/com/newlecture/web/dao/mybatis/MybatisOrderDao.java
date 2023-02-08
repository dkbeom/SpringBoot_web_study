package com.newlecture.web.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.OrderDao;
import com.newlecture.web.entity.Order;

@Repository
public class MybatisOrderDao implements OrderDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private OrderDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisOrderDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(OrderDao.class);
	}

	@Override
	public boolean insertOrder(Order order) {
		return mapper.insertOrder(order);
	}

	@Override
	public Order findById(String merchant_uid) {
		return mapper.findById(merchant_uid);
	}

	@Override
	public boolean updateOrder(Map<String, Object> map) {
		return mapper.updateOrder(map);
	}
}
