package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.User;

@WebServlet("/LoginServlet")//ここを変える
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ユーザーが入力したメールアドレスとパスワードを取得
		request.setCharacterEncoding("UTF-8");
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");


		// UsersDao をインスタンス化
		UsersDAO uDao = new UsersDAO();
		boolean boo = uDao.isLoginOK(userEmail, userPassword);
		if (boo) {

			//ログインした時の情報をもとに、ユーザーのメールアドレス、ユーザーネーム、ユーザーIDを取得
			User u = uDao.UserLogin(userEmail , userPassword);
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			// メニューサーブレットにリダイレクトする
			response.sendRedirect("/A4/MyReviewServlet");

		} else {
	        String message = "ログインに失敗しました。";
	        request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
