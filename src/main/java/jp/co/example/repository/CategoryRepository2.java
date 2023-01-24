package jp.co.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import jp.co.example.domain.Category;
import jp.co.example.domain.Category2;

/**
 * categoryテーブルを操作するリポジトリ.
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class CategoryRepository2 {

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
	public Integer insertCategory(Category2 category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "insert into category (name,path,depth) values(:name,:path, :depth);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumNames = { "category_id" };
		template.update(sql, param, keyHolder, keyColumNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}
}
