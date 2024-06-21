package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Review;


public class ReviewsScoresDAO {

	public int insert(int reviewId, int reviewItemId, int reviewItem1Score, int reviewItem2Score, int reviewItem3Score, int reviewItem4Score, int reviewItem5Score) {
		int num = 0;
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);
			//スコアの平均値を取得する
			int count = 0;
			if(reviewItem1Score!=0) {
				count++;
			}
			if(reviewItem2Score!=0) {
				count++;
			}
			if(reviewItem3Score!=0) {
				count++;
			}
			if(reviewItem4Score!=0) {
				count++;
			}
			if(reviewItem5Score!=0) {
				count++;
			}
			int sum = reviewItem1Score+reviewItem2Score+reviewItem3Score+reviewItem4Score+reviewItem5Score;
			//平均値
			double scoreAvg = (double)sum / count;
			
			// SELECT文を準備する
			String sql = "INSERT INTO reviews_scores (review_id, review_item_id, review_item1_score, review_item2_score, review_item3_score, review_item4, review_item5, double avg) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, reviewId);//
			pStmt.setInt(2, reviewItemId);
			pStmt.setInt(3, reviewItem1Score);
			pStmt.setInt(4, reviewItem2Score);
			pStmt.setInt(5, reviewItem3Score);
			pStmt.setInt(6, reviewItem4Score);
			pStmt.setInt(7, reviewItem5Score);
			pStmt.setDouble(8, scoreAvg);


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

	public int update(int reviewScoreId, int reviewItem1Score, int reviewItem2Score, int reviewItem3Score, int reviewItem4Score, int reviewItem5Score, int scoreAvg) {
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
			String sql = "UPDATE reviews_scores SET review_item1_score=?, review_item2_score=?, review_item3_score=?, review_item4_score=?, review_item5_score=?, score_avg=? WHERE review_score_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewItem1Score);
			pStmt.setInt(2, reviewItem2Score);
			pStmt.setInt(3, reviewItem3Score);
			pStmt.setInt(4, reviewItem4Score);
			pStmt.setInt(5, reviewItem5Score);
			pStmt.setInt(6, scoreAvg);
			pStmt.setInt(7, reviewScoreId);


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

	public Review viewScore1(int reviewId, int reviewItemId) {
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
			String sql = "SELECT review_item1_score FROM reviews_scores "
					+ "WHERE review_id=? "
					+ "and "
					+ "review_item_id=?"
					+ "and"
					+ "reviews_scores_id =(select  max(reviews_scores_id) from reviews_scores)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItemId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItem1Score(rs.getInt("review_item1_score"));
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

	public Review viewScore2(int reviewId, int reviewItemId) {
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
			String sql = "SELECT review_item2_score FROM reviews_scores "
					+ "WHERE review_id=? "
					+ "and "
					+ "review_item_id=?"
					+ "and"
					+ "reviews_scores_id =(select  max(reviews_scores_id) from reviews_scores)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItemId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItem2Score(rs.getInt("review_item2_score"));
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

	public Review viewScore3(int reviewId, int reviewItemId) {
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
			String sql = "SELECT review_item3_score FROM reviews_scores "
					+ "WHERE review_id=? "
					+ "and "
					+ "review_item_id=?"
					+ "and"
					+ "reviews_scores_id =(select  max(reviews_scores_id) from reviews_scores)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItemId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItem3Score(rs.getInt("review_item3_score"));
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

	public Review viewScore4(int reviewId, int reviewItemId) {
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
			String sql = "SELECT review_item4_score FROM reviews_scores "
					+ "WHERE review_id=? "
					+ "and "
					+ "review_item_id=?"
					+ "and"
					+ "reviews_scores_id =(select  max(reviews_scores_id) from reviews_scores)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItemId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItem4Score(rs.getInt("review_item4_score"));
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

	public Review viewScore5(int reviewId, int reviewItemId) {
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
			String sql = "SELECT review_item5_score FROM reviews_scores "
					+ "WHERE review_id=? "
					+ "and "
					+ "review_item_id=?"
					+ "and"
					+ "reviews_scores_id =(select  max(reviews_scores_id) from reviews_scores)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItemId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review = new Review();
				review.setReviewItem5Score(rs.getInt("review_item5_score"));
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
