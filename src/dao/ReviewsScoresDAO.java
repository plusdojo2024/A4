package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Review;


public class ReviewsScoresDAO {

	public int insert(int reviewId, int reviewItem1Score, int reviewItem2Score, int reviewItem3Score, int reviewItem4Score, int reviewItem5Score) {
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
			String sql = "INSERT INTO reviews_scores (review_id, review_item1_score, review_item2_score, review_item3_score, review_item4, review_item5, score_avg) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, reviewId);//
			pStmt.setInt(2, reviewItem1Score);
			pStmt.setInt(3, reviewItem2Score);
			pStmt.setInt(4, reviewItem3Score);
			pStmt.setInt(5, reviewItem4Score);
			pStmt.setInt(6, reviewItem5Score);
			pStmt.setDouble(7, scoreAvg);


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

	public int update(int reviewScoreId, int reviewId, int reviewItem1Score, int reviewItem2Score, int reviewItem3Score, int reviewItem4Score, int reviewItem5Score) {
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
			String sql = "UPDATE reviews_scores SET review_id = ?, review_item1_score=?, review_item2_score=?, review_item3_score=?, review_item4_score=?, review_item5_score=?, score_avg=? WHERE review_score_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItem1Score);
			pStmt.setInt(3, reviewItem2Score);
			pStmt.setInt(4, reviewItem3Score);
			pStmt.setInt(5, reviewItem4Score);
			pStmt.setInt(6, reviewItem5Score);
			pStmt.setDouble(7, scoreAvg);
			pStmt.setInt(8, reviewScoreId);


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

	public int delete(int reviewScoreId, int reviewId, int reviewItem1Score, int reviewItem2Score, int reviewItem3Score, int reviewItem4Score, int reviewItem5Score) {
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
			String sql = "UPDATE reviews_scores SET review_id = ?, review_item1_score=?, review_item2_score=?, review_item3_score=?, review_item4_score=?, review_item5_score=?, score_avg=? WHERE review_score_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);
			pStmt.setInt(2, reviewItem1Score);
			pStmt.setInt(3, reviewItem2Score);
			pStmt.setInt(4, reviewItem3Score);
			pStmt.setInt(5, reviewItem4Score);
			pStmt.setInt(6, reviewItem5Score);
			pStmt.setDouble(7, scoreAvg);
			pStmt.setInt(8, reviewScoreId);


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
			String sql = "SELECT review_score_id, review_item1_score, review_item2_score, review_item3_score, reveiw_item4_score, review_item5_score, score_avg FROM reviews_scores "
					+ "WHERE review_score_id = (select max(review_id = ?) from reviews_items)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, reviewId);

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				review.setReviewScoreId(rs.getInt("review_score_id"));
				review.setReviewItem1(rs.getString("review_item1_score"));
				review.setReviewItem1(rs.getString("review_item2_score"));
				review.setReviewItem1(rs.getString("review_item3_score"));
				review.setReviewItem1(rs.getString("review_item4_score"));
				review.setReviewItem1(rs.getString("review_item5_score"));
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
