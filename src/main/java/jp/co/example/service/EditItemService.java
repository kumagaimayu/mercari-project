package jp.co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * name_allからsmallCategoryのIDを取得.
	 * 
	 * @param name_all
	 * @return 小カテゴリID
	 */
	public Integer findByNameAll(String name_all) {
		Integer smallCategoryId = categoryRepository.findByNameAll(name_all);
		return smallCategoryId;
	}

	/**
	 * 中カテゴリIDの取得.
	 * 
	 * @param smallCategoryId
	 * @return 
	 */
	public Integer findMiddleId(Integer smallCategoryId) {
		Integer middleCategoryId = categoryRepository.findParentIdById(smallCategoryId);
		return middleCategoryId;
	}

	/**
	 * 下位カテゴリのIDを元に上位カテゴリのparentIdを取得.
	 * 
	 * @param categoryId 下位カテゴリのID
	 * @return 上位カテゴリのID
	 */
	public Integer findParentId(Integer categoryId) {
		Integer middleCategoryId = categoryRepository.findParentId(categoryId);
		return middleCategoryId;
	}
}