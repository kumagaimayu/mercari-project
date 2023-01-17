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

	public List<Item> findItemInfo() {
		List<Item> itemList = itemRepository.findItemInfo();
		System.out.println(itemList.size()+"サービスで作成したitemListの数");
		return itemList;
	}
	
	public void insertItem(Item item) {
		itemRepository.insertItem(item);
	}
}