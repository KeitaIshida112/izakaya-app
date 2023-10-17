<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Menu ,java.util.List ,model.Guest,java.util.HashMap,java.util.Map"%>
<%
List<Menu> menuList = (List<Menu>) request.getAttribute("menuList");
Guest guest = (Guest) session.getAttribute("guest");
//Map<String,Map<String,Integer>> cartList = (Map<String,Map<String,Integer>>)application.getAttribute("cartList");
Map<String, Integer> orderCount = (Map<String,Integer>)session.getAttribute("orderCount");
if(orderCount == null){
	orderCount = new HashMap<String,Integer>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MENU CATEGORY</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/kokoro.css">
<link rel="stylesheet" type="text/css" href="./css/MenuCategory.css">
</head>
<body>
	<header>
		<div class="div1">
			<h2>ORDER SYSTEM</h2>
			<h1 class="h1">卓No.<%= guest.getTable() %></h1>
		</div>
		<div class="div2">
			<div>
				<a href="MainMenu" style="color: white">TOP</a>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="CartMenu" style="color: white">カート</a>
				<% if(orderCount.size() > 0 ){ %>
					 <strong class="b"><%= orderCount.size() %></strong>
				<% } %>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="HistoryMenu" style="color: white">注文履歴</a>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="OkaikeiMenu" style="color: white">お会計</a>
			</div>
		</div>
	</header>

	<main>
		<div class="div4">
			<h1 class="title"><%=menuList.get(0).getMenuCategory() %>  メニュー</h1>
				<table class="table2">
					<%if (menuList.size() > 0) {%>
						<%for (Menu menu : menuList) {%>
							<tr>
								<td><a class="a" href="CheckMenu?menuId=<%= menu.getMenuId() %>"><%=menu.getMenuName()%></a></td>
								<td>………</td>
								<td style="color:#222222"><%=menu.getPrice()%> 円</td>
							</tr>
						<% } %>
					<% } %>
				</table>
			<button class="buttun" type="button" onclick="location.href='/izakaya/MainMenu'">戻る</button>
		</div>
	</main>
	
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>
</html>