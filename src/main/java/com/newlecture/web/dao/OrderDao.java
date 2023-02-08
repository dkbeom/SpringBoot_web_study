package com.newlecture.web.dao;

import java.util.Map;

import com.newlecture.web.entity.Order;

public interface OrderDao {

	boolean insertOrder(Order order);

	Order findById(String merchant_uid);

	boolean updateOrder(Map<String, Object> map);
}
