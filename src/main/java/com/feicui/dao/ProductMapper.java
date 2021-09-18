package com.feicui.dao;

import java.util.List;
import java.util.Map;

import com.feicui.model.PageBean;
import com.feicui.model.Products;

public interface ProductMapper {
	public int saveProduct(Products pro);
	public int updateProduct(Products pro);
	public int delProduct(String id);
	public Products findProductById(String id);
	public List<Products> findProductsList();
	public List<Products> findProductsListByType(String type,PageBean page);
	public Integer findCount();
	public List<Products> findProductsListPage(PageBean page);
	public Integer findCountByType(String type);
	public List<Products> findProductsListPage2(Map<String,Object> map);
}
