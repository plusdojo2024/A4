<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>いいねをするやで</title>
<!-- jQueryの読み込み -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<input type="button" value="いいね" onclick="goAjax()" ><br>
	いいねの数：<span id="counter"></span>

	<script>
	function goAjax(){
		alert("functionはいったよ！");

		//{変数名：中に入れるもの}みたいに書いて、複数の値をpostData変数に格納
		let postData = {data1:1}

		//非同期通信始めるよ
		$.ajaxSetup({scriptCharset:'utf-8'});
		$.ajax({
			//どのサーブレットに送るか
			//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
			url: '/iine/ClickPlusServlet',
			//どのメソッドを使用するか
			type:"POST",
			//受け取るデータのタイプ
			dataType:"json",
			//何をサーブレットに飛ばすか（変数を記述）
			data: postData,
			//この下の２行はとりあえず書いてる（書かなくても大丈夫？）
			processDate:false,
			timeStamp: new Date().getTime()
		   //非同期通信が成功したときの処理
		}).done(function(data) {
			alert("成功1");

		  })
		   //非同期通信が失敗したときの処理
		  .fail(function() {
			//失敗とアラートを出す
			alert("失敗！");
		  });
	}

	//１秒毎に実行される処理
	setInterval(function() {
    	// ここに1秒ごとに実行したい処理を書く
		//非同期通信始めるよ
		$.ajaxSetup({scriptCharset:'utf-8'});
		$.ajax({
			//どのサーブレットに送るか
			//ajaxSampleのところは自分のプロジェクト名に変更する必要あり。
			url: '/iine/ClickPlusServlet',
			//どのメソッドを使用するか
			type:"POST",
			//受け取るデータのタイプ
			dataType:"json",
			//何をサーブレットに飛ばすか（変数を記述）
			/* data: postData, */
			//この下の２行はとりあえず書いてる（書かなくても大丈夫？）
			processDate:false,
			timeStamp: new Date().getTime()
		   //非同期通信が成功したときの処理
		}).done(function(data) {

		// 今回は上の<div id="counter"></div>の中に返ってきた文字列を入れる
			document.getElementById("counter").innerText=data;
		  })
		   //非同期通信が失敗したときの処理
		  .fail(function() {
			//失敗とアラートを出す
			alert("失敗！");
		  });
	}, 1000);

	</script>
</body>
</html>