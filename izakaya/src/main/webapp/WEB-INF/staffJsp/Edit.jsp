<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Menu" %>
<%@ page import="java.util.List" %>
<%
List<Menu> menuList1 = (List<Menu>)session.getAttribute("menuList1");
//List<Menu> menuList1 = (List<Menu>)request.getAttribute("menuList1");
String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./staffcss/Edit.css">
<title>メニュー管理画面</title>
</head>
<body>
<main>
	<% if(msg != null && msg.length() > 0){ %>
		<p class="msg"><%= msg %></p>
	<% } %>
	<h1>商品検索</h1>
	<form action="MenuEdit" method="post">
	<table border="1" class="table1">
		<tr>
			<td>商品名</td>
			<td><input class="text" type="text" name="name"></td>
			<td>商品分類</td>
			<td>
				<select class="select" name="category">
					<option value="" ></option>
					<option value="おつまみ">おつまみ</option>
					<option value="一品料理">一品料理</option>
					<option value="ご飯類">ご飯類</option>
					<option value="スイーツ">スイーツ</option>
					<option value="ドリンク">ドリンク</option>
					<option value="サービス">サービス</option>
				</select>
			</td>
		</tr>
	</table>
	<input class="submit" type="submit" value="検索" name="submit">
	<table border="1" class="table1">
		<tr>
			<th>選択</th>
			<th>商品ID</th>
			<th>商品名</th>
			<th>商品分類</th>
			<th>値段</th>
			<th>在庫</th>
		</tr>
		<% if(menuList1 != null){ %>
			<% for(Menu menu : menuList1){ %>
				<tr>
					<td><input class="input" type="radio" name="id" value="<%= menu.getMenuId() %>"></td>
					<td><%= menu.getMenuId() %></td>
					<td><%= menu.getMenuName() %></td>
					<td><%= menu.getMenuCategory() %></td>
					<td><%= menu.getPrice() %></td>
					<td><%= menu.getStock() %></td>
				</tr>
			<% } %>
		<% } %>
	</table>
	<p class="p">
		<input class="submit" type="submit" value="更新" name="submit">
		<input class="submit" type="submit" value="削除" name="submit">
		<input class="submit" type="submit" value="新規登録" name="submit">
	</p>
	<!-- <input type="button" value="新規登録" onclick="location.href="http://localhost:8080/izakaya/NewMenu"> -->
	<p class="p2"><a href="">メイン画面へ</a></p>
	</form>
</main>
</body>
</html>