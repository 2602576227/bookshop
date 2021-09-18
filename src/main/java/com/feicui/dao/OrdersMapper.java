package com.feicui.dao;


import java.util.List;

import com.feicui.model.Orders;

public interface OrdersMapper {

	public int saveOrder(Orders order);

	public List<Orders> findOrdersList(Integer id);
	
	public int delOrder(String id);
	
	public int pay(String id);
}
