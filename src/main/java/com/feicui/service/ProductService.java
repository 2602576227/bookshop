package com.feicui.service;

import java.util.List;
import java.util.Map;

import com.feicui.model.PageBean;
import com.feicui.model.Products;

public interface ProductService {
	public int saveProduct(Products pro);
	public int updateProduct(Products pro);
	public int delProduct(String id);
	public Products findProductById(String id);
	public List<Products> findProductsList();
	/**
	 * 根据类别查询商品
	 * @param type		类别
	 * @return			差询到的商品
	 */
	public List<Products> findProductsListByType(String type,PageBean page);
	/**
	 * 获取商品的总记录数
	 * @return
	 */
	public Integer findCount();
	/**
	 * 有分页功能的查询商品的方法
	 * @param page
	 * @return
	 */
	public List<Products> findProductsListPage(PageBean page);
	public List<Products> findProductsListPage(Map<String,Object> map);
	public Integer findCountByType(String type);

}
