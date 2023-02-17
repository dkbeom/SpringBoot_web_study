package com.newlecture.web.other;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.newlecture.web.entity.Item;
import com.newlecture.web.service.ItemService;

@SpringBootTest
public class OtherTest {
	
	@Autowired
	private ItemService itemService;
	
	@Test
	public void test() {
		String a = itemService.getItem(4).getName();
		
		System.out.println("4번의 아이템의 이름은 => "+a);
		
		List<Item> list = itemService.getItemList(false);
		
		String b = list.get(2).getName();
		
		System.out.println("2번의 아이템의 이름은 => "+b);
	}
}
