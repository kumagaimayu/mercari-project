package jp.co.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウト機能を制御するコントローラー.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

	@Autowired
	private HttpSession session;

	/**
	 * ログアウトします.
	 * 
	 * @return 商品一覧
	 */
	@RequestMapping("/")
	public String logout() {
		session.invalidate();
		return "list";
	}
}