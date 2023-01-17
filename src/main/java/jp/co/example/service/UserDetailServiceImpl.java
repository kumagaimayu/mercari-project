package jp.co.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.LoginUser;
import jp.co.example.domain.User;
import jp.co.example.repository.UserRepository;


@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
		User user = userRepository.findByMail(mailAddress);
//		if (user == null) {
//			throw new UsernameNotFoundException("そのメールアドレスは登録されていません。");
//		}
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER")); // ユーザ権限付与
//		if(administrator.isAdmin()) {
//			authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
//		}
		return new LoginUser(user, authorityList);
	}
}