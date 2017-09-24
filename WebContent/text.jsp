<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
    <%String url = request.getAttribute("article_url").toString(); 
      int work_id = (int) request.getAttribute("article_id"); 
      int usr_id = (int) request.getAttribute("usr_id"); %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" type="text/css" rel="stylesheet">
<link href="article.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/my.js"></script>
<title>Insert title here</title>
</head>
<body>
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
                        <li><a href="article.html">文章</a></li>
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
    
    <div class="article_contain">
    	<jsp:include page="<%=url %>" flush="true"/>
    	
    	
    	
    	<div class="new_comment">
            <input type="hidden" value="<%= usr_id + "" %>" name="user_id">
            <input type="hidden" value="<%= work_id + "" %>" name="work_id">
            <% if(usr_id != -1) { %>
            <textarea name="comment" id="my_comment" cols="80" rows="10"></textarea>
            <button class="btn-primary" id="submit-comment" onclick="do_comment()">提交评论</button>
        	<%} %>
        </div>
        
    	<jsp:include page="comment.html" flush="true"/>
    	
    </div>


</body>
</html>