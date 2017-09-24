<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="style.css" type="text/css" rel="stylesheet">
    <link href="article.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/my.js"></script>

<title>文章</title>
</head>
<body style="background-color: #fafbfc;">

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
    
    <div class="container">
        <div class="article_main">
            <div class="article_card" id="card_1">
                <div class="article_avatar">
                    <img src="avatar.png">
                </div>
                <div class="card_main">
                    <a href="text.html" style="text-decoration: none">
                        <div class="card_img">
                            <img src="img/article1.jpg">
                            <div class="article_img_mask"></div>
                        </div>
                        <div class="article_header">一周 App 派评：森林谱曲 SoundForest，Android 刷推新选择 Fenix 2，短链转换工具 Short Menu 等</div>
                    </a>
                    <div class="article_brief">本周我们从 iOS、Android 和 Product Hunt 中选出了 9 款值得关注的应用，来看看有没有你感兴趣的。</div>
                </div>
            </div>
            <script type="text/javascript">
                getArticle(articles);
            </script>
        </div>
    </div>

</body>
</html>