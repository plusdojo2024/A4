<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/login.css">
<title>ログイン</title>
</head>
<body>
	<div class="loginb">
		<div class="logo">
			<img src="/A4/img/logo.png" width="200" alt="A reviewのロゴ">
		</div>
		<div class ="error_message">
		<span id="error_message">${message}</span>
		</div>
		<div class="login">
			<form method="post" action="/A4/LoginServlet">
				<div class="input-group">
				<input type="email" id="email" name="email" placeholder="メールアドレス" class="input-in">
				</div>
				<div class="input-group">
					<input type="password" id="password" name="password" placeholder="パスワード" class="input-in">
				</div>
				<div class="register-link">
					<input type="submit" class="login-button" value="ログイン">
				</div>
			</form>
			<button class="sinki-button" onclick="location.href='/A4/NewUserServlet'">新規登録</button>
			</div>
	</div>
</body>
</html>