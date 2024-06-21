package dao;//BCテーブルを操作するプログラム　改造必要

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Category;//modelのカテゴリー

//DAOクラスを定義
public class Categorys1DAO {
	
	// DB接続に必要なConnectionオブジェクトを宣言
	Connection conn = null;
	
	//selectメソッド（引数はいらない）
	public List<Category> select() {
		
		Category ca = null;
		
		ArrayList<Category> list = new ArrayList<Category>();

		try {
			Class.forName("org.h2.Driver");
			
			//DB接続に接続
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB");

			//SELECT文の準備
			String sql = "SELECT * FROM categorys1";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			//categorys1テーブル
			while (rs.next()) {
				ca = new Category();
				ca.setCategory1Id(rs.getInt("category1_id"));	
				ca.setCategory1Name(rs.getString("category1_name"));
				ca.setC1CreatedAt(rs.getTimestamp("created_at"));
				ca.setC1UpdatedAt(rs.getTimestamp("updated_at"));
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
public List<Category> AllSelectCategory() {
		
		ArrayList<Category> list = new ArrayList<Category>();

		try {
			Class.forName("org.h2.Driver");
			
			//DB接続に接続
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A4DB");

			//SELECT文の準備
			String sql = "SELECT categorys1.category1_id,categorys1.category1_name"
					+ "categorys2.category2_id,categorys2.category2_name FROM categorys1 "
					+ "JOIN categorys2 ON categorys1.category1_id = categorys2.category1_id";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			ResultSet rs = pStmt.executeQuery();
			
			//categorys1テーブル
			while (rs.next()) {
				Category ca = new Category();
				ca.setCategory1Id(rs.getInt("category1_id"));	
				ca.setCategory1Name(rs.getString("category1_name"));
				ca.setCategory2Id(rs.getInt("category2_id"));	
				ca.setCategory2Name(rs.getString("category2_name"));
//				ca.setC1CreatedAt(rs.getTimestamp("created_at"));
//				ca.setC1UpdatedAt(rs.getTimestamp("updated_at"));
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