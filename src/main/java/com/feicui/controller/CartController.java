package com.feicui.controller;

import com.feicui.model.Products;
import com.feicui.model.Users;
import com.feicui.service.ProductService;
import com.feicui.utils.ProductUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Controller
public class CartController {
	private static Logger logger;
	static {
		logger = Logger.getRootLogger();
		PropertyConfigurator.configure("log4j.properties");
	}
	@Autowired
	private ProductService service;
	//显示购物车页面
	@RequestMapping("showCart")
	public String showCart(){
		return "cart";
	}

	//加入购物车
	@RequestMapping("addCart")
	@ResponseBody
	public String addCart(String id,HttpServletRequest request){
		//获取到加入购物车的商品
		Products product = null;
		HttpSession session = request.getSession();
		
		
		//先从session中获取购物车对象
		Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		if(cart == null){
			//定义一个map集合作为购物车
			cart = new HashMap<Products, Integer>();
			product = service.findProductById(id);
			cart.put(product, 1);
		}else{
			
			//判断加入购物车的商品，在购物车中是否存在
			Set<Products> keySet = cart.keySet();
			product = ProductUtils.findProduct(keySet, id);
			
			if(product == null){
				product = service.findProductById(id);
				cart.put(product, 1);
			}else{
				cart.put(product, cart.get(product)+ 1);
			}
		}
		
		
		//将购物车对象放到session中
		session.setAttribute("cart", cart);
		
		Users users = (Users) session.getAttribute("user");
		product = service.findProductById(id);
		
		logger.error(users.getId()+","+product.getId()+",addCart");
		return "{\"msg\":\"true\"}";
	}
	
	//修改购物车信息
	@RequestMapping("updateCart")
	public String updateCart(String id,Integer count,HttpServletRequest request){
		//获取购物车对象
		HttpSession session = request.getSession();
		Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		Set<Products> keySet = cart.keySet();
		Products product = ProductUtils.findProduct(keySet, id);
		
		cart.put(product, count);
		
		if(count == 0){
			cart.remove(product);
		}
		
		session.setAttribute("cart", cart);
		
		
		return "redirect:showCart";
	}
	
	@RequestMapping("calculateMoney")
	public String calculateMoney(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		Map<Products, Integer> cart = (Map<Products, Integer>) session.getAttribute("cart");
		Iterator iter = cart.entrySet().iterator();
		int money = 0;
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Products pro = (Products) entry.getKey();
			Long value = pro.getPrice();
			int num = (int) entry.getValue();
			money += value*num;
		}
		System.err.println(money);
		model.addAttribute("money", money);
		return "redirect:showCart";
	}
}
