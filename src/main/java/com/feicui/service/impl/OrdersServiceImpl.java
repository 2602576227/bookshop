package com.feicui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feicui.dao.OrdersMapper;
import com.feicui.model.OrderItem;
import com.feicui.model.Orders;
import com.feicui.service.OrderItemService;
import com.feicui.service.OrdersService;
@Service
public class OrdersServiceImpl implements OrdersService {
	@Autowired
	private OrdersMapper mapper;
	@Autowired
	private OrderItemService service;

	@Override
	public int saveOrder(Orders order) {
		int num = mapper.saveOrder(order);
		//保存订单的订单项
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			service.saveOrderItem(orderItem);
		}
		return num;
	}

	@Override
	public List<Orders> findOrdersList(Integer id) {
		return mapper.findOrdersList(id);
	}
	
	public int delOrder(String id) {
		return mapper.delOrder(id);
	}

	@Override
	public int pay(String id) {
		return mapper.pay(id);
	}
}
