package model;
import java.io.Serializable;

public class Category implements Serializable {
	
	//category1テーブル
	private int category1Id; 				//大カテゴリーID
	private String category1Name;			//大カテゴリー名
	private String c1CreatedAt;				//大カテゴリー作成日時
	private String c1UpdatedAt;				//大カテゴリー更新日時
	
	//category2テーブル
	private int category2Id;			//小カテゴリーID
	private String category2Name;		//小カテゴリー名
	private String c2CreatedAt;			//小カテゴリー作成日時
	private String c2UpdatedAt;			//小カテゴリー更新日時
	
	//reviewsテーブル
	private int reviewId;				//レビューID
	private String reviewName;			//レビュー名
	private int reviewPrice;			//レビュー価格
	private String reviewComment;		//レビューコメント
	private int userId;					//ユーザーID
	private int rPrivacyFlg;			//レビュー公開・非公開フラグ
	private int rDeleteFlg;				//レビュー削除フラグ
	private String rCreatedAt;			//レビュー作成日時
	private String rUpdatedAt;			//レビュー更新日時
	
	//コンストラクタ
	public Category() {
		this.category1Name = "";
		this.c1CreatedAt = "";
		this.c1UpdatedAt = "";
		this.category2Name = "";
		this.c2CreatedAt = "";
		this.c2UpdatedAt = "";
		this.reviewName = "";
		this.reviewComment = "";
		this.rCreatedAt = "";
		this.rUpdatedAt = "";
	}
	
	
	//ゲッター、セッター
	public int getCategory1Id() {
		return category1Id;
	}

	public void setCategory1Id(int category1Id) {
		this.category1Id = category1Id;
	}

	public String getCategory1Name() {
		return category1Name;
	}

	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	public String getC1CreatedAt() {
		return c1CreatedAt;
	}

	public void setC1CreatedAt(String c1CreatedAt) {
		this.c1CreatedAt = c1CreatedAt;
	}

	public String getC1UpdatedAt() {
		return c1UpdatedAt;
	}

	public void setC1UpdatedAt(String c1UpdatedAt) {
		this.c1UpdatedAt = c1UpdatedAt;
	}

	public int getCategory2Id() {
		return category2Id;
	}

	public void setCategory2Id(int category2Id) {
		this.category2Id = category2Id;
	}

	public String getCategory2Name() {
		return category2Name;
	}

	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	public String getC2CreatedAt() {
		return c2CreatedAt;
	}

	public void setC2CreatedAt(String c2CreatedAt) {
		this.c2CreatedAt = c2CreatedAt;
	}

	public String getC2UpdatedAt() {
		return c2UpdatedAt;
	}

	public void setC2UpdatedAt(String c2UpdatedAt) {
		this.c2UpdatedAt = c2UpdatedAt;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewName() {
		return reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public int getReviewPrice() {
		return reviewPrice;
	}

	public void setReviewPrice(int reviewPrice) {
		this.reviewPrice = reviewPrice;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getrPrivacyFlg() {
		return rPrivacyFlg;
	}

	public void setrPrivacyFlg(int rPrivacyFlg) {
		this.rPrivacyFlg = rPrivacyFlg;
	}

	public int getrDeleteFlg() {
		return rDeleteFlg;
	}

	public void setrDeleteFlg(int rDeleteFlg) {
		this.rDeleteFlg = rDeleteFlg;
	}

	public String getrCreatedAt() {
		return rCreatedAt;
	}

	public void setrCreatedAt(String rCreatedAt) {
		this.rCreatedAt = rCreatedAt;
	}

	public String getrUpdatedAt() {
		return rUpdatedAt;
	}

	public void setrUpdatedAt(String rUpdatedAt) {
		this.rUpdatedAt = rUpdatedAt;
	}
	
	

}
