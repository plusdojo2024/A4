package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.User;

public class FollowsDao {
	//フォローしてる人のアイコン、なまえ、IDを持ってくる
	public ArrayList<User> followSelect(int myId) {			 //myIdにはログインしてるユーザーIDを入れてくる
		Connection conn = null;
		ArrayList<User> list = new ArrayList<>(); 

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
			pStmt.setInt(1, myId);							//上の?に取ってきた自分のidを入れる
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
				record.setUserId(rs.getInt("US.user_id"));
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
	public int follow(int myId, int yourId) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4Adb", "sa", "");

			String sql = "INSERT INTO Follows(user1_id,user2_id) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1,myId);
			pStmt.setInt(2,yourId);

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
	
	//フォローテーブルから削除する
	public int delete(int flgNum) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4Adb", "sa", "");

			String sql = "DELETE FROM Follows WHERE follow_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, flgNum);

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

}
