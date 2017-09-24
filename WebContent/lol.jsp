<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link href="style.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/my.js"></script>
	<style>
		#father {
			border: 0px solid red;
			width: 1300px;
			height: 2170px;
			margin: auto;
		}
	</style>
	<title>短腿猪游戏论坛首页</title>
	<meta charset="UTF-8" />
	<script>
		function init() {
			//书写轮图片显示的定时操作
			setInterval("changeImg()", 3000);
		}

		//书写函数
		var i = 30
		function changeImg() {
			i++;
			//获取图片位置并设置src属性值
			document.getElementById("img31").src = "img/" + i + ".jpg";
			if (i == 33) {
				i = 30;
			}
		}
	</script>

	<script type="text/javascript">
			function show_confirm(cost) {
				console.log("the cost is " + cost);
				var r = confirm("是否购买？");
				if (r == true) {
					buy_skin(cost);
					//alert("恭喜你购买成功!");
				}
				else {
					alert("已取消购买!");
				}
			}
	</script>

</head>

<body bgcolor="white" onload="init()">


	<header>
		<div class="div_logo"></div>
		<div class="div_bg"></div>
		<nav>
			<div class="div_blur_mask">
				<div class="nav_mask"></div>
				<div class="nav_blur"></div>

			</div>

			<div class="nav_div">
				<div class="nav_left">
					<ul>
						<li><a href="index">🐷&nbsp;&nbsp;&nbsp;主站</a>
						</li>
						<li><a href="lol.jsp">游戏</a></li>
						<li><a href="article.jsp">文章</a></li>
						<li><a href="#">讨论</a></li>
						<li><a href="#">视频</a></li>
					</ul>
				</div>
				<div class="nav_right">
					<ul>
						<%
										try {
											 String usr = request.getSession().getAttribute("logged").toString();
											 String value = "hidden";
											 if (usr.equals("yes")) {
												 int id = (int) request.getSession().getAttribute("usr_id");
												 String avatar = "img/avatar/" + id + "-avatar.png";
												 System.out.println(request.getSession().getAttribute("usr_id").toString());
												 out.write(" <li> <a href='space'> <img class='avatar_img' src='" + avatar + "'> </a> </li> ");
												 out.write("<li> <a href='submit'> <div class='admin_img' style=\"margin-top: -8px\">投稿</div> </a> </li> ");
											 } else {
												 out.write("<li><a href='login.html'>登录</a></li> <li id='nav_divider'>|</li><li><a href='register.html'>注册</a></li>");
											 }
										} catch (Exception ex) {
											ex.printStackTrace();
											out.write("<li><a href='login.html'>登录</a></li> <li id='nav_divider'>|</li><li><a href='register.html'>注册</a></li>");
										}
									   
										%>

					</ul>
				</div>
			</div>
		</nav>
	</header>


	<article id="index_article">

		<div id="father">
			<div id="">
				<img src="img/31.jpg" width="100%" height="700px" id="img31" />
			</div>
			<div id="">
				<table border="0px" width="1300px" align="center" cellpadding="0px" cellspacing="0px">
					<tr>
						<td>
							<table border="0px" width="100%">
								<tr height="50px">
									<td colspan="5">
										&nbsp;&nbsp;
										<font size="5">最新皮肤</font>&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/1.png" width="200px" height="300px"/></a><br />
										<font color="gray">星之守护者 阿狸</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/2.png" width="200px" height="300px"/></a><br />
										<font color="gray">星之守护者 迦娜</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/3.png" width="200px" height="300px"/></a><br />
										<font color="gray">大元素使 拉克丝</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(199)" value="¥199" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/4.png" width="200px" height="300px"/></a><br />
										<font color="gray">摄魂猎人 凯影</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(49)" value="¥49" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/5.png" width="200px" height="300px"/></a><br />
										<font color="gray">未来战士 凯特琳</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(199)" value="¥199" /><br /><br />
									</td>
								</tr>
								<tr>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/6.png" width="200px" height="300px"/></a><br />
										<font color="gray">祸害之光 凯尔萨斯</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/7.png" width="200px" height="300px"/></a><br />
										<font color="gray">审判天使 凯尔</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/8.png" width="200px" height="300px"/></a><br />
										<font color="gray">电玩勇者 伊泽瑞尔</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/9.png" width="200px" height="300px"/></a><br />
										<font color="gray">暮星之羽 霞</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
								</tr>
							</table>

						</td>
					</tr>
					<tr>
						<td>
							<table border="0px" width="100%">
								<tr height="50px">
									<td colspan="5">
										&nbsp;&nbsp;
										<font size="5">最热皮肤</font>&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/10.png" width="200px" height="300px"/></a><br />
										<font color="gray">SKT 艾克</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/11.png" width="200px" height="300px"/></a><br />
										<font color="gray">闭月之颜 貂蝉</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/12.png" width="200px" height="300px"/></a><br />
										<font color="gray">长者之森 乐芙兰</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/13.png" width="200px" height="300px"/></a><br />
										<font color="gray">古琴余韵 娑娜</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/14.png" width="200px" height="300px"/></a><br />
										<font color="gray">大魔王 提莫</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
								</tr>
								<tr>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/15.png" width="200px" height="300px"/></a><br />
										<font color="gray">电玩女神 厄运小姐</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/16.jpg" width="200px" height="300px"/></a><br />
										<font color="gray">黎明使者 锐雯</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/17.jpg" width="200px" height="300px"/></a><br />
										<font color="gray">拳神 李青</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
									<td width="185px" height="250px" align="center">
										<a href="#"><img src="img/18.jpg" width="200px" height="300px"/></a><br />
										<font color="gray">猩红之月 亚索</font>
										</a><br /><br />
										<input type="button" style="width:60px;height:30px;" onclick="show_confirm(99)" value="¥99" /><br /><br />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<p>${'Hello' }</p>

	</article>

</body>
</html>