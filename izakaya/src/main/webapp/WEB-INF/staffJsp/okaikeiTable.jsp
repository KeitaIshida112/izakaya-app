<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.Map,java.util.HashMap,java.util.List,java.util.ArrayList" %>
    <%@ page import="model.Guest,model.Menu" %>
    <% Guest guest = (Guest)session.getAttribute("guest"); %>
   
<% 
//Map<String, List<Menu>> =(Map<String, List<Menu>>)application.getAttribute("doneOrder");
List<Menu> allOrder = (List<Menu>)application.getAttribute("allOrder");
String table =  (String)session.getAttribute("tableNumber");
List<Menu> orderMenuList = new ArrayList<>();
for(Menu menu : allOrder){
	if(menu.getTableNo().equals(table)){
		orderMenuList.add(menu);
	}
}
%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>お会計</title>
	
</head>

<body>
	
	<main>
		<h3 class="h"><%=table %>卓お会計</h3>
		<table class="table1" align="center">
			<tr>
				<th>商品名</th>
				<th></th>
				<th width="10%">個数</th>
				<th width="30%">値段</th>
			</tr>
			<%int sum = 0;
			if(allOrder != null){
				for(Menu menu : allOrder){%>
			<tr>
				<td align="left">
				<%= menu.getMenuName()%></td>
				
				
				<td>……</td>
				
				<td align="center">
				<%= menu.getCount() %></td>
				
				<td align="right">
				<%= menu.getPrice()* menu.getCount() %></td>
				<%sum += menu.getPrice()* menu.getCount();  %>
				
			</tr>
				<%} %>
			<%} %>
		</table>

		<p class="h">合計、<%= sum %> </p>
		<form action="TableManage" method="post">
		<input type="submit" value="会計処理" />
	</form>
	</main>
	<footer>
		<p>&copy; 2023 居酒屋</p>
	</footer>
</body>

</html>