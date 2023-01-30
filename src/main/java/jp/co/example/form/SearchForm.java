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
	private String bigCategory;
	/** 中カテゴリ */
	private String middleCategory;
	/** 小カテゴリ */
	private String smallCategory;
	/** ブランド */
	private String brand;

	/**
	 * @return Integer型のsmallCategory
	 */
	public Integer getIntSmallCategory() {
		return Integer.parseInt(smallCategory);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBigCategory() {
		return bigCategory;
	}

	public void setBigCategory(String bigCategory) {
		this.bigCategory = bigCategory;
	}

	public String getMiddleCategory() {
		return middleCategory;
	}

	public void setMiddleCategory(String middleCategory) {
		this.middleCategory = middleCategory;
	}

	public String getSmallCategory() {
		return smallCategory;
	}

	public void setSmallCategory(String smallCategory) {
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
