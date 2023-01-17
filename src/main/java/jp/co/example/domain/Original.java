package jp.co.example.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * originalテーブル情報を表すドメイン.
 * 
 * @author kumagaimayu
 *
 */
@JsonPropertyOrder({"train_id", "name", "item_condition_id","category_name","brand_name", "price","shipping", "item_description"})
public class Original {

	/** ID */
	private Integer train_id;
	/** 名前 */
	private String name;
	/** 商品の状態 */
	private Integer item_condition_id;
	/** カテゴリ名 */
	private String category_name;
	/** ブランド名 */
	private String brand_name;
	/** 値段 */
	private double price;
	/** 出荷 */
	private Integer shipping;
	/** 説明 */
	private String item_description;

	public Integer getTrain_id() {
		return train_id;
	}

	public void setTrain_id(Integer train_id) {
		this.train_id = train_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getItem_condition_id() {
		return item_condition_id;
	}

	public void setItem_condition_id(Integer item_condition_id) {
		this.item_condition_id = item_condition_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	@Override
	public String toString() {
		return "Original [train_id=" + train_id + ", name=" + name + ", item_condition_id=" + item_condition_id
				+ ", category_name=" + category_name + ", brand_name=" + brand_name + ", price=" + price + ", shipping="
				+ shipping + ", item_description=" + item_description + "]";
	}
}