package jp.co.example.common;

/**
 * コンディションIDの定数クラス群.
 * 
 * @author kumagaimayu
 *
 */
public enum Condition {
	MINT("mint", 1), NEAR_MINT("near mint", 2), EXCELLENT("excellent", 3), VERY_GOOD("very good", 4), GOOD("good", 5);

	private final String key;
	private final int value;

	/**
	 * コンディションのIDから一致するインスタンスを返す.
	 * 
	 * @param id コンディションID
	 * @return 一致するインスタンス
	 */
	public static Condition getByValue(int id) {
		for (Condition condition : Condition.values()) {
			if (condition.getValue() == id) {
				return condition;
			}
		}
		return null;
	}

	private Condition(final String key, final int value) {
		this.key = key;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String getKey() {
		return key;
	}
}