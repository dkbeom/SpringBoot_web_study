package com.newlecture.web.service;

import com.newlecture.web.entity.Order;

public interface OrderService {
	
	// Order 객체 넣기
	boolean insertOrder(Order order);
	
	// uid로 Order 객체 찾기
	Order findById(String merchant_uid);
	
	// uid로 객체 찾은 다음, 객체 넣기(업데이트)
	boolean findByIdAndUpdate(String merchant_uid, Order order);
}
