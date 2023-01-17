package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jp.co.example.domain.Item;
import jp.co.example.repository.ItemRepository;

/**
 * itemテーブルに情報を挿入するためのサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class InsertItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * itemテーブルに商品を追加する際に必要な情報を取得.
	 * @return 商品のリスト
	 */
	public List<Item> findItemInfo() {
		List<Item> itemList = itemRepository.findItemInfo();
		return itemList;
	}
	
	/**
	 * itemテーブルに商品情報追加
	 * @param item 商品情報
	 */
	public void insertItem(Item item) {
		itemRepository.insertItem(item);
	}
}