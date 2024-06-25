package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Review;

//userテーブルとやり取りをするクラス
public class ReviewsItemsDAO {

	public int insert(int reviewId, String reviewItem1, String reviewItem2, String reviewItem3, String reviewItem4, String reviewItem5) {
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
			String sql = "INSERT INTO reviews_items (review_id, review_item1, review_item2, review_item3, review_item4, review_item5) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);//引数sqlにsetStringしてる
			pStmt.setString(2, reviewItem1);
			pStmt.setString(3, reviewItem2);
			pStmt.setString(4, reviewItem3);
			pStmt.setString(5, reviewItem4);
			pStmt.setString(6, reviewItem5);


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

	public int update(int reviewItemId, int reviewId, String reviewItem1, String reviewItem2, String reviewItem3, String reviewItem4, String reviewItem5) {
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
			String sql = "UPDATE reviews_items SET review_id = ?, review_item1=?, review_item2=?, review_item3=?, review_item4=?, review_item5=? WHERE review_item_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setString(2, reviewItem1);
			pStmt.setString(3, reviewItem2);
			pStmt.setString(4, reviewItem3);
			pStmt.setString(5, reviewItem4);
			pStmt.setString(6, reviewItem5);
			pStmt.setInt(7, reviewItemId);


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

	public int delete(int reviewItemId, int reviewId, String reviewItem1, String reviewItem2, String reviewItem3, String reviewItem4, String reviewItem5) {
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
			String sql = "UPDATE reviews_items SET review_id = ?, review_item1=?, review_item2=?, review_item3=?, review_item4=?, review_item5=? WHERE review_item_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setString(2, reviewItem1);
			pStmt.setString(3, reviewItem2);
			pStmt.setString(4, reviewItem3);
			pStmt.setString(5, reviewItem4);
			pStmt.setString(6, reviewItem5);
			pStmt.setInt(7, reviewItemId);


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

	//
	public void view(int reviewId, Review review) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item1, review_item2, review_item3, review_item4, review_item5 FROM reviews_items "
					+ "WHERE review_item_id = (select max(review_id = ?) from reviews_items)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem1(rs.getString("review_item1"));
				review.setReviewItem1(rs.getString("review_item2"));
				review.setReviewItem1(rs.getString("review_item3"));
				review.setReviewItem1(rs.getString("review_item4"));
				review.setReviewItem1(rs.getString("review_item5"));

			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			review = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			review = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					review = null;
				}
			}
		}


	}

}
