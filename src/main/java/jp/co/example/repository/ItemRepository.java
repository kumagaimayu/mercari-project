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

import jp.co.example.domain.Item;
import jp.co.example.domain.ShowItem;

/**
 * itemsテーブルを操作する
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * itemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	/**
	 * 商品数を含む商品表示に使用するローマッパー.
	 */
	private static final RowMapper<ShowItem> SHOW_ITEM_PAGE_ROW_MAPPER = new BeanPropertyRowMapper<>(ShowItem.class);

	/**
	 * 商品数を含まない商品表示に使用するローマッパー.
	 */
	private static final RowMapper<ShowItem> SHOW_ITEM_ROW_MAPPER = (rs, i) -> {
		ShowItem item = new ShowItem();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setCategory(rs.getString("category"));
		item.setBrand(rs.getString("brand"));
		item.setCondition(rs.getInt("condition"));
		item.setPrice(rs.getInt("price"));
		item.setShipping(rs.getInt("shipping"));
		item.setDescription(rs.getString("description"));
		return item;
	};

	/**
	 * originalテーブルとcategoryテーブルからItemテーブルに挿入する際に必要な情報を取得.
	 * 
	 * @return itemリスト
	 */
	public List<Item> findItemInfo() {
		String sql = "SELECT o.name as name, o.condition_id condition_id, c.id as category, o.brand brand, o.price price, o.shipping shipping, o.description description FROM original AS o LEFT OUTER JOIN category AS c ON o.category_name = c.name_all;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * itemテーブルにデータを挿入する.
	 */
	public void insertItem(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "insert into items (name, condition, category, brand, price,shipping, description)values(:name, :conditionId, :category, :brand, :price, :shipping, :description);";
		template.update(sql, param);
	}

	/**
	 * 商品の一覧表示を行う. ただし、全件取得せず30件ずつ取得するようにする
	 * 
	 * @return itemリスト
	 */
	public List<ShowItem> findAll(Integer offset) {
		String sql = "select i.count, i.id, i.name,i.condition, c.name_all as category, i.brand, i.price, i.shipping, i.description from (select count(id) over() as count, id,name,category,condition, brand, price,shipping,description from items order by id desc limit 30 offset :offset) as i left outer join category as c on i.category = c.id order by i.id desc;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("offset", offset);
		List<ShowItem> itemList = template.query(sql, param, SHOW_ITEM_PAGE_ROW_MAPPER);

		return itemList;
	}

	/**
	 * 商品を1件検索する
	 * 
	 * @param 商品ID
	 * @return 商品
	 */
	public ShowItem findById(Integer id) {
		String sql = "select i.id, i.name,i.condition, c.name_all as category, i.brand, i.price, i.shipping, i.description from items as i left outer join category as c on i.category = c.id where i.id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		ShowItem item = template.queryForObject(sql, param, SHOW_ITEM_ROW_MAPPER);
		return item;
	}

	/**
	 * 商品を追加する.
	 * 
	 * @param item 商品情報
	 */
	public Integer insert(Item item) {
		String sql = "insert into items(name,condition,category,brand,price,shipping,description) values(:name,:conditionId,:category,:brand,:price,:shipping,:description);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColumNames = { "id" };
		template.update(sql, param, keyHolder, keyColumNames);
		Integer id = keyHolder.getKey().intValue();
		return id;
	}

	/**
	 * 商品情報の変更を行う
	 * 
	 * @param item 商品
	 * @return ID 商品ID
	 */
	public Integer update(Item item) {
		String sql = "update items set name=:name,condition=:conditionId,category=:category,brand=:brand,price=:price,shipping=:shipping,description=:description where id=:id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
		return item.getId();
	}

	/**
	 * 名前、カテゴリ、ブランドから検索する.
	 * 
	 * @param name 商品名
	 * @param category　カテゴリ
	 * @param brand　ブランド
	 * @return
	 */
	public List<ShowItem> findByNameCategoryBrand(String name, Integer category, String brand, Integer offset) {
		System.out.println("名前" + name + category + brand);
		String sql = "select count(i.id) over() as count,i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description,c.name_all as category from items i left outer join category c on i.category = c.id where c.parent =:category and i.name ilike :name and i.brand ilike :brand order by id desc limit 30 offset :offset";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("category", category).addValue("brand", "%" + brand + "%").addValue("offset", offset);
		List<ShowItem> itemList = template.query(sql, param, SHOW_ITEM_PAGE_ROW_MAPPER);
		return itemList;
	}

	/**
	 * カテゴリから検索する（大カテゴリまで入力）.
	 * 
	 * @param category カテゴリ
	 * @return 商品一覧
	 */
	public List<ShowItem> findByBigCategory(String name, Integer category, String brand, Integer offset) {
		String sql = "select count(i.id) over() as count,i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description, cc.name_all as category from items i \n"
				+ " left outer join category c on i.category = c.id join category cc on c.parent = cc.id\n"
				+ " where cc.parent =:category and i.name ilike :name and i.brand ilike :brand order by id desc limit 30 offset :offset";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("category", category).addValue("brand", "%" + brand + "%").addValue("offset", offset);
		List<ShowItem> itemList = template.query(sql, param, SHOW_ITEM_PAGE_ROW_MAPPER);
		return itemList;
	}

	/**
	 * カテゴリから検索する（中カテゴリまで入力）.
	 * 
	 * @param category カテゴリ
	 * @return 商品一覧
	 */
	public List<ShowItem> findByMiddleCategory(String name, Integer category, String brand, Integer offset) {
		String sql = "select count(i.id) over() as count,i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description,c.name_all as category from items i left outer join category c on i.category = c.id where c.parent =:category and i.name ilike :name and i.brand ilike :brand order by id desc limit 30 offset :offset";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("category", category).addValue("brand", "%" + brand + "%").addValue("offset", offset);
		List<ShowItem> itemList = template.query(sql, param, SHOW_ITEM_PAGE_ROW_MAPPER);
		return itemList;
	}

	// Categoryがない場合のメソッド
	public List<ShowItem> findByNameBrand(String name, String brand, Integer offset) {
		String sql = "select count(i.id) over() as count, i.id, i.name, i.condition, i.category, i.brand, i.price, i.shipping, i.description, c.name_all as category from items i left outer join category c on i.category = c.id where i.name ilike :name and i.brand ilike :brand order by id desc limit 30 offset :offset";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
				.addValue("brand", "%" + brand + "%").addValue("offset", offset);
		List<ShowItem> itemList = template.query(sql, param, SHOW_ITEM_PAGE_ROW_MAPPER);
		return itemList;
	}
}