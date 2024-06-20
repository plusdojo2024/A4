package servlet;

import java.io.IOException;

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
import dao.SubReviewsDao;
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
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}
		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/my_review.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}
		
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		
		
		//値を取得

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
