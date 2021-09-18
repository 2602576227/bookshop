package com.feicui.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.feicui.model.PageBean;
import com.feicui.model.Products;
import com.feicui.model.Users;
import com.feicui.service.ProductService;
import com.feicui.utils.UUIDUtils;
import com.feicui.utils.UploadUtils;

@Controller
public class ProductController {
	private static Logger logger;
	static {
		logger = Logger.getRootLogger();
		PropertyConfigurator.configure("log4j.properties");
	}
	@Autowired
	private ProductService service;
	
	//打开商品管理的页面 
	@RequestMapping("showProductList")
	public String showProductList(Model model){
		//查询所有的商品
		List<Products> productsList = service.findProductsList();
		
		//将查询的商品传递到页面中展示
		//相当于request.setAttribute(arg0, arg1)
		model.addAttribute("productsList", productsList);
		
		return "admin/product";
	}
	
	//打开添加商品的页面
	@RequestMapping("showAddProduct")
	public String showAddProduct(){
		return "admin/addProduct";
	}
	
	//添加商品
	@RequestMapping("saveProduct")
	public String saveProduct(Products pro,MultipartFile imgpic){
		//将上传的文件上传到服务器
		//获取上传文件的路径
		String upload = UploadUtils.upload(imgpic);
		//jpg,png,gif
		
		//将路径保存到数据库中
		pro.setId(UUIDUtils.getUUID());
		pro.setImgurl(upload);
		pro.setState(1);//0:下架，1:上架
		
		service.saveProduct(pro);
		
		return "redirect:showProductList";
	}
	
	//删除商品
	@RequestMapping("delProduct")
	public String delProduct(String id){
		System.out.println(id);
		
		//调用service删除
		service.delProduct(id);
		
		return "redirect:showProductList";
	}
	
	//打开修改商品的页面
	@RequestMapping("showEditProduct")
	public String showEditProduct(String id,Model model){
		
		//查询要修改的商品
		Products product = service.findProductById(id);
		
		//将商品传到页面展示数据
		model.addAttribute("product", product);
		
		
		return "admin/editproduct";
	}
	//修改商品
	@RequestMapping("editProduct")
	public String editProduct(Products pro,MultipartFile imgpic){
		//判断是否有上传的文件
		if(!(imgpic.getOriginalFilename() == null) && !imgpic.getOriginalFilename().equals("")){
			String upload = UploadUtils.upload(imgpic);
			pro.setImgurl(upload);
		}
		service.updateProduct(pro);
		return "redirect:showProductList";
	}
	
	//打开商品展示的页面
	@RequestMapping("showProductkinds")
	public String showProductkinds(String type,Model model,Integer page){
		Integer  count = service.findCountByType(type);
		PageBean pageBean = new PageBean(8,page,count);
		
		//查询要显示的类型的商品
		List<Products> productList = service.findProductsListByType(type,pageBean);
		System.out.println(productList);
		
		//将商品传递到页面中展示
		model.addAttribute("productList", productList);
		
		return "productkinds";
	}
	@RequestMapping("showProductinfo")
	public String showProductinfo(String id, Model model,HttpSession session)
	{
		Products pro = service.findProductById(id);
		Users users = (Users) session.getAttribute("user");
		model.addAttribute("pro", pro);
		logger.error(users.getId()+","+pro.getId()+",showProduct");
		return "productinfo";
	}
}
