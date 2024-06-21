package model;
import java.io.Serializable;
import java.sql.Timestamp;

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
	private Timestamp rCreatedAt; //作成日時
	private Timestamp rUpdatedAt;	//更新日時

	//レビュー画像(reviews_imgs)テーブル
	private int reviewImgId;	//レビュー画像ID
	private String reviewImg;	//レビュー画像
	private int rimDeleteFlg;	//レビュー画像の削除フラグ
	private Timestamp rimCreatedAt;	//作成日時
	private Timestamp rimUpdatedAt;	//更新日時

	//レビュー項目(reviews_item)テーブル
	private int reviewItemId;		//レビュー項目ID
	private String reviewItem1;	//レビュー項目1
	private String reviewItem2;	//レビュー項目2
	private String reviewItem3;	//レビュー項目3
	private String reviewItem4;	//レビュー項目4
	private String reviewItem5;	//レビュー項目5
	private Timestamp ritCreatedAt;	//作成日時
	private Timestamp ritUpdatedAt;	//更新日時

	//スコア(reviews_scores)テーブル
	private int reviewScoreId;	//レビュースコアID
	private int reviewItem1Score;	//レビュー項目1スコア
	private int reviewItem2Score;	//レビュー項目2スコア
	private int reviewItem3Score;	//レビュー項目3スコア
	private int reviewItem4Score;	//レビュー項目4スコア
	private int reviewItem5Score;	//レビュー項目5スコア
	private int scoreAvg;	//スコア平均
	private Timestamp rsCreatedAt;	//作成日時
	private Timestamp rsUpdatedAt;	//更新日時

	//バックナンバー(backnumbers) テーブル
	private int backnumberId;	//バックナンバーID
	private String backnumberContent;	//バックナンバーコンテンツ(文章)
	private int bDeleteFlg;	//バックナンバーの削除フラグ
	private Timestamp bCreatedAt;	//作成日時
	private Timestamp bUpdatedAt;	//更新日時

	//リストレビュー(list_reviews)テーブル
	private int listReviewId;
	private int lrListId;
	private Timestamp lrCreatedAt;
	private Timestamp lrUpdatedAt;

	//ユーザー(user)テーブル
	private String userEmail;
	private String userPassword;
	private String userName;
	private String userImg;
	private int uPrivacyFlg;
	private Timestamp uCreatedAt;
	private Timestamp uUpdatedAt;

	//コンストラクタ
	public Review() {
		this.reviewName = "";
		this.reviewComment = "";
		this.reviewImg = "";
		this.reviewItem1 = "";
		this.reviewItem2 = "";
		this.reviewItem3 = "";
		this.reviewItem4 = "";
		this.reviewItem5 = "";
		this.backnumberContent = "";
	}

	public Review(int userId) {
		super();
		this.userId = userId;
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

	public synchronized final Timestamp getrCreatedAt() {
		return rCreatedAt;
	}

	public synchronized final void setrCreatedAt(Timestamp rCreatedAt) {
		this.rCreatedAt = rCreatedAt;
	}

	public synchronized final Timestamp getrUpdatedAt() {
		return rUpdatedAt;
	}

	public synchronized final void setrUpdatedAt(Timestamp rUpdatedAt) {
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

	public synchronized final Timestamp getRimCreatedAt() {
		return rimCreatedAt;
	}

	public synchronized final void setRimCreatedAt(Timestamp rimCreatedAt) {
		this.rimCreatedAt = rimCreatedAt;
	}

	public synchronized final Timestamp getRimUpdatedAt() {
		return rimUpdatedAt;
	}

	public synchronized final void setRimUpdatedAt(Timestamp rimUpdatedAt) {
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

	public synchronized final Timestamp getRitCreatedAt() {
		return ritCreatedAt;
	}

	public synchronized final void setRitCreatedAt(Timestamp ritCreatedAt) {
		this.ritCreatedAt = ritCreatedAt;
	}

	public synchronized final Timestamp getRitUpdatedAt() {
		return ritUpdatedAt;
	}

	public synchronized final void setRitUpdatedAt(Timestamp ritUpdatedAt) {
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

	public synchronized final Timestamp getRsCreatedAt() {
		return rsCreatedAt;
	}

	public synchronized final void setRsCreatedAt(Timestamp rsCreatedAt) {
		this.rsCreatedAt = rsCreatedAt;
	}

	public synchronized final Timestamp getRsUpdatedAt() {
		return rsUpdatedAt;
	}

	public synchronized final void setRsUpdatedAt(Timestamp rsUpdatedAt) {
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

	public synchronized final Timestamp getbCreatedAt() {
		return bCreatedAt;
	}

	public synchronized final void setbCreatedAt(Timestamp bCreatedAt) {
		this.bCreatedAt = bCreatedAt;
	}

	public synchronized final Timestamp getbUpdatedAt() {
		return bUpdatedAt;
	}

	public synchronized final void setbUpdatedAt(Timestamp bUpdatedAt) {
		this.bUpdatedAt = bUpdatedAt;
	}

	public synchronized final int getListReviewId() {
		return listReviewId;
	}

	public synchronized final void setListReviewId(int listReviewId) {
		this.listReviewId = listReviewId;
	}

	public synchronized final int getLrListId() {
		return lrListId;
	}

	public synchronized final void setLrListId(int lrListId) {
		this.lrListId = lrListId;
	}

	public synchronized final Timestamp getLrCreatedAt() {
		return lrCreatedAt;
	}

	public synchronized final void setLrCreatedAt(Timestamp lrCreatedAt) {
		this.lrCreatedAt = lrCreatedAt;
	}

	public synchronized final Timestamp getLrUpdatedAt() {
		return lrUpdatedAt;
	}

	public synchronized final void setLrUpdatedAt(Timestamp lrUpdatedAt) {
		this.lrUpdatedAt = lrUpdatedAt;
	}

	public synchronized final String getUserEmail() {
		return userEmail;
	}

	public synchronized final void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public synchronized final String getUserPassword() {
		return userPassword;
	}

	public synchronized final void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public synchronized final String getUserName() {
		return userName;
	}

	public synchronized final void setUserName(String userName) {
		this.userName = userName;
	}

	public synchronized final String getUserImg() {
		return userImg;
	}

	public synchronized final void setUserImg(String userImg) {
		this.userImg = userImg;
	}


	public synchronized final Timestamp getuCreatedAt() {
		return uCreatedAt;
	}

	public synchronized final void setuCreatedAt(Timestamp uCreatedAt) {
		this.uCreatedAt = uCreatedAt;
	}

	public synchronized final Timestamp getuUpdatedAt() {
		return uUpdatedAt;
	}

	public synchronized final void setuUpdatedAt(Timestamp uUpdatedAt) {
		this.uUpdatedAt = uUpdatedAt;
	}

	public int getuPrivacyFlg() {
		return uPrivacyFlg;
	}

	public void setuPrivacyFlg(int uPrivacyFlg) {
		this.uPrivacyFlg = uPrivacyFlg;
	}

}

