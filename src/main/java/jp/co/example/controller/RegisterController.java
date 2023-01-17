package jp.co.example.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.User;
import jp.co.example.form.RegisterForm;
import jp.co.example.service.RegisterService;

/**
 * ユーザー登録を行うコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	/**
	 * ユーザー登録ページの表示.
	 * 
	 * @param registerForm 登録用フォーム
	 * @return ユーザー登録ページ
	 */
	@RequestMapping("/toRegister")
	public String toRegister(RegisterForm registerForm) {
		System.out.println("画面表示");
		return "register";
	}

	/**
	 * ユーザー登録を行う.
	 * 
	 * @param registerForm 登録用フォーム
	 * @param result       エラーを格納する
	 * @param model        モデル
	 * @return ログイン画面
	 */
	@RequestMapping("/register")
	public String register(@Validated RegisterForm registerForm, BindingResult result, Model model) {
		System.out.println("コントローラーまで来たよ");
		// 入力内容を保持しつつ登録画面に遷移
		System.out.println("フォームに入ってるか？" + registerForm);
		
		if (result.hasErrors()) {
			return toRegister(registerForm);
		}
		
		User user = new User();
		BeanUtils.copyProperties(registerForm, user);
		// authorityは0とする.
		user.setAuthority(0);
		registerService.register(user);
		System.out.println("ユーザー情報" + user);
		return "redirect:/login/toLogin";
	}
}
