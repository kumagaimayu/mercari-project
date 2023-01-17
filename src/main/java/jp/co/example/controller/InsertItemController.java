package jp.co.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.example.domain.Item;
import jp.co.example.service.InsertItemService;

/**
 * itemsテーブルに挿入するコントローラ.
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/")
public class InsertItemController {
	
	@Autowired
	private InsertItemService insertItemService;
	
	@RequestMapping("/insertItem")
	public String insertItem() {
		List<Item> itemList = insertItemService.findItemInfo();
		for(Item item : itemList) {
			insertItemService.insertItem(item);
		}
		return "index";
	}
}