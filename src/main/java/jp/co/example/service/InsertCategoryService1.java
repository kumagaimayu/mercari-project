package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.Category1;
import jp.co.example.repository.CategoryRepository1;
import jp.co.example.repository.OriginalRepository;

/**
 * カテゴリを振り分けるサービス .
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class InsertCategoryService1 {

	@Autowired
	private CategoryRepository1 categoryRepository;

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
	public Integer insertCategory(Category1 category) {
		Integer id = categoryRepository.insertCategory(category);
		return id;
	}
}