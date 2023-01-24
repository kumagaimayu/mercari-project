package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.Category2;
import jp.co.example.repository.CategoryRepository2;
import jp.co.example.repository.OriginalRepository;

/**
 * OriginalテーブルからCategoryを振り分けるサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class InsertCategoryService2 {

	@Autowired
	private CategoryRepository2 categoryRepository2;

	@Autowired
	private OriginalRepository originalRepository;

	/**
	 * カテゴリ名を検索する.
	 * 
	 * @return カテゴリ名のリスト
	 */
	public List<String> findCategoryName() {
		List<String> categoryNameList = originalRepository.findCategoryName();
		return categoryNameList;
	}

	/**
	 * カテゴリテーブルに情報を挿入する.
	 * 
	 * @param category カテゴリ
	 * @return ID
	 */
	public Integer insertCategory(Category2 category) {
		Integer category_id = categoryRepository2.insertCategory(category);
		return category_id;
	}

}
