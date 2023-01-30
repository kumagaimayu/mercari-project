package jp.co.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import jp.co.example.domain.Category;

/**
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * カテゴリーオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);

	/**
	 * カテゴリテーブルに情報を挿入する.
	 * 
	 * @param category カテゴリ
	 * @return ID
	 */
	public Integer insertCategory(Category category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "insert into category (name,path,depth) values(:name,:path, :depth);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumNames = { "category_id" };
		template.update(sql, param, keyHolder, keyColumNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}

	/**
	 * 大カテゴリのリストのみを取得する.
	 * 
	 * @return 大カテゴリのリスト
	 */
	public List<Category> findBigCategory() {
		String sql = "select category_id, name, path, depth from category where depth = 1";
		List<Category> bigCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return bigCategoryList;
	}

	/**
	 * 親の名前,深さからカテゴリのリストを取得.
	 * 
	 * @param path  パス
	 * @param depth 深さ
	 * @return カテゴリのリスト
	 */
	public List<Category> findChildCategory(String path, Integer depth) {
		String sql = "select category_id, name, path, depth from category where path like :path and depth = :depth;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("path", path + "/%").addValue("depth", depth);
		List<Category> childCategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return childCategoryList;
	}

	/**
	 * IDからcategoryを1件検索する.
	 * 
	 * @param id カテゴリID
	 * @return カテゴリ
	 */
	public Category findById(Integer id) {
		String sql = "select category_id, name, path, depth from category where category_id =:categoryId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryId", id);
		Category category = template.queryForObject(sql, param, CATEGORY_ROW_MAPPER);
		return category;
	}
}