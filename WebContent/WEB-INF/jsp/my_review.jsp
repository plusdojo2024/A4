<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // Servletのデータ受け取り
  request.setCharacterEncoding("UTF8");
  String strMessage = (String) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/Areview.css">
<title>マイレビューぺージ</title>
</head>
<body>
		<!-- タブヘッダー（ここから） -->
<header>
	  <h1 class ="headline">
      <a href="MyReviewServlet"><img src="/A4/img/logo.png" width="100" height="100" alt="A review"></a>
	  </h1>
		<ul class ="tabu-list">
			<li class="tabu-list-item"><a href="/A4/MyReviewServlet">マイレビュー</a></li>
			<li class="tabu-list-item"><a href="/A4/SearchServlet">全体検索</a></li>
			<li class="tabu-list-item"><a href="/A4/ListServlet">リスト</a></li>
		</ul>

	  <figure class="icon-circle">
	  <a href="/A4/LogoutServlet"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></a></figure>


</header>
	<h2 class="my">
	<a>マイレビュー</a>
	</h2>
	<ul class="main-menu">
	<li><a href="#">すべて</a>
	<ul class="sub-menu">
	<li><a href="#">い</a></li>
	<li><a href="#">う</a></li>
	<li><a href="#">え</a></li>
	<li><a href="#">お</a></li>
	</ul>
	</li>
	<li><a href="#">フォロー</a>
	<ul class="sub-menu">
	<li><a href="#">き</a></li>
	<li><a href="#">く</a></li>
	<li><a href="#">け</a></li>
	<li><a href="#">こ</a></li>
	</ul>
	</li>
	<li><a href="#">レビュー内検索</a>
	<ul class="sub-menu">
	<li ><a href="#">し</a></li>
	<li ><a href="#">す</a></li>
	<li ><a href="#">せ</a></li>
	<li ><a href="#">そ</a></li>
	</ul>
	</li>
	</ul>


</body>
</html>