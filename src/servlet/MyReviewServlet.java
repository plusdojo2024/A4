package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Categorys1DAO;
import dao.FollowsDao;
import dao.ReviewsDAO;
import dao.ReviewsImgsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import dao.UsersDAO;
import model.Category;
import model.Review;
import model.User;



@WebServlet("/MyReviewServlet")//ここを変える
public class MyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


//URLにID,PWに乗っちゃう　
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		 //もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		//大カテゴリー、小カテゴリーの内容を取得
	    Categorys1DAO ca1dao = new Categorys1DAO();
	    ArrayList<Category> calist = ca1dao.AllSelectCategory();

	    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
	    request.setAttribute("calist", calist);
	    System.out.println(calist.size());

		User user = (User)session.getAttribute("user");
		int id = user.getUserId();

		//すべてのレビューを持ってくる
		ReviewsDAO rDao = new ReviewsDAO();
		ArrayList<Review> myAllReview0 = rDao.descDateView(id);
		int tai=0;
		ArrayList<Review> myAllReview = new ArrayList<>();
		//重複するものを排除
		for(Review r : myAllReview0) {
			if(r.getReviewId()!=tai) {
				tai = r.getReviewId();
				myAllReview.add(r);
			}
		}
		//大カテゴリー、小カテゴリーも一緒に持ってくる
		Categorys1DAO dao = new Categorys1DAO();
		ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
		//フォローしてる人を取ってくる
		FollowsDao fDao = new FollowsDao();
		ArrayList<User> fUserList = fDao.followSelect(id);
		request.setAttribute("list", myAllReview);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("fUserList", fUserList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}
	//URLにID,PWに乗らない　チラ見対策
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		User user = (User)session.getAttribute("user");
		int id = user.getUserId();

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");


		//レビュー新規登録
		if (request.getParameter("submit")!=null && request.getParameter("submit").equals("新規登録")) {
			int category2Id = Integer.parseInt(request.getParameter("category2Id"));
			String reviewName = request.getParameter("reviewName");
			int reviewPrice = Integer.parseInt(request.getParameter("reviewPrice"));
			String reviewComment = request.getParameter("reviewComment");
			int privacyFlg = Integer.parseInt(request.getParameter("rPrivacyFlg"));
//			String UpCreatedAt = request.getParameter("UpCreatedAt");
//			//String型からTimestamp型へ変換
//			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
//			java.util.Date parsedDate = null;
//			try {
//				parsedDate = f.parse(UpCreatedAt);
//			} catch (ParseException e) {
//				// TODO 自動生成された catch ブロック
//				e.printStackTrace();
//			}
//			Timestamp upCreatedAt = new Timestamp(parsedDate.getTime());

			String reviewImg = request.getParameter("reviewImg");

			String reviewItem1 = request.getParameter("reviewItem1");
			String reviewItem2 = request.getParameter("reviewItem2");
			String reviewItem3 = request.getParameter("reviewItem3");
			String reviewItem4 = request.getParameter("reviewItem4");
			String reviewItem5 = request.getParameter("reviewItem5");

			int reviewItem1Score = Integer.parseInt(request.getParameter("reviewItem1Score"));
			int reviewItem2Score = Integer.parseInt(request.getParameter("reviewItem2Score"));
			int reviewItem3Score = Integer.parseInt(request.getParameter("reviewItem3Score"));
			int reviewItem4Score = Integer.parseInt(request.getParameter("reviewItem4Score"));
			int reviewItem5Score = Integer.parseInt(request.getParameter("reviewItem5Score"));

			ReviewsDAO rDao = new ReviewsDAO();
			int result1 = rDao.insert(category2Id,reviewName,reviewPrice,reviewComment,id,privacyFlg);




			Review review = rDao.selectId();
			int rid = review.getReviewId();



			ReviewsImgsDAO rimgsDao = new ReviewsImgsDAO();
			int result2 = rimgsDao.insert(rid, reviewImg);

			ReviewsItemsDAO ritemDao = new ReviewsItemsDAO();
			int result3 = ritemDao.insert(rid, reviewItem1, reviewItem2, reviewItem3, reviewItem4, reviewItem5);

			ReviewsScoresDAO rSDao = new ReviewsScoresDAO();
			int result4 = rSDao.insert(rid,reviewItem1Score, reviewItem2Score, reviewItem3Score, reviewItem4Score, reviewItem5Score);

			if (result1 == 1 &&result2 == 1 &&result3 == 1 &&result4 == 1) {
				request.setAttribute("result", "登録しました。");
			} else {
				request.setAttribute("result", "登録できませんでした");
			}


			//dogetの内容ここからーーーーーーーーー

			//大カテゴリー、小カテゴリーの内容を取得
		    Categorys1DAO ca1dao = new Categorys1DAO();
		    ArrayList<Category> calist = ca1dao.AllSelectCategory();

		    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
		    request.setAttribute("calist", calist);
		    System.out.println(calist.size());


			//すべてのレビューを持ってくる
			ArrayList<Review> myAllReview0 = rDao.descDateView(id);
			int tai=0;
			ArrayList<Review> myAllReview = new ArrayList<>();
			//重複するものを排除
			for(Review r : myAllReview0) {
				if(r.getReviewId()!=tai) {
					tai = r.getReviewId();
					myAllReview.add(r);
				}
			}
			//大カテゴリー、小カテゴリーも一緒に持ってくる
			Categorys1DAO dao = new Categorys1DAO();
			ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
			//フォローしてる人を取ってくる
			FollowsDao fDao = new FollowsDao();
			ArrayList<User> fUserList = fDao.followSelect(id);
			request.setAttribute("list", myAllReview);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("fUserList", fUserList);
			//dogetの内容ここまでーーーーーーーーー


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}


		//レビュー削除
		else if(request.getParameter("submit")!=null && request.getParameter("submit").equals("削除")) {

			int reviewId = Integer.parseInt(request.getParameter("reviewId"));

			ReviewsDAO rDao = new ReviewsDAO();
			int result = rDao.delete(reviewId);

			if (result == 1) {
				request.setAttribute("result", "削除しました。");
			} else {
				request.setAttribute("result", "削除できませんでした");
			}


			//dogetの内容ここからーーーーーーーーー

			//大カテゴリー、小カテゴリーの内容を取得
		    Categorys1DAO ca1dao = new Categorys1DAO();
		    ArrayList<Category> calist = ca1dao.AllSelectCategory();

		    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
		    request.setAttribute("calist", calist);
		    System.out.println(calist.size());


			//すべてのレビューを持ってくる
			ArrayList<Review> myAllReview0 = rDao.descDateView(id);
			int tai=0;
			ArrayList<Review> myAllReview = new ArrayList<>();
			//重複するものを排除
			for(Review r : myAllReview0) {
				if(r.getReviewId()!=tai) {
					tai = r.getReviewId();
					myAllReview.add(r);
				}
			}
			//大カテゴリー、小カテゴリーも一緒に持ってくる
			Categorys1DAO dao = new Categorys1DAO();
			ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
			//フォローしてる人を取ってくる
			FollowsDao fDao = new FollowsDao();
			ArrayList<User> fUserList = fDao.followSelect(id);
			request.setAttribute("list", myAllReview);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("fUserList", fUserList);
			//dogetの内容ここまでーーーーーーーーー


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//レビュー更新
		else if(request.getParameter("submit").equals("更新")) {
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			int category2Id = Integer.parseInt(request.getParameter("category2Id"));
			String reviewName = request.getParameter("reviewName");
			int reviewPrice = Integer.parseInt(request.getParameter("reviewPrice"));
			String reviewComment = request.getParameter("reviewComment");
			int privacyFlg = Integer.parseInt(request.getParameter("rPrivacyFlg"));
			String UpDatedAt = request.getParameter("UpDatedAt");
			UpDatedAt =UpDatedAt.replace("00:00:00.0","");
			//String型からTimestamp型へ変換
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date parsedDate=null;
			try {
				parsedDate = f.parse(UpDatedAt);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Timestamp upDatedAt = new Timestamp(parsedDate.getTime());

			String reviewImg = request.getParameter("reviewImg");

			String reviewItem1 = request.getParameter("reviewItem1");
			String reviewItem2 = request.getParameter("reviewItem2");
			String reviewItem3 = request.getParameter("reviewItem3");
			String reviewItem4 = request.getParameter("reviewItem4");
			String reviewItem5 = request.getParameter("reviewItem5");

			int reviewItem1Score = Integer.parseInt(request.getParameter("reviewItem1Score"));
			int reviewItem2Score = Integer.parseInt(request.getParameter("reviewItem2Score"));
			int reviewItem3Score = Integer.parseInt(request.getParameter("reviewItem3Score"));
			int reviewItem4Score = Integer.parseInt(request.getParameter("reviewItem4Score"));
			int reviewItem5Score = Integer.parseInt(request.getParameter("reviewItem5Score"));

//			String backnumberContent = request.getParameter("backnumberContent");
//			int backnumberId = Integer.parseInt(request.getParameter("backnumberId"));

			ReviewsDAO dao1 = new ReviewsDAO();
			//とりあえず、全てのレビューの情報を取得してくる
			ArrayList<Review> re = dao1.view4();
		    Review review = new Review();
		    //選んだreviewIdのコンテンツの情報を取得
			for(Review r : re) {
				if(r.getReviewId()==reviewId) {
					review = r;
					break;
				}
			}



			ReviewsDAO rDao = new ReviewsDAO();
			int result1 = rDao.update(category2Id,reviewName, reviewPrice, reviewComment, privacyFlg, upDatedAt, reviewId);
			ReviewsImgsDAO rimgsDao = new ReviewsImgsDAO();
			int result2 = rimgsDao.update(reviewId, reviewImg);
			ReviewsItemsDAO ritemDao = new ReviewsItemsDAO();
			int result3 = ritemDao.update(review.getReviewItemId(),review.getReviewId(), reviewItem1, reviewItem2, reviewItem3, reviewItem4, reviewItem5);
			ReviewsScoresDAO rSDao = new ReviewsScoresDAO();
			int result4 = rSDao.update(review.getReviewScoreId(),review.getReviewId(),reviewItem1Score, reviewItem2Score, reviewItem3Score, reviewItem4Score, reviewItem5Score);
//			BacknumbersDAO bDao = new BacknumbersDAO();
//			int result5 = bDao.insert(reviewId,backnumberContent);
			/*int result6 = bDao.delete(backnumberId);*/
			/*int result7 = bDao.update(backnumberId, backnumberContent);
			*/

			if (result1 == 1 &&result2 == 1 &&result3 == 1 &&result4 == 1 /*&&result5 == 1*/) {
				request.setAttribute("result", "更新しました。");
			} else {
				request.setAttribute("result", "更新できませんでした");
			}
//			User user1 = (User)session.getAttribute("user");
//			int id1 = user1.getUserId();
//
//			//すべてのレビューを持ってくる
//			ReviewsDAO rDao1 = new ReviewsDAO();
//			ArrayList<Review> myAllReview0 = rDao1.descDateView(id1);
//			ArrayList<Review> myAllReview = new ArrayList<Review>();
//			int tai = 0;
//			//重複するものを排除
//			for(Review r : myAllReview0) {
//				if(r.getReviewId()!=tai) {
//					tai = r.getReviewId();
//					myAllReview.add(r);
//				}
//			}

//			BacknumbersDAO backDao = new BacknumbersDAO();
//			ArrayList<Review> bkList = backDao.getBackNumbers(reviewId);
//
//			//大カテゴリー、小カテゴリーも一緒に持ってくる
//			Categorys1DAO dao = new Categorys1DAO();
//			ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
//			//フォローしてる人を取ってくる
//			FollowsDao fDao = new FollowsDao();
//			ArrayList<User> fUserList = fDao.followSelect(id1);
//			request.setAttribute("list", myAllReview);
//			request.setAttribute("categoryList", categoryList);
//			request.setAttribute("fUserList", fUserList);


//			//dogetの内容ここからーーーーーーーーー
//
//			//大カテゴリー、小カテゴリーの内容を取得
//		    Categorys1DAO ca1dao = new Categorys1DAO();
//		    ArrayList<Category> calist = ca1dao.AllSelectCategory();
//
//		    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
//		    request.setAttribute("calist", calist);
//		    System.out.println(calist.size());
//
//
//			//すべてのレビューを持ってくる
//			ArrayList<Review> myAllReview0 = rDao.descDateView(id);
//			int tai=0;
//			ArrayList<Review> myAllReview = new ArrayList<>();
//			//重複するものを排除
//			for(Review r : myAllReview0) {
//				if(r.getReviewId()!=tai) {
//					tai = r.getReviewId();
//					myAllReview.add(r);
//				}
//			}
//			//大カテゴリー、小カテゴリーも一緒に持ってくる
//			Categorys1DAO dao = new Categorys1DAO();
//			ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
//			//フォローしてる人を取ってくる
//			FollowsDao fDao = new FollowsDao();
//			ArrayList<User> fUserList = fDao.followSelect(id);
//			request.setAttribute("list", myAllReview);
//			request.setAttribute("categoryList", categoryList);
//			request.setAttribute("fUserList", fUserList);
//			//dogetの内容ここまでーーーーーーーーー


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//自分のアイコン、名前、公開非公開を変更(※Ajaxで値を送る仕掛けが必要)
		else if(request.getParameter("hen")!=null) {
			request.setCharacterEncoding("UTF-8");
			UsersDAO uDao = new UsersDAO();
			//公開非公開の変更--------------------
			int privacyFlg = Integer.parseInt(request.getParameter("privacyFlg"));
			int num1 =uDao.priUpdate(privacyFlg,id);

			//名前の変更--------------------
			String userName = request.getParameter("userName");
			int num2 = uDao.nameUpdate(userName, id);

			//画像の変更--------------------
			String newIcom = request.getParameter("icon");
			int num3 =uDao.iconUpdate(id, newIcom);

			//最後は消す-------
			if (num1+num2+num3 == 3) {
				request.setAttribute("result", "登録しました。");
			} else {
				request.setAttribute("result", "登録できませんでした");
			}

			//大カテゴリー、小カテゴリーの内容を取得
		    Categorys1DAO ca1dao = new Categorys1DAO();
		    ArrayList<Category> calist = ca1dao.AllSelectCategory();

		    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
		    request.setAttribute("calist", calist);
		    System.out.println(calist.size());

			//すべてのレビューを持ってくる
			ReviewsDAO rDao = new ReviewsDAO();
			ArrayList<Review> myAllReview0 = rDao.descDateView(id);
			int tai=0;
			ArrayList<Review> myAllReview = new ArrayList<>();
			//重複するものを排除
			for(Review r : myAllReview0) {
				if(r.getReviewId()!=tai) {
					tai = r.getReviewId();
					myAllReview.add(r);
				}
			}
			//大カテゴリー、小カテゴリーも一緒に持ってくる
			Categorys1DAO dao = new Categorys1DAO();
			ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
			//フォローしてる人を取ってくる
			FollowsDao fDao = new FollowsDao();
			ArrayList<User> fUserList = fDao.followSelect(id);
			request.setAttribute("list", myAllReview);
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("fUserList", fUserList);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}


		//レビュー登録の場合-------------
//		ReviewsDao d1 = new ReviewsDao();4
//		d1.insert(引数たくさん);
//		int reviewsId = d1.selectId();
//
//		ReviewsImgDao d2 = new ReviewsImgDao();3
//		for(String gazo : gazoList) {
//			d2.insert(reviewsId,gazo);
//		}
//		ReviewsItemDao d3 = new ReviewsItemDao();3
//		d3.insert(reviewsId,xxxx);
//
//		ReviewsScoresDao d4 = new ReviewsScoresDao();3
//		d4.insert(reviewsId,引数);
		//-------------






















		//↓レビュー表示用


		 if(request.getParameter("submit")!=null && request.getParameter("submit").equals("検索")) {
			//価格検索のテキストの内容を取得する文
			int category2Id = Integer.parseInt(request.getParameter("category2Id"));
			String freeWord = request.getParameter("freeWord");
			String stpriceA = request.getParameter("price_a");
			int priceA = Integer.parseInt(stpriceA);
			String stpriceB = request.getParameter("price_b");
			int priceB = Integer.parseInt(stpriceB);

			//評価検索のテキストの内容を取得する文
			String stevaA = request.getParameter("eva_a");
			int evaA = Integer.parseInt(stevaA);
			String stevaB = request.getParameter("eva_b");
			int evaB = Integer.parseInt(stevaB);

			//日付検索のテキストの内容を取得する文
			String stcreatedA = request.getParameter("created_a");
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
			java.util.Date parsedDate = null;
			try {
			 	parsedDate = f.parse(stcreatedA);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			Timestamp createdA = new Timestamp(parsedDate.getTime());

			String stcreatedB = request.getParameter("created_b");
			try {
			 	parsedDate = f.parse(stcreatedB);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			Timestamp createdB = new Timestamp(parsedDate.getTime());



			//ReviewsDAOをインスタンス化
			ReviewsDAO rDao = new ReviewsDAO();

			//ReviewItemsDAOをインスタンス化
			ReviewsItemsDAO ritemDao = new ReviewsItemsDAO();

			//ReviewScoresDAOをインスタンス化
			ReviewsScoresDAO rscoreDao  = new ReviewsScoresDAO();

		//↓全体検索画面のレビュー表示について

	    if (request.getParameter("categoryId") != null) {

	    }



	    //「すべて」を選択した場合
		if (request.getParameter("categoryId").equals("すべて")) {

			//昇順ボタンを押したとき
	    	if (request.getParameter("sort1").equals("昇順")) {

	    		//五十音を押したとき
	    		if (request.getParameter("sort2").equals("五十音")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascWordSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「昇順」「五十音」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascWordView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//価格を押したとき
	    		if (request.getParameter("sort2").equals("価格")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascPriceSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「昇順」「価格」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascPriceView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    		//更新日を押したとき
	    		if (request.getParameter("sort2").equals("更新日")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascDateSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「昇順」「更新日」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascDateView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    		//評価を押したとき
	    		if (request.getParameter("sort2").equals("評価")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascEvaSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「昇順」「評価」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascEvaView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    	//降順ボタンを押したとき
	    	} else {

	    		//五十音を押したとき
	    		if (request.getParameter("sort2").equals("五十音")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descWordSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「降順」「五十音」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descWordView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//価格を押したとき
	    		if (request.getParameter("sort2").equals("価格")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descPriceSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「降順」「価格」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descPriceView(user.getUserId());//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//更新日を押したとき
	    		if (request.getParameter("sort2").equals("更新日")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descDateSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「降順」「更新日」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descDateView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//評価を押したとき
	    		if (request.getParameter("sort2").equals("評価")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descEvaSearch(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「降順」「評価」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descEvaView(user.getUserId());

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    	}

	    //小カテゴリーを選択した場合
		} else {


			//昇順ボタンを押したとき
	    	if (request.getParameter("sort1").equals("昇順")) {

	    		//五十音を押したとき
	    		if (request.getParameter("sort2").equals("五十音")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascWordSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「昇順」「五十音」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascWordView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//価格を押したとき
	    		if (request.getParameter("sort2").equals("価格")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascPriceSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「すべて」「昇順」「価格」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascPriceView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    		//更新日を押したとき
	    		if (request.getParameter("sort2").equals("更新日")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascDateSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「昇順」「更新日」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascDateView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    		//評価を押したとき
	    		if (request.getParameter("sort2").equals("評価")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.ascEvaSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「昇順」「評価」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.ascEvaView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
 	    		}

	    	//降順ボタンを押したとき
	    	} else {

	    		//五十音を押したとき
	    		if (request.getParameter("sort2").equals("五十音")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descWordSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「降順」「五十音」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descWordView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//価格を押したとき
	    		if (request.getParameter("sort2").equals("価格")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descPriceSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「降順」「価格」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descPriceView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//更新日を押したとき
	    		if (request.getParameter("sort2").equals("更新日")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descDateSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「降順」「更新日」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descDateView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    		//評価を押したとき
	    		if (request.getParameter("sort2").equals("評価")) {

	    			//検索ボタンを押したとき
	    			if (request.getParameter("search") != null) {
	    				ArrayList<Review> rlist = rDao.descEvaSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);

	    			//「小カテゴリー」「降順」「評価」にならべる
	    			} else {
	    				ArrayList<Review> rlist = rDao.descEvaView2(user.getUserId(), category2Id);

	    				//レビュー項目をrlistに追加
	    				for (Review r : rlist) {
	    					ritemDao.view(r.getReviewId(), r);
	    				}

	    				//レビュースコアをrlistに追加
	    				for (Review r: rlist) {
	    					rscoreDao.view(r.getReviewId(), r);
	    				}

	    				//スコープに格納
	    				request.setAttribute("rlist", rlist);
	    			}
	    		}

	    	}


		}

		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);
		 }
	}

}
