<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/newuser.css">
<title>新規登録</title>
</head>
<body>
<div class="loginb">
	<div class ="logo">
      <img src="/A4/img/logo.png" width="250" height="300" alt="A review">
	</div>
	<form method="post" action="/A4/NewUserServlet">
    <div class="login">
        <div class="input-group">
	        <input type="email" id="email" name="email" placeholder="メールアドレス">
        </div>
        <div class="input-group">
            <input type="password" id="password" name="password" placeholder="パスワード">
        </div>
        <div class="input-group">
            <input type="text" id="username" name="username" placeholder="ユーザーネーム">
        </div>
        <div class="register-link">
        <input type="submit" class="login-button" value="登録">
	    <button class="sinki-button" onclick="location.href='/A4/LoginServlet'">戻る</button>
        </div>
    </div>
    </form>
</div>
<p>${message}</p>
</body>
</html>