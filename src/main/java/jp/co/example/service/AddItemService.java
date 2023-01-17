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
		List<Category> bigCategoryList = categoryRepository.findBigCategoryName();
		return bigCategoryList;
	}

	/**
	 * 親のIDから該当するカテゴリーのリストを取得.
	 * 
	 * @param parentId 親のID
	 * @return　カテゴリのリスト
	 */
	public List<Category> findChildCategory(Integer parentId) {
		List<Category> childCategoryList = categoryRepository.findChildCategoryName(parentId);
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