package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.Category;
import jp.co.example.domain.Item;
import jp.co.example.repository.CategoryRepository;
import jp.co.example.repository.ItemRepository;

/**
 * 商品を追加するサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class AddItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 商品追加ページのプルダウン表示を行う.
	 * 
	 * @return 大カテゴリリスト
	 */
	public List<Category> findBigCategory() {
		List<Category> bigCategoryList = categoryRepository.findBigCategory();
		return bigCategoryList;
	}

	/**
	 * カテゴリ名と深さからカテゴリのリストを取得.
	 * 
	 * @param name カテゴリ名
	 * @param depth 深さ
	 * @return カテゴリのリスト
	 */
	public List<Category> findChildCategory(String path, Integer depth) {
		List<Category> childCategoryList = categoryRepository.findChildCategory(path, depth);
		return childCategoryList;
	}

	/**
	 * 商品を追加する.
	 * 
	 * @param item 商品
	 * @return ID
	 */
	public Integer addItem(Item item) {
		Integer id = itemRepository.insert(item);
		return id;
	}
}