package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.Category;
import jp.co.example.repository.CategoryRepository;
import jp.co.example.repository.OriginalRepository;

/**
 * カテゴリを振り分けるサービス .
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class InsertCategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private OriginalRepository originalRepository;

//	public void categoryInsert(Category category) {
//		categoryRepository.insertCategory(category);
//	}
	
	/**
	 * カテゴリ名を検索する.
	 * @return カテゴリ名のリスト
	 */
	public List<String> findCategoryName(){
		List<String> categoryNameList = originalRepository.findCategoryName();
		return categoryNameList;
	}
	
	/**
	 * カテゴリテーブルに情報を挿入する.
	 * @param category カテゴリ
	 * @return　ID
	 */
	public Integer insertCategory(Category category) {
		Integer id = categoryRepository.insertCategory(category);
		return id;
	}
}