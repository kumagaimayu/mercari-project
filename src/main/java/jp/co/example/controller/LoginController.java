package jp.co.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.form.LoginForm;

/**
 * ログインを行うコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {


	/**
	 * ログイン画面表示.
	 * 
	 * @param loginForm ログインフォーム
	 * @param model モデル
	 * @param error エラーの有無（正しいログイン情報が入力されたか）
	 * @return ログイン画面
	 */
	@RequestMapping("/toLogin")
	public String toLogin(LoginForm loginForm, Model model, Boolean error) {
		//初期表示などの場合
		if (error == null) {
			return "login";
		}
		if (error) {
			model.addAttribute("loginError", "メールアドレスかパスワードが不正です。");
		}
		return "login";
	}

	/**
	 * ログインを行い、正常に行われた場合商品一覧に遷移.
	 * 
	 * @return 商品一覧
	 */
	@RequestMapping("/")
	public String login() {
		return "/showList/top";
	}
}