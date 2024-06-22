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

import dao.Categorys1DAO;
import dao.ReviewsDAO;
import dao.UsersDAO;
import model.Category;
import model.Count;
import model.Review;
import model.User;

@WebServlet("/SearchServlet")//ここを変える
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/A4/LoginServlet");
//			return;
//		}

		//ReviewsDAOをインスタンス化
		ReviewsDAO rdao = new ReviewsDAO();

		//UsersDAOをインスタンス化
		UsersDAO udao = new UsersDAO();

		//Count.javaをインスタンス化
		Count co = new Count();

		//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用レビューデータをrlistに格納
		ArrayList<Review> rlist = rdao.view();

		//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用ユーザーデータをulistに格納
		ArrayList<User> ulist = udao.view();

		//「すべて」選択時のレビューの検索結果の数を数える
		int sum1 = 0;
		for(Review r : rlist) {
			sum1+=1;
		}

		//Count.javaのreviewSearchCountに数えた数を格納
		co.setReviewSearchCount(sum1);

		//「すべて」選択時のユーザーの検索結果の数を数える
		int sum2 = 0;
		for(User u : ulist) {
			sum2+=1;
		}

		//Count.javaのuserSearchCountに数えた数を格納
		co.setUserSearchCount(sum2);

		//「すべて」選択時かつ「検索ボタン」が押されていない時のレビューデータをスコープに格納
		request.setAttribute("rlist", rlist);

		//「すべて」選択時かつ「検索ボタン」が押されていない時のユーザーデータをスコープに格納
		request.setAttribute("ulist", ulist);

		//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
		request.setAttribute("count", co);

		// 全体検索ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
	    dispatcher.forward(request, response);
	}

	//ここからdoPost。ここに追加していく。
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//			if (session.getAttribute("id") == null) {
//			response.sendRedirect("/A4/LoginServlet");
//			return;
//		}

		//ReviewsDAOをインスタンス化
		ReviewsDAO rdao = new ReviewsDAO();

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
				ArrayList<Review> rlist = rdao.wholeSearch2(category2Id, freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

				//検索結果のユーザーデータをulistに格納
				ArrayList<User> ulist = udao.search(freeWord);

				//レビューの検索結果の数を調べる
				int sum1 = 0;
				for(Review r : rlist) {
					sum1+=1;
				}

				//Count.javaのreviewSearchCountに数えたレビュー数を格納
				co.setReviewSearchCount(sum1);

				//ユーザーの検索結果の数を数える
				int sum2 = 0;
				for(User u : ulist) {
					sum2+=2;
				}

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
				ArrayList<Review> rlist = rdao.view3(category2Id);

				//すべてのユーザー情報をlistに格納
				ArrayList<User> ulist = udao.view();

				//選択したカテゴリーの検索結果のレビュー数を調べる
				int sum1 = 0;
				for(Review r : rlist) {
					sum1+=1;
				}

				//Count.javaのreviewSearchCountに数えたレビュー数を格納
				co.setReviewSearchCount(sum1);

				//すべてのユーザーの検索結果の数を数える
				int sum2 = 0;
				for(User u : ulist) {
					sum2+=2;
				}

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

				//「すべて」選択時の検索結果のレビューデータをrlistに格納
				ArrayList<Review> rlist = rdao.wholeSearch1(freeWord, priceA, priceB, evaA, evaB, createdA, createdB);

				//「すべて」選択時の検索結果のユーザーデータをulistに格納
				ArrayList<User> ulist = udao.search(freeWord);

				//「すべて」選択時のレビューの検索結果の数を数える
				int sum1 = 0;
				for(Review r : rlist) {
					sum1+=1;
				}

				//Count.javaのreviewSearchCountに数えた数を格納
				co.setReviewSearchCount(sum1);

				//「すべて」選択時のユーザーの検索結果の数を数える
				int sum2 = 0;
				for(User u : ulist) {
					sum2+=1;
				}

				//Count.javaのuserSearchCountに数えた数を格納
				co.setUserSearchCount(sum2);

				//「すべて」選択時におけるレビュー検索結果をスコープに格納
				request.setAttribute("rlist", rlist);

				//「すべて」選択時におけるユーザー検索結果をスコープに格納
				request.setAttribute("ulist", ulist);

				//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
				request.setAttribute("count", co);

			} else {
				//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用レビューデータをrlistに格納
				ArrayList<Review> rlist = rdao.view();

				//「すべて」選択時かつ「検索ボタン」が押されていない時の表示用ユーザーデータをulistに格納
				ArrayList<User> ulist = udao.view();

				//「すべて」選択時のレビューの検索結果の数を数える
				int sum1 = 0;
				for(Review r : rlist) {
					sum1+=1;
				}

				//Count.javaのreviewSearchCountに数えた数を格納
				co.setReviewSearchCount(sum1);

				//「すべて」選択時のユーザーの検索結果の数を数える
				int sum2 = 0;
				for(User u : ulist) {
					sum2+=1;
				}

				//Count.javaのuserSearchCountに数えた数を格納
				co.setUserSearchCount(sum2);

				//「すべて」選択時かつ「検索ボタン」が押されていない時のレビューデータをスコープに格納
				request.setAttribute("rlist", rlist);

				//「すべて」選択時かつ「検索ボタン」が押されていない時のユーザーデータをスコープに格納
				request.setAttribute("ulist", ulist);

				//カテゴリー選択時に置けるレビューの検索結果の数をスコープに格納
				request.setAttribute("count", co);
			}

		//大カテゴリー、小カテゴリーの内容を取得
	    Categorys1DAO ca1dao = new Categorys1DAO();
	    ArrayList<Category> calist = ca1dao.AllSelectCategory();

	    // 大カテゴリー、小カテゴリーのデータをリクエストスコープに格納する
	    request.setAttribute("calist", calist);

	    // 全体検索ページにフォワードする
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
	    dispatcher.forward(request, response);

	}
	}
}
