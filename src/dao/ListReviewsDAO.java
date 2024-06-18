package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.List;

//userテーブルとやり取りをするクラス
public class ListReviewsDAO {

	public int insert(int listReviewsId) {
		int num = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "INSERT INTO List_Reviews VALUES (NULL, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, listReviewsId);//引数sqlにsetStringしてる

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			num = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return num;
	}

	public int delete(int listReviewId) {
		int num = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "DELETE FROM  WHERE list_review_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, listReviewId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			num = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return num;
	}

	public ArrayList<List> show() {
		List li = null;
		ArrayList<List> al = new ArrayList<>();
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT * FROM LIST_REVIEWS";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				li = new List();
				li.setReviewId(rs.getInt("review_id"));
				al.add(li);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			al = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			al = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					al = null;
				}
			}
		}

		// 結果を返す
		return al;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}
}
