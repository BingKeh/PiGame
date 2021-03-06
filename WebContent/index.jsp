<%@ page language="java" pageEncoding="UTF-8"%>  
<%@ page contentType="text/html;charset=utf-8"%>  
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="style.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/my.js"></script>
    <title>PiGame</title>
</head>

<body class="index_body">
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
        <div class="game_top">
            <a href="#">
                <div class="game_img">
                    <img src="bg1.jpg" id="img_big">
                    <div class="img_mask img_balck_mask"></div>
                    <div class="img_mask">
                        <div class="big_logo"></div>
                        <span class="article_date"><img src="date.png">2017-09-04</span>
                        <div class="big_info">
                            <p>老电脑也能流畅运行的游戏</p>
                        </div>
                    </div>
                </div>
            </a>

            <div class="game_right">
                <table class="right_table" id="table">

                    <tr>
                        <td>
                            <a href="#1">
                            <img src="bg2.jpg">
                            <div class="small_img">
                                <div class="small_info">是谁破环了游戏，是内购？</div>
                            </div>
                        </a>
                        </td>
                        <td>
                            <a href="#2">
                                <img src="bg3.jpg">
                                <div class="small_img">
                                    <div class="small_info">三大平台的服务，谁会胜出</div>
                                </div>
                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <a href="#3">
                                <img src="bg4.jpg" class="img_bottom">
                                <div class="small_img img_bottom">
                                    <div class="small_info">为什么吃鸡如此流行</div>
                                </div>
                            </a>
                        </td>
                        <td>
                            <a href="#4">
                                <img src="bg5.jpg" class="img_bottom">
                                <div class="small_img img_bottom">
                                    <div class="small_info">好恐怖的游戏啊，不敢玩了</div>
                                </div>
                            </a>
                        </td>
                    </tr>
                </table>

                <script type="text/javascript">
                    window.onresize = function () {
                        var table = document.getElementById('test');
                        console.log("The ratio is " + table.offsetHeight / table.offsetWidth);
                    }
                </script>

            </div>
            <div class="clear"></div> 
        </div>

        <div class="article_body">
            <div class="body_mask">

                    <div class="game_chart">
                            <div class="chart_header" style="border-bottom: 1px solid rgba(0, 0, 0, 0.2);"><span>每周销量排行</span></div>
                            <div class="game_chart_1">
                                <img src="img/chart1.jpg">
                                <div class="chart_info">
                                    <span class="span_no">No.1</span>
                                    <p>Splatoon2</p>
                                    <span>1,104,549</span>
                                </div>
                                <div class="clear"></div>
                            </div>
            
                            <div class="game_chart_1">
                                <img src="img/chart2.jpg">
                                <div class="chart_info">
                                    <span class="span_no">No.2</span>
                                    <p>Crash Bandicoot N. Sane Trilogy</p>
                                    <span>170,779</span>
                                </div>
                                <div class="clear"></div>
                            </div>
            
                            <div class="game_chart_1">
                                <img src="img/chart3.jpg">
                                <div class="chart_info">
                                    <span class="span_no">No.3</span>
                                    <p>Final Fantasy XII: The Zodiac Age</p>
                                    <span>80,985</span>
                                </div>
                                <div class="clear"></div>
                            </div>
            
                            <div class="game_chart_2">
                                <span class="small_no">4</span>
                                <span>Mario Kart 8 Deluxe</span>
                                <span class="small_sold">76,284</span>
                            </div>
            
                            <div class="game_chart_2">
                                <span class="small_no">5</span>
                                <span>Layton's Mystery Journey: Katrielle and the 
                                    Millionaire's Conspiracy</span>
                                <span class="small_sold">70,778</span>
                            </div>
            
                            <div class="game_chart_2">
                                <span class="small_no">6</span>
                                <span>The Legend of Zelda: Breath of the Wild</span>
                                <span class="small_sold">67,617</span>
                            </div>
            
                            <div class="game_chart_2">
                                <span class="small_no">7</span>
                                <span>Pokemon Sun/Moon</span>
                                <span class="small_sold">55,707</span>
                            </div>
            
                            <div class="game_chart_2">
                                <span class="small_no">8</span>
                                <span>Arms</span>
                                <span class="small_sold">52,276</span>
                            </div>
            
            
                            <div class="game_chart_2">
                                <span class="small_no">9</span>
                                <span>Grand Theft Auto V</span>
                                <span class="small_sold">51,997</span>
                            </div>
            
                            <div class="game_chart_2" id="chart_10">
                                    <span class="small_no">10</span>
                                    <span>Overwatch</span>
                                    <span class="small_sold">32,959</span>
                                </div>
                        </div>

            </div>
            <div class="game_banner">
                <div class="banner_left">游戏推荐</div>
                <div class="banner_right">销量排行</div>
            </div>
            <div class="post_father">
                <div class="game_post">
                    <ul class="post_img_ul">
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post1.jpg" class="img_game">
                                </a>
                                <span class="post_name">耻辱2</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
    
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post2.jpg" class="img_game">
                                </a>
                                <span class="post_name">口袋铁拳DX</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post3.jpg" class="img_game">
                                </a>
                                <span class="post_name">FIFA18</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post4.jpg" class="img_game">
                                </a>
                                <span class="post_name">NBA 2K18</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post5.jpg" class="img_game">
                                </a>
                                <span class="post_name">命运2</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post6.jpg" class="img_game">
                                </a>
                                <span class="post_name">火焰纹章</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post7.jpg" class="img_game">
                                </a>
                                <span class="post_name">英雄传说</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                        <li>
                            <div class="post_img">
                                <a href="post1">
                                    <img src="img/post8.jpg" class="img_game">
                                </a>
                                <span class="post_name">新弹丸论破</span>
                                <div class="post_more">
                                    <ul>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>资讯</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>俱乐部</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span>攻略</span>
                                        </li>
                                        <li>
                                            <div class="blue_dot"></div>
                                            <span> 评测</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </li>
                    </ul>
                    <div class="clear"></div>
                </div>
                <div class="clear"></div>
            </div> 
            
            
            
            
            
        </div>
       
    </article>

</body>

</html>