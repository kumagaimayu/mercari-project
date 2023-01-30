package jp.co.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.ShowItem;
import jp.co.example.form.SearchForm;
import jp.co.example.repository.ItemRepository;

/**
 * 商品検索を行うサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class SearchService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品を検索します.
	 * 
	 * @param searchForm 検索フォーム
	 * @param offset     ページングに使用する商品の順番
	 * @return 商品リスト
	 */
	public List<ShowItem> search(SearchForm searchForm, Integer offset) {
		List<ShowItem> itemList = new ArrayList<ShowItem>();

		if (searchForm == null) {
			itemList = itemRepository.findAll(offset);
			return itemList;
		}
		// nameがnullだったら空文字を挿入
		if (searchForm.getName() == null) {
			searchForm.setName("");
		}

		// brandがnullだったら空文字を挿入
		if (searchForm.getBrand() == null) {
			searchForm.setBrand("");
		}

		// パスを変数宣言.
		String path = "";
		// Categoryが大まで
		if (searchForm.getBigCategory() == null || searchForm.getBigCategory().equals("")) {
			// 必要処理なし
			// Categoryが大まで
		} else if (searchForm.getMiddleCategory() == null || searchForm.getMiddleCategory().equals("")) {
			// パスに大カテゴリ名を入れる
			path = searchForm.getBigCategory();
			// Category中まで
		} else if (searchForm.getSmallCategory() == null || searchForm.getSmallCategory().equals("")) {
			path = searchForm.getBigCategory() + "/" + searchForm.getMiddleCategory();
		} else {
			path = searchForm.getBigCategory() + "/" + searchForm.getMiddleCategory() + "/"
					+ searchForm.getSmallCategory();
		}
		// 全ての項目が""の時は下記に直接入る
		itemList = itemRepository.findByNameCategoryBrand(searchForm.getName(), path, searchForm.getBrand(), offset);
		return itemList;
	}
}