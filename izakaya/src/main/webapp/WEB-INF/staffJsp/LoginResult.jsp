<%@page import="model.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Login loginUser = (Login)request.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Result page</title>
</head>
<body>
<h1>ログイン結果画面</h1>
<% if(loginUser == null) { %>
	<p><h3>ログインに成功しました</h3></p>
	<p><h4>➡ <a href="StaffTop">TOPページ</a>へ</h4></p>
<% }else { %>
	<p><h3>ログインに失敗しました</h3></p>
	<p><h4>【エラーメッセージ：<%= loginUser.getErrorMsg() %>】</h4></p>
	<p><h4>➡ <a href="StaffPage">ログイン画面</a>へ戻る</h4></p>
<% } %>
</body>
</html>