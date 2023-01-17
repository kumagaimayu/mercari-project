package jp.co.example.domain;

/**
 * ユーザードメイン.
 * 
 * @author kumagaimayu
 *
 */
public class User {

	/** ユーザーID */
	private Integer id;
	/** ユーザー名 */
	private String name;
	/** メールアドレス */
	private String mailAddress;
	/** パスワード */
	private String password;
	/** ユーザー名 */
	private Integer authority;

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

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", authority=" + authority + "]";
	}

}
