package jp.co.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.ShowItem;
import jp.co.example.service.ShowDetailService;

/**
 * 商品詳細を表示するコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/showDetail")
public class ShowDetailController {

	@Autowired
	private ShowDetailService showDetailService;
	
	@RequestMapping("/detail")
	public String showDetail(Integer id, Model model) {
		ShowItem item = showDetailService.findById(id);
		model.addAttribute("item", item);
		return "detail";
	}

}
