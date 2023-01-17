package jp.co.example.domain;

/**
 * 商品を表示するためのドメイン.
 * 
 * @author kumagaimayu
 *
 */
public class ShowItem {
	/** 商品ID */
	private Integer id;
	/** 商品名 */
	private String name;
	/** 状態 */
	private Integer condition;
	/** 商品カテゴリ */
	private String category;
	/** ブランド */
	private String brand;
	/** 価格 */
	private double price;
	/** 発送 */
	private Integer shipping;
	/** 説明 */
	private String description;
	/** 商品の個数 */
	private Integer count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ShowItem [id=" + id + ", name=" + name + ", condition=" + condition + ", category=" + category
				+ ", brand=" + brand + ", price=" + price + ", shipping=" + shipping + ", description=" + description
				+ ", count=" + count + "]";
	}
}