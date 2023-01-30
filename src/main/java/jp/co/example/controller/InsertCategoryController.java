package jp.co.example.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.example.domain.Category;
import jp.co.example.service.InsertCategoryService;

/**
 * カテゴリを挿入するコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/")
public class InsertCategoryController {

	@Autowired
	private InsertCategoryService insertCategoryService;

	/**
	 * カテゴリ名を取得する.
	 * 
	 * @return /区切りの文字列
	 */
	@RequestMapping("/insertCategory")
	public String insertCategory() {
		// スラッシュ区切りの文字列を取得.
		List<String> categoryNameList = insertCategoryService.findCategoryName();
		// 重複を許さない大項目のハッシュセットを生成.
		Set<String> bigCategoryList = new HashSet<>();
		// スラッシュ区切りの文字列を配列にしたものを挿入するリスト.
		List<String[]> categoryNameArrayList = new ArrayList<>();
		// スラッシュ区切りの文字列をスラッシュ区切りにして配列に格納.
		// 取得したスラッシュ区切りの文字列のリストを回して/で区切った配列をリストに格納
		for (String categoryName : categoryNameList) {
			// nullだった場合、空行が入るため、配列の[0]はnullじゃない、[1]、[2]がnullの場合をキャッチして処理を実施しない
			try {
				// splitで区切る
				String[] categoryNameArray = categoryName.split("/");
				// [1][2]がnullのものがあるためエラーをキャッチ
				System.out.println(categoryNameArray[1] + categoryNameArray[2]);
				// 重複を許さないリスト（ハッシュセット）に配列の1番目の文字列を挿入
				bigCategoryList.add(categoryNameArray[0]);
				// 配列が格納されているリストに格納
				categoryNameArrayList.add(categoryNameArray);
			} catch (Exception e) {
			}
		}

		// 重複のない大項目のリストを回す
		for (String bigCategoryName : bigCategoryList) {
			//
			Category bigCategory = new Category();
			bigCategory.setName(bigCategoryName);
			// 大項目だけインサートして、返ってきたIDをセットする.
			Integer middleParentId = insertCategoryService.insertCategory(bigCategory);

			// 親の所持している中カテゴリ名を中カテゴリの名前のリストに挿入
			Set<String> middleNameList = new HashSet<>();
			for (String[] name : categoryNameArrayList) {
				if (name[0].equals(bigCategoryName)) {
					middleNameList.add(name[1]);
				}
			}
			// 中カテゴリの名前リスト（重複がない）を回す
			for (String middleCategoryName : middleNameList) {
				// 中カテゴリをインスタンス化して必要情報を挿入してインサート
				Category middleCategory = new Category();
				middleCategory.setParent(middleParentId);
				middleCategory.setName(middleCategoryName);
				// インサートした際にIDが返ってくるため小カテゴリのParentにセット
				Integer smallParentId = insertCategoryService.insertCategory(middleCategory);
				// 全ての配列が格納されているリストを回す
				for (String[] categoryNameArray : categoryNameArrayList) {

					// categoryNameArrayから1番目と2番目を取得、1番目が同じ場合かつ2番目が違うやつを実行.
					if (categoryNameArray[1].equals(middleCategoryName)
							&& categoryNameArray[0].equals(bigCategoryName)) {
						// 小カテゴリをインスタンス化
						Category smallCategory = new Category();
						// 必要情報をセット
						smallCategory.setName(categoryNameArray[2]);
						smallCategory.setParent(smallParentId);
						/// 区切りの文字列を取得
						String smallSetNameAll = bigCategoryName + "/" + middleCategory.getName() + "/"
								+ smallCategory.getName();
						smallCategory.setName_all(smallSetNameAll);
						// 小カテゴリをインサート
						insertCategoryService.insertCategory(smallCategory);
					}
				}
			}
		}
		return "index";
	}
}