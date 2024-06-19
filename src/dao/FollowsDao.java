//ユーザーID2に合致するユーザーのID,ネーム、アイコンを取ってくる
//フォローテーブルに追加
//フローテーブルから削除
//ユーザーネームがあってるやつを探す（あいまい検索）
 
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class FollowsDao {
	//フォローしてる人のアイコン、なまえ、IDを持ってくる、引数いらない
	public List<User> selectfoll(User foll) { //follにはログインしてるユーザーIDを入れる
		Connection conn = null;
		ArrayList<User> list = new ArrayList<User>(); 

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");


			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4db","sa","");

			// SELECT文を準備する
			String sql = "SELECT US.user_id, US.user_name, US.user_img "//Usersテーブルのuser_id、user_name、user_imgを持ってくる
					+ "FROM Users AS US INNER JOIN Follows AS F "		//UsersをUSに、FollowsをFに改名して内部結合
					+ "ON F.user2_id = US.user_id "						//Followsテーブルのuser2_idとUsersテーブルのuser_idが同じになるように内部結合
					+ "WHERE F.user1_id = ?";							//Followsのuser1_idが自分のidと一緒という条件
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, foll.getUser1Id());							//上の?に取ってきた自分のidを入れる

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				//コンストラクタを使った書き方
//				User record = new User( //recordという枝豆のさや
//				rs.getInt("US.user_id"),
//				rs.getString("US.user_name"),
//				rs.getString("US.user_img")
//				);
				//セッターを使った書き方
				User record = new User();
				record.setUser2Id(rs.getInt("US.user_id"));
				record.setUserName(rs.getString("US.user_name"));
				record.setUserImg(rs.getString("US.user_img"));
				
				list.add(record);			
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
	

	//フォローテーブルに追加する
	public int insert(User in) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4Adb", "sa", "");

			String sql = "INSERT INTO Follows(user1_id,user2_id) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1,in.getUser1Id());
			pStmt.setInt(2,in.getUser2Id());

			// 何個インサートできたか数える
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
	
	//フォローテーブルから削除する
	public int delete(int number) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4Adb", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM Follows WHERE follow_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, number);

			// 何個デリートできたか数える
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
