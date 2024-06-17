package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bc;

public class Reviews_scoresDao {
	// 引数で検索項目を指定し、検索結果のリストを返す

	public List<Bc> select(Bc card) {	//自分が検索欄で入力したものがcardに入る
		ArrayList<Bc> cardList = new ArrayList<Bc>();
		//準備①マイバッグ　BCというbeansしか入らないアレイリストを作った

		Connection conn = null;
		//準備②地図を書き込む白紙のメモを用意する	使い終わったら破棄しないといけないのでtryの外
		//準備③は省略

		try {
			Class.forName("org.h2.Driver");
			//準備④ドライバー(船乗り)を連れてくる　h2のJDBCドライバを読み込む

			String id="sa";
			String pw="";
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC",id,pw);
//			↑を書き換えるとconn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");
			//準備⑤地図を完成させ、通行証も同梱する　データベースに接続する
			//引数にH2に繋ぐためのアドレスを入れている(地図)＝simpleBCの部分が作るときで変わる、そのユーザのid,pw


			String sql = "SELECT * FROM Bc WHERE company LIKE ? AND department LIKE ? AND position LIKE ? AND  "
					+ "name  LIKE ? AND hurigana LIKE ? AND  address LIKE ? AND remarks LIKE ? ORDER BY number";
			//準備⑥必要なものリストを用意する　ページで検索欄に入れたものが？に入る
			// 名前検索のSQL文を準備する★住所、会社名、備考、全体検索も　(必要なものリスト)

			PreparedStatement pStmt = conn.prepareStatement(sql);
			//準備⑦船を用意し、必要なものを乗っける
			//pStmt(船) conn(地図,id,pw),sql(持ってくるものリスト)
			//データベースにアクセスするためにオブジェクトが入ってる
			//？を埋めないと出発できない(検索で入れる)

			pStmt.setString(1, "%" + card.getCompany() + "%");//引数sqlにsetStringしてる
			//準備⑧未完成だったSQL文の？のところに値を入れる
			//1番目のnamedeで検索　両端任意の文字列０文字以上
			//１→？の一番目　getNameに検索したいものが入る
			pStmt.setString(2, "%" + card.getDepartment() + "%");
			pStmt.setString(3, "%" + card.getPosition() + "%");
			pStmt.setString(4, "%" + card.getName() + "%");
			pStmt.setString(5, "%" + card.getHurigana() + "%");
			pStmt.setString(6, "%" + card.getAddress() + "%");
			pStmt.setString(7, "%" + card.getRemarks() + "%");


			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();
			//⑨出向して一瞬で戻ってくる船　いってらっしゃい～
			//なんでも入れていい箱ResultSet(DAOの中でしか入れられない)
			//検索結果の表をrsに入れている
			//上をアレイリストに入れないといけない

			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				Bc record = new Bc(	//beansをrecordに入れる
				rs.getInt("number"),	//今フォーカスがあたっている行のnumberを探す
				rs.getString("company"),
				rs.getString("department"),
				rs.getString("position"),
				rs.getString("name"),
				rs.getString("hurigana"),
				rs.getString("zipcode"),
				rs.getString("address"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getString("remarks")
				);
				cardList.add(record);	//検索表に新しい行を作成
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;		//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	//追加メソッド　全体検索-----------------------------------------------
	public List<Bc> selectAll(String all) {	//自分が検索欄で入力したものがcardに入る
		ArrayList<Bc> cardList = new ArrayList<Bc>();
		//準備①マイバッグ　BCというbeansしか入らないアレイリストを作った

		Connection conn = null;
		//準備②地図を書き込む白紙のメモを用意する	使い終わったら破棄しないといけないのでtryの外
		//準備③は省略

		try {
			Class.forName("org.h2.Driver");
			//準備④ドライバー(船乗り)を連れてくる　h2のJDBCドライバを読み込む

			String id="sa";
			String pw="";
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC",id,pw);
//			↑を書き換えるとconn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");
			//準備⑤地図を完成させ、通行証も同梱する　データベースに接続する
			//引数にH2に繋ぐためのアドレスを入れている(地図)＝simpleBCの部分が作るときで変わる、そのユーザのid,pw


			String sql = "SELECT * FROM Bc WHERE company LIKE ? OR department LIKE ? OR position LIKE ? OR  "
					+ "name  LIKE ? OR hurigana LIKE ? OR  address LIKE ? OR remarks LIKE ? ORDER BY number";
			//準備⑥必要なものリストを用意する　ページで検索欄に入れたものが？に入る
			// 名前検索のSQL文を準備する★住所、会社名、備考、全体検索も　(必要なものリスト)

			PreparedStatement pStmt = conn.prepareStatement(sql);
			//準備⑦船を用意し、必要なものを乗っける
			//pStmt(船) conn(地図,id,pw),sql(持ってくるものリスト)
			//データベースにアクセスするためにオブジェクトが入ってる
			//？を埋めないと出発できない(検索で入れる)

			pStmt.setString(1, "%" + all+ "%");//引数sqlにsetStringしてる
			//準備⑧未完成だったSQL文の？のところに値を入れる
			//1番目のnamedeで検索　両端任意の文字列０文字以上
			//１→？の一番目　getNameに検索したいものが入る
			pStmt.setString(2, "%" + all + "%");
			pStmt.setString(3, "%" + all + "%");
			pStmt.setString(4, "%" + all + "%");
			pStmt.setString(5, "%" + all + "%");
			pStmt.setString(6, "%" + all + "%");
			pStmt.setString(7, "%" + all + "%");


			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();
			//⑨出向して一瞬で戻ってくる船　いってらっしゃい～
			//なんでも入れていい箱ResultSet(DAOの中でしか入れられない)
			//検索結果の表をrsに入れている
			//上をアレイリストに入れないといけない

			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				Bc record = new Bc(	//beansをrecordに入れる
				rs.getInt("number"),	//今フォーカスがあたっている行のnumberを探す
				rs.getString("company"),
				rs.getString("department"),
				rs.getString("position"),
				rs.getString("name"),
				rs.getString("hurigana"),
				rs.getString("zipcode"),
				rs.getString("address"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getString("remarks")
				);
				cardList.add(record);	//検索表に新しい行を作成
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;		//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}

	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");	//h2コンソールのドライバクラス

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Bc VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";	//追加するときのやつ
			PreparedStatement pStmt = conn.prepareStatement(sql);	//インジェクション攻撃対策のプリペアードステートメントに対応

			// SQL文を完成させる
			pStmt.setString(1,card.getCompany());//引数sqlにsetStringしてる
			//準備⑧未完成だったSQL文の？のところに値を入れる
			//1番目のnamedeで検索　両端任意の文字列０文字以上
			//１→？の一番目　getNameに検索したいものが入る
			pStmt.setString(2,card.getDepartment());
			pStmt.setString(3,card.getPosition());
			pStmt.setString(4,card.getName());
			pStmt.setString(5,card.getHurigana());
			pStmt.setString(6,card.getZipcode());
			pStmt.setString(7,card.getAddress());
			pStmt.setString(8,card.getPhone());
			pStmt.setString(9,card.getEmail());
			pStmt.setString(10,card.getRemarks());

			//空白を未設定にするを全体に設定

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				//executeUpdate=データベースの更新や削除などの操作を行うためのメソッド
				result = true;
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
		return result;
	}

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Bc card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE Bc SET company=?,department=?,position=?,name=?,hurigana=?,zipcode=?,"
					+ "address=?,phone=?,email=?,remarks=? WHERE number=?";
			//Bcテーブルのデータ書き換え　条件number
			System.out.println(card.getNumber()+"←こいつはナンバー");
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			//nullか空白のときにnullを入れてる	でもBcで未記入いれたからnullだけでよくない
			//そもそも名前入れてないのＯＫはダメじゃない

				//！は論理否定もある　trueの場合にfalse、falseの場合にtrue
				//equalsはStringの比較
				//cardのgetName()メソッドが０じゃない場合　の論理否定
				System.out.println(card.getCompany()+"こいつはカードのカンパニ");
				pStmt.setString(1,card.getCompany());//引数sqlにsetStringしてる
				//準備⑧未完成だったSQL文の？のところに値を入れる
				//1番目のnamedeで検索　両端任意の文字列０文字以上
				//１→？の一番目　getNameに検索したいものが入る
				pStmt.setString(2,card.getDepartment());
				pStmt.setString(3,card.getPosition());
				pStmt.setString(4,card.getName());
				pStmt.setString(5,card.getHurigana());
				pStmt.setString(6,card.getZipcode());
				pStmt.setString(7,card.getAddress());
				pStmt.setString(8,card.getPhone());
				pStmt.setString(9,card.getEmail());
				pStmt.setString(10,card.getRemarks());
				pStmt.setInt(11,card.getNumber());	//どの列の情報を書き換えるのか

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}else {
				System.out.println("失敗");
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
		return result;
	}

	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int number) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SQL文を準備する
			String sql = "DELETE FROM Bc WHERE number=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, number);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
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
		return result;
	}
	//ナンバーで検索するメソッド
	public List<Bc> selectNum(int number) {	//自分が検索欄で入力したものがcardに入る
		ArrayList<Bc> cardList = new ArrayList<Bc>();
		//準備①マイバッグ　BCというbeansしか入らないアレイリストを作った

		Connection conn = null;
		//準備②地図を書き込む白紙のメモを用意する	使い終わったら破棄しないといけないのでtryの外
		//準備③は省略

		try {
			Class.forName("org.h2.Driver");
			//準備④ドライバー(船乗り)を連れてくる　h2のJDBCドライバを読み込む

			String id="sa";
			String pw="";
			conn=DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC",id,pw);
//			↑を書き換えるとconn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");
			//準備⑤地図を完成させ、通行証も同梱する　データベースに接続する
			//引数にH2に繋ぐためのアドレスを入れている(地図)＝simpleBCの部分が作るときで変わる、そのユーザのid,pw


			String sql = "SELECT * FROM Bc WHERE number=? ";//準備⑥必要なものリストを用意する　ページで検索欄に入れたものが？に入る
			// 名前検索のSQL文を準備する★住所、会社名、備考、全体検索も　(必要なものリスト)

			PreparedStatement pStmt = conn.prepareStatement(sql);
			//準備⑦船を用意し、必要なものを乗っける
			//pStmt(船) conn(地図,id,pw),sql(持ってくるものリスト)
			//データベースにアクセスするためにオブジェクトが入ってる
			//？を埋めないと出発できない(検索で入れる)

			pStmt.setInt(1, number);//引数sqlにsetStringしてる

			// SQL文を実行し、結果表を取得する	検索して結果の表をrsに入れる構文
			ResultSet rs = pStmt.executeQuery();
			//⑨出向して一瞬で戻ってくる船　いってらっしゃい～
			//なんでも入れていい箱ResultSet(DAOの中でしか入れられない)
			//検索結果の表をrsに入れている
			//上をアレイリストに入れないといけない

			// 結果表をコレクションにコピーする
			while (rs.next()) {
			//rs.nextで表の次の行にフォーカスが合う　もう行がなければfalseが返ってきて終わり
				Bc record = new Bc(	//beansをrecordに入れる
				rs.getInt("number"),	//今フォーカスがあたっている行のnumberを探す
				rs.getString("company"),
				rs.getString("department"),
				rs.getString("position"),
				rs.getString("name"),
				rs.getString("hurigana"),
				rs.getString("zipcode"),
				rs.getString("address"),
				rs.getString("phone"),
				rs.getString("email"),
				rs.getString("remarks")
				);
				cardList.add(record);	//検索表に新しい行を作成
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;		//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}
}
