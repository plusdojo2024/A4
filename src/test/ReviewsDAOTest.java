package test;

import dao.ReviewsDAO;

public class ReviewsDAOTest {

	public static void main(String[] args) {
		ReviewsDAO dao = new ReviewsDAO();
		//引数のでーた　int category2Id,String reviewName,int reviewPrice,String reviewComment,int privacyFlg,Timestamp updatedAt,int reviewId) {
		int result = dao.update(0, null, 0, null, 0, null, 0);
		System.out.println(result);//←こいつが１なら成功

	}

}
