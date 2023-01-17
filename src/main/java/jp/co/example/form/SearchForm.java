package jp.co.example.form;

/**
 * 商品検索用フォーム.
 * 
 * @author kumagaimayu
 *
 */
public class SearchForm {

	/** 商品名 */
	private String name;
	/** 大カテゴリ */
	private Integer bigCategory;
	/** 中カテゴリ */
	private Integer middleCategory;
	/** 小カテゴリ */
	private Integer smallCategory;
	/** ブランド */
	private String brand;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBigCategory() {
		return bigCategory;
	}

	public void setBigCategory(Integer bigCategory) {
		this.bigCategory = bigCategory;
	}

	public Integer getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(Integer middleCategory) {
		this.middleCategory = middleCategory;
	}

	public Integer getSmallCategory() {
		return smallCategory;
	}

	public void setSmallCategory(Integer smallCategory) {
		this.smallCategory = smallCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "SearchForm [name=" + name + ", bigCategory=" + bigCategory + ", middleCategory=" + middleCategory
				+ ", smallCategory=" + smallCategory + ", brand=" + brand + "]";
	}

}
