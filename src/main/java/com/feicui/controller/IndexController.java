package com.feicui.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feicui.model.PageBean;
import com.feicui.model.Products;
import com.feicui.model.Users;
import com.feicui.service.ProductService;

@Controller
public class IndexController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("showIndex")
	public String showIndex(Model model,Integer page,HttpSession session){
		return showIndex1(model,page,session);
	}
	@RequestMapping("/")
	public String showIndex1(Model model,Integer page,HttpSession session){
		//查询商品
//		List<Products> productsList = productService.findProductsList();
		
	
		
		//获取商品的总记录数
		Integer count = productService.findCount();
	
		
		//创建pageBean的对象，设置页面的信息
		PageBean pageBean = new PageBean(8, page, count);
		
		
		model.addAttribute("pagebean", pageBean);
		
		//调用具有分页功能的查询所有商品的方法
		Users users = (Users) session.getAttribute("user");
		List<Products> productsList= new ArrayList<Products>();
		if(users!=null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id",users.getId());
			params.put("pageNum",pageBean.getPageNum());
			params.put("pageSize",pageBean.getPageSize());
			productsList = productService.findProductsListPage(params);
			if(productsList.size() == 0) {
				productsList = productService.findProductsListPage(pageBean);
			}
			productsList.forEach(System.out::println);
		}else {
			//调用具有分页功能的查询所有商品的方法
			productsList = productService.findProductsListPage(pageBean);
		}
		//将查询到的商品在首页面中展示
		model.addAttribute("productsList", productsList);
		
		return "index";
	}
	
	
	//打开后台管理的主页面
	@RequestMapping("showAdminIndex")
	public String showAdminIndex(HttpServletRequest request, HttpServletResponse resp, Model model){
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		if(user == null)
		{
			model.addAttribute("power", false);   
			return "jump";
		}
		//System.err.println(user);
		String role = user.getRole();
		if(role.equals("admin"))
			return "admin/index";
		else
		{
			model.addAttribute("power", false);
			return "jump";
		}
	}
	
	

}
