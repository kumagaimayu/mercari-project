package jp.co.example.common;

/**
 * コンディションIDの定数クラス群.
 * 
 * @author kumagaimayu
 *
 */
public enum ConditionId {
	NEW("new", 1), ALMOST＿NEW("almost new", 2), USED("used", 3),;

	private final String key;
	private final int value;

	private ConditionId(final String key, final int value) {
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
