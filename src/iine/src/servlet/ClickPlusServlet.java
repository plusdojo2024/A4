package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.DAO;

/**
 * Servlet implementation class ClickPlusServlet
 */
@WebServlet("/ClickPlusServlet")
public class ClickPlusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/iine_suru.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//決まりの文章、とりあえず書くと覚えておこう
		request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		//文字コードの指定（これがないとJSPで文字化けする）
        response.setContentType("text/html;charser=UTF-8");

        //いいねボタンが押されたときの処理
        if(request.getParameter("data1")!=null) {
			// 送信されたデータの取得
			String data1 = request.getParameter("data1");
			int num = Integer.parseInt(data1);
			//入力されたデータを表示
			System.out.println(data1);

			DAO dao = new DAO();
			boolean ans = dao.insert(num);

			//Jackson機能のmapperをインスタンス（実体）化
			ObjectMapper mapper = new ObjectMapper();
			try {
	            //JavaオブジェクトからJSONに変換
	            String testJson = mapper.writeValueAsString(ans);
	            System.out.println(testJson);
	            //JSONの出力
	            response.setContentType("application/json; charset=UTF-8");
	            response.getWriter().write(testJson);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
		//１秒毎にいいねの数を取得するメソッド
        }else {
        	DAO dao = new DAO();
			int count = dao.getCount();
			//Jackson機能のmapperをインスタンス（実体）化
			ObjectMapper mapper = new ObjectMapper();
			try {
	            //JavaオブジェクトからJSONに変換
	            String testJson = mapper.writeValueAsString(count);
	            System.out.println(testJson);
	            //JSONの出力
	            response.setContentType("application/json; charset=UTF-8");
	            response.getWriter().write(testJson);
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	        }
        }

	}

}
