package test;		//改造必要ヨシ！BcDAO.javaをテストするmainメソッドを持ったプログラム
import java.util.List;

import dao.BcDAO;
import model.Bc;

public class BcDAOTest {
	public static void main(String[] args) {
		BcDAO dao = new BcDAO();

		// select()のテスト
		System.out.println("---------- select()のテスト ----------");
		List<Bc> cardList2 = dao.select(new Bc());
		for (Bc card : cardList2) {
			System.out.println("番号：" + card.getNumber());
			System.out.println("会社名:"+card.getCompany());
			System.out.println("部署名:"+card.getDepartment());
			System.out.println("役職名:"+card.getPosition());
			System.out.println("ふりがな:"+card.getHurigana());
			System.out.println("氏名：" + card.getName());
			System.out.println("郵便番号:"+card.getZipcode());
			System.out.println("住所：" + card.getAddress());
			System.out.println("電話番号:"+card.getPhone());
			System.out.println("メールアドレス:"+card.getEmail());
			System.out.println("備考:"+card.getRemarks());
		}

		// insert()のテスト
		int upDelNumber = 1;		// 後で更新および削除する番号
		System.out.println("---------- insert()のテスト ----------");
		Bc insRec = new Bc(0,"株式会社会社 ","人事部","部長","山本六郎","やまだろくろう", "651-4727",
				"東京都","945-8418-4541","kaisya@onmicrosoft.com","");
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<Bc> cardList3 = dao.select(insRec);
			for (Bc card : cardList3) {
				System.out.println("番号：" + card.getNumber());
				System.out.println("会社名:"+card.getCompany());
				System.out.println("部署名:"+card.getDepartment());
				System.out.println("役職名:"+card.getPosition());
				System.out.println("氏名：" + card.getName());
				System.out.println("ふりがな:"+card.getHurigana());
				System.out.println("郵便番号:"+card.getZipcode());
				System.out.println("住所：" + card.getAddress());
				System.out.println("電話番号:"+card.getPhone());
				System.out.println("メールアドレス:"+card.getEmail());
				System.out.println("備考:"+card.getRemarks());

				upDelNumber = card.getNumber();	// 最後のレコードを後で更新および削除する
			}
		}
		else {
			System.out.println("登録失敗！");
		}

		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		Bc upRec = new Bc(1, "株式会社社長 ","人事部","社長","山田社長", "やまだしゃちょう","684-9427",
				"神奈川県","746-8578-4541","syatyo@onmicrosoft.com","しゃちょ～");
		if (dao.update(upRec)) {
			System.out.println("更新成功！");
			List<Bc> cardList4 = dao.select(upRec);
			for (Bc card : cardList4) {
				System.out.println("番号：" + card.getNumber());
				System.out.println("会社名:"+card.getCompany());
				System.out.println("部署名:"+card.getDepartment());
				System.out.println("役職名:"+card.getPosition());
				System.out.println("氏名：" + card.getName());
				System.out.println("ふりがな:"+card.getHurigana());
				System.out.println("郵便番号:"+card.getZipcode());
				System.out.println("住所：" + card.getAddress());
				System.out.println("電話番号:"+card.getPhone());
				System.out.println("メールアドレス:"+card.getEmail());
				System.out.println("備考:"+card.getRemarks());
			}
		}
		else {
			System.out.println("更新失敗！");
		}

		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		if (dao.delete(upDelNumber)) {
			System.out.println("削除成功！");
		}
		else {
			System.out.println("削除失敗！");
		}
	}
}
