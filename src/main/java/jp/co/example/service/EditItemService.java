package jp.co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.example.domain.Category;
import jp.co.example.domain.Item;
import jp.co.example.repository.CategoryRepository;
import jp.co.example.repository.ItemRepository;

/**
 * 商品情報の変更を行う.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class EditItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 商品情報の変更を行う.
	 * 
	 * @param item 商品
	 * @return 商品ID
	 */
	public Integer edit(Item item) {
		Integer id = itemRepository.update(item);
		return id;
	}

	// コメントアウト部分はリファクタリング前
	/**
	 * name_allからsmallCategoryのIDを取得.
	 * 
	 * @param name_all
	 * @return 小カテゴリID
	 */
//	public Integer findByNameAll(String name_all) {
//		Integer smallCategoryId = categoryRepository.findByNameAll(name_all);
//		return smallCategoryId;
//	}

	/**
	 * 中カテゴリIDの取得.
	 * 
	 * @param smallCategoryId
	 * @return
	 */
//	public Integer findMiddleId(Integer smallCategoryId) {
//		Integer middleCategoryId = categoryRepository.findParentIdById(smallCategoryId);
//		return middleCategoryId;
//	}

	/**
	 * 下位カテゴリのIDを元に上位カテゴリのparentIdを取得.
	 * 
	 * @param categoryId 下位カテゴリのID
	 * @return 上位カテゴリのID
	 */
//	public Integer findParentId(Integer categoryId) {
//		Integer middleCategoryId = categoryRepository.findParentId(categoryId);
//		return middleCategoryId;
//	}

	/**
	 * Item型の商品を1件検索.
	 * 
	 * @param id 商品ID
	 * @return item型の商品
	 */
	public Item findItemById(Integer id) {
		Item item = itemRepository.findItemById(id);
		return item;
	}

	/**
	 * カテゴリIDからカテゴリを検索
	 * 
	 * @param id カテゴリID
	 * @return カテゴリ
	 */
	public Category findCategoryById(Integer id) {
		Category category = categoryRepository.findById(id);
		return category;
	}
}