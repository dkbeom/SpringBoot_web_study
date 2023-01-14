package com.newlecture.web.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeServiceImpTest {

	@Autowired
	private NoticeService service;
	
	@Test
	void test() {
//		System.out.println(service.getViewList("title", "이").get(0));
//		System.out.println(service.getCount("title", "이"));
	}

}
