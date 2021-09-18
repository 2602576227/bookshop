<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的订单</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/public.css">
      <script type="text/javascript" src = "${pageContext.request.contextPath }/js/jquery-3.3.1.min.js"></script>
</head>

<body>
    <div class="topbar">
        <div class="topbar_content">
            <div class="topbar_content_left">
                欢迎光临Briup图书商城
            </div>
            <div class="topbar_content_right">
                <ul>
                	<c:if test="${empty user }">
	                    <li>
	                        <a href="${pageContext.request.contextPath}/showLogin" style="color:rgb(241, 187, 10)">亲，请登录</a>
	                    </li>
	                    <li>
                        	<a href="${pageContext.request.contextPath}/showReg" style="color: rgb(241, 187, 10)">免费注册</a>
                   		</li>
                	</c:if>
                	<c:if test="${!empty user}">
	                    <li>
	                        <a href="#" style="color:rgb(241, 187, 10)">${user.nickname}</a>
	                    </li>
	                     <li>
	                        <a href="${pageContext.request.contextPath}/logout" style="color:rgb(241, 187, 10)">退出登录</a>
	                    </li>
                	</c:if>
                    
                    <li>
                        <a href="${pageContext.request.contextPath}/showIndex">首页</a>
                    </li>
                    <li>|</li>
                    <li>
                        <a href="${pageContext.request.contextPath}/showCart">购物车</a>
                    </li>
                    <li>|</li>
                    <li>
                        <a href="${pageContext.request.contextPath}/showOrders">我的订单</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="cart_content">
        <div class="header">
            <div class="header_png"></div>
            <div class="estore" align="center">
                <h1 align="center" style="font-size: 28px;color: #87520E;margin: 40px 0 10px 0;">Briup图书商城</h1>
                <img src="images/book6.png" style="border-radius:50%">
                <br/>
                <font size="2">Hi!你好</font>
                <br/>
                <br/>
                <c:if test="${empty user }">
                <a href="${pageContext.request.contextPath}/showReg"><button>注册</button></a>
                <a href="${pageContext.request.contextPath}/showLogin"><button>登陆</button></a>
                </c:if>
                <c:if test="${!empty user }">
                <a href="${pageContext.request.contextPath}/changeusers"><button>切换账号</button></a>
                <a href="${pageContext.request.contextPath}/logout"><button>退出登录</button></a>
                </c:if>
            </div>
        </div>

        <div align="center" id="cart">
            <table class="maintable" border="1" style="text-align: center">
                <tbody>
                    <tr style="background:#87520E;color:white;line-height:40px;font-size:18px;border:#87520E solid 1px;text-align: center">
                        <td>订单编号</td>
                        <td>订单信息</td>
                        <td>订单状态</td>
                        <td>订单操作</td>
                    </tr>

					<c:if test="${empty list}">
						<tr><td colspan="4">没有订单信息</td></tr>
					</c:if>
					<c:if test="${!empty list}">
						<c:forEach items="${list}" var="order">
		                    <tr>
		                        <td width="26%">${order.id}</td>
		                        <td width="50%">
		                            <table style="border-collapse: collapse;border:#f8e49b;width:100%;text-align:center;" border="1">
		                                <tbody>
		                                    <tr style="background:#87520E;color:white;line-height:35px;border:white solid 1px;">
		                                        <td  width="45%">商品名称</td>
		                                        <td  width="20%">商品单价</td>
		                                        <td  width="20%">购买数量</td>
		                                        <td >总价</td>
		                                    </tr>
		
										<c:forEach items="${order.orderItems}" var="item" varStatus="count">
		                                    <tr>
		                                        <td>${item.product.name}</td>
		                                        <td>${item.product.price}</td>
		                                        <td>${item.buynum }</td>
		                                        <c:if test="${count.count==1}">
		                                       	 <td rowspan="10">${order.money}</td>
		                                        
		                                        </c:if>
		                                    </tr>
										
										</c:forEach>
		                                </tbody>
		                            </table>
		                        </td>
		                        <c:if test="${order.paystate ==0}">
								<td>
		                            <button onclick="pay('${order.id}')">未支付</button>
		                        </td>
								</c:if>
								<c:if test="${order.paystate ==1}">
								<td>
		                            <a href="#">已支付</a>
		                        </td>
								</c:if>
		                        
		                        <td>
		                            <button onclick="delOrders('${order.id}')">取消订单</button>
		                        </td>
		
		
		                    </tr>
						</c:forEach>
					</c:if>
                   
                </tbody>
            </table>


        </div>
    </div>

    <div class="footer1">
        <p align="center">
            总部地址:江苏省苏州市昆山市巴城浦东软件园 电话：0512-50190290-8060
        </p>
        <p align="center"> Copyright © 2005-2021 昆山杰普软件科技有限公司，All Rights Reserved 苏ICP备12036804号-23</p>
    </div>

<script type="text/javascript">
		//支付
		function pay(id) {
			var f = confirm("确定支付吗?");
			if(!f){
				return;
			}
			window.location.href = "${pageContext.request.contextPath}/pay?id=" + id;
		}
		//删除订单
		function delOrders(id) {
			var f = confirm("确定删除吗?");
			if(!f){
				return;
			}
			window.location.href = "${pageContext.request.contextPath}/delOrders?id=" + id;
		}
</script>



</body>

</html>