package jp.co.example.service;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jp.co.example.domain.Item;
import jp.co.example.repository.CategoryRepository1;
import jp.co.example.repository.ItemRepository;

@SpringBootTest
class AddItemServiceTest {

//	@InjectMocks
//	private AddItemService addItemService;

	@MockBean
	private ItemRepository itemRepository;

	@MockBean
	private CategoryRepository1 categoryRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void findBigCategoryTest() {

	}

	@Test
	void findChildCategoryTest() {

	}

	@Test
	void addItemTest() {
		Item item = new Item();
//		when(itemRepository.insert(item)).thenReturn(1);
		doReturn(1).when(itemRepository).insert(item);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
