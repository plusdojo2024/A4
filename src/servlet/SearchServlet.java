package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Categorys1DAO;
import dao.Categorys2DAO;
import dao.ReviewsDAO;
import model.Category;
import model.Review;

@WebServlet("/SearchServlet")//ここを変える
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//あいまい検索の

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}


		// 検索ページにフォワードする
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


		//文字化け防止処理構文を記述
		request.setCharacterEncoding("UTF-8");

		//価格検索のテキストの内容を取得する文
		String search = request.getParameter("search");
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
		String stcreateA = request.getParameter("create_a");
		int createA = Integer.parseInt(stcreateA);
		String stcreateB = request.getParameter("create_b");
		int createB = Integer.parseInt(stcreateB);

		//リクエストのアトリビュート区画にセットする文
		ReviewsDAO dao = new ReviewsDAO();
		ArrayList<Review> list = dao.select(priceA,priceB,evaA,evaB,createA,createB);
		request.setAttribute("allSearchList", list);


		//Category1の内容を取得
		Categorys1DAO ca1Dao = new Categorys1DAO();
		List<Category> ca1List = ca1Dao.select();

		//Category2の内容を取得
		Categorys2DAO ca2Dao = new Categorys2DAO();
		List<Category> ca2List = ca2Dao.select();

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/search.jsp");
		dispatcher.forward(request, response);

	}

}
