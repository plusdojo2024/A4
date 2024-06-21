package model;
import java.io.Serializable;

public class Count implements Serializable {

	//Usersテーブル
	private int wholeCount;				//全体検索数（すべて）「

	public int getWholeCount() {
		return wholeCount;
	}

	public void setWholeCount(int wholeCount) {
		this.wholeCount = wholeCount;
	}
}


