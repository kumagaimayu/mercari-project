package jp.co.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザー登録時に使用するフォーム.
 * 
 * @author kumagaimayu
 *
 */
public class RegisterForm {

	/** ユーザー名 */
	@NotBlank(message = "名前は必須です。")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です。")
	@Email(message = "形式が不正です。")
	private String mailAddress;
	/** パスワード */
	@Pattern(regexp = "^(?=.*[A-Z])[a-zA-Z0-9]{8,24}$", message = "大文字、小文字、数字を含めたパスワードを入力してください。")
	@Size(min = 8, max = 24, message = "パスワードは8文字以上24文字以内で入力してください。")
	private String password;
	/** 権限 */
	private String authority;

	/**
	 * 権限情報をIntegerで返す
	 * 
	 * @return Integer型の権限情報
	 */
	public Integer getIntAuthority() {
		return Integer.parseInt(authority);
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

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "RegisterForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + ", authority="
				+ authority + "]";
	}

}
