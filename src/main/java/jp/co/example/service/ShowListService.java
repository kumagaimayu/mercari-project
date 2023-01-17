package jp.co.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.ShowItem;
import jp.co.example.repository.ItemRepository;

/**
 * 商品の一覧表示を行うサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class ShowListService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品一覧を全件表示する.
	 * 
	 * @return itemList
	 */
	public List<ShowItem> showList(Integer offset) {
		List<ShowItem> itemList = itemRepository.findAll(offset);
		return itemList;
	}
}