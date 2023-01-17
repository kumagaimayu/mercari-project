package jp.co.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.example.domain.User;

/**
 * Userテーブルを操作するリポジトリ.
 * 
 * @author kumagaimayu
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザーオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);

	/**
	 * ユーザーを追加する.
	 * 
	 * @param user ユーザー
	 */
	public void insert(User user) {
		String sql = "insert into users (name, mailaddress, password,authority) values(:name, :mailAddress, :password, :authority);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}

	/**
	 * ユーザーを1件検索します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return ユーザー
	 */
	public User findByMail(String mailAddress) {
		String sql = "select id, name, mailaddress, password from users where mailaddress = :mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			System.out.println("通過!!!!!!");
			return null;
		}
		return userList.get(0);
	}
}