package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reviews_imgsDao {

	public int insert(String reviewImg) {
		Connection conn = null;
		int num=0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");	//h2コンソールのドライバクラス

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する） でもJOINしたらどうなるんですか
			String sql = "INSERT INTO reviews VALUES (NULL, ReviewsDao.selectId(), ?, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";	//追加するときのやつ
			PreparedStatement pStmt = conn.prepareStatement(sql);	//インジェクション攻撃対策のプリペアードステートメントに対応

			// SQL文を完成させる
			pStmt.setString(1,reviewImg);

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

}
