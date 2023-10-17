<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.Map,java.util.HashMap,java.util.List,java.util.ArrayList" %>
    <%@ page import="model.Guest,model.Menu" %>
<% Guest guest = (Guest)session.getAttribute("guest"); %>
<% 
//Map<String, List<Menu>> doneOrder=(Map<String, List<Menu>>)application.getAttribute("doneOrder");
List<Menu> allOrder = (List<Menu>)application.getAttribute("allOrder");
Map<String, Integer> orderCount = (Map<String,Integer>)session.getAttribute("orderCount");
if(orderCount == null){
	orderCount = new HashMap<String,Integer>();
}
String table = guest.getTable();
List<Menu> orderMenuList = new ArrayList<>();
for(Menu menu : allOrder){
	if(menu.getTableNo().equals(table)){
		orderMenuList.add(menu);
	}
}
int totalPrice = 0;
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>お会計</title>
	<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/kokoro.css">
	<link rel="stylesheet" type="text/css" href="./css/okaikei.css">
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
			<h1>お会計</h1>
			<table class="table1" align="center">
				<tr>
					<th>商品名</th>
					<th></th>
					<th width="10%">個数</th>
					<th width="20%">値段</th>
				</tr>
				<%int sum = 0;
				if(orderMenuList != null){
					for(Menu menu :orderMenuList){%>
				<tr>
					<td align="left">
					<%= menu.getMenuName()%></td>
					
					
					<td>……</td>
					
					<td align="center">
					<%= menu.getCount() %></td>
					
					<td align="right">
					<%= menu.getPrice()* menu.getCount() %> 円</td>
					<%sum += menu.getPrice()* menu.getCount();  %>
					
				</tr>
					<%} %>
				<%} %>
			</table>
			<p><b>合計、<%= sum %>  円</b></p>
		</div>
	</main>
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>

</html>