package model;
import java.io.Serializable;

public class User implements Serializable {

	//Usersテーブル
	private int userId;				//ユーザーID
	private String userEmail;		//メールアドレス
	private String userPassword;	//パスワード
	private String userName;		//ユーザーネーム
	private String userImg;			//アイコン画像
	private int uPrivacyFlg;		//ユーザー公開・非公開フラグ
	private String uCreatedAt;		//ユーザー作成日時
	private String uUpdatedAt;		//ユーサー更新日時
	//followsテーブル
	private int followId;			//フォローID
	private int user1Id;			//ユーザー1ID
	private int user2Id;			//ユーザー2ID
	private String fCreatedAt;		//フォロー作成日時
	private String fUpdatedAt;		//フォロー更新日時

	
	public User() {
		this.userEmail = "";
		this.userPassword = "";
		this.userName = "";
		this.userImg = "";
		this.uCreatedAt = "";
		this.uUpdatedAt = "";
		this.fCreatedAt = "";
		this.fUpdatedAt = "";
	}
	
	public User(int userId, String userEmail, String userPassword, String userName, String userImg,
		String uCreatedAt, String uUpdatedAt) {
		super();
		this.userId = userId;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userImg = userImg;
		this.uCreatedAt = uCreatedAt;
		this.uUpdatedAt = uUpdatedAt;
	}


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


	public int getuPrivcyFlg() {
		return uPrivacyFlg;
	}


	public void setuPrivcyFlg(int uPrivcyFlg) {
		this.uPrivacyFlg = uPrivcyFlg;
	}


	public String getuCreatedAt() {
		return uCreatedAt;
	}


	public void setuCreatedAt(String uCreatedAt) {
		this.uCreatedAt = uCreatedAt;
	}


	public String getuUpdatedAt() {
		return uUpdatedAt;
	}


	public void setuUpdatedAt(String uUpdatedAt) {
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


	public String getfCreatedAt() {
		return fCreatedAt;
	}


	public void setfCreatedAt(String fCreatedAt) {
		this.fCreatedAt = fCreatedAt;
	}


	public String getfUpdatedAt() {
		return fUpdatedAt;
	}


	public void setfUpdatedAt(String fUpdatedAt) {
		this.fUpdatedAt = fUpdatedAt;
	}
	
	
}
