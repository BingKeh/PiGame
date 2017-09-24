<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/qrcode.js"></script>
    <script type="text/javascript" src="js/my.js"></script>
<title>我的主页</title>
</head>
<body class="space_body">

    <header>
        <nav>
            <div class="div_blur_mask">
                <div class="nav_mask"></div>
                <div class="nav_blur" id="login_nav"></div>
            </div>

            <div class="div_father">

                <div class="nav_div space_nav">
                    <div class="nav_left">
                        <ul>
                            <li><a href="index">🐷&nbsp;&nbsp;&nbsp;主站</a></a>
                            </li>
                            <li><a href="#">游戏</a></li>
                            <li><a href="#">文章</a></li>
                            <li><a href="#">讨论</a></li>
                            <li><a href="#">视频</a></li>
                        </ul>
                    </div>
                    <div class="nav_right" id="myspace_nav">
                        <ul>
                        <!--  
                            <li><a href="login.html">登录</a></li>
                            <li id="nav_divider">|</li>
                            <li><a href="register.html">注册</a></li>  -->
                            <li><a href="do_login?action=logout">退出登录</a>
                            <li>
                                <a href="submit">
                                    <div class="admin_img">投稿</div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="space_bg">
                    <img src="img/space_bg.png">
                    <div class="space_banner">
                        <ul>
                            <li>
                                <a href="#!/index">
                                    <div class="space_banner_item">
                                        <span class="space_icon" id="space_icon_1"></span>
                                        <span>主页</span>
                                    </div>
                                </a>

                            </li>
                            <li>
                                <a href="#!/work">
                                    <div class="space_banner_item">
                                        <span class="space_icon" id="space_icon_2"></span>
                                        <span>稿件</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#!/follow">
                                    <div class="space_banner_item">
                                        <span class="space_icon" id="space_icon_3"></span>
                                        <span> 关注</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#!/club">
                                    <div class="space_banner_item">
                                        <span class="space_icon" id="space_icon_4"></span>
                                        <span>俱乐部</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#!/setting">
                                    <div class="space_banner_item">
                                        <span class="space_icon" id="space_icon_5"></span>
                                        <span>设置</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <font style="visibility: hidden">hidden</font>
                            </li>
                        </ul>
                        <div class="space_nav_cours"></div>
                    </div>
                </div>

            </div>


        </nav>


    </header>
</html>