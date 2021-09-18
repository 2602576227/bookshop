package com.feicui.service;

import java.util.List;

import com.feicui.model.OrderItem;

public interface OrderItemService {

	public int saveOrderItem(OrderItem item);
	
	public List<String> findProductId(String id);
}
