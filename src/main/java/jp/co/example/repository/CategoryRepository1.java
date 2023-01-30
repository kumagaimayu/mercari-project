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

import jp.co.example.domain.Category1;

/**
 * 【改修前】categoryテーブルを操作するリポジトリ.
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class CategoryRepository1 {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * カテゴリーオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Category1> CATEGORY_NAME_ROW_MAPPER = new BeanPropertyRowMapper<>(Category1.class);

	/**
	 * カテゴリテーブルに情報を挿入する.
	 * 
	 * @param category カテゴリ
	 * @return ID
	 */
	public Integer insertCategory(Category1 category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "insert into category (parent,name,name_all) values(:parent,:name,:name_all);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumNames = { "id" };
		template.update(sql, param, keyHolder, keyColumNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}

	/**
	 * 大カテゴリのみのリストを取得する.
	 * 
	 * @return 大カテゴリのリスト
	 */
	public List<Category1> findBigCategoryName() {
		String sql = "select id, parent, name, name_all from category where parent is null";
		List<Category1> bigCategoryList = template.query(sql, CATEGORY_NAME_ROW_MAPPER);
		return bigCategoryList;
	}

	/**
	 * 中小カテゴリのみのリストを取得する.
	 * 
	 * @return 中小カテゴリのリスト
	 */
	public List<Category1> findChildCategoryName(Integer parentId) {
		String sql = "select id, parent, name, name_all from category where parent = :parent;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("parent", parentId);
		List<Category1> childCategoryList = template.query(sql, param, CATEGORY_NAME_ROW_MAPPER);
		return childCategoryList;
	}

	/**
	 * name_allから商品IDを取得する.
	 * 
	 * @param name_all
	 * @return 小カテゴリID
	 */
	public Integer findByNameAll(String name_all) {
		String sql = "select id,parent, name, name_all from category where name_all=:name_all;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name_all", name_all);
		Category1 smallCategory = template.queryForObject(sql, param, CATEGORY_NAME_ROW_MAPPER);
		Integer smallCategoryId = smallCategory.getId();
		return smallCategoryId;
	}

	/**
	 * 小カテゴリIDからparentIdを取得する.
	 * 
	 * @param smallCategoryId 小カテゴリID
	 * @return parentId 中カテゴリのID
	 */
	public Integer findParentIdById(Integer smallCategoryId) {
		String sql = "select id,parent, name, name_all from category where id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", smallCategoryId);
		Category1 smallCategory = template.queryForObject(sql, param, CATEGORY_NAME_ROW_MAPPER);
		Integer parentId = smallCategory.getParent();
		return parentId;
	}

	/**
	 * parentIdから親のカテゴリIDを取得する.
	 * 
	 * @param parentId
	 * @return 大中（親の）カテゴリID
	 */
	public Integer findParentId(Integer id) {
		String sql = "select id,parent, name, name_all from category where id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Category1 parentCategory = template.queryForObject(sql, param, CATEGORY_NAME_ROW_MAPPER);
		Integer parentId = parentCategory.getParent();
		return parentId;
	}

	/**
	 * 中カテゴリIDを取得する.
	 * 
	 * @param middleCategoryName 中カテゴリ名
	 * @return 中カテゴリID
	 */
	public Integer findMiddleId(String middleCategoryName) {
		String sql = "select id,parent, name, name_all from category where name =:name and parent is not null and name_all is null;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", middleCategoryName);
		Category1 middleCategory = template.queryForObject(sql, param, CATEGORY_NAME_ROW_MAPPER);
		Integer middleCategoryId = middleCategory.getId();
		return middleCategoryId;
	}

	public Integer findBigId(String bigCategoryName) {
		String sql = "select id,parent, name, name_all from category where name =:name and parent is null and name_all is null;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", bigCategoryName);
		Category1 bigCategory = template.queryForObject(sql, param, CATEGORY_NAME_ROW_MAPPER);
		Integer bigCategoryId = bigCategory.getId();
		return bigCategoryId;
	}
}
