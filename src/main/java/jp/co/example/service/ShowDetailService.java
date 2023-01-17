package jp.co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.ShowItem;
import jp.co.example.repository.ItemRepository;

/**
 * 商品の詳細を表示するサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class ShowDetailService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品をIDで1件取得する.
	 * @param id
	 * @return 商品情報
	 */
	public ShowItem findById(Integer id) {
		ShowItem item = itemRepository.findById(id);
		System.out.println("商品詳細サービス商品" + item.getName());
		return item;
	}
}