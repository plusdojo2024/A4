package servlet;

import java.io.IOException;
import java.sql.Timestamp;
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
import dao.Categorys2DAO;
import dao.FollowsDao;
import dao.ListDAO;
import dao.ListReviewsDAO;
import dao.ReviewsDAO;
import dao.ReviewsImgsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import dao.UsersDao;

import model.Category;
import model.List;
import model.Review;
import model.User;


@WebServlet("/MyReviewServlet")//ここを変える
public class MyReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/A4/LoginServlet");
		//	return;
		//}
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//HttpSession session = request.getSession();
		//if (session.getAttribute("id") == null) {
		//	response.sendRedirect("/A4/LoginServlet");
		//	return;
		//}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		//ログイン後の最初のページ,アプリのロゴをクリックしたとき（自分のレビューをすべて表示する）
		if(request.getParameter("submit").equals("すべて")) {
			HttpSession session = request.getSession();
			int id = (int)session.getAttribute("id");
			
			ReviewsDAO rDao = new ReviewsDAO();
			Review pa = new Review(id);						//Review(id)のコンストラクタを作る
			ArrayList<Review> view1 = rDao.view1(id);
			
			request.setAttribute("list", view1);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		//自分のレビューのカテゴリーごとの表示
		else if(request.getParameter("submit").equals("カテゴリ")){
			HttpSession session = request.getSession();
			int id = (int)session.getAttribute("id");
			
			request.setCharacterEncoding("UTF-8");
			int category2Id = Integer.parseInt(request.getParameter("category2Id"));
			
			ReviewsDAO rDao = new ReviewsDAO();				
			ArrayList<Review> view2 = rDao.view2(id,category2Id);
			
			request.setAttribute("list", view2);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//自分がフォローしているユーザーを出す
		else if(request.getParameter("submit").equals("フォロー")) {
			HttpSession session = request.getSession();
			int id = (int)session.getAttribute("Id");
			
			FollowsDao fDao = new FollowsDao();
			User bc = new User(id);
			ArrayList<User> list = fDao.selectfoll(bc);
			
			request.setAttribute("list", list);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		
		//レビュー新規登録
		

		//レビュー削除
		else if(request.getParameter("submit").equals("削除")) {
			
			int reviewId = Integer.parseInt(request.getParameter("reviewId"));
			
			ReviewsDAO rDao = new ReviewsDAO();
			rDao.delete(reviewId);
			
			request.setAttribute("result", "削除しました");
			
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
			Timestamp UpDatedAt = request.getParameter("UpDatedAt");
			
			
			ReviewsDAO rDao = new ReviewsDAO();
			rDao.update(category2Id,reviewName, reviewPrice, reviewComment, privacyFlg, UpDatedAt, reviewId);
			
			if (rDao.return == 1) {
				request.setAttribute("result", "削除しました");
			} else {
				request.setAttribute("result", "削除できませんでした");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}

		//自分の公開非公開を表示
		else if(request.getParameter("submit").equals("公開非公開を更新")) {
			UsersDao uDao = new UsersDao();
			
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("user_id"));
			
			uDao.showPri(id);
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
			}
		}
		
		
		//自分の公開非公開を変更
		else if(request.getParameter("submit").equals("")) {
			UsersDao uDao = new UsersDao();
			int id = Integer.parseInt(request.getParameter("user_id"));
			int privacyFlg = Integer.parseInt(request.getParameter("privacyFlg"));
			
			uDao.priUpdate(privacyFlg,id);
			request.setAttribute("result", "削除できませんでした");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		
		//自分の名前を変更
		else if(request.getParameter("submit").equals("名前を更新")) {
			UsersDao uDao = new UsersDao();
			
			request.setCharacterEncoding("UTF-8");
			int id = Integer.parseInt(request.getParameter("user_id"));
			String userName = request.getParameter("userName");
			
			int num = uDao.nameUpdate(userName, id);
			
			if (num == 1) {

				ArrayList <User>  name = uDao.show(id);
				request.setAttribute("name", name);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
				dispatcher.forward(request, response);
			} 
		}
		
		//自分の画像を変更
		
		//カテゴリを押したとき、大カテゴリーが出る
		else if(request.getParameter("submit").equals("カテゴリー")) {
			//カテゴリーの名前
			Categorys1DAO c1DAO = new Categorys1DAO();
			c1DAO.select();
			List<Category> list  = c1DAO.select();
			
			request.setAttribute("List", list);
			
			//レビューの数
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		
		//大カテゴリーを押したとき、小カテゴリーが出る
		else if(request.getParameter("submit").equals("大カテゴリー")) {
			//カテゴリーの名前
			Categorys2DAO c2DAO = new Categorys2DAO();
			int category1Id = Integer.parseInt(request.getParameter("category1Id"));
			List<Category> list = c2DAO.select(category1Id);
			
			request.setAttribute("List", list);
			
			//レビューの数
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
			dispatcher.forward(request, response);
		}
		

		//レビュー登録の場合-------------
		ReviewsDao d1 = new ReviewsDao();4
		d1.insert(引数たくさん);
		int reviewsId = d1.selectId();

		ReviewsImgDao d2 = new ReviewsImgDao();3
		for(String gazo : gazoList) {
			d2.insert(reviewsId,gazo);
		}
		ReviewsItemDao d3 = new ReviewsItemDao();3
		d3.insert(reviewsId,xxxx);

		ReviewsScoresDao d4 = new ReviewsScoresDao();3
		d4.insert(reviewsId,引数);
		//-------------

		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}

}
