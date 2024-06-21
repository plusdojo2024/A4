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

import dao.BacknumbersDAO;
import dao.Categorys1DAO;
import dao.FollowsDao;
import dao.ReviewsDAO;
import dao.ReviewsImgsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import dao.UsersDao;
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
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}
		
		//ログイン後の最初のページ,アプリのロゴをクリックしたとき（自分のレビューをすべて表示する）
		int id = (int)session.getAttribute("id");
		
		//すべてのレビューを持ってくる
		ReviewsDAO rDao = new ReviewsDAO();
		ArrayList<Review> view1 = rDao.view1(id);
		//大カテゴリー、小カテゴリーも一緒に持ってくる
		Categorys1DAO dao = new Categorys1DAO();
		ArrayList<Category> categoryList = (ArrayList<Category>)dao.AllSelectCategory();
		//フォローしてる人を取ってくる
		FollowsDao fDao = new FollowsDao();
		ArrayList<User> fUserList = fDao.followSelect(id);
		
		request.setAttribute("fUserList", fUserList);
		
		
		request.setAttribute("list", view1);
		request.setAttribute("categoryList", categoryList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}
	//URLにID,PWに乗らない　チラ見対策
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		
		//レビュー新規登録
		if (request.getParameter("submit").equals("新規登録")) {
			int id = Integer.parseInt(request.getParameter("user_id"));
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			int category2Id = Integer.parseInt(request.getParameter("category2Id"));
			String reviewName = request.getParameter("reviewName");
			int reviewPrice = Integer.parseInt(request.getParameter("reviewPrice"));
			String reviewComment = request.getParameter("reviewComment");
			int privacyFlg = Integer.parseInt(request.getParameter("privacyFlg"));
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
			
			int reviewItemId = Integer.parseInt(request.getParameter("reviewItemId"));
			int reviewItem1Score = Integer.parseInt(request.getParameter("reviewItem1Score"));
			int reviewItem2Score = Integer.parseInt(request.getParameter("reviewItem2Score"));
			int reviewItem3Score = Integer.parseInt(request.getParameter("reviewItem3Score"));
			int reviewItem4Score = Integer.parseInt(request.getParameter("reviewItem4Score"));
			int reviewItem5Score = Integer.parseInt(request.getParameter("reviewItem5Score"));
			
			ReviewsDAO rDao = new ReviewsDAO();
			int result1 = rDao.insert(category2Id,reviewName,reviewPrice,reviewComment,id,privacyFlg);
			ReviewsImgsDAO rimgsDao = new ReviewsImgsDAO();
			int result2 = rimgsDao.insert(reviewId, reviewImg);
			ReviewsItemsDAO ritemDao = new ReviewsItemsDAO();
			int result3 = ritemDao.insert(category2Id, reviewItem1, reviewItem2, reviewItem3, reviewItem4, reviewItem5); 
			ReviewsScoresDAO rSDao = new ReviewsScoresDAO();
			int result4 = rSDao.insert(reviewId, reviewItemId, reviewItem1Score, reviewItem2Score, reviewItem3Score, reviewItem4Score, reviewItem5Score);
					
			if (result1 == 1 &&result2 == 1 &&result3 == 1 &&result4 == 1) {
				request.setAttribute("result", "登録しました。");
			} else {
				request.setAttribute("result", "登録できませんでした");
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
			
			
		//レビュー削除
		else if(request.getParameter("submit").equals("削除")) {
			
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			
			ReviewsDAO rDao = new ReviewsDAO();
			int result = rDao.delete(reviewId);
			
			if (result == 1) {
				request.setAttribute("result", "削除しました。");
			} else {
				request.setAttribute("result", "削除できませんでした");
			}
			
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
			int privacyFlg = Integer.parseInt(request.getParameter("privacyFlg"));
			String UpDatedAt = request.getParameter("UpDatedAt");
			//String型からTimestamp型へ変換
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
			java.util.Date parsedDate=null;
			try {
				parsedDate = f.parse(UpDatedAt);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			Timestamp upDatedAt = new Timestamp(parsedDate.getTime());
			
			String reviewImg = request.getParameter("reviewImg");
			
			String reviewItem1 = request.getParameter("reviewItem1");
			String reviewItem2 = request.getParameter("reviewItem2");
			String reviewItem3 = request.getParameter("reviewItem3");
			String reviewItem4 = request.getParameter("reviewItem4");
			String reviewItem5 = request.getParameter("reviewItem5");
			
			int reviewItemId = Integer.parseInt(request.getParameter("reviewItemId"));
			int reviewItem1Score = Integer.parseInt(request.getParameter("reviewItem1Score"));
			int reviewItem2Score = Integer.parseInt(request.getParameter("reviewItem2Score"));
			int reviewItem3Score = Integer.parseInt(request.getParameter("reviewItem3Score"));
			int reviewItem4Score = Integer.parseInt(request.getParameter("reviewItem4Score"));
			int reviewItem5Score = Integer.parseInt(request.getParameter("reviewItem5Score"));

			String backnumberContent = request.getParameter("backnumberContent");
			int backnumberId = Integer.parseInt(request.getParameter("backnumberId"));
			
			ReviewsDAO rDao = new ReviewsDAO();
			int result1 = rDao.update(category2Id,reviewName, reviewPrice, reviewComment, privacyFlg, upDatedAt, reviewId);
			ReviewsImgsDAO rimgsDao = new ReviewsImgsDAO();
			int result2 = rimgsDao.insert(reviewId, reviewImg);
			ReviewsItemsDAO ritemDao = new ReviewsItemsDAO();
			int result3 = ritemDao.insert(category2Id, reviewItem1, reviewItem2, reviewItem3, reviewItem4, reviewItem5); 
			ReviewsScoresDAO rSDao = new ReviewsScoresDAO();
			int result4 = rSDao.insert(reviewId, reviewItemId, reviewItem1Score, reviewItem2Score, reviewItem3Score, reviewItem4Score, reviewItem5Score);
			BacknumbersDAO bDao = new BacknumbersDAO();
			int result5 = bDao.insert(backnumberContent);
			int result6 = bDao.delete(backnumberId);
			int result7 = bDao.update(backnumberId, backnumberContent);
			
			
			if (result1 == 1 &&result2 == 1 &&result3 == 1 &&result4 == 1 &&result5 == 1 &&result6 == 1 &&result7 == 1) {
				request.setAttribute("result", "更新しました。");
			} else {
				request.setAttribute("result", "更新できませんでした");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//自分のアイコン、名前、公開非公開を変更(※Ajaxで値を送る仕掛けが必要)
		else if(request.getParameter("hen")!=null) {
			request.setCharacterEncoding("UTF-8");
			UsersDao uDao = new UsersDao();
			//公開非公開の変更--------------------
			int id = Integer.parseInt(request.getParameter("user_id"));
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
			//------------------
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

		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}

}
