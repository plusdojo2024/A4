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
import dao.ReviewsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import dao.UsersDAO;
import model.Category;
import model.Count;
import model.Review;
import model.User;

@WebServlet("/SearchServlet")//ここを変える
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
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

		//ログイン時に受け取ったユーザー情報を取得する
	    User user = (User)session.getAttribute("user");

		//ReviewsDAOをインスタンス化
		ReviewsDAO rdao = new ReviewsDAO();

		//ReviewsItemsDAOをインスタンス化
		ReviewsItemsDAO ridao = new ReviewsItemsDAO();

		//ReviewsScoresDAOをインスタンス化
		ReviewsScoresDAO rsdao = new ReviewsScoresDAO();

		//UsersDAOをインスタンス化
		UsersDAO udao = new UsersDAO();

		//Count.javaをインスタンス化
		Count co = new Count();

		//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用レビューデータをrlistに格納
		ArrayList<Review> rlist0 = rdao.view(user.getUserId());

		ArrayList<Review> rlist = new ArrayList<Review>();
		int tai = 0;
		//重複するものを排除
		for(Review r : rlist0) {
			if(r.getReviewId()!=tai) {
				tai = r.getReviewId();
				rlist.add(r);
			}
		}


		//レビュー項目をrlistに追加
		for (Review r : rlist) {
			ridao.view(r.getReviewId(), r);
		}

		//レビュースコアをrlistに追加
		for (Review r: rlist) {
			rsdao.view(r.getReviewId(), r);
		}

		//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用ユーザーデータをulistに格納
//		ArrayList<User> ulist = udao.view(user.getUserId());

		//「すべて」選択時のレビューの検索結果の数を数える
		int sum1 = rlist.size();

		//Count.javaのreviewSearchCountに数えた数を格納
		co.setReviewSearchCount(sum1);

		//「すべて」選択時のユーザーの検索結果の数を数える
//		int sum2 = ulist.size();

		//Count.javaのuserSearchCountに数えた数を格納
//		co.setUserSearchCount(sum2);

		//「すべて」選択時かつ「検索ボタン」が押されていない時のレビューデータをスコープに格納
		request.setAttribute("rlist", rlist);

		//「すべて」選択時かつ「検索ボタン」が押されていない時のユーザーデータをスコープに格納
