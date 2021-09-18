<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Briup图书商城</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/public.css">
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

    <div class="estore_content">
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
        <aside class="leftaside">
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
            <div class="author">
                <h1>人气作家</h1>
                <div class="content">
                    <figure>
                        <img src="images/bj.jpeg" width="77" height="83">
                        <figcaption>巴金</figcaption>
                        <p>现代著名作家。原名李芾甘，笔名巴金，四川成都人，1928年写成第一部长篇小说《灭亡》</p>
                    </figure>
                    <a href="#" style="background:#87520E;color:white; padding:6px;text-decoration:none;">查看</a>
                </div>
                <div class="content">
                    <figure>
                        <img src="images/ls.jpeg" width="77" height="83">
                        <figcaption>鲁迅</figcaption>
                        <p>我国现代伟大的文学家、思想家、革命家，中国现代文学的奠基者，新文化运动的主将</p>
                    </figure>
                    <a href="#" style="background:#87520E;color:white; padding:6px;text-decoration:none;">查看</a>
                </div>
                <div class="content">
                    <figure>
                        <img src="images/gmr.jpeg" width="77" height="83">
                        <figcaption>郭沫若</figcaption>
                        <p>我国现代文学家、诗人、戏剧家、历史文学家，新诗的奠基者</p>
                    </figure>
                    <a href="#" style="background:#87520E;color:white; padding:6px;text-decoration:none;">查看</a>
                </div>
                <div class="content">
                    <figure>
                        <img src="images/ls.jpeg" width="77" height="83">
                        <figcaption>老舍</figcaption>
                        <p>中国现代小说家、著名作家、杰出的语言大师、人民艺术家</p>
                    </figure>
                    <a href="#" style="background:#87520E;color:white; padding:6px;text-decoration:none;">查看</a>
                </div>
                <div class="content">
                    <figure>
                        <img src="images/qzs.jpeg" width="77" height="83">
                        <figcaption>钱钟书</figcaption>
                        <p>中国现代作家、文学研究家，与饶宗颐并称为“南饶北钱”</p>
                    </figure>
                    <a href="#" style="background:#87520E;color:white; padding:6px;text-decoration:none;">查看</a>
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
                            <a href="#">大数据开发基础</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">生命中的飞羽</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">flink编程思想</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">笑点</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">算法导论</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">剑指offer</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">外婆出租中</a>
                        </li>
                        <li>
                            <img src="images/star.png" align="center">
                            <a href="#">方向比努力更重要</a>
                        </li>
                    </ul>
                </div>
            </div>
        </aside>

        <div class="main">
            <div class="menubox">
                <div class="flash">
                	<video width="512" height="340" controls="controls">
					  <source src="img/1.mp4" type="video/mp4" />
					</video>
                </div>
                <div class="menu">
                    <ul>
                        <li style="background: #87520E;font-weight: 900;font-size: 18px;color: #fff">导航菜单</li>
                       <a href="${pageContext.request.contextPath}"><li class="aa">主页</li></a>
                        <a href="${pageContext.request.contextPath}/showCart"><li class="aa">我的购物车</li></a>
                        <a href="${pageContext.request.contextPath}/showOrders"><li class="aa">我的订单</li></a>
                        <li class="aa">下载榜单</li>
                        <li class="aa">关于我们</li>
                        <li class="aa">联系方式</li>
                    </ul>
                    <input type="text" style="width: 150px;height: 48px;border: 2px #87520E solid ;" />
                    <button style="width: 70px;height: 50px;;margin: 0;background: #87520E;border: 2px solid #87520E;font-size: 16px;font-weight: 800;color: #fff">search</button>
                </div>



            </div>


            <div class="tuijian">
                <div class="tuijian_top">
                    <h1 style="float: left;">倾情推荐</h1>
                    <p style="float: right;">更多商品>></p>
                </div>
                <div class="tuijian_product" style="min-width: 750px;">
                	<c:forEach items="${productList}" var="pro">
	                    <div style="float:left; margin: 10px 30px;">
	                        <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">${pro.name }</h3>
	                        <a href="${pro.imgurl}">
	                            <img src="${pro.imgurl }" alt="an image" class="image" width="108px" height="100px">
	                        </a>
	                        <br>
	                        <span>价格: ￥${pro.price }</span>
	                        <br>
	                        <a href="${pageContext.request.contextPath}/showProductinfo?id=${pro.id}">查看详细</a>
	                        <br>
	                        <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
	                    </div>
                	</c:forEach>
                </div>
                    
                <div style="float: none; margin-top: 20px;">
                    <button type="button" id="up" style="float: left;background:#87520E;line-height:30px;color:white;border:0;padding:3px 7px;font-size:15px;cursor:pointer;">上一页</button>
                    <button type="button" id="down" style="float: right;background:#87520E;line-height:30px;color:white;border:0;padding:3px 7px;font-size:15px;cursor:pointer;">下一页</button>
                </div>
				
				<div class="new">
                    <div class="new_top">
                        <h1 style="float: left;">新书预售</h1>
                        <p style="float: right;">更多商品>></p>
                    </div>
                    <div class="new_product">
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">麦田里的守望者（70周年纪念版）</h3>
                            <a href="#">
                                <img src="images/mtl.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">从你的全世界路过：修订本</h3>
                            <a href="#">
                                <img src="images/cnd.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">房思琪的初恋乐园 </h3>
                            <a href="#">
                                <img src="images/fsq.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">富爸爸穷爸爸精装典藏版</h3>
                            <a href="#">
                                <img src="images/fbb.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">人力资源从新手到高手</h3>
                            <a href="#">
                                <img src="images/rlz.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">你好，拼音（套装6册）</h3>
                            <a href="#">
                                <img src="images/nhp.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">方向比努力更重要</h3>
                            <a href="#">
                                <img src="images/fxb.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                        <div style="float:left; margin: 10px 30px;">
                            <h3 style="overflow:hidden;width:110px;text-overflow:ellipsis;white-space: nowrap;">郑渊洁四大名传</h3>
                            <a href="#">
                                <img src="images/zyj.jpeg" alt="an image" class="image" width="108px" height="100px">
                            </a>
                            <br>
                            <span>价格: ￥190</span>
                            <br>
                            <a href="#">查看详细</a>
                            <br>
                            <button style="background:#87520E;color:white;line-height:15px;font-size:13px;border-radius:5px;border:#87520E;cursor:pointer;">加入购物车</button>
                        </div>
                    </div>
                    <div style="float: none; margin-top: 20px;">
                        <button type="button" id="up" style="float: left;background:#87520E;line-height:30px;color:white;border:0;padding:3px 7px;font-size:15px;cursor:pointer;">上一页</button>
                        <button type="button" id="down" style="float: right;background:#87520E;line-height:30px;color:white;border:0;padding:3px 7px;font-size:15px;cursor:pointer;">下一页</button>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <div class="footer1">
        <p align="center">
            总部地址:江苏省苏州市昆山市巴城浦东软件园 电话：0512-50190290-8060
        </p>
        <p align="center"> Copyright © 2005-2021 昆山杰普软件科技有限公司，All Rights Reserved 苏ICP备12036804号-23</p>
    </div>


</body>

</html>