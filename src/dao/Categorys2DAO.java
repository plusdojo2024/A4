package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;

public class Categorys2DAO {

	// DB接続に必要なConnectionオブジェクトを宣言
	Connection conn = null;

	//selectメソッド（引数はいらない）
	public List<Category> select(int category1Id) {

		ArrayList<Category> list = new ArrayList<Category>();//Category.javaの内容をArrayListに格納
			
		
		try {
			Class.forName("org.h2.Driver");
			
			//DB接続に接続
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB");

			//SELECT文の準備（categorys2テーブルの内容を取得）
			String sql = "SELECT * FROM categorys2 WHERE category1_id = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			pStmt.setInt(1, category1Id);
			
			ResultSet rs = pStmt.executeQuery();
			
			//categorys2テーブル
			while (rs.next()) {
				Category ca = new Category();
				ca.setCategory2Id(rs.getInt("category2_id"));	
				ca.setCategory2Name(rs.getString("category2_name"));
				ca.setC2CreatedAt(rs.getTimestamp("created_at"));
				ca.setC2UpdatedAt(rs.getTimestamp("updated_at"));
				list.add(ca);
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
		return list;//返ってきた結果をサーブレットに渡す(この後スコープに渡してjspに渡して表示)
	}
}