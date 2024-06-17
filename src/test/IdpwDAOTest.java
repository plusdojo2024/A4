package test;		//IdPwDAO.javaをテストするmainメソッドを持ったプログラム＜改造不要＞
import dao.IdpwDAO;
import model.Idpw;

public class IdpwDAOTest {
	public static void main(String[] args) {
		testIsLoginOK1();	// ユーザーが見つかる場合のテスト
		testIsLoginOK2();	// ユーザーが見つからない場合のテスト
	}

	// ユーザーが見つかる場合のテスト
	public static void testIsLoginOK1() {
		IdpwDAO dao = new IdpwDAO();
		if (dao.isLoginOK(new Idpw("DOJO", "password"))) {
			System.out.println("testIsLoginOK1：テストが成功しました");
		}
		else {
			System.out.println("testIsLoginOK1：テストが失敗しました");
		}
	}

	// ユーザーが見つからない場合のテスト
	public static void testIsLoginOK2() {
		IdpwDAO dao = new IdpwDAO();
		if (!dao.isLoginOK(new Idpw("DOJO", "pass"))) {
			System.out.println("testIsLoginOK2：テストが成功しました");
		}
		else {
			System.out.println("testIsLoginOK2：テストが失敗しました");
		}
	}
}
