package jp.co.example.domain;

/**
 * Categoryテーブルを表すドメイン.
 * 
 * @author kumagaimayu
 *
 */
public class Category {

	/** カテゴリID */
	private int categoryId;
	/** カテゴリ名 */
	private String name;
	/** パス */
	private String path;
	/** 深さ */
	private int depth;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", path=" + path + ", depth=" + depth + "]";
	}

}
