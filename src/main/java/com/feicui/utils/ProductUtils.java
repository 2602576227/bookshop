package com.feicui.utils;

import java.util.Set;

import com.feicui.model.Products;

public class ProductUtils {
	public static Products findProduct(Set<Products> set,String id){
		for (Products product : set) {
			if(product.getId().equals(id)){
				return product;
			}
		}
		return null;
	}
}
