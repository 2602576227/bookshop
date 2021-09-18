<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Briup图书商城</title>
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

    <div class="estore_content" style="min-height:900px">
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
        <aside class="leftaside" style="min-height:520px">
            <div class="category">
                <h1>图书类别</h1>
                <div>
                    <ul>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=novel">小说</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=cartoon">童书</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=study">学习考试</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=literature">文学</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=music">音乐</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=art">艺术</a>
                        </li>
                        <li>
                            <img src="images/plane.png" align="center">
                            <a href="${pageContext.request.contextPath }/showProductkinds?type=science">科技</a>
                        </li>
                    </ul>
                </div>
            </div>
           

            <div class="newbook">
                <h1>最新图书</h1>
                <div>
                    <ul>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">人间滋味</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">伊朗四千年</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">阿加莎·克里斯蒂必</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">孩子你可以更勇敢</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">罗马帝国衰亡史</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">重启人生</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">笨狼的故事</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">生命中的飞羽</a>
                        </li>
                    </ul>
                </div>
            </div>
        </aside>

        <div class="main" style = "height:600px">
            <div class="proMore" style="margin:20px 50px">
			
			
			   <img src="${pro.imgurl }" style="float:left;margin-right:20px;width:216px" height="200px">
				<table style="width:60%;">

					<tr>
						<td><h2>商品名称:${pro.name }</h2><br>
						
						</td>
				</tr>

					<tr class="lineH">
						<td>商品价格:￥${pro.price }</td>
				</tr>
					<tr class="lineH">
					<c:choose>
					    <c:when test="${pro.category eq 'novel' }">
					        <td>商品类别:小说</td>
					    </c:when>
					    <c:when test="${pro.category eq 'cartoon' }">
					        <td>商品类别:童书</td>
					    </c:when>
					    <c:when test="${pro.category eq 'study' }">
					        <td>商品类别:学习考试</td>
					    </c:when>
					    <c:when test="${pro.category eq 'literature' }">
					        <td>商品类别:文学</td>
					    </c:when>
					    <c:when test="${pro.category eq 'music' }">
					        <td>商品类别:音乐</td>
					    </c:when>
					    <c:when test="${pro.category eq 'art' }">
					        <td>商品类别:艺术</td>
					    </c:when>
					    <c:when test="${pro.category eq 'science' }">
					        <td>商品类别:科技</td>
					    </c:when>
					    <c:otherwise>
					        <td>商品类别:其他</td>
					    </c:otherwise>
					</c:choose>
					
				</tr>
					<tr class="lineH">
						<td>商品数量:${pro.pnum }</td>
				</tr>
					<tr style="font-size:18px;">
						<td >商品描述:&nbsp;&nbsp;<span style="height:10px;width:200px;font-size:16px;">${pro.description }</span></td>
				</tr>

					<tr>
						<td colspan="2"><button onclick="addCart('${pro.id}')" style="background:#87520E;color:white;line-height:30px;font-size:18px;border-radius:5px;border:#87520E;padding:5px 40px;cursor:pointer;">加入购物车</button></td>
				</tr>
			</table>
			</div> 
        </div>
    </div>

    <div class="footer1">
        <p align="center">
            总部地址:江苏省苏州市昆山市巴城浦东软件园 电话：0512-50190290-8060
        </p>
        <p align="center"> Copyright © 2005-2021 昆山杰普软件科技有限公司，All Rights Reserved 苏ICP备12036804号-23</p>
    </div>
	<script type="text/javascript">
		//加入购物车
		function addCart(id) {
			$.ajax({
				url:"${pageContext.request.contextPath}/addCart",
				data:{"id":id},
				type:"post",
				dataType:"json",
				success:function(data){
					if(data.msg == 'true'){
						alert('加入购物车成功')
					}
				}
			});
		}
	</script>


</body>

</html>