//		request.setAttribute("ulist", ulist);

		//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
		request.setAttribute("count", co);

		// 全体検索ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
	    dispatcher.forward(request, response);
	}

	//ここからdoPost。ここに追加していく。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		//ログイン時に受け取ったユーザー情報を取得する
	    User user = (User)session.getAttribute("user");

		//ReviewsDAOをインスタンス化
		ReviewsDAO rdao = new ReviewsDAO();

		//ReviewsItemsDAOをインスタンス化
		ReviewsItemsDAO ridao = new ReviewsItemsDAO();

		//ReviewsScoresDAOをインスタンス化
		ReviewsScoresDAO rsdao = new ReviewsScoresDAO();

		//UsersDAOをインスタンス化
		UsersDAO udao = new UsersDAO();

		//Count.javaをインスタンス化
		Count co = new Count();

		//↓全体検索画面のレビュー表示について
		//小カテゴリーが選択された場合
		if(request.getParameter("category2Id")!=null) {

		//Ajaxで受け取った小カテゴリーIDをパラメータとして受け取り、int型に変換
		int category2Id = Integer.parseInt(request.getParameter("category2Id"));

			//検索ボタンが押された場合
			if (request.getParameter("search") != null) {
				//文字化け防止処理構文を記述
				request.setCharacterEncoding("UTF-8");

				//価格検索のテキストの内容を取得する文
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

				//小カテゴリー選択時の検索結果のレビューデータをrlistに格納
				ArrayList<Review> rlist = rdao.wholeSearch2(user.getUserId(), category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

				//レビュー項目をrlistに追加
				for (Review r : rlist) {
					ridao.view(r.getReviewId(), r);
				}

				//レビュースコアをrlistに追加
				for (Review r: rlist) {
					rsdao.view(r.getReviewId(), r);
				}

				//検索結果のユーザーデータをulistに格納
				ArrayList<User> ulist = udao.search(user.getUserId(), freeWord);

				//レビューの検索結果の数を調べる
				int sum1 = rlist.size();

				//Count.javaのreviewSearchCountに数えたレビュー数を格納
				co.setReviewSearchCount(sum1);

				//ユーザーの検索結果の数を数える
				int sum2 = ulist.size();

				//Count.javaのuserSearchCountに数えたユーザー数を格納
				co.setUserSearchCount(sum2);

				//カテゴリー選択時におけるレビュー検索結果をスコープに格納
				request.setAttribute("rlist", rlist);

				//ユーザー検索結果をスコープに格納
				request.setAttribute("ulist", ulist);

				//カテゴリー選択時におけるレビュー、ユーザーの検索結果の数をスコープに格納
				request.setAttribute("count", co);

			} else {

			    //カテゴリー選択時かつ「検索ボタン」が押されていない時の表示用データをlistに格納
				ArrayList<Review> rlist = rdao.view3(user.getUserId(), category2Id);

				//レビュー項目をrlistに追加
				for (Review r : rlist) {
					ridao.view(r.getReviewId(), r);
				}

				//レビュースコアをrlistに追加
				for (Review r: rlist) {
					rsdao.view(r.getReviewId(), r);
				}

				//すべてのユーザー情報をlistに格納
				ArrayList<User> ulist = udao.view(user.getUserId());

				//選択したカテゴリーの検索結果のレビュー数を調べる
				int sum1 = rlist.size();

				//Count.javaのreviewSearchCountに数えたレビュー数を格納
				co.setReviewSearchCount(sum1);

				//すべてのユーザーの検索結果の数を数える
				int sum2 = ulist.size();

				//Count.javaのuserSearchCountにすべてのユーザー数を格納
				co.setUserSearchCount(sum2);

				//カテゴリー選択時における検索結果をスコープに格納
				request.setAttribute("rlist", rlist);
				//ユーザー検索結果をスコープに格納
				request.setAttribute("ulist", ulist);

				//カテゴリー選択時におけるレビュー、ユーザーの数をスコープに格納
				request.setAttribute("count", co);
			}

	    //「すべて」を選択した場合
		} else {

			//検索ボタンが押された場合
			if (request.getParameter("search") != null) {
				//文字化け防止処理構文を記述
				request.setCharacterEncoding("UTF-8");
				//価格検索のテキストの内容を取得する文
				String freeWord = request.getParameter("freeWord");

				String stpriceA = request.getParameter("price_a");
				int priceA =0;
				int priceB = 9999999;
				if(!stpriceA.equals("")) {
					priceA = Integer.parseInt(stpriceA);
				}
				String stpriceB = request.getParameter("price_b");
				if(!stpriceB.equals("")) {
					priceB = Integer.parseInt(stpriceB);
				}

				//評価検索のテキストの内容を取得する文
				int evaA =0;
				int evaB =5;
				String stevaA = request.getParameter("eva_a");
				if(!stevaA.equals("")) {
					evaA = Integer.parseInt(stevaA);
				}
				String stevaB = request.getParameter("eva_b");
				if(!stevaB.equals("")) {
					evaB = Integer.parseInt(stevaB);
				}


				//日付検索のテキストの内容を取得する文
				String stcreatedA ="0001-01-01";
				if(!request.getParameter("created_a").equals("")) {
					stcreatedA = request.getParameter("created_a");
				}

				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date parsedDate = null;
				try {
				 	parsedDate = f.parse(stcreatedA);
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				Timestamp createdA = new Timestamp(parsedDate.getTime());
				String stcreatedB ="9999-12-12";
				if(!request.getParameter("created_b").equals("")) {
					stcreatedB = request.getParameter("created_b");
				}
				try {
				 	parsedDate = f.parse(stcreatedB);
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				Timestamp createdB = new Timestamp(parsedDate.getTime());

				//「すべて」選択時の検索結果のレビューデータをrlistに格納
				ArrayList<Review> rlist0 = rdao.wholeSearch1(user.getUserId(), freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

				ArrayList<Review> rlist = new ArrayList<Review>();
				int tai = 0;
				//重複するものを排除
				for(Review r : rlist0) {
					if(r.getReviewId()!=tai) {
						tai = r.getReviewId();
						rlist.add(r);
					}
				}

				//レビュー項目をrlistに追加
				for (Review r : rlist) {
					ridao.view(r.getReviewId(), r);
				}

				//レビュースコアをrlistに追加
				for (Review r: rlist) {
					rsdao.view(r.getReviewId(), r);
				}

				//「すべて」選択時の検索結果のユーザーデータをulistに格納
				ArrayList<User> ulist = udao.search(user.getUserId(), freeWord);

				//「すべて」選択時のレビューの検索結果の数を数える
				int sum1 = rlist.size();

				//Count.javaのreviewSearchCountに数えた数を格納
				co.setReviewSearchCount(sum1);

				//「すべて」選択時のユーザーの検索結果の数を数える
//				int sum2 = ulist.size();

				//Count.javaのuserSearchCountに数えた数を格納
//				co.setUserSearchCount(sum2);

				//「すべて」選択時におけるレビュー検索結果をスコープに格納
				request.setAttribute("rlist", rlist);

				//「すべて」選択時におけるユーザー検索結果をスコープに格納
//				request.setAttribute("ulist", ulist);

				//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
				request.setAttribute("count", co);

			} else {
				//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用レビューデータをrlistに格納
				ArrayList<Review> rlist = rdao.view(user.getUserId());

				//レビュー項目をrlistに追加
				for (Review r : rlist) {
					ridao.view(r.getReviewId(), r);
				}

				//レビュースコアをrlistに追加
				for (Review r: rlist) {
					rsdao.view(r.getReviewId(), r);
				}

				//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用ユーザーデータをulistに格納
//				ArrayList<User> ulist = udao.view(user.getUserId());

				//「すべて」選択時のレビューの検索結果の数を数える
				int sum1 = rlist.size();

				//Count.javaのreviewSearchCountに数えた数を格納
				co.setReviewSearchCount(sum1);

				//「すべて」選択時のユーザーの検索結果の数を数える
//				int sum2 = ulist.size();

				//Count.javaのuserSearchCountに数えた数を格納
//				co.setUserSearchCount(sum2);

				//「すべて」選択時かつ「検索ボタン」が押されていない時のレビューデータをスコープに格納
				request.setAttribute("rlist", rlist);

				//「すべて」選択時かつ「検索ボタン」が押されていない時のユーザーデータをスコープに格納
//				request.setAttribute("ulist", ulist);

				//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
				request.setAttribute("count", co);
			}

		//大カテゴリー、小カテゴリーの内容を取得
	    Categorys1DAO ca1dao = new Categorys1DAO();
	    ArrayList<Category> calist = ca1dao.AllSelectCategory();

	    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
	    request.setAttribute("calist", calist);
	    System.out.println(calist.size());

	    // 全体検索ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
	    dispatcher.forward(request, response);

	}

	}
}
