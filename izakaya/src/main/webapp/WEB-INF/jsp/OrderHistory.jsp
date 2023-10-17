<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page
	import="java.util.Map,java.util.List,model.Menu,java.util.HashMap,java.util.ArrayList,model.Guest"%>
<%
Guest guest = (Guest) session.getAttribute("guest");
String table = guest.getTable();
//Map<String, List<Menu>> doneOrder = (Map<String, List<Menu>>)application.getAttribute("doneOrder");
List<Menu> allOrder = (List<Menu>)application.getAttribute("allOrder");
Map<String, Integer> orderCount = (Map<String,Integer>)session.getAttribute("orderCount");
if(orderCount == null){
	orderCount = new HashMap<String,Integer>();
}
//int table = Integer.parseInt(guest.getTable());
if(allOrder == null){
	allOrder = new ArrayList<Menu>();
}
List<Menu> orderMenuList = new ArrayList<>();
for(Menu menu : allOrder){
	if(menu.getTableNo().equals(table)){
		orderMenuList.add(menu);
	}
}
int totalPrice = 0;

//↓↓↓テスト用変数設定↓↓↓

//↑↑↑テスト用変数設定↑↑↑
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/earlyaccess/kokoro.css">
<link rel="stylesheet" type="text/css" href="./css/orderHistory.css">
<title>注文履歴画面</title>
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
			<h1>注文履歴</h1>
			<table class="table1">
				<tr>
					<th style="width=50%">商品名</th>
					<th></th>
					<th>個数</th>
					<!-- <th>値段</th> -->
				</tr>
				<%
				if (orderMenuList != null) {
					for (Menu menu : orderMenuList) {
				%>
						<tr>
							<td align="left"><a href="CheckMenu?menuId=<%= menu.getMenuId() %>"><%= menu.getMenuName()%></a></td>
							<td>……</td>
							<td><%= menu.getCount()%></td>
							<!--  <td align="right"><%= menu.getPrice() * menu.getCount()%>円</td>
							<%
							totalPrice += menu.getPrice() * menu.getCount();
							%>
							-->
						</tr>
					<%}%>
				<%}%>
			</table>
			<!-- <p>合計金額：<%=totalPrice%>円</p> -->
		</div>
	</main>
	
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>
</html>