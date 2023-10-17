<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Guest" %>
<%@ page import="java.util.Map,java.util.HashMap" %>
<% 
Guest guest = (Guest)session.getAttribute("guest");
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
	<!--  <link rel="stylesheet" href="${pageContext.request.contextPath}./css/MenuTop2.css"> -->
	<link rel="stylesheet" type="text/css" href="./css/MenuTop.css">
	<title>TOP</title>
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
		<table class="table1">
			<tr>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=おつまみ" class="menu">
							おつまみ</a></p>
					<img class="img" src="./images/26715630_s.jpg">
				</td>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=一品料理" class="menu">
							一品料理</a></p>
					<img class="img" src="images/4047451_s.jpg">
				</td>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=ご飯類" class="menu">
							ご飯・麺</a></p>
					<img class="img" src="images/3993519_s.jpg">
				</td>
			</tr>
			<tr>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=ドリンク" class="menu">
							ドリンク</a></p>
					<img class="img" src="images/1778236_s.jpg">
				</td>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=スイーツ" class="menu">
							スイーツ</a></p>
					<img class="img" src="images/26561995_s.jpg">
				</td>
				<td>
					<p class="p2"><a href="/izakaya/SelectMenu?name=サービス" class="menu">
							サービス</a></p>
					<img class="img" src="images/help-2478193_1280.jpg">
				</td>
			</tr>
		</table>
	</main>
	
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>

</html>