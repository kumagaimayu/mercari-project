package jp.co.example.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.Category2;
import jp.co.example.service.InsertCategoryService2;

/**
 * Categoryテーブルにデータを挿入するコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/insert")
public class InsertCategoryController2 {

	@Autowired
	private InsertCategoryService2 insertCategoryService2;

	/**
	 * categoryテーブルに振り分ける.
	 * 
	 * @return
	 */
	@RequestMapping("/insertCategory2")
	public String insertCategory() {
		// スラッシュ区切りの文字列を取得.
		List<String> categoryNameList = insertCategoryService2.findCategoryName();
		// 重複を許さない大項目のハッシュセットを生成.
		Set<String> bigCategoryList = new HashSet<>();
		// スラッシュ区切りの文字列を配列にしたものを挿入するリスト.
		List<String[]> categoryNameArrayList = new ArrayList<>();
		// 取得したスラッシュ区切りの文字列のリストを回して/で区切った配列をリストに格納
		// if文で配列の一番めが""だったらcontinue
		for (String categoryName : categoryNameList) {
			if (categoryName.equals("")) {
				continue;
			}
			// splitで区切る
			String[] categoryNameArray = categoryName.split("/");
			// 重複を許さないリスト（ハッシュセット）に配列の1番目の文字列を挿入
			bigCategoryList.add(categoryNameArray[0]);
			// 配列が格納されているリストに格納
			categoryNameArrayList.add(categoryNameArray);
		}

		// 重複のない大項目のリストを回す
		for (String bigCategoryName : bigCategoryList) {
			Category2 bigCategory = new Category2();
			bigCategory.setName(bigCategoryName);
			// 深さを設定
			bigCategory.setDepth(1);
			// 大項目だけインサートするとcategory_idが帰ってくる.（middleCategoryのpathとなる,大カテゴリ自体のpathはnull）
			Integer middleCategoryPath = insertCategoryService2.insertCategory(bigCategory);
			// 重複を許さない中カテゴリの名前のリストをインスタンス化
			Set<String> middleNameList = new HashSet<>();
			// 大カテゴリと配列のリストの1番目（0）が一致したら中カテゴリのリストに配列の2番目をセット（1）
			for (String[] name : categoryNameArrayList) {
				if (name[0].equals(bigCategoryName)) {
					middleNameList.add(name[1]);
				}
			}
			// 中カテゴリの名前リスト（重複がない）を回す
			for (String middleCategoryName : middleNameList) {
				// 中カテゴリをインスタンス化
				Category2 middleCategory = new Category2();
				middleCategory.setName(middleCategoryName);
				// pathをセット
				middleCategory.setPath(middleCategoryPath.toString());
				middleCategory.setDepth(2);
				Integer smallCategoryPath = insertCategoryService2.insertCategory(middleCategory);
				for (String[] categoryNameArray : categoryNameArrayList) {
					// categoryNameArrayから1番目と2番目を取得、1番目が同じ場合かつ2番目が違うやつを実行.
					if (categoryNameArray[1].equals(middleCategoryName)
							&& categoryNameArray[0].equals(bigCategoryName)) {
						// 小カテゴリをインスタンス化
						Category2 smallCategory = new Category2();
						smallCategory.setName(categoryNameArray[2]);
						smallCategory.setDepth(3);
						smallCategory.setPath(middleCategoryPath.toString() + "/" + smallCategoryPath.toString());
						insertCategoryService2.insertCategory(smallCategory);
					}
				}
			}
		}
		return "index";
	}
}