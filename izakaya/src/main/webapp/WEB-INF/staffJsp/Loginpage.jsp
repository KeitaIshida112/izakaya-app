<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login page</title>
</head>

<body>
	<div>
		<form action="StaffTop" method="post">
			<h2>ログイン画面</h2>
			<table class="table">
				<tr>
					<td>ID</td>
					<td>：<input type="text" name="id" placeholder="IDを入力"></td>
				</tr>
				<tr>
					<td>PASSWORD</td>
					<td>：<input type="password" name="password" placeholder="パスワードを入力"></td>
				</tr>
			</table>
			<p><input type="submit" name="login" value="login"></p>
		</form>
	</div>
</body>

</html>