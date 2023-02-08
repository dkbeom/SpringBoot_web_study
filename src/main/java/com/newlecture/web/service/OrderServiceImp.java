package com.newlecture.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.OrderDao;
import com.newlecture.web.entity.Order;

@Service
public class OrderServiceImp implements OrderService {
	
	@Autowired
	private OrderDao orderDao;

	@Override
	public boolean insertOrder(Order order) {
		return orderDao.insertOrder(order);
	}

	@Override
	public Order findById(String merchant_uid) {
		return orderDao.findById(merchant_uid);
	}

	@Override
	public boolean findByIdAndUpdate(String merchant_uid, Order order) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("merchant_uid", merchant_uid);
		map.put("order", order);
		
		return orderDao.updateOrder(map);
//		return orderDao.updateOrder(order);
	}

}
