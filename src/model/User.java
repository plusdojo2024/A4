package model;
import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {

	//Usersテーブル
	private int userId;				//ユーザーID
	private String userEmail;		//メールアドレス
	private String userPassword;	//パスワード
	private String userName;		//ユーザーネーム
	private String userImg;			//アイコン画像
	private int uPrivacyFlg;		//ユーザー公開・非公開フラグ
	private Timestamp uCreatedAt;		//ユーザー作成日時      ここはフィールド
	private Timestamp uUpdatedAt;		//ユーサー更新日時
	//followsテーブル
	private int followId;			//フォローID
	private int user1Id;			//ユーザー1ID
	private int user2Id;			//ユーザー2ID
	private Timestamp fCreatedAt;		//フォロー作成日時
	private Timestamp fUpdatedAt;		//フォロー更新日時

	
	public User() {                     //Stringのやつだけ空白にする
		this.userEmail = "";
		this.userPassword = "";
		this.userName = "";
		this.userImg = "";
	}
	
	//public User(int userId) {一旦コメントアウトにする
	//	super();
	//	this.userId = userId;
	//}
	
	//public User(int userId, String userName, String userImg) {
	//	super();
	//	this.userId = userId;
	//	this.userName = userName;
	//	this.userImg = userImg;
	//}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getuPrivacyFlg() {
		return uPrivacyFlg;
	}

	public void setuPrivacyFlg(int uPrivacyFlg) {
		this.uPrivacyFlg = uPrivacyFlg;
	}

	public Timestamp getuCreatedAt() {
		return uCreatedAt;
	}

	public void setuCreatedAt(Timestamp uCreatedAt) {
		this.uCreatedAt = uCreatedAt;
	}

	public Timestamp getuUpdatedAt() {
		return uUpdatedAt;
	}

	public void setuUpdatedAt(Timestamp uUpdatedAt) {
		this.uUpdatedAt = uUpdatedAt;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	public int getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	public Timestamp getfCreatedAt() {
		return fCreatedAt;
	}

	public void setfCreatedAt(Timestamp fCreatedAt) {
		this.fCreatedAt = fCreatedAt;
	}

	public Timestamp getfUpdatedAt() {
		return fUpdatedAt;
	}

	public void setfUpdatedAt(Timestamp fUpdatedAt) {
		this.fUpdatedAt = fUpdatedAt;
	}
}
	
	
