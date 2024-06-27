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

import dao.ListDAO;
import dao.ListReviewsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import model.List;
import model.Review;
import model.User;

@WebServlet("/ListServlet")//ここを変える
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		//ログイン時に受け取ったユーザー情報を取得する
		User user = (User)session.getAttribute("user");

		//リストDAOをインスタンス化
		ListDAO ldao = new ListDAO();

		//リストレビューズDAOをインスタンス化
	    ListReviewsDAO lrdao = new ListReviewsDAO();

		//リスト項目の表示
		ArrayList<List> list = ldao.view(user.getUserId());

	    //リスト数を数えてlistに格納
		for (List li : list) {
			lrdao.countList(li);
		}
		for(List a:list) {
			System.out.println("確認用:"+a.getListCount());
		}

	    request.setAttribute("list", list);


		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			if (session.getAttribute("user") == null) {
			response.sendRedirect("/A4/LoginServlet");
			return;
		}

		//リストDAOをインスタンス化
		ListDAO ldao = new ListDAO();

		//リストレビューズDAOをインスタンス化
	    ListReviewsDAO lrdao = new ListReviewsDAO();

		//ログイン時に受け取ったユーザー情報を取得する
		User user = (User)session.getAttribute("user");

		//リスト項目DAOをインスタンス化
		ReviewsItemsDAO ridao = new ReviewsItemsDAO();

		//リストスコアDAOをインスタンス化
		ReviewsScoresDAO rsdao = new ReviewsScoresDAO();

		if (request.getParameter("submit") != null) {

			//リスト新規登録
			if (request.getParameter("submit").equals("新規リスト登録")) {
				String listName = request.getParameter("list_name");
				ldao.insert(listName, user.getUserId());
			}

		}


		//個々のリストを押したとき
		if (request.getParameter("list_id") != null) {

			//Ajaxをもちいてlist_idを取得する
			int listId = Integer.parseInt(request.getParameter("list_id"));
			ArrayList<Review> rlist = lrdao.view(listId);

			//レビュー項目をrlistに追加
			for (Review r : rlist) {
				ridao.view(r.getReviewId(), r);
			}

			//レビュースコアをrlistに追加
			for (Review r: rlist) {
				rsdao.view(r.getReviewId(), r);
			}

			//rlistをスコープに格納
			request.setAttribute("rlist", rlist);
		}


		//リスト項目の表示
		ArrayList<List> list = ldao.view(user.getUserId());

		//リスト数を数えてlistに格納
		for (List li : list) {
			lrdao.countList(li);
		}

		request.setAttribute("list", list);


		//↓マイレビューサーブレットと同じ


		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}

}
