<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Menu" %>
<%@ page import="java.util.List" %>
<%
Menu menu = (Menu)request.getAttribute("menu1");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./staffcss/UpdateMenu.css">
<title>メニュー編集画面</title>
</head>
<body>
<main>
	<form action="NewMenu" method="post">
		<h1>メニュー更新</h1>
		<table class="table1" border="1">
			<tr>
				<td>商品ID</td>
				<td><input class="text" type="text" name="id" value="<%= menu.getMenuId() %>" readonly></td>
			</tr>
			<tr>
				<td>商品名</td>
				<td><input class="text" type="text" name="name" value="<%= menu.getMenuName() %>"></td>
			</tr>
			<tr>
				<td>商品分類</td>
				<td>
					<select class="text" name="category">
						<% if(menu.getMenuCategory().equals("おつまみ")){ %>
							<option value="おつまみ" selected>おつまみ</option>
						<% } else { %>
							<option value="おつまみ">おつまみ</option>
						<% } %>
						<% if(menu.getMenuCategory().equals("一品料理")){ %>
							<option value="一品料理" selected>一品料理</option>
						<% } else { %>
							<option value="一品料理">一品料理</option>
						<% } %>
						<% if(menu.getMenuCategory().equals("ご飯類")){ %>
							<option value="ご飯類" selected>ご飯類</option>
						<% } else { %>
							<option value="ご飯類">ご飯類</option>
						<% } %>	
						<% if(menu.getMenuCategory().equals("スイーツ")){ %>
							<option value="スイーツ" selected>スイーツ</option>
						<% } else { %>
							<option value="スイーツ">スイーツ</option>
						<% } %>	
						<% if(menu.getMenuCategory().equals("ドリンク")){ %>
							<option value="ドリンク" selected>ドリンク</option>
						<% } else { %>
							<option value="ドリンク">ドリンク</option>
						<% } %>
						<% if(menu.getMenuCategory().equals("サービス")){ %>
							<option value="サービス" selected>サービス</option>
						<% } else { %>
							<option value="サービス">サービス</option>
						<% } %>
					</select>
				</td>
			</tr>
			<tr>
				<td>値段</td>
				<td><input class="text" type="number" name="price" value="<%= menu.getPrice() %>">円</td>
			</tr>
			<tr>
				<td>在庫</td>
				<td><input class="text" type="number" name="stock" value="<%= menu.getStock() %>">個</td>
			</tr>
		</table>
		<p class="p"><input class="submit" type="submit" value="更新" name="submit"></p>
		<p class="p2"><a href="MenuEdit">商品検索画面へ</a></p>
	</form>
</main>
</body>
</html>