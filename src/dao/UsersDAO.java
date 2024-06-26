package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

//userテーブルとやり取りをするクラス
public class UsersDAO {

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
			String sql = "SELECT * FROM users WHERE user_email = ? AND user_password = ?";
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

	//ログイン？
	public boolean isLoginOK(String email, String password) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM users WHERE user_email = ? AND user_password = ?";
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



	//ユーザーを新規追加
	public int insert(String newEmail, String newPassword, String newName) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			String sql = "INSERT INTO users (USER_EMAIL, USER_PASSWORD, USER_NAME, USER_IMG) VALUES (?, ?, ?,'/A4/img/defoicon.png')";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, newEmail);
			pStmt.setString(2, newPassword);
			pStmt.setString(3, newName);

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

	//ユーザーネームを出す
//	public ArrayList<User> showName(int myId) {
//		Connection conn = null;
//		ArrayList<User> list = new ArrayList<User>();
//		try {
//			Class.forName("org.h2.Driver");
//			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");
//
//			String sql = "SELECT user_name FROM Users WHERE user_id = ?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, myId);
//			ResultSet rs = pStmt.executeQuery();
//
//			// 結果表をコレクションにコピーする
//			while (rs.next()) {
//				//セッターを使った書き方
//				User record = new User();
//				record.setUserName(rs.getString("user_name"));
//				list.add(record);
//				}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				}
//				catch (SQLException e) {
//					e.printStackTrace();
//					list = null;
//				}
//			}
//		}
//		return list;
//	}
//
	//ユーザーネームを変更する
	public int nameUpdate(String myName, int myId) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			String sql = "UPDATE users SET user_name=? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, myName);
			pStmt.setInt(2, myId);

			num = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return num;
	}

	//自分のアイコンの画像を出す
//	public ArrayList<User> showIcon(int myId) {
//		Connection conn = null;
//		ArrayList<User> list = new ArrayList<User>();
//		try {
//			Class.forName("org.h2.Driver");
//			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");
//
//			String sql = "SELECT user_img FROM Users WHERE user_id = ?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, myId);
//			ResultSet rs = pStmt.executeQuery();
//
//			// 結果表をコレクションにコピーする
//			while (rs.next()) {
//				//セッターを使った書き方
//				User record = new User();
//				record.setUserImg(rs.getString("user_img"));
//				list.add(record);
//				}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				}
//				catch (SQLException e) {
//					e.printStackTrace();
//					list = null;
//				}
//			}
//		}
//		return list;
//	}

	//アイコンの変更
	public int iconUpdate(int myId, String newIcon) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			String sql = "UPDATE users SET user_img = ? WHERE user_id = ?" ;
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, newIcon);//newIconに画像の絶対パスを入れる
			pStmt.setInt(2, myId);

			num = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return num;
	}

	//公開非公開を出す
//	public ArrayList<User> showPri(int myId) {
//		Connection conn = null;
//		ArrayList<User> list = new ArrayList<User>();
//		try {
//			Class.forName("org.h2.Driver");
//			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");
//
//			String sql = "SELECT privacy_flg FROM Users WHERE user_id = ?";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
//			pStmt.setInt(1, myId);
//			ResultSet rs = pStmt.executeQuery();
//
//			// 結果表をコレクションにコピーする
//			while (rs.next()) {
//				//セッターを使った書き方
//				User record = new User();
//				record.setuPrivacyFlg(rs.getInt("privacy_flg"));
//				list.add(record);
//				}
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			list = null;
//		}
//		finally {
//			// データベースを切断
//			if (conn != null) {
//				try {
//					conn.close();
//				}
//				catch (SQLException e) {
//					e.printStackTrace();
//					list = null;
//				}
//			}
//		}
//		return list;
//	}

	//公開非公開の切り替え
	public int priUpdate(int myPri, int myId) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db", "sa", "");

			String sql = "UPDATE users SET privacy_flg = ? WHERE user_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, myPri);//myPriに0か1を入れてくる
			pStmt.setInt(2, myId);
			num = pStmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return num;
	}

	//すべてのユーザーのユーザーID、ユーザーネーム、アイコン画像を格納
	public ArrayList<User> view(int userId) {
		Connection conn = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");

			String sql = "SELECT user_id, user_name, user_img FROM users WHERE user_id = ? OR (privacy_flg = 1)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, userId);

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				//セッターを使った書き方
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserImg(rs.getString("user_img"));
				list.add(user);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}
		return list;
	}

	//フリーワード検索をしたときのユーザーID、ユーザーネーム、ユーザー画像を格納する
	public ArrayList<User> search(int userId, String freeWord) {
		Connection conn = null;
		ArrayList<User> list = new ArrayList<User>();
		try {
			Class.forName("org.h2.Driver");
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");

			String sql = "SELECT user_id, user_name, user_img FROM users WHERE user_name = ? AND user_id = ? OR (privacy_flg = 1)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%"+freeWord+"%");
			pStmt.setInt(2, userId);

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				//セッターを使った書き方
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserImg(rs.getString("user_img"));
				list.add(user);
				}
		}
		catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}
		return list;
	}
}
