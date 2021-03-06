<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="Info.*" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>搜索...</title>
<style type="text/css">
	#menu{
		    position: fixed;
		    left: 155px;
		    top: 60px;
		    width: 140px;
		    height: 600px;
		    background: white;
		    border-radius: 5px;
		    z-index: 1000;
	}
	#content{
	position: relative;
    left: 310px;
    top: 60px;
    width: 830px;
    	background-color: #ff000000;
	}
	#question{
		border-radius: 5px;
		background-color: #ffffff
	}
	#tb1{
		    position: relative;
    		left: 10px;
    		top: 10px;
	}
	#title{
		text-align:center;
		color:#C4723C;
	}
	#menu_bution{
		text-align:center;
		font-size: 18px;
		border-radius: 5px;
		margin: 0px 0 10px;
	}
	#content2{
		    padding: 30px;
	}
	p.p2:hover{
		background:#66ff66;
	}
	
	p.active{
		background: #bff8a7;
	}        
    </style>
</head>
<body>
	<%@ include file="base.jsp" %>
	<div id="menu">
		<p id="menu_bution" class="p2"><a href="index1.jsp" class="three">问题大厅</a></p>
		<p id="menu_bution" class="p2"><a href="myanswer.jsp" class="three">我的回答</a></p>
		<p id="menu_bution" class="p2"><a href="offerquestion.jsp" class="three">提问问题</a></p>
		<p id="menu_bution" class="p2"><a href="mysave.jsp" class="three">我的收藏</a></p>
		<p id="menu_bution" class="p2"><a href="aboutus.jsp" class="three">关于我们</a></p>
			
	</div>
	<div id="content">
	<%
		if(user==null){
			out.print("<script>alert('你还未等录，请登陆后回答!');window.location.href='login.jsp'</script>");
		}
		String question_title = request.getParameter("searchquestion");
		question_title=new String(question_title.getBytes("iso-8859-1"),"utf-8");
		if(question_title.length()>15){
			out.print("<script>alert('您输入数字较多，请输入少于15字以内！');</script>");
		}
		QuestionDao qdao = new QuestionDao();
		List<QuestionInfo> questions = qdao.search(question_title);
		if(questions==null){
			%>
		<h3 style="background:white;text-algin:center;">没有找到相关内容</h3>
		<%	
		}else{
			UserDao udao=new UserDao();
			for(QuestionInfo question:questions){
				String question_user_id=question.getUser_id();
				int id=question.getQuestion_id();
				UserInfo question_user=udao.SerachByUser_id(question_user_id);
				String question_user_img_src="img/"+question_user.getUser_img_url();
		%>
		<div id="question">
			<table id="tb1">
					<tr>
					  <th rowspan="2"><a href = "user.jsp"><img  src=<%=question_user_img_src %> width =50 height=50 class="img-circle"/></a></th>
					  <td><b><%= question_user.getUser_name() %></b></td>
					</tr>
					<tr>
					  <td>此人很懒，只会提问问题</td>
					</tr>
				</table>
			<h4 id="title"><b><%= question.getQuestion_title()  %></b></h4>
			
			<p id="content2"><%= question.getPart_content() %><p>
			<p style="text-align:right;">
					
				 	回答数：<%=question.getQuestion_answers() %>
				 	<a class="btn" href="questions.jsp?id=<%=id %>">查看详情</a>
				 
			</p>
		</div>
		<% }
		}
		%>
	</div>
</body>
</html>