package jp.co.example.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * categoryテーブルの情報を表すドメイン.
 * 
 * @author kumagaimayu
 *
 */
@JsonPropertyOrder({ "id", "parent", "name", "name_all" })
public class Category1 {

	/** ID */
	private Integer id;
	/** 親カテゴリID */
	private Integer parent;
	/** カテゴリ名 */
	private String name;
	/** 全てのカテゴリ名 */
	private String name_all;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_all() {
		return name_all;
	}

	public void setName_all(String name_all) {
		this.name_all = name_all;
	}

	@Override
	public String toString() {
		return "Category1 [id=" + id + ", parent=" + parent + ", name=" + name + ", name_all=" + name_all + "]";
	}
}