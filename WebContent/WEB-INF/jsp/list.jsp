<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/list.css">
<title>リスト</title>
</head>
<body>
	<!-- タブヘッダー（ここから） -->
	<header>
		<h1 class="headline">
			<a href="MyReviewServlet"><img src="/A4/img/logo.png" width="100"
				height="100" alt="A review"></a>
		</h1>
		<ul class="tab">
			<li><a href="/A4/MyReviewServlet">マイレビュー</a></li>
			<li><a href="/A4/SearchServlet">全体検索</a></li>
			<li class="selected"><a href="/A4/ListServlet">リスト</a></li>
		</ul>

		<figure class="icon-circle">
			<a href="/A4/LogoutServlet"><img src="/A4/img/myicon.jpg"
				width="100" height="100" alt="マイアイコン"></a>
		</figure>
	</header>

	<h2 class="my">
		<a>リスト一覧</a>
	</h2>
	<div class="container">
		<div class="scroll">
			<div class="flex-container">
				<c:forEach var="li" items="${list}">
					<div class="flex-item">${li.listName} ${li.listCount}</div>
				</c:forEach>
			</div>
		</div>
	<div class="bo">
		<div class="post-btn">
			<a href="/"> <img src="/A4/img/sinkilist.png">
			</a>
		</div>
	</div>
	</div>
</body>
</html>