<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.Bean" %>
<%@ page import ="java.util.ArrayList" %>
<%@ page import = "java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	  <a href="/A4/LogoutServlet"><img src="/A4/img/満夫.png" width="100" height="100" alt="マイアイコン"></a></figure>

</header>
	<div class="search_grid">
		<!-- ↓全体検索左側のエリア（ここから）↓ -->
		<div class="search_grid_left">
			<!-- ここでアコーディオンが表示される -->

			<%
				//データ作ってるだけやで
				ArrayList<String> list = new ArrayList<String>();
				list.add("い～ち");
				list.add("に～い");
				list.add("さ～ん");
				list.add("しぃ～");
				request.setAttribute("list", list);
			%>

			<!-- 大項目と小項目を考えて表示 -->



			<c:forEach var="nav" items="${calist}">
				<c:choose>
					<%-- ${e.big}==${taihi}と同じ --%>
					<c:when test="${nav.category1Name !=taihi}">
						<!-- 大項目を表示する -->
						<p class="navi-open">${nav.category1Name}</p>
						<!-- taihiに大項目のデータを入れる -->
						<!-- 小項目を表示する -->
						<p  class="s_ca_list">${nav.category2Name}</p>
					</c:when>
					<c:when test="${nav.category1Name ==taihi}">
						<!-- 小項目を表示する -->
						<p id="s_ca_list" class="s_ca_list">${nav.category2Name}</p>
					</c:when>
				</c:choose>

				<%-- request.setAttribute("taihi",${nav.category1Name});と同じ処理 --%>
				<c:set var="taihi" value="${nav.category1Name}" />
			</c:forEach>
			<!-- ↑全体検索左側のエリア（ここまで）↑ -->
		</div>
		<!-- ↑全体検索左側のエリア（ここまで）↑ -->
		<div class="search_grid_right">
			<h2 class="my">
				<a>すべて</a>
			</h2>
			<div class="search_drop" >
				<form method="post" action="/A4/SearchServlet">
				<input type="checkbox" id="search_drop" class="search_drop">
      			<label for="search_drop">検索</label>

				<!-- ↓全体検索のアコーディオン表示（ここから）↓ -->

				<ul id="search_drop_menu" class="search_drop_menu">
					<li>
						<input type="checkbox" id="search_drop" class="search_drop">
      					<label class="grid-label" for="search_drop">✖</label>
      				</li>
					<li>
						<input type="text" name="freeWord" class="search_text" placeholder="フリー検索">
      				</li>
					<li>
						<input class="search_input" type="text" name="price_a">
						<h1>～</h1>
						<input class="search_input" type="text" name="price_b">
					</li>
					<li>
						<SELECT class="search_input" id="eva_list_a" name="eva_a">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</SELECT>
						<h1>～</h1>
						<SELECT class="search_input" id="eva_list_b" name="eva_b">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5" selected>5</option>
						</SELECT>
					</li>
					<li>
						<input class="search_input" type="date" name="created_a">
						<h1>～</h1>
						<input class="search_input" type="date" name="created_b">
					</li>
					<li><input class="search_btn" type="submit" name="search" value="検索"></li>
				</ul>
				</form>
				<!-- ↑全体検索のアコーディオン表示（ここから）↑ -->
			</div>
			<!-- ↑検索の要素（ここまで）↑ -->


			<!-- ↓レビューアイテムの要素（ここから）↓ -->
			<!-- ↓ここからレビューアイテムのエリア↓ -->
  <div class="container">
  	<div class="scroll">
  	<div class="flex-container">
  	<c:forEach var="e" items="${rlist}" varStatus="status">
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
        					<h1>${e.reviewName}</h1>
        				</div>
        				<div class="modal_grid">
        					<div class="modal_grid_left">
        						<table class="eva">
        							<tr>
        								<td><p>カテゴリー</p></td>
        								<td><p>評価点数</p></td>
        							</tr>
        							<tr>
        								<td>${e.reviewItem1}</td>
        								<td>${e.reviewItem1Score}</td>
        							</tr>
        							<tr>
        								<td>${e.reviewItem2}</td>
        								<td>${e.reviewItem2Score}</td>
        							</tr>
        							<tr>
        								<td>${e.reviewItem3}</td>
        								<td>${e.reviewItem3Score}</td>
        							</tr>
        							<tr>
        								<td>${e.reviewItem4}</td>
        								<td>${e.reviewItem4Score}</td>
        							</tr>
        							<tr>
        								<td>${e.reviewItem5}</td>
        								<td>${e.reviewItem5Score}</td>
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
        							<p>${e.reviewPrice}円</p>
        						</div>
        						<h1 class="avg">総合評価${e.scoreAvg}/5</h1>
							<!-- ↑ここまでモーダルウインドウのエリア↑ -->
        					</div>
        				</div>
        				<div class="">
        				<p>備考</p>
        				<p class="kkk">${e.reviewComment}"</p>
        			</div>
        			</div>

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
	</div>

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
アコーディオンのjQu
==============================*/
$(document).ready(function() {
    $('.navi-open').click(function() {
        // すべての小項目を非表示にする
        $('.s_ca_list').slideUp();

        // 現在の大項目に対応する小項目をトグル表示
        $(this).nextUntil('.navi-open').slideToggle();
    });
});



		</script>
</body>
</html>