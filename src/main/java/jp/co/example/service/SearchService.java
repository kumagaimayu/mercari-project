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
	 * @param offset ページングに使用する商品の順番
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

		// Categoryがnull
		if (searchForm.getBigCategory() == null) {
			itemList = itemRepository.findByNameBrand(searchForm.getName(), searchForm.getBrand(), offset);
			return itemList;
		}

		// Categoryが大まで
		if (searchForm.getMiddleCategory() == null) {
			itemList = itemRepository.findByBigCategory(searchForm.getName(), searchForm.getBigCategory(),
					searchForm.getBrand(), offset);
			return itemList;
		}
		// Category中まで
		if (searchForm.getSmallCategory() == null) {
			itemList = itemRepository.findByMiddleCategory(searchForm.getName(), searchForm.getMiddleCategory(),
					searchForm.getBrand(), offset);
			return itemList;
		}

		// 全ての項目がnullじゃない場合（Categoryも小まで埋まっている）
		itemList = itemRepository.findByNameCategoryBrand(searchForm.getName(), searchForm.getSmallCategory(),
				searchForm.getBrand(), offset);
		return itemList;
	}
}