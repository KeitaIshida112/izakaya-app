<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Menu" %>
<%@ page import="java.util.List" %>
<%
Menu menu = (Menu)request.getAttribute("menu1");
String msg = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="./staffcss/NewMenu.css">
<title>メニュー編集画面</title>
</head>
<body>
<main>
	<form action="NewMenu" method="post">
		<% if(msg != null && msg.length() > 0){ %>
			<p class="msg"><%= msg %></p>
		<% } %>
		<h1>メニュー更新</h1>
		<table class="table1" border="1">
			<tr>
				<td>商品ID</td>
				<td><input class="text" type="text" name="id" max="4"></td>
			</tr>
			<tr>
				<td>商品名</td>
				<td><input class="text" type="text" name="name"></td>
			</tr>
			<tr>
				<td>商品分類</td>
				<td>
					<select class="text" name="category" required> 
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
			<tr>
				<td>値段</td>
				<td><input class="text" type="number" name="price" min="0">円</td>
			</tr>
			<tr>
				<td>在庫</td>
				<td><input class="text" type="number" name="stock" min="0">個</td>
			</tr>
		</table>
		<p class="p"><input class="submit" type="submit" value="登録" name="submit"></p>
		<p class="p2"><a href="MenuEdit">商品検索画面へ</a></p>
	</form>
</main>
</body>
</html>