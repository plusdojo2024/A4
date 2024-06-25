<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/search.css">
<title>全体検索</title>
</head>
<body>
<!-- タブヘッダー（ここから） -->
<header>
	<h1 class ="headline">
    <a href="MyReviewServlet"><img src="/A4/img/logo.png" width="100" height="100" alt="A review"></a>
	</h1>
	<ul class ="tab">
		<li><a href="/A4/MyReviewServlet">マイレビュー</a></li>
		<li class="selected"><a href="/A4/SearchServlet">全体検索</a></li>
		<li><a href="/A4/ListServlet">リスト</a></li>
	</ul>

	  <figure class="icon-circle">
	  <a href="/A4/LogoutServlet"><img src="/A4/img/myicon.jpg" width="100" height="100" alt="マイアイコン"></a></figure>

</header>
	<div class="search_grid">
		<div class="search_grid_left"></div>
		<div class="search_grid_right">
			<h2 class="my">
				<a>すべて</a>
			</h2>
			<div class="search_drop" >
				<input type="checkbox" id="search_drop" class="search_drop">
      			<label for="search_drop">検索</label>
      		
				<!-- ↓レビューアイテムのモーダルのエリア↓ -->
				<ul id="search_drop_menu" class="search_drop_menu">
					<li><h1 class="search_search">検索</h1></li>
					<li>
						<input class="search_input" type="text" name="" value="">
						<h1>～</h1>
						<input class="search_input" type="text" name="" value="">
					</li>
					<li>
						<input class="search_input" type="text" name="" value="">
						<h1>～</h1>
						<input class="search_input" type="text" name="" value="">
					</li>
					<li>
						<input class="search_input" type="text" name="" value="">
						<h1>～</h1>
						<input class="search_input" type="text" name="" value="">
					</li>
					<li><input class="search_btn" type="submit" name="search" value="検索"></li>
				</ul>
				<!-- ↑レビューアイテムのモーダルのエリア↑ -->
			</div>
			<div class="container">
				<div class="scroll">
					<div class="flex-container">

						<div class="flex-item">
							<div class="item_img">
								<img src="/A4/img/myicon.jpg" width="100" height="100"
									alt="マイアイコン">
							</div>
							<div class="grid_item2">
								<h1>子要素1</h1>
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
			</div>
		</div>
	</div>
<script>
		//ここからアイコンのモーダル
	 	function openS(){
 		let search_drop_menu =document.getElementById("search_drop_menu");
 		search_drop_menu.style.display = "block";
 		}
		</script>
</body>
</html>