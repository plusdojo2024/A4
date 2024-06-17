package model;
import java.io.Serializable;

public class Review implements Serializable {

	//レビュー(reviews)テーブル
	private int reviewId;		//レビューID
	private int category2Id;	//小カテゴリーID
	private String reviewName;	//レビュー名
	private int reviewPrice;	//レビュー価格
	private String reviewComment;	//レビューコメント
	private int userId;	//ユーザーID
	private int rPrivacyFlg;	//レビュー自体の公開・非公開フラグ
	private int rDeleteFlg;	//レビュー自体の削除フラグ
	private String rCreatedAt; //作成日時
	private String rUpdatedAt;	//更新日時

	//レビュー画像(reviews_imgs)テーブル
	private int reviewImgId;	//レビュー画像ID
	private String reviewImg;	//レビュー画像
	private int rimDeleteFlg;	//レビュー画像の削除フラグ
	private String rimCreatedAt;	//作成日時
	private String rimUpdatedAt;	//更新日時

	//レビュー項目(reviews_item)テーブル
	private int reviewItemId;		//レビュー項目ID
	private String reviewItem1;	//レビュー項目1
	private String reviewItem2;	//レビュー項目2
	private String reviewItem3;	//レビュー項目3
	private String reviewItem4;	//レビュー項目4
	private String reviewItem5;	//レビュー項目5
	private String ritCreatedAt;	//作成日時
	private String ritUpdatedAt;	//更新日時

	//スコア(reviews_scores)テーブル
	private int reviewScoreId;	//レビュースコアID
	private int reviewItem1Score;	//レビュー項目1スコア
	private int reviewItem2Score;	//レビュー項目2スコア
	private int reviewItem3Score;	//レビュー項目3スコア
	private int reviewItem4Score;	//レビュー項目4スコア
	private int reviewItem5Score;	//レビュー項目5スコア
	private int scoreAvg;	//スコア平均
	private String rsCreatedAt;	//作成日時
	private String rsUpdatedAt;	//更新日時

	//バックナンバー(backnumbers) テーブル
	private int backnumberId;	//バックナンバーID
	private String backnumberContent;	//バックナンバーコンテンツ(文章)
	private int bDeleteFlg;	//バックナンバーの削除フラグ
	private String bCreatedAt;	//作成日時
	private String bUpdatedAt;	//更新日時

	//コンストラクタ
	public Review() {
		this.reviewName = "";
		this.reviewComment = "";
		this.rCreatedAt = "";
		this.rUpdatedAt = "";
		this.reviewImg = "";
		this.rimCreatedAt = "";
		this.rimUpdatedAt = "";
		this.reviewItem1 = "";
		this.reviewItem2 = "";
		this.reviewItem3 = "";
		this.reviewItem4 = "";
		this.reviewItem5 = "";
		this.ritCreatedAt = "";
		this.ritUpdatedAt = "";
		this.rsCreatedAt = "";
		this.rsUpdatedAt = "";
		this.backnumberContent = "";
		this.bCreatedAt = "";
		this.bUpdatedAt = "";
	}

	public synchronized final int getReviewId() {
		return reviewId;
	}

	public synchronized final void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public synchronized final int getCategory2Id() {
		return category2Id;
	}

	public synchronized final void setCategory2Id(int category2Id) {
		this.category2Id = category2Id;
	}

	public synchronized final String getReviewName() {
		return reviewName;
	}

	public synchronized final void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	public synchronized final int getReviewPrice() {
		return reviewPrice;
	}

	public synchronized final void setReviewPrice(int reviewPrice) {
		this.reviewPrice = reviewPrice;
	}

	public synchronized final String getReviewComment() {
		return reviewComment;
	}

	public synchronized final void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public synchronized final int getUserId() {
		return userId;
	}

	public synchronized final void setUserId(int userId) {
		this.userId = userId;
	}

	public synchronized final int getrPrivacyFlg() {
		return rPrivacyFlg;
	}

	public synchronized final void setrPrivacyFlg(int rPrivacyFlg) {
		this.rPrivacyFlg = rPrivacyFlg;
	}

	public synchronized final int getrDeleteFlg() {
		return rDeleteFlg;
	}

	public synchronized final void setrDeleteFlg(int rDeleteFlg) {
		this.rDeleteFlg = rDeleteFlg;
	}

	public synchronized final String getrCreatedAt() {
		return rCreatedAt;
	}

	public synchronized final void setrCreatedAt(String rCreatedAt) {
		this.rCreatedAt = rCreatedAt;
	}

	public synchronized final String getrUpdatedAt() {
		return rUpdatedAt;
	}

	public synchronized final void setrUpdatedAt(String rUpdatedAt) {
		this.rUpdatedAt = rUpdatedAt;
	}

	public synchronized final int getReviewImgId() {
		return reviewImgId;
	}

	public synchronized final void setReviewImgId(int reviewImgId) {
		this.reviewImgId = reviewImgId;
	}

	public synchronized final String getReviewImg() {
		return reviewImg;
	}

