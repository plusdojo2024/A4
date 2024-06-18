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
	public List<User> selectAll() {
		Connection conn = null;
		ArrayList<User> follList = new ArrayList<User>(); 

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT US.user_id, US.user_name, US.user_img "
					+ "FROM Users AS US INNER JOIN Follows AS F "
					+ "ON F.user2Id = US.userId "
					+ "WHERE ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);//引数sqlにsetStringしてる
			pStmt.setString(2, password);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
				User record = new User();
				rs.setUserName(rs.getString("user_name"));
				rs.setUserEmail(rs.getString("user_email"));
				rs.setUserId(rs.getInt("user_id"));
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
	}
	
	//ユーザーネームから合ってるやつを探す
	public List<User> select(User search) {
		Connection conn = null;
		ArrayList<User> searchList = new ArrayList<User>(); 

		//クロちゃんが会員制の市場へ船で買いに行く指示を受けた
		try {
			// JDBCドライバを読み込む（船のドライバー）
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB", "sa", "");

			// SQL文を準備する（買い物メモ）（この構文を実行しなさい）
			String sql = "SELECT * FROM Users WHERE userName LIKE ? AND uPrivacyFlg = 1 ORDER BY userId";

			//PreparedStatementはデータベースにアクセスするためのオブジェクト（船）船に地図と買い物メモをのっけた
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる（買い物メモの？を埋める、数字は左から何番目）

			pStmt.setString(1, "%" + search.getUserName() + "%");

			ResultSet rs = pStmt.executeQuery();
			
			// 結果表をコレクションにコピーする

			while (rs.next()) { //next()一行づつ見ていく、次あるかなーあればtrue、なければfalseでループを抜ける
				User record = new User( //recordという枝豆のさや
				rs.getInt("userId"),
				rs.getString("userEmail"),
				rs.getString("userPassword"),
				rs.getString("userName"),
				rs.getString("userImg"),
				rs.getString("uCreatedAt"),
				rs.getString("uUpdatedAt")
				);
				searchList.add(record);
			} //アレイリスト名：cardの中に枝豆が入った状態
		}
		catch (SQLException e) {
			e.printStackTrace();
			searchList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			searchList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					searchList = null;
				}
			}
		}

		// 結果を返す、中華テーブルに置く
		return searchList;
	}
	//フォローテーブルに追加する
	public int insert(User in) {
		Connection conn = null;
		int num = 0;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4ADB", "sa", "");

			String sql = "INSERT INTO Follows VALUES (NULL, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1,in.getUser1Id());
			pStmt.setInt(2,in.getUser2Id());
			pStmt.setString(3,in.getfCreatedAt());
			pStmt.setString(4,in.getfUpdatedAt());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				num = 1;
			}
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
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/4ADB", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM Follows WHERE followId=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, number);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				num = 1;
			}
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
