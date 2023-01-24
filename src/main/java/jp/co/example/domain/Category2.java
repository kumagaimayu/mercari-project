package jp.co.example.domain;

/**
 * Categoryテーブルを表すドメイン.
 * 
 * @author kumagaimayu
 *
 */
public class Category2 {

	private int category_id;
	private String name;
	private String path;
	private int depth;

	public int getId() {
		return category_id;
	}

	public void setId(int category_id) {
		this.category_id = category_id;
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
		return "Category2 [category_id=" + category_id + ", name=" + name + ", path=" + path + ", depth=" + depth + "]";
	}

}
