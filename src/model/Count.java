package model;
import java.io.Serializable;

public class Count implements Serializable {

	//Usersテーブル
	private int reviewSearchCount;				//レビューの全体検索数
	private int userSearchCount;

	public int getReviewSearchCount() {
		return reviewSearchCount;
	}

	public void setReviewSearchCount(int sum) {
		this.reviewSearchCount = sum;
	}

	public int getUserSearchCount() {
		return userSearchCount;
	}

	public void setUserSearchCount(int sum) {
		this.userSearchCount = sum;
	}
}


