package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ListReviewsDAO;
import dao.ReviewsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import model.Review;


@WebServlet("/ListReviewServlet")//ここを変える
public class ListReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		int listId = Integer.parseInt(request.getParameter("list_id"));

		//ListReviewsDAOをインスタンス化
		ReviewsDAO rdao = new ReviewsDAO();

		//リスト用のレビューデータを格納
		ArrayList<Review> list = rdao.viewList(listId);

		//ReviewItemsDAOをインスタンス化
		ReviewsItemsDAO ridao = new ReviewsItemsDAO();

		//ReviewScoresDAOをインスタンス化
		ReviewsScoresDAO rsdao  = new ReviewsScoresDAO();

		//レビュー項目をrlistに追加
		for (Review r : list) {
			ridao.view(r.getReviewId(), r);
		}

		//レビュースコアをrlistに追加
		for (Review r: list) {
			rsdao.view(r.getReviewId(), r);
		}


		//スコープに格納
		request.setAttribute("list", list);


		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list_review.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		int listId = Integer.parseInt(request.getParameter("list_id"));

		//ListReviewsDAOをインスタンス化
		ListReviewsDAO lrdao = new ListReviewsDAO();

		//ReviewsItemsDAOをインスタンス化
		ReviewsItemsDAO ridao = new ReviewsItemsDAO();

		//ReviewsScoresDAOをインスタンス化
		ReviewsScoresDAO rsdao = new ReviewsScoresDAO();

		//リスト用のレビューデータを格納
		ArrayList<Review> rlist = lrdao.view(listId);

		//レビュー項目をrlistに追加
		for (Review r : rlist) {
			ridao.view(r.getReviewId(), r);
		}

		//レビュースコアをrlistに追加
		for (Review r: rlist) {
			rsdao.view(r.getReviewId(), r);
		}

		//スコープに格納
		request.setAttribute("rlist", rlist);


		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list_review.jsp");
		dispatcher.forward(request, response);
	}

}
