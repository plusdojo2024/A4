package test;

public class ReviewsDAOTest {

	public static void main(String[] args) {
//		ReviewsDAO dao = new ReviewsDAO();
//		//引数のでーた　int category2Id,String reviewName,int reviewPrice,String reviewComment,int privacyFlg,Timestamp updatedAt,int reviewId) {
//		int result = dao.update(0, null, 0, null, 0, null, 0);
//		System.out.println(result);//←こいつが１なら成功

		String sql = "SELECT reviews.review_id,reviews.category2_id,reviews.review_name,reviews.review_price,reviews.review_comment,"
				+ "reviews.privacy_flg,reviews.delete_flg,reviews.created_at,reviews.updated_at, "
				+ "reviews_imgs.review_img_id,reviews_imgs.review_img,reviews_imgs.delete_flg,reviews_imgs.created_at,reviews_imgs.updated_at,"
				+ "reviews_items.review_item_id,reviews_items.review_item1,reviews_items.review_item2,reviews_items.review_item3,reviews_items.review_item4,reviews_items.review_item5,"
				+ "reviews_items.created_at,reviews_items.updated_at,"
				+ "reviews_scores.review_score_id,reviews_scores.review_item_id,"
				+ "reviews_scores.review_item1_score,reviews_scores.review_item2_score,reviews_scores.review_item3_score,reviews_scores.review_item4_score,reviews_scores.review_item5_score,"
				+ "reviews_scores.score_avg,reviews_scores.created_at,reviews_scores.updated_at,"
				+ "backnumbers.backnumber_id,backnumbers.backnumber_content,backnumbers.delete_flg,backnumbers.created_at,backnumbers.updated_at,"
				+ "list_reviews.list_id,list_reviews.created_at,list_reviews.updated_at,"
				+ "users.user_email,users.user_password,users.user_name,users.user_img,users.privacy_flg,users.created_at,users.updated_at FROM reviews "
				+ "LEFT OUTER JOIN reviews_imgs ON reviews.review_id = reviews_imgs.review_id "
				+ "LEFT OUTER JOIN categorys2 ON reviews.category2_id = categorys2.category2_id "
				+ "LEFT OUTER JOIN reviews_items ON categorys2.category2_id = reviews_items.category2_id "
				+ "LEFT OUTER JOIN reviews_scores ON reviews_scores.review_id = reviews.review_id "
				+ "LEFT OUTER JOIN backnumbers ON backnumbers.review_id = reviews.review_id "
				+ "LEFT OUTER JOIN list_reviews ON list_reviews.review_id = reviews.review_id "
				+ "LEFT OUTER JOIN users ON users.user_id = reviews.user_id ";

		System.out.println(sql);

	}

}
