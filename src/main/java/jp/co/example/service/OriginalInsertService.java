package jp.co.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.domain.Original;
import jp.co.example.repository.OriginalRepository;

/**
 * データをインサートするためのサービス.
 * 
 * @author kumagaimayu
 *
 */
@Service
@Transactional
public class OriginalInsertService {

	@Autowired
	private OriginalRepository originalRepository;

	/**
	 * originalテーブルにデータを挿入する.
	 * 
	 * @param original データ
	 */
	public void originalInsert(Original original) {
		originalRepository.insert(original);
	}
}