package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.Review;

public class ReviewsDAO{


	public int selectId() {
		int reviewId= 0;

		Connection conn = null;
		//準備②地図を書き込む白紙のメモを用意する	使い終わったら破棄しないといけないのでtryの外
		//準備③は省略

		try {
			Class.forName("org.h2.Driver");
			//準備④ドライバー(船乗り)を連れてくる　h2のJDBCドライバを読み込む

			String id="sa";
			String pw="";
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);
			//準備⑤地図を完成させ、通行証も同梱する　データベースに接続する

			//一番新しいところを選んできている
			String sql = "SELECT max(reviews_id) from reviews";

			//準備⑥必要なものリストを用意する　ページで検索欄に入れたものが？に入る

			PreparedStatement pStmt = conn.prepareStatement(sql);
			//準備⑦船を用意し、必要なものを乗っける

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();
			//⑨出向して一瞬で戻ってくる船

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				reviewId = rs.getInt(" max(reviews_id)");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			reviewId = 0;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			reviewId = 0;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					reviewId = 0;
				}
			}
		}

	// 結果を返す
		return reviewId;
	}

	//selectメソッド
	public ArrayList<Review> select(int userId,int category2Id ) {
		ArrayList<Review> list = new ArrayList<Review>();
		//準備①マイバッグ　BCというbeansしか入らないアレイリストを作った

		Connection conn = null;
		//準備②地図を書き込む白紙のメモを用意する	使い終わったら破棄しないといけないのでtryの外
		//準備③は省略

		try {
			Class.forName("org.h2.Driver");
			//準備④ドライバー(船乗り)を連れてくる　h2のJDBCドライバを読み込む

			String id="sa";
			String pw="";
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB",id,pw);
			//準備⑤地図を完成させ、通行証も同梱する　データベースに接続する
			//引数にH2に繋ぐためのアドレスを入れている(地図)＝simpleBCの部分が作るときで変わる、そのユーザのid,pw


			String sql = "SELECT reviews.review_id,reviews.category2_id,reviews.review_name,reviews.review_price,reviews.review_comment,"
					+ "reviews.privacy_flg,reviews.delete_flg,reviews.created_at,reviews.updated_at, "
					+ "reviews_imgs.review_img_id,reviews_imgs.review_img,reviews_imgs.delete_flg,reviews_imgs.created_at,reviews_imgs.updated_at,"
					+ "reviews_items.review_item_id,reviews_items.review_item1,reviews_items.review_item2,reviews_items.review_item3,reviews_items.review_item4,reviews_items.review_item5,"
					+ "reviews_items.created_at,reviews_items.updated_at,"
					+ "reviews_scores.review_score_id,reviews_scores.review_item_id,"
					+ "reviews_scores.review_item1_score,reviews_scores.review_item2_score,reviews_scores.review_item3_score,reviews_scores.review_item4_score,reviews_scores.review_item5_score,"
					+ "reviews_scores.score_avg,reviews_scores.created_at,reviews_scores.updated_at,"
					+ "backnumbers.backnumber_id,backnumbers.backnumber_content,backnumbers.delete_flg,backnumbers.created_at,backnumbers.updated_at,"
					+ "list_reviews.list_id,list_reviews.created_at,list_reviews.updated_at,"
					+ "users.user_email,users.user_password,users.user_name,users.user_img,users.privcy_flg,users.created_at,users.updated_at FROM reviews "
					+ "JOIN reviews_imgs ON reviews.review_id = reviews_imgs.review_id "
					+ "JOIN categorys2 ON reviews.category2_id = categorys2.category2_id "
					+ "JOIN reviews_items ON categorys2.category2_id = reviews_items.category2_id "
					+ "JOIN reviews_scores ON reviews_scores.review_id = reviews.review_id "
					+ "JOIN backnumbers ON backnumbers.review_id = reviews.review_id "
					+ "JOIN list_reviews ON list_reviews.review_id = reviews.review_id "
					+ "JOIN users ON users.user_id = reviews.user_id "
					+ " WHERE reviews.user_id = ? ";
					if(category2Id != 0) {
						sql += " and categorys2.category2_id = ? ";
					}

			//準備⑥必要なものリストを用意する　ページで検索欄に入れたものが？に入る
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//準備⑦船を用意し、必要なものを乗っける
			pStmt.setInt(1,userId );//引数sqlにsetStringしてる

			//準備⑧未完成だったSQL文の？のところに値を入れる
			if(category2Id != 0) {
				pStmt.setInt(2, category2Id);
			}



			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();
			//⑨出向して一瞬で戻ってくる船　いってらっしゃい
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				Review re = new Review();	//beansをrecordに入れる
				re.setUserId(rs.getInt("reviews.user_id"));


				list.add(re);	//検索表に新しい行を作成
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
		return list;		//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	//insertメソッド
	public int insert(int category2Id,String reviewName,int reviewPrice,String reviewComment,int userId) {
		Connection conn = null;
		int num=0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");	//h2コンソールのドライバクラス

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する） でもJOINしたらどうなるんですか
			String sql = "INSERT INTO reviews (review_name,review_price,review_comment,user_id)  VALUES ( ?, ?, ?, ?, ?, ?, ?)";	//追加するときのやつ
			PreparedStatement pStmt = conn.prepareStatement(sql);	//インジェクション攻撃対策のプリペアードステートメントに対応

			// SQL文を完成させる
			pStmt.setString(1,reviewName);
			pStmt.setInt(2,reviewPrice);
			pStmt.setString(3,reviewComment);
			pStmt.setInt(4,userId);

			//空白を未設定にするを全体に設定

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

	public int delete() {
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
			String sql = "UPDATE list SET delete_flg=1 WHERE review_id=selectId()";
			PreparedStatement pStmt = conn.prepareStatement(sql);

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


	public ArrayList<Review> view() {
		Review li = null;
		ArrayList<Review> al = new ArrayList<>();
		Connection conn = null;

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
					+ "reviews_scores.review_score_id,reviews_scores.review_item_id,"
					+ "reviews_scores.review_item1_score,reviews_scores.review_item2_score,reviews_scores.review_item3_score,reviews_scores.review_item4_score,reviews_scores.review_item5_score,"
					+ "reviews_scores.score_avg,reviews_scores.created_at,reviews_scores.updated_at,"
					+ "backnumbers.backnumber_id,backnumbers.backnumber_content,backnumbers.delete_flg,backnumbers.created_at,backnumbers.updated_at,"
					+ "list_reviews.list_id,list_reviews.created_at,list_reviews.updated_at,"
					+ "users.user_email,users.user_password,users.user_name,users.user_img,users.privcy_flg,users.created_at,users.updated_at FROM reviews "
					+ "JOIN reviews_imgs ON reviews.review_id = reviews_imgs.review_id "
					+ "JOIN categorys2 ON reviews.category2_id = categorys2.category2_id "
					+ "JOIN reviews_items ON categorys2.category2_id = reviews_items.category2_id "
					+ "JOIN reviews_scores ON reviews_scores.review_id = reviews.review_id "
					+ "JOIN backnumbers ON backnumbers.review_id = reviews.review_id "
					+ "JOIN list_reviews ON list_reviews.review_id = reviews.review_id "
					+ "JOIN users ON users.user_id = reviews.user_id ";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, 1);//引数sqlにsetStringしてる
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				li = new Review();
				li.setReviewId(rs.getInt("reviews.review_id"));
				li.setReviewName(rs.getString("reviews.category2_id"));
				li.setReviewName(rs.getString("reviews.review_name"));
				li.setReviewPrice(rs.getInt("reviews.review_price"));
				li.setReviewComment(rs.getString("reviews.review_comment"));
				li.setrPrivacyFlg(rs.getInt("reviews.privacy_flg"));
				li.setrDeleteFlg(rs.getInt("reviews.delete_flg"));
				li.setrCreatedAt(rs.getTimestamp("reviews.created_at"));
				li.setrUpdatedAt(rs.getTimestamp("reviews.updated_at"));
				li.setReviewImgId(rs.getInt("reviews_imgs.review_img_id"));
				li.setReviewImg(rs.getString("reviews_imgs.review_img"));
				li.setRimDeleteFlg(rs.getInt("reviews_imgs.delete_flg"));
				li.setRimCreatedAt(rs.getTimestamp("reviews_imgs.created_at"));
				li.setRimUpdatedAt(rs.getTimestamp("reviews_imgs.updated_at"));
				li.setReviewItemId(rs.getInt("reviews_item.review_item_id"));
				li.setReviewItem1(rs.getString("reviews_item.review_item1"));
				li.setReviewItem2(rs.getString("reviews_item.review_item2"));
				li.setReviewItem3(rs.getString("reviews_item.review_item3"));
				li.setReviewItem4(rs.getString("reviews_item.review_item4"));
				li.setReviewItem5(rs.getString("reviews_item.review_item5"));
				li.setRitCreatedAt(rs.getTimestamp("reviews_item.created_at"));
				li.setRitUpdatedAt(rs.getTimestamp("reviews_item.updated_at"));
				li.setReviewScoreId(rs.getInt("reviews_scores.review_score_id"));
				li.setReviewItem1Score(rs.getInt("reviews_scores.review_item1_score"));
				li.setReviewItem2Score(rs.getInt("reviews_scores.review_item2_score"));
				li.setReviewItem3Score(rs.getInt("reviews_scores.review_item3_score"));
				li.setReviewItem4Score(rs.getInt("reviews_scores.review_item4_score"));
				li.setReviewItem5Score(rs.getInt("reviews_scores.review_item5_score"));
				li.setScoreAvg(rs.getInt("reviews_scores.score_avg"));
				li.setRsCreatedAt(rs.getTimestamp("reviews_scores.created_at"));
				li.setRsUpdatedAt(rs.getTimestamp("reviews_scores.updated_at"));
				li.setBacknumberId(rs.getInt("backnumbers.backnumber_id"));
				li.setBacknumberContent(rs.getString("backnumbers.backnumber_content"));
				li.setbDeleteFlg(rs.getInt("backnumbers.delete_flg"));
				li.setbCreatedAt(rs.getTimestamp("backnumbers.created_at"));
				li.setbUpdatedAt(rs.getTimestamp("backnumbers.updated_at"));
				li.setListReviewId(rs.getInt("list_reviews.list_review_id"));
				li.setLrListId(rs.getInt("list_reviews.list_id"));
				li.setLrCreatedAt(rs.getTimestamp("list_reviews.created_at"));
				li.setLrUpdatedAt(rs.getTimestamp("list_reviews.updated_at"));
				li.setUserEmail(rs.getString("users.user_email"));
				li.setUserPassword(rs.getString("users.user_password"));
				li.setUserName(rs.getString("users.user_name"));
				li.setUserImg(rs.getString("users.user_img"));
				li.setuPrivcyFlg(rs.getInt("users.privcy_flg"));
				li.setuCreatedAt(rs.getTimestamp("users.created_at"));
				li.setuUpdatedAt(rs.getTimestamp("users.updated_at"));

				al.add(li);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			al = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			al = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					al = null;
				}
			}
		}

		// 結果を返す
		return al;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	public int update(int category2Id,String reviewName,int reviewPrice,String reviewComment,int privacyFlg,Timestamp updatedAt,int reviewId) {
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
			String sql = "UPDATE category2_id=? review_name=? review_price=? review_comment=? privacy_flg=? updated_at=? SET  WHERE review_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, category2Id);
			pStmt.setString(2, reviewName);
			pStmt.setInt(3, reviewPrice);
			pStmt.setString(4, reviewComment);
			pStmt.setInt(5, privacyFlg);
			pStmt.setTimestamp(6, updatedAt);
			pStmt.setInt(7, reviewId);

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

}

