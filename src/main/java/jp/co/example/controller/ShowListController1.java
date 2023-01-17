package jp.co.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.Category;
import jp.co.example.domain.ShowItem;
import jp.co.example.form.SearchForm;
import jp.co.example.service.AddItemService;
import jp.co.example.service.ShowListService;

/**
 * 商品の一覧表示を行うコントローラ.
 * 使用しないため後で消去。
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/notuse")
public class ShowListController1 {

	@Autowired
	private ShowListService showListService;

	@Autowired
	private AddItemService addItemService;

	@Autowired
	private HttpSession session;

	@ModelAttribute("searchForm")
	public SearchForm setUpSearchForm() {
		return new SearchForm();
	}

	/**
	 * @param model モデル
	 * @param page  ページ数
	 * @return 一来表示のページ
	 */
	@RequestMapping("/list")
	public String showList(@ModelAttribute("searchForm") SearchForm searchForm, Model model, Integer page) {
		List<ShowItem> itemList = new ArrayList<>();
		Integer offset = 0;
		if (page == null) {
			page = 1;
			itemList = showListService.showList(offset);
		} else {
			offset = (page - 1) * 30;
			itemList = showListService.showList(offset);
		}
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);
		model.addAttribute("itemList", itemList);
		int count = itemList.get(0).getCount();
		int pageCount = (count - 1) / 30 + 1;
		model.addAttribute("pageCount", pageCount);
		System.out.println("pageCount" + pageCount);
		model.addAttribute("page", page);
		session.setAttribute("searchForm", searchForm);
		
		return "list";
		
		
	}
}