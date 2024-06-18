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
		<ul class ="tab">
			<li class="tabu-list-item"><a href="/A4/MyReviewServlet">マイレビュー</a></li>
			<li class="tabu-list-item"><a href="/A4/SearchServlet">全体検索</a></li>
			<li class="tabu-list-item"><a href="/A4/ListServlet">リスト</a></li>
		</ul>
	  <div id ="myreview" class="area">
		<h2>マイレビュー</h2>
	  </div>
	  <div id ="zentai" class="area">
		<h2>全体検索</h2>
	  </div>
	  <div id ="list" class="area">
		<h2>リスト</h2>
	  </div>
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
<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="js/5-4-1.js"></script>
<script>

//任意のタブにURLからリンクするための設定
function GethashID (hashIDName){
	if(hashIDName){
		//タブ設定
		$('.tab li').find('a').each(function() { //タブ内のaタグ全てを取得
			var idName = $(this).attr('href'); //タブ内のaタグのリンク名（例）#lunchの値を取得
			if(idName == hashIDName){ //リンク元の指定されたURLのハッシュタグ（例）http://example.com/#lunch←この#の値とタブ内のリンク名（例）#lunchが同じかをチェック
				var parentElm = $(this).parent(); //タブ内のaタグの親要素（li）を取得
				$('.tab li').removeClass("active"); //タブ内のliについているactiveクラスを取り除き
				$(parentElm).addClass("active"); //リンク元の指定されたURLのハッシュタグとタブ内のリンク名が同じであれば、liにactiveクラスを追加
				//表示させるエリア設定
				$(".area").removeClass("is-active"); //もともとついているis-activeクラスを取り除き
				$(hashIDName).addClass("is-active"); //表示させたいエリアのタブリンク名をクリックしたら、表示エリアにis-activeクラスを追加
			}
		});
	}
}

//タブをクリックしたら
$('.tab a').on('click', function() {
	var idName = $(this).attr('href'); //タブ内のリンク名を取得
	GethashID (idName);//設定したタブの読み込みと
	return false;//aタグを無効にする
});


// 上記の動きをページが読み込まれたらすぐに動かす
$(window).on('load', function () {
    $('.tab li:first-of-type').addClass("active"); //最初のliにactiveクラスを追加
    $('.area:first-of-type').addClass("is-active"); //最初の.areaにis-activeクラスを追加
	var hashName = location.hash; //リンク元の指定されたURLのハッシュタグを取得
	GethashID (hashName);//設定したタブの読み込み
});
</script>
</body>
</html>