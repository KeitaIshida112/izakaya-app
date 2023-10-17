<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List,model.Menu,model.Guest"%>
<%
List<Menu> orderList = (List<Menu>) session.getAttribute("Listshow");
Guest guest = (Guest) session.getAttribute("guest");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/earlyaccess/kokoro.css">
<link rel="stylesheet" type="text/css" href="./css/orderCart.css">
<title>注文リスト画面</title>
</head>
<body id="sample">
	<header>
		<div class="div1">
			<h2>ORDER SYSTEM</h2>
			<h1 class="h1">
				卓No.<%=guest.getTable()%></h1>
		</div>
		<div class="div2">
			<div>
				<a href="/izakaya/MainMenu" style="color: white">TOP</a>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="" style="color: white">カート</a>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="" style="color: white">注文履歴</a>
			</div>
			<div class="div3">|</div>
			<div>
				<a href="OkaikeiMenu" style="color: white">お会計</a>
			</div>
		</div>
	</header>
	
	<main>
		<h3>注文リスト</h3>
	<form action="MainMenu" method="get">
		<table align="center">
			<tr>
				<th>商品名</th>
				<th></th>
				<th width="70">個数</th>
				<th>値段</th>
			</tr>
			<%
			if (orderList != null && orderList.size() > 0) {
				for (Menu menu : orderList) {
			%>
			<tr>
				<td align="left"><a
					href="CheckMenu?menuId=<%=menu.getMenuId()%>"><%=menu.getMenuName()%></a></td>
				<td>……</td>
				<td><%=menu.getCount()%>個</td>
				<td align="right"><%=menu.getPrice() * menu.getCount()%>円</td>
			</tr>
			<%
			}
			}
			%>
		</table>
		<p>
			<input type="submit" value="注文">
		</p>
	</form>
	</main>
	
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>
</html>