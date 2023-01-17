package jp.co.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

	// cssファイルなどへのアクセス制限に関する記述.
	// TODO 書き方修正必要？
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/fonts/**");
	}

	/**
	 * @param http
	 * @return ログイン成功時は商品一覧、ログイン失敗時はログイン画面に遷移.
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// ログインしていなくても遷移できる場所
		http.authorizeHttpRequests().antMatchers("/login/toLogin", "/showList/", "/register/toRegister",
				"/register/register", "/showDetail/detail", "/addItem/findChildCategory","/showList/top").permitAll().anyRequest()
				.authenticated();

		http.formLogin() // ログイン時の設定
				.loginPage("/login/toLogin") // ログイン画面
				.loginProcessingUrl("/login/") // ログイン情報の送信先URL
				.failureUrl("/login/toLogin?error=true") // ログイン失敗後のパス
				.defaultSuccessUrl("/showList/", true) // ログイン成功後リダイレクト先
				.usernameParameter("mailAddress") // 認証時に使用するユーザ名のリクエストパラメータ名(今回はメールアドレスを使用)
				.passwordParameter("password");// 認証時に使用するパスワードのリクエストパラメータ名

		http.logout() // ログアウトの設定
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout/")) //ログアウト後の遷移先
				.logoutSuccessUrl("/showList/") // ログアウト後に遷移させるパス(ここではログイン画面を設定)
				.deleteCookies("JSESSIONID") // ログアウト後Cookieに保存されているsessionIDを削除
				.invalidateHttpSession(true); // ログアウト後sessionを削除

		return http.build();
	}
	
	// パスワードハッシュ化
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
