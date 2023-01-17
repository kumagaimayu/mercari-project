package jp.co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.User;
import jp.co.example.repository.UserRepository;

/**
 * ユーザー登録を行うサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class RegisterService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザーを追加する.
	 * 
	 * @param user ユーザー
	 */
	public void register(User user) {
		System.out.println("サービスクラス" + user);
		// パスワードをハッシュ化
		// encodeメソッドでハッシュ化
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		// ハッシュ化されたパスワードが入る
		userRepository.insert(user);
	}
}
