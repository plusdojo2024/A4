package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class List implements Serializable {
	//レビューテーブル
	private int reviewId;            //レビューID
	private int category2Id;         //小カテゴリー名
	private String reviewName;       //レビュー名
	private int reviewPrice;         //レビュー価格
	private String reviewComment;    //レビューコメント
	private int userId;              //ユーザーID
	private int rPrivacyFlg;         //レビュー公開・非公開フラグ
	private int rDeleteFlg;          //レビュー削除フラグ
	private Timestamp rCreatedAt;       //レビュー作成日時
	private Timestamp rUpdatedAt;       //レビュー更新日時

	//リストテーブル
	private int listId;             //リストID
	private String listName;        //リスト名
	private int lDeleteFlg;         //リスト削除フラグ
	private Timestamp lCreatedAt;      //リスト作成日時
	private Timestamp lUpdatedAt;      //リスト更新日時

	//リストレビューテーブル
	private int listReviewId;       //リストレビューID
	private Timestamp lrCreatedAt;     //リストレビュー作成日時
	private Timestamp lrUpdatedAt;     //リストレビュー更新日時

	public List() {
		this.reviewName = "";
		this.reviewComment = "";
		this.listName = "";
	}

	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getCategory2Id() {
		return category2Id;
	}
	public void setCategory2Id(int category2Id) {
		this.category2Id = category2Id;
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
	public Timestamp getrCreatedAt() {
		return rCreatedAt;
	}
	public void setrCreatedAt(Timestamp rCreatedAt) {
		this.rCreatedAt = rCreatedAt;
	}
	public Timestamp getrUpdatedAt() {
		return rUpdatedAt;
	}
	public void setrUpdatedAt(Timestamp rUpdatedAt) {
		this.rUpdatedAt = rUpdatedAt;
	}
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public int getlDeleteFlg() {
		return lDeleteFlg;
	}
	public void setlDeleteFlg(int lDeleteFlg) {
		this.lDeleteFlg = lDeleteFlg;
	}
	public Timestamp getlCreatedAt() {
		return lCreatedAt;
	}
	public void setlCreatedAt(Timestamp lCreatedAt) {
		this.lCreatedAt = lCreatedAt;
	}
	public Timestamp getlUpdatedAt() {
		return lUpdatedAt;
	}
	public void setlUpdatedAt(Timestamp lUpdatedAt) {
		this.lUpdatedAt = lUpdatedAt;
	}
	public int getListReviewId() {
		return listReviewId;
	}
	public void setListReviewId(int listReviewId) {
		this.listReviewId = listReviewId;
	}
	public Timestamp getLrCreatedAt() {
		return lrCreatedAt;
	}
	public void setLrCreatedAt(Timestamp lrCreatedAt) {
		this.lrCreatedAt = lrCreatedAt;
	}
	public Timestamp getLrUpdatedAt() {
		return lrUpdatedAt;
	}
	public void setLrUpdatedAt(Timestamp lrUpdatedAt) {
		this.lrUpdatedAt = lrUpdatedAt;
	}

}
