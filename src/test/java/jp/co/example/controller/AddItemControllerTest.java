package jp.co.example.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import jp.co.example.domain.Category1;
import jp.co.example.form.AddItemForm;
import jp.co.example.service.AddItemService;

@SpringBootTest
class AddItemControllerTest {

	@MockBean
	private AddItemService addItemService;

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("/toAddItemにアクセスした場合")
	void toAddItemTest() throws Exception {
		// プルダウン表示用大カテゴリ
		List<Category1> bigCategoryList = new ArrayList<>();
		doReturn(bigCategoryList).when(addItemService).findBigCategory();

//		MvcResult result = this.mockMvc.perform(get("/addItem/toAddItem").with(csrf())).andReturn();
		this.mockMvc.perform(get("/addItem/toAddItem").with(csrf()))
				.andExpect(model().attribute("bigCategoryList", bigCategoryList)).andExpect(view().name("add"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "testT-shirt", "" })
	@DisplayName("バリデーションテスト(name)")
	public void nameAddTest(String name) throws Exception {
		AddItemForm addItemForm = new AddItemForm();
		addItemForm.setName(name);
		addItemForm.setCondition("1");
		addItemForm.setPrice("1000");
		this.mockMvc.perform(post("/addItem/addItem") // 送信先
				.flashAttr("addItemForm", addItemForm)) // mockMvc.perform(post("url名").flashAttr(パラメーター名, オブジェクト）)
				.andExpect(model().hasNoErrors()); // エラーがないと成功
	}

	@ParameterizedTest
	@ValueSource(strings = { "1000", "" })
	@DisplayName("バリデーションテスト(price)")
	public void priceAddTest(String price) throws Exception {
		AddItemForm addItemForm = new AddItemForm();
		addItemForm.setPrice(price);
		addItemForm.setName("testT-shirt");
		addItemForm.setCondition("1");
		this.mockMvc.perform(post("/addItem/addItem").flashAttr("addItemForm", addItemForm))
				.andExpect(model().hasNoErrors());
	}

	@ParameterizedTest
	@ValueSource(strings = { "1", "2", "3", "" })
	@DisplayName("バリデーションテスト(conditionId)")
	public void conditionAddTest(String conditionId) throws Exception {
		AddItemForm addItemForm = new AddItemForm();
		addItemForm.setCondition(conditionId);
		addItemForm.setName("testT-shirt");
		addItemForm.setPrice("1000");
		this.mockMvc.perform(post("/addItem/addItem").flashAttr("addItemForm", addItemForm))
				.andExpect(model().hasNoErrors());

	}

	@Test
	void test() {
		fail("Not yet implemented");
	}
}