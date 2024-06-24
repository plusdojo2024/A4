package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Review;

//userテーブルとやり取りをするクラス
public class ReviewsItemsDAO {

	public int insert(int category2Id, String reviewItem1, String reviewItem2, String reviewItem3, String reviewItem4, String reviewItem5) {
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
			String sql = "INSERT INTO reviews_items (category2_id, review_item1, review_item2, review_item3, review_item4, review_item5) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);//引数sqlにsetStringしてる
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

	public int update(int reviewItemId, String reviewItem1, String reviewItem2, String reviewItem3, String reviewItem4, String reviewItem5) {
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
			String sql = "UPDATE reviews_items SET review_item1=?, review_item2=?, review_item3=?, review_item4=?, review_item5=? WHERE review_item_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, reviewItem1);
			pStmt.setString(2, reviewItem2);
			pStmt.setString(3, reviewItem3);
			pStmt.setString(4, reviewItem4);
			pStmt.setString(5, reviewItem5);
			pStmt.setInt(6, reviewItemId);


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

	public Review viewItem1(int category2Id) {
		Review review = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item1 FROM reviews_items "
					+ "WHERE category2_id=? "
					+ "and "
					+ "reviews_items_id =(select  max(reviews_items_id) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem1(rs.getString("review_item1"));
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

		// 結果を返す
		return review;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public Review viewItem2(int category2Id) {
		Review review = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item2 FROM reviews_items "
					+ "WHERE category2_id=? "
					+ "and "
					+ "reviews_items_id =(select  max(reviews_items_id) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem2(rs.getString("review_item2"));
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

		// 結果を返す
		return review;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public Review viewItem3(int category2Id) {
		Review review = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item3 FROM reviews_items "
					+ "WHERE category2_id=? "
					+ "and "
					+ "reviews_items_id =(select  max(reviews_items_id) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem3(rs.getString("review_item3"));
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

		// 結果を返す
		return review;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public Review viewItem4(int category2Id) {
		Review review = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item4 FROM reviews_items "
					+ "WHERE category2_id=? "
					+ "and "
					+ "reviews_items_id =(select  max(reviews_items_id) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem4(rs.getString("review_item4"));
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

		// 結果を返す
		return review;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public Review viewItem5(int category2Id) {
		Review review = null;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT review_item_id, review_item5 FROM reviews_items "
					+ "WHERE category2_id=? "
					+ "and "
					+ "reviews_items_id =(select  max(reviews_items_id) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, category2Id);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItemId(rs.getInt("review_item_id"));
				review.setReviewItem5(rs.getString("review_item5"));
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

		// 結果を返す
		return review;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

}
