package jp.co.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.example.domain.User;

@SpringBootTest
class UserRepositoryTest {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private UserRepository userRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("UserRepositoryのテスト開始");
		User user = new User();
		user.setName("テスト太郎");
		user.setMailAddress("test@example.com");
		user.setPassword("testPass11");
		user.setAuthority(0);
		userRepository.insert(user);
		System.out.println("インサート終了");
	}

	@AfterEach
	void tearDown() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", "test@example.com");
		template.update("delete from users where mailaddress = :mailAddress", param);
		System.out.println("テスト用データの削除");
	}

	@Test
	void findByMailTest() {
		System.out.println("メールアドレスで検索するテスト開始");
		User resultUser = userRepository.findByMail("test@example.com");
		System.out.println(resultUser);
		assertEquals("テスト太郎", resultUser.getName(), "名前が登録されていません");
		assertEquals("test@example.com", resultUser.getMailAddress(), "メールアドレスが登録されていません");
		// パスワードハッシュ化,Encorder使用
		assertEquals("testPass11", resultUser.getPassword(), "パスワードが登録されていません");
		assertEquals(0, resultUser.getAuthority(), "権限情報がありません");
		System.out.println("メールアドレスで検索するテスト終了");
	}

	@Test
	void findByMailMiss() {
		System.out.println("メールアドレスで検索し失敗させるテスト開始");
		User resultUser = userRepository.findByMail("test@example.com");
		assertEquals("テスト次郎", resultUser.getName(), "名前が登録されていません");
		System.out.println("メールアドレスで検索し失敗させるテスト終了");
	}
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
}