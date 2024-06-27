<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  // Servletのデータ受け取り
  request.setCharacterEncoding("UTF8");
  String strMessage = (String) request.getAttribute("message");
%>
<%@ page import ="java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/A4/css/Areview.css">
<title>マイレビューぺージ</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
/*タブ切り替え全体のスタイル*/
.tabs {

	margin-top: 50px;
	padding-bottom: 40px;
	background-color: #fff;
	/* width: 700px; */
	margin: 0 auto;
}

/*タブのスタイル*/
.tab_item {
	margin-top:-3.25%;
	width: calc(70%/3);
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
	position: flex;


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
<c:set var="name" value="${name}" />
<c:set var="img" value="${img}" />
	<button id="openiconModal">
	  <figure class="icon-circle">
	  	<img src="${img}" width="100" height="100" alt="マイアイコン">
	  </figure>
	</button>
	  <!-- ↓アイコンモーダルのエリア↓ -->
		<div id="myiconModal" class="icon_modal">
    		<div class="icon_modal_content">
        		<span id="closeiconModal">&times;</span>
        		<div class ="icon_modal_box">
        			<img class="modal_myicon" src="${img}">
        			<div class="name_box">${name}</div>
        			<div class="icon_flex">
        				<div class="logout"><a href="/A4/LoginServlet">ログアウト</a></div>
        			</div>
        		</div>
    		</div>
		</div>
		<!-- ↑ここまでアイコンモーダルのエリア↑ -->

</header>
<!-- タブヘッダー（ここまで） -->

	<h2 class="my">
	<a>マイレビュー</a>
	</h2>
	<span style="color:red">${result}</span>
	<!-- ↓ドロップのエリア↓ -->
	<div id="drop-down-menu">
		<div class="dropdown all" >
      		<input type="checkbox" id="submenu-toggle-1">
      		<label for="submenu-toggle-1">すべて▽</label>
      		<ul class="submenu all_submenu">
        		<li><input type="submit" class="category" id="adult_category_1" name="search" value="すべて"></li>
        		<li class="adult_category_1">
        			<input type="checkbox" id="adult_category_input_1	">
        			<label for="adult_category_1">ファッション</label>
        			<ul id="child_category_1">
        				<li><input type="submit" id="child_category_input1"></li>
        			</ul>
        		</li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
		<div class="dropdown follow">
      		<input type="checkbox" id="submenu-toggle-2">
      		<label for="submenu-toggle-2">フォロー▽</label>
      		<ul class="submenu follow_submenu">
        		<li><input type="submit" name="search" value="すべて"></li>
        		<li><a href="#">Submenu 2-2</a></li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
		<div class="dropdown in_review">
      		<input type="checkbox" id="submenu-toggle-3">
      		<label for="submenu-toggle-3">レビュー内検索▽</label>
      		<ul class="submenu in_review_submenu">
        		<li><a href="#">Submenu 2-1</a></li>
        		<li><a href="#">Submenu 2-2</a></li>
        		<li><a href="#">Submenu 2-3</a></li>
      		</ul>
		</div>
	</div>
	<!-- ロ↑ここまでドロップダウンのエリア↑ -->

<!-- ↓ここからレビューアイテムのエリア↓ -->
  <div class="container">
  	<div class="scroll">
  	<div class="flex-container">
  	<c:forEach var="e" items="${list}" varStatus="status">
  		<button id="openModal${status.index}" onclick="openM(${status.index})">
    		<div class="flex-item">
   			<div class= "item_img"><img src="${e.reviewImg}" width="100" height="100" alt="マイアイコン"></div>
    			<div class="grid_item2">
    				<h1>${e.reviewName}</h1>

    				<p class= "item_text">${e.reviewPrice}円</p>
    				<p class= "item_text">総合評価${e.scoreAvg}/5</p>
    			</div>
			</div>
		</button>
		<!-- ↑ここまでレビューアイテムのエリア↑ -->

		<!-- ↓レビューアイテムのモーダルのエリア↓ -->
		<div id="myModal${status.index}" class="modal">
    		<form class="modal-content" method="post" action="/A4/MyReviewServlet">
    			<!-- ↓これが俺たちのIDだーーーー！！！↓ -->

        					<input type="hidden" name="reviewId" value="${e.reviewId}">
        					<input type="hidden" name="category2Id" value="${e.category2Id}">
        					<input type="hidden" name="reviewPrice" value="${e.reviewPrice}">
        					<input type="hidden" name="reviewComment" value="${e.reviewComment}">
        					<input type="hidden" name="rPrivacyFlg" value="${e.rPrivacyFlg}">
        					<input type="hidden" name="UpDatedAt" value="${e.rUpdatedAt}">
        					<input type="hidden" name="backnumberContent" value="${e.backnumberContent}">
        					<input type="hidden" name="backnumberId" value="${e.backnumberId}">

        					<!-- ↑これが俺たちのIDだーーーー！！！↑ -->
        		<span id="closeModal${status.index}" class="closeModal" onclick="closeM(${status.index})">&times;</span>
        		<div class="modal_box">
        			<img class="modal_img" src="${e.reviewImg}">
        			<div class="modal_text">
        				<div class="title_box">
        					<p class="create_at">${e.rCreatedAt}</p>
        					<input class="modal_title" type="text" name="reviewName" value="${e.reviewName}">
        				</div>
        				<div class="modal_grid">
        					<div class="modal_grid_left">
        						<table class="eva">
        							<tr>
        								<td><p>カテゴリー</p></td>
        								<td><p>評価点数</p></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem1" value="${e.reviewItem1}"></td>
        								<td><input class="modal_table" type="text" name="reviewItem1Score" value="${e.reviewItem1Score}"></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem2" value="${e.reviewItem2}"></td>
        								<td><input class="modal_table" type="text" name="reviewItem2Score" value="${e.reviewItem2Score}"></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem3" value="${e.reviewItem3}"></td>
        								<td><input class="modal_table" type="text" name="reviewItem3Score" value="${e.reviewItem3Score}"></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem4" value="${e.reviewItem4}"></td>
        								<td><input class="modal_table" type="text" name="reviewItem4Score" value="${e.reviewItem4Score}"></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem5" value="${e.reviewItem5}"></td>
        								<td><input class="modal_table" type="text" name="reviewItem5Score" value="${e.reviewItem5Score}"></td>
        							</tr>
        						</table>
        					</div>
        					<div class="modal_grid_right">
        						<div class="up_box">
        							<p class="open_close_title">公開・非公開</p>
        							<div class="open_close">
													<label class="switch"> <input checked="true"
														type="checkbox" name="ch" value="checked">
														<div class="slider">
															<div class="circle">
																<svg class="cross" xml:space="preserve"
																	style="enable-background: new 0 0 512 512"
																	viewBox="0 0 365.696 365.696" y="0" x="0" height="6"
																	width="6" xmlns:xlink="http://www.w3.org/1999/xlink"
																	version="1.1" xmlns="http://www.w3.org/2000/svg">
                													<g>
                    												<path data-original="#000000" fill="currentColor"
																		d="M243.188 182.86 356.32 69.726c12.5-12.5 12.5-32.766 0-45.247L341.238 9.398c-12.504-12.503-32.77-12.503-45.25 0L182.86 122.528 69.727 9.374c-12.5-12.5-32.766-12.5-45.247 0L9.375 24.457c-12.5 12.504-12.5 32.77 0 45.25l113.152 113.152L9.398 295.99c-12.503 12.503-12.503 32.769 0 45.25L24.48 356.32c12.5 12.5 32.766 12.5 45.247 0l113.132-113.132L295.99 356.32c12.503 12.5 32.769 12.5 45.25 0l15.081-15.082c12.5-12.504 12.5-32.77 0-45.25zm0 0"></path>
                													</g>
            													</svg>
																<svg class="checkmark" xml:space="preserve"
																	style="enable-background: new 0 0 512 512"
																	viewBox="0 0 24 24" y="0" x="0" height="10" width="10"
																	xmlns:xlink="http://www.w3.org/1999/xlink"
																	version="1.1" xmlns="http://www.w3.org/2000/svg">
                												<g>
                    											<path class="" data-original="#000000"
																		fill="currentColor"
																		d="M9.707 19.121a.997.997 0 0 1-1.414 0l-5.646-5.647a1.5 1.5 0 0 1 0-2.121l.707-.707a1.5 1.5 0 0 1 2.121 0L9 14.171l9.525-9.525a1.5 1.5 0 0 1 2.121 0l.707.707a1.5 1.5 0 0 1 0 2.121z"></path>
               					 								</g>
            													</svg>
															</div>
														</div>
													</label>
									</div>
        						<%--  --%>
        						</div>
        						<div class="price_text">
        							<input class="" type="text" name="reviewPrice" value="${e.reviewPrice}" ><p>円</p>
        						</div>
        						<h1 class="avg">総合評価${e.scoreAvg}/5</h1>
							<!-- ↑ここまでモーダルウインドウのエリア↑ -->
        					</div>
        				</div>
        			</div>
        			<input class="remarks" type="text" name="reviewComment" value="${e.reviewComment}" placeholder="備考">
	<c:forEach var="e" items="${bkList}" varStatus="backnumber">
        			<input class="back_number" type="hidden" name="backnumberId" value="${e.backnumberId}">
        			<input class="back_number" type="text" name="backnumberContent" value="${e.backnumberContent}">
    </c:forEach>
        			<!-- ↓更新用ボタン（ここから）↓ -->
        			<input class="modal_btn btn_up" type="submit" name="submit" value="更新">
        			<input class="modal_btn btn_dl" type="submit" name="submit" value="削除">
        			<!-- ↑更新用ボタン（ここまで）↑ -->
        		</div>
    		</form>
		</div>
		</c:forEach>
		<!-- ↑ここまでモーダルウインドウのエリア↑ -->
  	</div>
	</div>
	<div class= "side">
  		<div class="sort">
  			<img src="/A4/img/down.png" width="100" height="100" alt="マイアイコン">
  		</div>
  		<ul class="sort_item">
			<li>五十音順</li>
			<li>価格</li>
			<li>更新日</li>
			<li>評価</li>
		</ul>
		<!-- ↓投稿用ボタンのエリア↓ -->
		<button id="open_post_Modal">
	  		<div class="post-btn">
	  			<img src="/A4/img/removebg-preview.png">
	  		</div>
		</button>
		<!-- ↑ここまで投稿用ボタンのエリア↑ -->

	  	<!-- ↓投稿用モーダルのエリア↓ -->

	  	<div id="my_post_Modal" class="modal">
	  		<form class="modal-content" method="post" action="/A4/MyReviewServlet">
			<span id="close_post_Modal" class="closeModal">&times;</span>
		        	<div class ="post_modal_box">
		        		<img class="modal_img" src="/A4/img/add.png">
		        		<div class="modal_text_box">
		        			<input type="hidden" name="UpDatedAt"  placeholder="投稿日時">
		        			<input class="modal_title" type="text" name="reviewName"  placeholder="レビュー名">
		        		<div class="modal_grid">
		        			<div class="modal_grid_left">
		        				<table class="eva">
		        					<tr>
        								<td><p>評価項目</p></td>
        								<td><p>評価点数</p></td>
        							</tr>
        							<tr>
        								<td><input class="modal_table" type="text" name="reviewItem1" placeholder="立地"></td>
        								<td><input class="modal_table" type="text" name="reviewItem1Score" placeholder="1～5で入力"></td>
        							</tr>
		        					<tr>
        								<td><input class="modal_table" type="text" name="reviewItem2"  placeholder="雰囲気"></td>
        								<td><input class="modal_table" type="text" name="reviewItem2Score" placeholder="1～5で入力"></td>
        							</tr>
		        					<tr>
        								<td><input class="modal_table" type="text" name="reviewItem3" placeholder="営業時間"></td>
        								<td><input class="modal_table" type="text" name="reviewItem3Score" placeholder="1～5で入力"></td>
        							</tr>
		        					<tr>
        								<td><input class="modal_table" type="text" name="reviewItem4" placeholder="料理"></td>
        								<td><input class="modal_table" type="text" name="reviewItem4Score" placeholder="1～5で入力"></td>
        							</tr>
		        					<tr>
        								<td><input class="modal_table" type="text" name="reviewItem5" placeholder="コスパ"></td>
        								<td><input class="modal_table" type="text" name="reviewItem5Score" placeholder="1～5で入力"></td>
        							</tr>
		        				</table>
		        				<p><input type="text" name="reviewPrice" placeholder="価格">円</p>
		        				<input type="text" name="category2Id" placeholder="カテゴリーID" value=1>
		        				<input type="hidden" name="rPrivacyFlg" value=1>
		        			</div>
		        			<div class="modal_grid_right">
		        				<div class="modal_grid_right_top">
		        				<p class="open_close_title">公開・非公開</p>
		        				<div class="open_close">
										<label class="switch"> <input checked="true" type="checkbox" name="ch" value="checked">
											<div class="slider">
												<div class="circle">
													<svg class="cross" 	xml:space="preserve" style="enable-background: new 0 0 512 512"
																		viewBox="0 0 365.696 365.696" y="0" x="0" height="6"
																		width="6" xmlns:xlink="http://www.w3.org/1999/xlink"
																		version="1.1" xmlns="http://www.w3.org/2000/svg">
                										<g>
                    										<path data-original="#000000" fill="currentColor" d="M243.188 182.86 356.32 69.726c12.5-12.5 12.5-32.766 0-45.247L341.238 9.398c-12.504-12.503-32.77-12.503-45.25 0L182.86 122.528 69.727 9.374c-12.5-12.5-32.766-12.5-45.247 0L9.375 24.457c-12.5 12.504-12.5 32.77 0 45.25l113.152 113.152L9.398 295.99c-12.503 12.503-12.503 32.769 0 45.25L24.48 356.32c12.5 12.5 32.766 12.5 45.247 0l113.132-113.132L295.99 356.32c12.503 12.5 32.769 12.5 45.25 0l15.081-15.082c12.5-12.504 12.5-32.77 0-45.25zm0 0"></path>
                										</g>
            										</svg>
													<svg class="checkmark" 	xml:space="preserve"
																			style="enable-background: new 0 0 512 512"
																			viewBox="0 0 24 24" y="0" x="0" height="10" width="10"
																			xmlns:xlink="http://www.w3.org/1999/xlink"
																			version="1.1" xmlns="http://www.w3.org/2000/svg">
                										<g>
                    										<path class="" data-original="#000000" fill="currentColor" d="M9.707 19.121a.997.997 0 0 1-1.414 0l-5.646-5.647a1.5 1.5 0 0 1 0-2.121l.707-.707a1.5 1.5 0 0 1 2.121 0L9 14.171l9.525-9.525a1.5 1.5 0 0 1 2.121 0l.707.707a1.5 1.5 0 0 1 0 2.121z"></path>
               					 						</g>
            										</svg>
												</div>
											</div>
										</label>
									</div>
									</div>
		        					<input type="text" name="reviewComment" placeholder="備考">
		        			</div>
		        		</div>
		        	</div>
		        			<!-- ↓新規登録用ボタン（ここから）↓ -->
		        			<input class="modal_btn btn_up" type="submit" name="submit" value="新規登録">
		        			<!-- ↑新規登録用ボタン（ここまで）↑ -->
	        		</div>
	    	</form>
		</div>
		<!-- ↑ここまで投稿用モーダルのエリア↑ -->

	</div>
  </div>


 	<script>

 	/*==============================
 	レビューアイテムのjs
 	==============================*/

 // モーダルウィンドウとボタン、クローズアイコンの要素を取得
 	let modal = document.getElementById("myModal");

 	// ボタンがクリックされた時にモーダルを表示
 	function openM(num){
 		let modal =document.getElementById("myModal" + num);
 		modal.style.display = "block";
 	}

 	// ×（クローズアイコン）がクリックされた時にモーダルを非表示
 	function closeM(num){
 		let modal =document.getElementById("myModal" + num);
 		let span =document.getElementById("closeModal" + num);
 		modal.style.display = "none";
 	}

 	// モーダルの外側をクリックした時にモーダルを非表示
 	/*
 	window.onclick = function(event) {
 		let modal =document.getElementById("myModal" + num);
 	    if (event.target == modal) {
 	      modal.style.display = "none";
 	    }
 	}
 	*/

 // クリックイベントリスナーを関数にする
    function handleClick(event) {
        // クリックされた要素を取得
        var clickedElement = event.target;
        var elementId = clickedElement.id;

        // クリックされた要素のIDが "myModal" で始まる場合に処理を実行
        if (elementId.startsWith("openModal")) {
            // IDの数字部分を取得
            var num = elementId.replace("openModal", "");
            closeModal(num, event);
        }
    }

    // モーダルを閉じる処理を行う関数
    function closeModal(num, event) {
        var modal = document.getElementById("myModal" + num);
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    // window にクリックイベントリスナーを追加
    window.onclick = handleClick;


    /*==============================
 	ここからアイコンのモーダルのjs
 	==============================*/

 	//ここからアイコンのモーダル
 	var icon_modal = document.getElementById("myiconModal");
 	var icon_btn = document.getElementById("openiconModal");
 	var icon_span = document.getElementById("closeiconModal");

 	// ボタンがクリックされた時にモーダルを表示
 	icon_btn.onclick = function() {
 	icon_modal.style.display = "block";
 	}

 	// ×（クローズアイコン）がクリックされた時にモーダルを非表示
 	icon_span.onclick = function() {
 	icon_modal.style.display = "none";
 	}

 	// モーダルの外側をクリックした時にモーダルを非表示
 	window.onclick = function(event) {
 	    if (event.target == modal) {
 	      icon_modal.style.display = "none";
 	    }
 	}



 	/*==============================
 	編集用のモーダルを表示するjs
 	==============================*/

 	/*
 // モーダルウィンドウとボタン、クローズアイコンの要素を取得
 	let edit_modal = document.getElementById("my_edit_Modal");

 	// ボタンがクリックされた時にモーダルを表示
 	function open_edit_M(num){
 		let modal =document.getElementById("myModal" + num);
 		let edit_modal =document.getElementById("my_edit_Modal" + num);
 		edit_modal.style.display = "block";
 		modal.style.display = "none";
 	}

 	// ×（クローズアイコン）がクリックされた時にモーダルを非表示
 	function closeM(num){
 		let edit_modal =document.getElementById("my_edit_Modal" + num);
 		let edit_span =document.getElementById("close_edit_Modal" + num);
 		modal.style.display = "none";
 	}

 	// モーダルの外側をクリックした時にモーダルを非表示
 	/*
 	window.onclick = function(event) {
 		let modal =document.getElementById("myModal" + num);
 	    if (event.target == modal) {
 	      modal.style.display = "none";
 	    }
 	}
 	*/
 /*
 // クリックイベントリスナーを関数にする
    function handleClick(event) {
        // クリックされた要素を取得
        var clickedElement = event.target;
        var elementId = clickedElement.id;

        // クリックされた要素のIDが "myModal" で始まる場合に処理を実行
        if (elementId.startsWith("open_edit_Modal")) {
            // IDの数字部分を取得
            var num = elementId.replace("open_edit_Modal", "");
            closeModal(num, event);
        }
    }

    // モーダルを閉じる処理を行う関数
    function closeModal(num, event) {
        var modal = document.getElementById("my_edit_Modal" + num);
        if (event.target == edit_modal) {
            modal.style.display = "none";
        }
    }

    // window にクリックイベントリスナーを追加
    window.onclick = handleClick;
 	*/


 	/*==============================
 	ここから投稿用モーダルjs
 	==============================*/

 	let post_modal = document.getElementById("my_post_Modal");
 	let post_btn = document.getElementById("open_post_Modal");
 	let post_span = document.getElementById("close_post_Modal");

 	// ボタンがクリックされた時にモーダルを表示
 	post_btn.onclick = function() {
 		post_modal.style.display = "block"
 	}

 	// ×（クローズアイコン）がクリックされた時にモーダルを非表示
 	post_span.onclick = function() {
 		post_modal.style.display = "none";
 	}

 	// モーダルの外側をクリックした時にモーダルを非表示
 	window.onclick = function(event) {
 	    if (event.target == post_modal) {
 	      post_modal.style.display = "none";
 	    }
 	}

	</script>
</body>
</html>