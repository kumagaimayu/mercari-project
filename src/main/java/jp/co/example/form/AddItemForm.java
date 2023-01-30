package jp.co.example.form;

import javax.validation.constraints.NotBlank;

/**
 * 商品を追加する際に使用するフォーム.
 * 
 * @author kumagaimayu
 *
 */
public class AddItemForm {

	/** 商品ID */
	private String id;
	/** 商品名 */
	@NotBlank(message = "商品名は必須です。")
	private String name;
	/** 状態 */
	@NotBlank(message = "コンディションを選択してください。")
	private String condition;
	/** カテゴリ */
	private String category;
	/** ブランド */
	private String brand;
	/** 価格 */
	@NotBlank(message = "値段は必須です。")
	private String price;
	/** 発送 */
	private String shipping;
	/** 説明 */
	private String description;

	/**
	 * @return Integer型ID
	 */
	public Integer getIntid() {
		return Integer.parseInt(id);
	}

	/**
	 * @return double型値段
	 */
	public double getDoublePrice() {
		return Double.parseDouble(price);
	}

	/**
	 * @return Integer型のカテゴリ
	 */
	public Integer getIntCategory() {
		return Integer.parseInt(category);
	}

	/**
	 * @return Integer型の発送時間
	 */
	public Integer getIntShipping() {
		return Integer.parseInt(shipping);
	}

	/**
	 * @return Integer型の状態
	 */
	public Integer getIntCondition() {
		return Integer.parseInt(condition);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AddItemForm [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ "]";
	}
}