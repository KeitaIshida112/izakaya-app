<%@page import="model.Menu"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<Menu> staffCheck = (List<Menu>)request.getAttribute("staffCheck");

%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文情報表示画面</title>
</head>
<body>
<h1>全卓-注文情報</h1>

<table border="1">
<tr>
<th>テーブル番号</th>
<th>商品ID</th>
<th>商品名</th>
<th>注文個数</th>
<th>商品カテゴリー</th>
<th>価格</th>
<th>注文時間</th>
</tr>

<% for(int i = 0; i < staffCheck.size(); i++) { %>
<tr>
<td><%= staffCheck.get(i).getTableNo() %></td>
<td><%= staffCheck.get(i).getMenuId() %></td>
<td><%= staffCheck.get(i).getMenuName() %></td>
<td><%= staffCheck.get(i).getCount() %></td>
<td><%= staffCheck.get(i).getMenuCategory() %></td>
<td><%= staffCheck.get(i).getPrice() %></td>
<td><%= staffCheck.get(i).getOrderTime() %></td>	
</tr>
<% }%>

</table>

<a href="">TOPへ戻る</a>
</body>
</html>