 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户主页</title>
<style> *{
    box-sizing: border-box;
}

.body{
    margin-top: 5%;
    margin-right:20%;
    margin-left:20%;
}
#all{
	 margin-top: 5%;
    margin-right:20%;
    margin-left:20%;
}
/* 头部样式 */
.header {
    background-color: #f1f1f1;
    padding: 20px;
}
.col {
    float: left;
    padding: 10px;
}
.col.side {
    width: 25%;
}
.col.middle {
    width: 50%;
}
.r:after {
    content: "";
    display: table;
    clear: both;
}
/* 导航条 */
.topnav {
    overflow: hidden;
    background-color: #333;
}

/* 导航链接 */
.topnav a {
    float: left;
    display: block;
    color: #f2f2f2;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

/* 链接 - 修改颜色 */
.topnav a:hover {
    background-color: #ddd;
    color: black;
}

/* 创建三个相等的列 */
.column {
    text-indent:50px;
    float: left;
    width: 50%;
    background-color: #ffff0f;
}
 
/* 列后清除浮动 */
.row:after {
    content: "";
    display: table;
    clear: both;
}
#rcorners1{
    border-radius: 50px;
    background: #ddd;
    padding: 20px; 
	position: relative;
    left: 330px;
    height: 100%; 
} 
#rcorners2 {
    border-radius: 50px;
    background: #ddd;
    padding: 20px; 
	position: relative;
    left: 440px;
    top: -65px;
    height: 100%; 
}
/* 响应式布局 - 小于 600 px 时改为上下布局 */
@media screen and (max-width: 600px) {
    .column {
        width: 100%;
    }
}

/* 底部样式 */
.footer {
  background-color: #f1f1f1;
  padding: 10px;
}
</style>
</head>
	<%@ include file="base.jsp" %>
	
<body>
<% 
	String user_img_src="img/"+user.getUser_img_url();
%>
<div id="all">
<div class="header">
    <div class="r">    
	        <div class="col">
	            <img src=<%=user_img_src %> width="100px" height="100px">
	        </div>
	        <div class="col">
	            <h2><%=user.getUser_name() %></h2>
	            <p><%=user.getUser_describe() %></p>
	        </div>
	    </div>
	</div>

	<div class="topnav">
	    <a href="index1.jsp">首页</a>
	    <a href="mysave.jsp">收藏</a>
	    <a href="myanswer.jsp">回答</a>
	</div>
	
	<div class="row">
	    <div class="column" >
	        <h1>资料</h1><br>
	        <h2>用户名:</h2> <p><%=user.getUser_name() %></p><br>
	        <h2>账号:</h2> <p><%=user.getUser_id() %></p><br>
	        <h2>性别:</h2> <p><%=user.getUser_sex() %></p><br>
	    </div>
	    <div class="column" >
	        <h1>成就</h1><br>
	        <h2>回答数:</h2> <p><%=user.getUser_answer_num() %></p><br>
	        <h2>获赞数:</h2> <p><%=user.getUser_score() %></p><br>
	        <h2>提问数:</h2> <p><%=user.getUse_question_num() %></p><br>
	    </div>
	</div>
	<div class="footer">
	
	<form action="index1.jsp"><span>
	    <button id="rcorners1" >修改资料</button></span>
	</form>
	
	
	<form action="index1.jsp">
	<span>
	    <button id="rcorners2">修改密码</button></span>
	</form>
	
	</div>
</div>
</body>
</html>