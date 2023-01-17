package jp.co.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import jp.co.example.domain.Original;
import jp.co.example.service.OriginalInsertService;

/**
 * 
 * @author kumagaimayu
 *
 */
@Controller
@RequestMapping("/")
public class OriginalController {

	@Autowired
	private OriginalInsertService originalInsertService;

	@RequestMapping("/readTsv")
	public String readTsv() throws IOException {

		CsvMapper mapper = new CsvMapper();
		// ヘッダあり、タブ区切り
		CsvSchema schema = mapper.schemaFor(Original.class).withHeader().withColumnSeparator('\t');

		Path path = Paths.get("./train.tsv");
		try (BufferedReader br = Files.newBufferedReader(path)) {

			MappingIterator<Original> it = mapper.readerFor(Original.class).with(schema).readValues(br);
			
			//全てのデータが挿入されている
			List<Original> originalList = it.readAll();
			for (Original original : originalList) {
				originalInsertService.originalInsert(original);
				if(original.getTrain_id()%10000==0) {
					System.out.println(original.getTrain_id() + "件挿入");
				}
			}
		}
		return "index";
	}

//	@RequestMapping("/insert")
//	public String dataInsert(Original original) {
//		originalInsertService.originalInsert(original);
//		return "index";
//	}
}
