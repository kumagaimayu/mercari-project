package jp.co.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.Category;
import jp.co.example.domain.Item;
import jp.co.example.domain.ShowItem;
import jp.co.example.form.AddItemForm;
import jp.co.example.service.AddItemService;
import jp.co.example.service.EditItemService;
import jp.co.example.service.ShowDetailService;

/**
 * 商品情報の変更を行うコントローラ.
 * 
 * @author kumagaimayu
 */
@Controller
@RequestMapping("/editItem")
public class EditItemController {

	@Autowired
	private EditItemService editItemService;

	@Autowired
	private AddItemService addItemService;

	@Autowired
	private ShowDetailService showDetailService;

	@Autowired
	private EditItemService ediItemService;

	/**
	 * 編集画面の表示
	 * 
	 * @param addItemForm フォーム
	 * @param result      エラーを格納するresult
	 * @param model       モデル
	 * @param id          商品ID
	 * @return 編集画面
	 */
	@RequestMapping("/toEditItem")
	public String toEditItem(AddItemForm addItemForm, BindingResult result, Model model, Integer id) {
		// 表示用のitem情報が格納
		// Item型に直す
		ShowItem item = showDetailService.findById(id);
		model.addAttribute("item", item);
		// splitして配列のListを作って
		String[] categoryNameArray = item.getCategory().split("/");
		// 小カテゴリのIDを取得してモデルに入れる
		Integer smallCategoryId = ediItemService.findByNameAll(item.getCategory());
		model.addAttribute("smallCategoryId", smallCategoryId);
		// 中カテゴリの名前
		String middleCategoryName = categoryNameArray[1];
		model.addAttribute("middleCategoryName", middleCategoryName);
		// 中カテゴリIDを取得
		Integer middleCategoryId = editItemService.findMiddleId(smallCategoryId);
		model.addAttribute("middleCategoryId", middleCategoryId);
		// 大カテゴリIDを取得
		Integer bigCategoryId = editItemService.findParentId(middleCategoryId);
		model.addAttribute("bigCategoryId", bigCategoryId);
		// 小カテゴリの名前
		String smallCategoryName = categoryNameArray[2];
		model.addAttribute("smallCategoryName", smallCategoryName);
		// 親が選択された時点でidが送られるためjsを経由して表示可能 IDを入れるよ
		List<Category> smallCategoryList = addItemService.findChildCategory(middleCategoryId);
		model.addAttribute("smallCategoryList", smallCategoryList);
		// 大カテゴリの名前
		String bigCategoryName = categoryNameArray[0];
		model.addAttribute("bigCategoryName", bigCategoryName);
		// 親が選択された時点でidが送られるためjsを経由して表示可能
		List<Category> middleCategoryList = addItemService.findChildCategory(bigCategoryId);
		model.addAttribute("middleCategoryList", middleCategoryList);
		// 編集画面表示用のカテゴリプルダウン情報をモデルに格納
		List<Category> bigCategoryList = addItemService.findBigCategory();
		model.addAttribute("bigCategoryList", bigCategoryList);
		return "edit";
	}

	/**
	 * 編集を行う.
	 * 
	 * @param addItemForm フォーム
	 * @param result      エラーを格納するresult
	 * @param model       モデル
	 * @return 編集した商品詳細ページ
	 */
	@RequestMapping("/editItem")
	public String editItem(AddItemForm addItemForm, BindingResult result, Model model) {
		Item item = new Item();
		BeanUtils.copyProperties(addItemForm, item);
		item.setShipping(0);
		item.setId(Integer.parseInt(addItemForm.getId()));
		Integer id = editItemService.edit(item);
		return "redirect:/showDetail/detail?id=" + id;
	}
}