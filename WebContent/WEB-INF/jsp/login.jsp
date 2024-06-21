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
			<img src="/A4/img/logo.png" width="250" height="300" alt="A review">
		</div>
		<div class="login">
			<div class="input-group">
			<form method="post" action="/A4/MyReviewServlet">
				<input type="email" id="email" name="email" placeholder="メールアドレス">
				<div class="input-group">
					<input type="password" id="password" name="password" placeholder="パスワード">
				</div>
				<div class="register-link">
					<input type="submit" class="login-button" value="ログイン">
					<button class="sinki-button" onclick="location.href='/A4/NewUserServlet'">新規登録</button>
					<div class="register-link2">
						<br> <a href="#">パスワードを忘れた場合</a>
					</div>
				</div>
			</form>
			</div>
		</div>
	</div>
	<p>${message}</p>
</body>
</html>