package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDAO;

@WebServlet("/NewUserServlet")//ここを変える
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");

		//一つでも空白の項目があったら"すべての項目に入力してください。"と返す
		if(email.trim().equals("") || password.trim().equals("") || username.trim().equals("")) {
			request.setAttribute("message","すべての項目に入力してください。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_login.jsp");
			dispatcher.forward(request, response);


		} else {
			UsersDAO uDao = new UsersDAO();
			int result = uDao.insert(email, password, username);

			if (result == 1) {	// 登録成功
				request.setAttribute("message","登録しました。ログインしてください。");
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			} else {			// 登録失敗
				request.setAttribute("message", "何らかの問題が発生し、登録できませんでした。");
				// 結果ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/new_login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

}
