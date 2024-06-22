<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>マイレビューぺージ</title>
<link rel="stylesheet" href="/A4/css/Areview.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/*タブ切り替え全体のスタイル*/
.tabs {
	margin-top: 50px;
	padding-bottom: 40px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	/* width: 700px; */
	margin: 0 auto;
}

/*タブのスタイル*/
.tab_item {
	width: calc(100%/ 3);
	height: 50px;
	background-color: #ffc679;
	line-height: 50px;
	font-size: 16px;
	text-align: center;
	color: #565656;
	display: block;
	float: left;
	text-align: center;
	font-weight: bold;
	transition: all 0.2s ease;
}

.tab_item:hover {
	opacity: 0.75;
}

/*ラジオボタンを全て消す*/
input[name="tab_item"] {
	display: none;
}

/*タブ切り替えの中身のスタイル*/
.tab_content {
	display: none;
	padding: 40px 40px 0;
	clear: both;
	overflow: hidden;
}

/*選択されているタブのコンテンツのみを表示*/
#all:checked ~ #all_content, #aaa:checked ~ #aaa_content, #bbb:checked
	~ #bbb_content {
	display: block;
}

/*選択されているタブのスタイルを変える*/
.tabs input:checked+.tab_item {
	background-color: #fff4e0;
	color: black;
}
</style>
</head>
<body>
<header>
	<h1 class ="headline">
   	 <a href="MyReviewServlet"><img src="/A4/img/logo.png" width="100" height="100" alt="A review"></a>
	</h1>
</header>
	<div class="tabs">
		<input id="all" type="radio" name="tab_item" checked>
		<!-- ここからタブ -->
		<label class="tab_item" for="all">マイレビュー</label>
			<input id="aaa"	type="radio" name="tab_item">
		<label class="tab_item"	for="aaa">全体検索</label>
			<input id="bbb" type="radio" name="tab_item">
		<label class="tab_item" for="bbb">リスト</label>

<!-- -----------------------------マイレビュー---------------------------------------------------------- -->

		<div class="tab_content" id="all_content"><!-- -------- -->
			<h2 class="my">
				<a>マイレビュー</a>
			</h2>

			<div id="drop-down-menu">
				<div class="dropdown">
					<input type="checkbox" id="submenu-toggle-2"> <label
						for="submenu-toggle-2">ドロップダウンメニュー</label>
					<ul class="submenu">
						<li><a href="#">Submenu 2-1</a></li>
						<li><a href="#">Submenu 2-2</a></li>
						<li><a href="#">Submenu 2-3</a></li>
					</ul>
				</div>
				<div class="dropdown">
					<input type="checkbox" id="submenu-toggle-2"> <label
						for="submenu-toggle-2">ドロップダウンメニュー</label>
					<ul class="submenu">
						<li><a href="#">Submenu 2-1</a></li>
						<li><a href="#">Submenu 2-2</a></li>
						<li><a href="#">Submenu 2-3</a></li>
					</ul>
				</div>
				<div class="dropdown">
					<input type="checkbox" id="submenu-toggle-2"> <label
						for="submenu-toggle-2">ドロップダウンメニュー</label>
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
						<c:forEach var="e" items="${list}" >
						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>${e.reviewName}</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
							<!-- モーダルウインドウのエリア -->
							<div id="modalArea" class="modalNoDisp">
								<div class="modalWindow">
									<!-- ここがウインドウとなります -->
									<p>モーダルウインドウ</p>
									<p>
										xxx入力<br>
										<input type="text" value="" style="width: 200px">
									</p>
									<p>
										<input type="button" value="閉じる" onclick="modalClose()">
									</p>
								</div>
							</div>
						</div>
						</c:forEach>

						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素2</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
						</div>
						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素3</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
						</div>
						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素4</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
						</div>
						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素5</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
						</div>
						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素6</h1>
								<p class="item_text">テキスト</p>
								<p class="item_text">テキスト</p>
							</div>
						</div>
					</div>
				</div>
				<div class="side">
					<div class="sort">
						<img src="/A4/img/myicon.jpg" width="100" height="100"
							alt="マイアイコン">
					</div>
					<ul class="sort_item">
						<li>五十音順</li>
						<li>価格</li>
						<li>更新日</li>
						<li>評価</li>
					</ul>
					<div class="post-btn">
						<a href="/"> <img src="/A4/img/removebg-preview.png">
						</a>
					</div>
				</div>
			</div>
		</div>
		<!-- -------- -->

<!-- ----------------------------------全体検索------------------------------------------- -->
		<div class="tab_content" id="aaa_content">BBBの内容がここに入ります</div>
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


<!-- ----------------------------------リスト--------------------------------------------------- -->
		<div class="tab_content" id="bbb_content">CCCの内容がここに入ります</div>
		<div class="container">
		<div class="scroll">
			<div class="flex-container">
				<br> <br> <br>
				<div class="flex-item">子要素1</div>
				<div class="flex-item">子要素2</div>
				<div class="flex-item">子要素3</div>
				<div class="flex-item">子要素4</div>
				<div class="flex-item">子要素5</div>
				<div class="flex-item">子要素6</div>
			</div>
		</div>
	<div class="bo">
		<div class="post-btn">
			<a href="/"> <img src="/A4/img/sinkilist.png">
			</a>
		</div>
	</div>
	</div>

	</div>
</body>
</html>