	public synchronized final void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}

	public synchronized final int getRimDeleteFlg() {
		return rimDeleteFlg;
	}

	public synchronized final void setRimDeleteFlg(int rimDeleteFlg) {
		this.rimDeleteFlg = rimDeleteFlg;
	}

	public synchronized final String getRimCreatedAt() {
		return rimCreatedAt;
	}

	public synchronized final void setRimCreatedAt(String rimCreatedAt) {
		this.rimCreatedAt = rimCreatedAt;
	}

	public synchronized final String getRimUpdatedAt() {
		return rimUpdatedAt;
	}

	public synchronized final void setRimUpdatedAt(String rimUpdatedAt) {
		this.rimUpdatedAt = rimUpdatedAt;
	}

	public synchronized final int getReviewItemId() {
		return reviewItemId;
	}

	public synchronized final void setReviewItemId(int reviewItemId) {
		this.reviewItemId = reviewItemId;
	}

	public synchronized final String getReviewItem1() {
		return reviewItem1;
	}

	public synchronized final void setReviewItem1(String reviewItem1) {
		this.reviewItem1 = reviewItem1;
	}

	public synchronized final String getReviewItem2() {
		return reviewItem2;
	}

	public synchronized final void setReviewItem2(String reviewItem2) {
		this.reviewItem2 = reviewItem2;
	}

	public synchronized final String getReviewItem3() {
		return reviewItem3;
	}

	public synchronized final void setReviewItem3(String reviewItem3) {
		this.reviewItem3 = reviewItem3;
	}

	public synchronized final String getReviewItem4() {
		return reviewItem4;
	}

	public synchronized final void setReviewItem4(String reviewItem4) {
		this.reviewItem4 = reviewItem4;
	}

	public synchronized final String getReviewItem5() {
		return reviewItem5;
	}

	public synchronized final void setReviewItem5(String reviewItem5) {
		this.reviewItem5 = reviewItem5;
	}

	public synchronized final String getRitCreatedAt() {
		return ritCreatedAt;
	}

	public synchronized final void setRitCreatedAt(String ritCreatedAt) {
		this.ritCreatedAt = ritCreatedAt;
	}

	public synchronized final String getRitUpdatedAt() {
		return ritUpdatedAt;
	}

	public synchronized final void setRitUpdatedAt(String ritUpdatedAt) {
		this.ritUpdatedAt = ritUpdatedAt;
	}

	public synchronized final int getReviewScoreId() {
		return reviewScoreId;
	}

	public synchronized final void setReviewScoreId(int reviewScoreId) {
		this.reviewScoreId = reviewScoreId;
	}

	public synchronized final int getReviewItem1Score() {
		return reviewItem1Score;
	}

	public synchronized final void setReviewItem1Score(int reviewItem1Score) {
		this.reviewItem1Score = reviewItem1Score;
	}

	public synchronized final int getReviewItem2Score() {
		return reviewItem2Score;
	}

	public synchronized final void setReviewItem2Score(int reviewItem2Score) {
		this.reviewItem2Score = reviewItem2Score;
	}

	public synchronized final int getReviewItem3Score() {
		return reviewItem3Score;
	}

	public synchronized final void setReviewItem3Score(int reviewItem3Score) {
		this.reviewItem3Score = reviewItem3Score;
	}

	public synchronized final int getReviewItem4Score() {
		return reviewItem4Score;
	}

	public synchronized final void setReviewItem4Score(int reviewItem4Score) {
		this.reviewItem4Score = reviewItem4Score;
	}

	public synchronized final int getReviewItem5Score() {
		return reviewItem5Score;
	}

	public synchronized final void setReviewItem5Score(int reviewItem5Score) {
		this.reviewItem5Score = reviewItem5Score;
	}

	public synchronized final int getScoreAvg() {
		return scoreAvg;
	}

	public synchronized final void setScoreAvg(int scoreAvg) {
		this.scoreAvg = scoreAvg;
	}

	public synchronized final String getRsCreatedAt() {
		return rsCreatedAt;
	}

	public synchronized final void setRsCreatedAt(String rsCreatedAt) {
		this.rsCreatedAt = rsCreatedAt;
	}

	public synchronized final String getRsUpdatedAt() {
		return rsUpdatedAt;
	}

	public synchronized final void setRsUpdatedAt(String rsUpdatedAt) {
		this.rsUpdatedAt = rsUpdatedAt;
	}

	public synchronized final int getBacknumberId() {
		return backnumberId;
	}

	public synchronized final void setBacknumberId(int backnumberId) {
		this.backnumberId = backnumberId;
	}

	public synchronized final String getBacknumberContent() {
		return backnumberContent;
	}

	public synchronized final void setBacknumberContent(String backnumberContent) {
		this.backnumberContent = backnumberContent;
	}

	public synchronized final int getbDeleteFlg() {
		return bDeleteFlg;
	}

	public synchronized final void setbDeleteFlg(int bDeleteFlg) {
		this.bDeleteFlg = bDeleteFlg;
	}

	public synchronized final String getbCreatedAt() {
		return bCreatedAt;
	}

	public synchronized final void setbCreatedAt(String bCreatedAt) {
		this.bCreatedAt = bCreatedAt;
	}

	public synchronized final String getbUpdatedAt() {
		return bUpdatedAt;
	}

	public synchronized final void setbUpdatedAt(String bUpdatedAt) {
		this.bUpdatedAt = bUpdatedAt;
	}

}