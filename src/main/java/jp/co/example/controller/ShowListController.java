package jp.co.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.Category;
import jp.co.example.domain.LoginUser;
import jp.co.example.domain.ShowItem;
import jp.co.example.form.SearchForm;
import jp.co.example.service.AddItemService;
import jp.co.example.service.SearchService;
import jp.co.example.service.ShowListService;

/**
 * 商品を一覧表示、検索するコントローラー.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/showList")
public class ShowListController {

	@Autowired
	private SearchService searchService;

	@Autowired
	private AddItemService addItemService;

	@Autowired
	private ShowListService showListService;

	@ModelAttribute("searchForm")
	public SearchForm setUpSearchForm() {
		return new SearchForm();
	}

	@Autowired
	private HttpSession session;

	/**
	 * 商品を検索する.
	 * 
	 * @return 結果の一覧ページ
	 */
	@RequestMapping("/")
	public String searchByNameCategoryBrand(@ModelAttribute("searchForm") SearchForm searchForm, Model model,
			Integer page, @AuthenticationPrincipal LoginUser loginUser) {
		List<ShowItem> itemList = new ArrayList<>();

		// Goが押された時
		if (searchForm.getName() == null && searchForm.getBigCategory() == null
				&& searchForm.getMiddleCategory() == null && searchForm.getSmallCategory() == null
				&& searchForm.getBrand() == null) {
			searchForm = (SearchForm) session.getAttribute("searchForm");
		}

		Integer offset = 0;
		if (page == null) {
			page = 1;
			itemList = searchService.search(searchForm, offset);
		} else {
			offset = (page - 1) * 30;
			itemList = searchService.search(searchForm, offset);
		}

		// 大カテゴリ表示用
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);

		// もし中カテゴリに値が入っていたら
		if (searchForm.getMiddleCategory() == null) {
			List<Category> middleCategoryList = addItemService.findChildCategory(searchForm.getBigCategory(), 2);
			List<Category> smallCategoryList = addItemService
					.findChildCategory(searchForm.getBigCategory() + "/" + searchForm.getMiddleCategory(), 3);
			model.addAttribute("middleCategoryList", middleCategoryList);
			model.addAttribute("smallCategoryList", smallCategoryList);

			// もし大カテゴリに値が入っていたら
		} else if (searchForm.getBigCategory() != null) {
			List<Category> middleCategoryList = addItemService.findChildCategory(searchForm.getBigCategory(), 2);
			model.addAttribute("middleCategoryList", middleCategoryList);
		}

		if (itemList.isEmpty()) {
			// 検索結果が0件の時に全件表示、エラー文表示
			model.addAttribute("result", "該当する商品がありません。");
			itemList = showListService.showList(offset);
		}

		int count = itemList.get(0).getCount();
		int pageCount = (count - 1) / 30 + 1;
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("page", page);
		model.addAttribute("itemList", itemList);
		session.setAttribute("searchForm", searchForm);
		return "list";
	}

	/**
	 * 検索情報などを保持しない商品一覧の表示を行う.
	 * 
	 * @param model     モデル
	 * @param page      ページ
	 * @param loginUser ユーザーのログイン情報
	 * @return 商品一覧画面
	 */
	@RequestMapping("/top")
	public String top(Model model, Integer page, @AuthenticationPrincipal LoginUser loginUser) {
		List<ShowItem> itemList = new ArrayList<>();
		Integer offset = 0;
		if (page == null) {
			page = 1;
			itemList = showListService.showList(offset);
		} else {
			offset = (page - 1) * 30;
			itemList = showListService.showList(offset);
		}

		// Formをインスタンス化
		session.setAttribute("searchForm", new SearchForm());
		// 大カテゴリ表示用
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);
		int count = itemList.get(0).getCount();
		int pageCount = (count - 1) / 30 + 1;
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("page", page);
		model.addAttribute("itemList", itemList);
		return "list";
	}
}