package servlet;

import java.io.IOException;
import java.sql.Timestamp;
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
import dao.UsersDao;
import model.Category;
import model.Review;
import model.User;



@WebServlet("/MyReviewServlet")//ここを変える
public class MyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


//URLにID,PWに乗っちゃう　
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/A4/LoginServlet");
		//	return;
		//}
		
		//ログイン後の最初のページ,アプリのロゴをクリックしたとき（自分のレビューをすべて表示する）
		HttpSession session = request.getSession();
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
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/A4/LoginServlet");
		//	return;
		//}
		
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
			String UpDatedAt = request.getParameter("UpDatedAt");
			//String型からTimestamp型へ変換
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
			java.util.Date parsedDate = f.parse(UpDatedAt);
			Timestamp upDatedAt = new Timestamp(parsedDate.getTime());	
			
			ReviewsDAO rDao = new ReviewsDAO();
			int result1 = rDao.insert(category2Id,reviewName,reviewPrice,reviewComment,id);
			ReviewsImgsDAO riDAO = new ReviewsImgsDAO();
			int result2 =riDao.insert(reviewId, reviewImg);
			
			if (result == 1) {
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
			java.util.Date parsedDate = f.parse(UpDatedAt);
			Timestamp upDatedAt = new Timestamp(parsedDate.getTime());			
			
			
			ReviewsDAO rDao = new ReviewsDAO();
			int result = rDao.update(category2Id,reviewName, reviewPrice, reviewComment, privacyFlg, upDatedAt, reviewId);
			
			if (result == 1) {
				request.setAttribute("result", "更新しました。");
			} else {
				request.setAttribute("result", "更新できませんでした");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//自分の公開非公開を変更
		else if(request.getParameter("submit").equals("")) {
			UsersDao uDao = new UsersDao();
			int id = Integer.parseInt(request.getParameter("user_id"));
			int privacyFlg = Integer.parseInt(request.getParameter("privacyFlg"));
			
			uDao.priUpdate(privacyFlg,id);
			request.setAttribute("result", "");//スライドするだけどうする

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		
		//自分の名前を変更
		else if(request.getParameter("submit").equals("名前を更新")) {
			UsersDao uDao = new UsersDao();//入力したら更新されるシステム
			
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("user_id"));
			String userName = request.getParameter("userName");
			
			int num = uDao.nameUpdate(userName, id);
			
			if (num == 1) {
				request.setAttribute("result", "登録しました。");
			} else {
				request.setAttribute("result", "登録できませんでした");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		
		//自分の画像を変更
		else if(request.getParameter("submit").equals("")) {
			UsersDao uDao = new UsersDao();
			int id = Integer.parseInt(request.getParameter("user_id"));
			String newIcom = request.getParameter("icon");
			uDao.iconUpdate(id, newIcom);
			request.setAttribute("result", "");//スライドするだけどうする

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
