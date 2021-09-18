package com.feicui.service;

import java.util.List;

import com.feicui.model.Orders;

public interface OrdersService {
	public int saveOrder(Orders order);
	/**
	 * 根据用户id查询订单
	 * @param id	用户id
	 * @return		查询到的订单
	 */
	public List<Orders> findOrdersList(Integer id);
	public int delOrder(String id);
	public int pay(String id);
}
