package com.feicui.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feicui.dao.ProductMapper;
import com.feicui.model.PageBean;
import com.feicui.model.Products;
import com.feicui.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductMapper mapper;

	@Override
	public int saveProduct(Products pro) {
		return mapper.saveProduct(pro);
	}

	@Override
	public int updateProduct(Products pro) {
		return mapper.updateProduct(pro);
	}

	@Override
	public int delProduct(String id) {
		return mapper.delProduct(id);
	}

	@Override
	public Products findProductById(String id) {
		return mapper.findProductById(id);
	}

	@Override
	public List<Products> findProductsList() {
		return mapper.findProductsList();
	}

	@Override
	public List<Products> findProductsListByType(String type,PageBean page) {
		return mapper.findProductsListByType(type,page);
	}

	@Override
	public Integer findCount() {
		return mapper.findCount();
		
	}

	@Override
	public List<Products> findProductsListPage(PageBean page) {
		return mapper.findProductsListPage(page);
	}

	@Override
	public Integer findCountByType(String type) {
		return mapper.findCountByType(type);
	}

	@Override
	public List<Products> findProductsListPage(Map<String, Object> map) {
		return mapper.findProductsListPage2(map);
	}
}
