package jp.co.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.domain.Original;

/**
 * originalテーブルを操作するリポジトリ.
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = new BeanPropertyRowMapper<>(Original.class);
	
	private static final RowMapper<String> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		String categoryName = new String();
		//従来は例えばItem型のidとかを格納していたが今回はcategory_nameのみ取得すれば良いためrs以降のみでよい.
		categoryName = rs.getString("category_name");
		return categoryName;
	};
	
	/**
	 * originalテーブルにデータを挿入する.
	 * 
	 * @param original
	 */
	public void insert(Original original) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(original);
		String sql = "insert into original (id,name,condition_id,category_name,brand,price,shipping,description) values(:train_id,:name,:item_condition_id,:category_name,:brand_name,:price,:shipping,:item_description)";
		template.update(sql, param);
	}
	
	/**
	 * category_nameを重複なく取得する.
	 * 
	 * @return category_nameのString型のリスト
	 */
	public List<String> findCategoryName() {
		String sql = "select category_name from original group by category_name;";
		List<String> categoryNameList = template.query(sql,CATEGORY_NAME_ROW_MAPPER);
		return categoryNameList;
	}
}
