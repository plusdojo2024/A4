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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>


		<!-- タブヘッダー（ここから） -->
<header>
	<h1 class ="headline">
    <a href="MyReviewServlet"><img src="/A4/img/logo.png" width="100" height="100" alt="A review"></a>
	</h1>
	<ul class ="tab">
		<li class="selected"><a href="/A4/MyReviewServlet">マイレビュー</a></li>
		<li><a href="/A4/SearchServlet">全体検索</a></li>
		<li><a href="/A4/ListServlet">リスト</a></li>
	</ul>

	  <figure class="icon-circle">
	  <a href="/A4/LogoutServlet"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></a></figure>

</header>

	<h2 class="my">
	<a>マイレビュー</a>
	</h2>

	<div id="drop-down-menu">
		<div class="dropdown">
      		<input type="checkbox" id="submenu-toggle-2">
      		<label for="submenu-toggle-2">ドロップダウンメニュー</label>
      		<ul class="submenu">
        		<li><a href="#">Submenu 2-1</a></li>
        		<li><a href="#">Submenu 2-2</a></li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
		<div class="dropdown">
      		<input type="checkbox" id="submenu-toggle-2">
      		<label for="submenu-toggle-2">ドロップダウンメニュー</label>
      		<ul class="submenu">
        		<li><a href="#">Submenu 2-1</a></li>
        		<li><a href="#">Submenu 2-2</a></li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
		<div class="dropdown">
      		<input type="checkbox" id="submenu-toggle-2">
      		<label for="submenu-toggle-2">ドロップダウンメニュー</label>
      		<ul class="submenu">
        		<li><a href="#">Submenu 2-1</a></li>
        		<li><a href="#">Submenu 2-2</a></li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
	</div>
  <div class="container">
  	<div class="scroll">
  	<div class="flex-container">

    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素1</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    		<!-- モーダルウインドウのエリア -->
			<div id="modalArea" class="modalNoDisp">
				<div class="modalWindow">
					<!-- ここがウインドウとなります -->
					<p>モーダルウインドウ</p>
					<p>xxx入力<br><input type="text" value="" style="width: 200px">
					</p>
					<p><input type="button" value="閉じる" onclick="modalClose()"></p>
				</div>
			</div>
		</div>
    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素2</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    	</div>
    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素3</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    	</div>
    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素4</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    	</div>
    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素5</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    	</div>
    	<div class="flex-item">
    		<div class= "item_img"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></div>
    		<div class="grid_item2">
    			<h1>子要素6</h1>
    			<p class= "item_text">テキスト</p>
    			<p class= "item_text">テキスト</p>
    		</div>
    	</div>
  	</div>
	</div>
	<div class= "side">
  		<div class="sort">
  			<img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン">
  		</div>
  		<ul class="sort_item">
			<li>五十音順</li>
			<li>価格</li>
			<li>更新日</li>
			<li>評価</li>
		</ul>
  		<div class="post-btn">
  			<a href="/">
    			<img src="/A4/img/removebg-preview.png">
    		</a>
  		</div>
	</div>
  </div>
</body>
</html>