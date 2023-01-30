package jp.co.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	/**
	 * Originalのオブジェクトを生成するローマッパー.
	 */
//	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = new BeanPropertyRowMapper<>(Original.class);

	/**
	 * categoryの名前のオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<String> CATEGORY_NAME_ROW_MAPPER = (rs, i) -> {
		String categoryName = new String();
		// 従来は例えばItem型のidとかを格納していたが今回はcategory_nameのみ取得すれば良いためrs以降のみでよい.
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
		List<String> categoryNameList = template.query(sql, CATEGORY_NAME_ROW_MAPPER);
		return categoryNameList;
	}

	/**
	 * カテゴリを/ごとに取り出し、文字列配列に格納. 今回はDBアクセス回数を減らすためコメントアウト
	 * 
	 * @param category_name カテゴリ名
	 * @return /区切りのカテゴリ名から生成した文字列配列
	 */
//	public String[] loadCategory(String category_name) {
//		String sql = "select distinct regexp_split_to_array(:category_name, '/') from original;";
//		SqlParameterSource param = new MapSqlParameterSource().addValue("category_name", category_name);
//		String[] category = template.query(sql, param, ORIGINAL_ROW_MAPPER);
//		return category;
//	}
}
