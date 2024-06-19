package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//userテーブルとやり取りをするクラス
public class UsersDao {

	public User UserLogin(String email , String password) {
		User user = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);//引数sqlにsetStringしてる
			pStmt.setString(2, password);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserId(rs.getInt("user_id"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			user = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			user = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					user = null;
				}
			}
		}

		// 結果を返す
		return user;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public boolean isLoginOK(String email, String password) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
			pStmt.setString(2,password);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("COUNT(*)") >= 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}
	
	//ユーザーネームを変更する
	public int update(User beams) {
		Connection conn = null;
		int num = 0;
		
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Users SET user_name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, beams.getUserName());
			
			// SQL文を実行する
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
}
