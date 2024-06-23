package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ListDAO;
import dao.ListReviewsDAO;
import dao.ReviewsDAO;
import dao.ReviewsImgsDAO;
import dao.ReviewsItemsDAO;
import dao.ReviewsScoresDAO;
import model.List;
import model.Count;

@WebServlet("/ListServlet")//ここを変える
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//			if (session.getAttribute("id") == null) {
//			response.sendRedirect("/A4/LoginServlet");
//			return;
//		}

		//リストDAOをインスタンス化
		ListDAO ldao = new ListDAO();

		//リストレビューズDAOをインスタンス化
	    ListReviewsDAO lrdao = new ListReviewsDAO();

		//Count.javaをインスタンス化
		Count co = new Count();

		//リスト項目の表示
		//セッションスコープのユーザーIDを引数として取得（方法は後で考える）
		ArrayList<List> list = ldao.view(int userId);

	    //このリストIDはどのようにして受け取ればいい？
	    int sum = lrdao.countList(int listId);

	    //リストの数をCount.javaに格納
	    co.setListCount(sum);

	    request.setAttribute("list", list);
	    request.setAttribute("count", co);

		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//			if (session.getAttribute("id") == null) {
//			response.sendRedirect("/A4/LoginServlet");
//			return;
//		}

		//リスト新規登録
		if (request.getParameter("submit").equals("新規リスト登録")) {

		}

		//↓マイレビューサーブレットと同じ


		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/list.jsp");
		dispatcher.forward(request, response);
	}

}
