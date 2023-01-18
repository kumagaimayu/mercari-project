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
	private String conditionId;
	/** カテゴリ */
	private Integer category;
	/** ブランド */
	private String brand;
	/** 価格 */
	@NotBlank(message = "値段は必須です。")
	private String price;
	/** 発送 */
	private Integer shipping;
	/** 説明 */
	private String description;
	/** 大カテゴリの値 */
	private String bigCategoryList;
	/** 中カテゴリの値 */
	private String middleCategoryList;

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
	 * @return Integer型の状態
	 */
	public Integer getIntConditionId() {
		return Integer.parseInt(conditionId);
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

	public String getConditionId() {
		return conditionId;
	}

	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
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

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
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
		return "AddItemForm [id=" + id + ", name=" + name + ", conditionId=" + conditionId + ", category=" + category
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ "]";
	}

	public String getBigCategoryList() {
		return bigCategoryList;
	}

	public void setBigCategoryList(String bigCategoryList) {
		this.bigCategoryList = bigCategoryList;
	}

	public String getMiddleCategoryList() {
		return middleCategoryList;
	}

	public void setMiddleCategoryList(String middleCategoryList) {
		this.middleCategoryList = middleCategoryList;
	}
}