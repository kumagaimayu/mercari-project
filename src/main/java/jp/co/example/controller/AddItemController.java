package jp.co.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.example.domain.Category;
import jp.co.example.domain.Item;
import jp.co.example.form.AddItemForm;
import jp.co.example.service.AddItemService;

/**
 * 商品を追加するコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/addItem")
public class AddItemController {

	@Autowired
	private AddItemService addItemService;

	/**
	 * 商品追加画面の表示.
	 * 
	 * @param addItemForm 商品追加のためのフォーム
	 * @param result      エラーを格納するためのresult
	 * @param model       モデル
	 * @return 商品追加画面
	 */
	@RequestMapping("/toAddItem")
	public String toAddItem(AddItemForm addItemForm, Model model) {
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);
		return "add";
	}

	/**
	 * 商品を追加する.
	 * 
	 * @param addItemForm 商品追加のためのフォーム
	 * @param result      エラーを格納するためのresult
	 * @param model       モデル
	 * @return 詳細画面に遷移
	 */
	@RequestMapping("/addItem")
	public String addItem(@Validated AddItemForm addItemForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toAddItem(addItemForm, model);
		}
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);
		Item item = new Item();
		BeanUtils.copyProperties(addItemForm, item);
		item.setShipping(0);
		item.setPrice(addItemForm.getDoublePrice());
		Integer id = addItemService.addItem(item);
		return "redirect:/showDetail/detail?id=" + id;
	}

	/**
	 * 中小カテゴリを検索する.
	 * 
	 * @param id    ID
	 * @param model モデル
	 * @return 中小カテゴリのリスト
	 */
	@RequestMapping("/findChildCategory")
	@ResponseBody
	public List<Category> setChildSelectBox(@RequestBody Category category) {
		List<Category> childList = addItemService.findChildCategory(category.getId());
		return childList;
	}
}