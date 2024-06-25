package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.List;
import model.Review;

//userテーブルとやり取りをするクラス
public class ListReviewsDAO {

	public int insert(int listId, int reviewId) {
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
			String sql = "INSERT INTO list_reviews (list_id, review_id) VALUES (?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, listId);//引数sqlにsetStringしてる
			pStmt.setInt(2, reviewId);

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
			String sql = "DELETE FROM WHERE list_review_id=?";
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

	//リストの数を数える（リスト項目表示画面のリスト数を表示するために使用する）
	public void countList(List list) {
		Connection conn = null;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) list_review_id FROM list_reviews WHERE list_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, list.getListId());

			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				list.setListCount(rs.getInt("COUNT(*)"));
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

	}

	//リスト内でのレビュー表示
	public ArrayList<Review> view(int listId) {

		Connection conn = null;
		ArrayList<Review> list = new ArrayList<>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			String id="sa";
			String pw="";

			// データベースに接続する
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);

			// SELECT文を準備する
			String sql = "SELECT reviews.review_id,reviews.category2_id,reviews.review_name,reviews.review_price,reviews.review_comment,"
					+ "reviews.privacy_flg,reviews.delete_flg,reviews.created_at,reviews.updated_at, "
					+ "reviews_imgs.review_img_id,reviews_imgs.review_img,reviews_imgs.delete_flg,reviews_imgs.created_at,reviews_imgs.updated_at,"
					+ "reviews_items.review_item_id,reviews_items.review_item1,reviews_items.review_item2,reviews_items.review_item3,reviews_items.review_item4,reviews_items.review_item5,"
					+ "reviews_items.created_at,reviews_items.updated_at,"
					+ "reviews_scores.review_score_id,"
					+ "reviews_scores.review_item1_score,reviews_scores.review_item2_score,reviews_scores.review_item3_score,reviews_scores.review_item4_score,reviews_scores.review_item5_score,"
					+ "reviews_scores.score_avg,reviews_scores.created_at,reviews_scores.updated_at,"
					+ "backnumbers.backnumber_id,backnumbers.backnumber_content,backnumbers.created_at,backnumbers.updated_at,"
					+ "list_reviews.list_review_id,list_reviews.list_id,list_reviews.created_at,list_reviews.updated_at,"
					+ "users.user_email,users.user_password,users.user_id,users.user_name,users.user_img,users.privacy_flg,users.created_at,users.updated_at,"
					+ "list.list_name FROM reviews "
					+ "LEFT OUTER JOIN reviews_imgs ON reviews.review_id = reviews_imgs.review_id "
					+ "LEFT OUTER JOIN categorys2 ON reviews.category2_id = categorys2.category2_id "
					+ "LEFT OUTER JOIN reviews_items ON categorys2.category2_id = reviews_items.category2_id "
					+ "LEFT OUTER JOIN reviews_scores ON reviews_scores.review_id = reviews.review_id "
					+ "LEFT OUTER JOIN backnumbers ON backnumbers.review_id = reviews.review_id "
					+ "LEFT OUTER JOIN list_reviews ON list_reviews.review_id = reviews.review_id "
					+ "LEFT OUTER JOIN users ON users.user_id = reviews.user_id "
					+ "LEFT OUTER JOIN list ON list.list_id = list_reviews.list_id "
					+ "WHERE reviews.delete_flg = 1"
			        + "AND list.list_id = ?"
			        + "ORDER BY list_reviews.list ASC";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, listId);
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				Review review = new Review();
				review.setReviewId(rs.getInt("reviews.review_id"));
				review.setCategory2Id(rs.getInt("categorys2.category2_id"));
				review.setReviewName(rs.getString("reviews.review_name"));
				review.setReviewPrice(rs.getInt("reviews.review_price"));
				review.setReviewComment(rs.getString("reviews.review_comment"));
				review.setrPrivacyFlg(rs.getInt("reviews.privacy_flg"));
				review.setrDeleteFlg(rs.getInt("reviews.delete_flg"));
				review.setrCreatedAt(rs.getTimestamp("reviews.created_at"));
				review.setrUpdatedAt(rs.getTimestamp("reviews.updated_at"));
				review.setReviewImgId(rs.getInt("reviews_imgs.review_img_id"));
				review.setReviewImg(rs.getString("reviews_imgs.review_img"));
				review.setRimDeleteFlg(rs.getInt("reviews_imgs.delete_flg"));
				review.setRimCreatedAt(rs.getTimestamp("reviews_imgs.created_at"));
				review.setRimUpdatedAt(rs.getTimestamp("reviews_imgs.updated_at"));
				review.setReviewItemId(rs.getInt("reviews_items.review_item_id"));
				review.setReviewItem1(rs.getString("reviews_items.review_item1"));
				review.setReviewItem2(rs.getString("reviews_items.review_item2"));
				review.setReviewItem3(rs.getString("reviews_items.review_item3"));
				review.setReviewItem4(rs.getString("reviews_items.review_item4"));
				review.setReviewItem5(rs.getString("reviews_items.review_item5"));
				review.setRitCreatedAt(rs.getTimestamp("reviews_items.created_at"));
				review.setRitUpdatedAt(rs.getTimestamp("reviews_items.updated_at"));
				review.setReviewScoreId(rs.getInt("reviews_scores.review_score_id"));
				review.setReviewItem1Score(rs.getInt("reviews_scores.review_item1_score"));
				review.setReviewItem2Score(rs.getInt("reviews_scores.review_item2_score"));
				review.setReviewItem3Score(rs.getInt("reviews_scores.review_item3_score"));
				review.setReviewItem4Score(rs.getInt("reviews_scores.review_item4_score"));
				review.setReviewItem5Score(rs.getInt("reviews_scores.review_item5_score"));
				review.setScoreAvg(rs.getInt("reviews_scores.score_avg"));
				review.setRsCreatedAt(rs.getTimestamp("reviews_scores.created_at"));
				review.setRsUpdatedAt(rs.getTimestamp("reviews_scores.updated_at"));
				review.setBacknumberId(rs.getInt("backnumbers.backnumber_id"));
				review.setBacknumberContent(rs.getString("backnumbers.backnumber_content"));
				review.setbCreatedAt(rs.getTimestamp("backnumbers.created_at"));
				review.setbUpdatedAt(rs.getTimestamp("backnumbers.updated_at"));
				review.setLrListId(rs.getInt("list_reviews.list_id"));
				review.setLrCreatedAt(rs.getTimestamp("list_reviews.created_at"));
				review.setLrUpdatedAt(rs.getTimestamp("list_reviews.updated_at"));
				review.setUserEmail(rs.getString("users.user_email"));
				review.setUserPassword(rs.getString("users.user_password"));
				review.setUserName(rs.getString("users.user_id"));
				review.setUserName(rs.getString("users.user_name"));
				review.setUserImg(rs.getString("users.user_img"));
				review.setuPrivacyFlg(rs.getInt("users.privacy_flg"));
				review.setuCreatedAt(rs.getTimestamp("users.created_at"));
				review.setuUpdatedAt(rs.getTimestamp("users.updated_at"));
				list.add(review);
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

		// 結果を返す
		return list;
	}
}
