package com.newlecture.web.other;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
public class OtherTest {

	// @Autowired
	// private ItemService itemService;
	
	@Value("${random}")
	private String abc;

	@Test
	public void test() {
		
	}
}
