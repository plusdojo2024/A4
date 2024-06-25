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
import model.List;
import model.User;

@WebServlet("/ListServlet")//ここを変える
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			if (session.getAttribute("id") == null) {
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
		//セッションスコープのユーザーIDを引数として取得（方法は後で考える）
		ArrayList<List> list = ldao.view(user.getUserId());

	    //リスト数を数えてlistに格納
		for (List li : list) {
			lrdao.countList(li.getListId());
		}

	    request.setAttribute("list", list);


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
