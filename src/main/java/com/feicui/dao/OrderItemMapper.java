package com.feicui.dao;

import java.util.List;
import com.feicui.model.OrderItem;

public interface OrderItemMapper {
	public int saveOrderItem(OrderItem item);
	public List<String> findProductId(String id);
}
