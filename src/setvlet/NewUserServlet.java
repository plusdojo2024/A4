package setvlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")//ここを変える
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/◯◯.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//JSPに処理を委譲
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/◯◯.jsp");
		dispatcher.forward(request, response);

	}

}
