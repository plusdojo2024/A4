package model;
import java.io.Serializable;

public class Count implements Serializable {

	//Usersテーブル
	private int reviewSearchCount;				//レビューの全体検索数
	private int userSearchCount;
	private int listCount;

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

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int sum) {
		this.listCount = sum;
	}
}


