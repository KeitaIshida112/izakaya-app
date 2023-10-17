<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Menu,model.Guest"%>
<%@ page import="java.util.Map,java.util.HashMap"%>
<%
Menu menu = (Menu) request.getAttribute("menu");
Guest guest = (Guest) session.getAttribute("guest");
//Map<String,Map<String,Integer>> cartList = (Map<String,Map<String,Integer>>)application.getAttribute("cartList");
Map<String, Integer> orderCount = (Map<String,Integer>)session.getAttribute("orderCount");
if(orderCount == null){
	orderCount = new HashMap<String,Integer>();
}
%>
<!DOCTYPE html>
/*個数選択*/
<html>

<head>
	<meta charset="UTF-8">

	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/kokoro.css">
		<link rel="stylesheet" type="text/css" href="./css/SelectCount.css">
	</head>
	<title>個数選択画面</title>
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
			<h1>個数選択</h1>
			<form action="OrderSet" method="post">
				<table class="table1">
					<tr>
						<th>商品名</th>
						<th></th>
						<th>個数</th>
					</tr>
					<% if (orderCount.size() > 0) { %>
							<tr>
								<td class="td"><%= menu.getMenuName() %></td>
								<td>………</td>
								<td><input class="input" type="number" name="count" min="0" value="<%= orderCount.get(menu.getMenuId()) %>"></td>
							</tr>
					<% } %>
				</table>
				<p><input class="input2" type="submit" value="確定"  name=<%= menu.getMenuId() %>></p>
				<p class="p"><input class="input3" type="submit" value="取消" name=<%= menu.getMenuId() %>></p>
			</form>
		</div>
	</main>
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>

</html>