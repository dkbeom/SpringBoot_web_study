package com.newlecture.web.other;

import org.junit.jupiter.api.Test;

//@SpringBootTest
public class OtherTest {
	
	//@Autowired
	//private ItemService itemService;
	
	@Test
	public void test() {
		String s = "abcdef";
		
		char[] c = s.toCharArray();
		
		int a = (int)'a';
		int z = (int)'z';
		
		System.out.println("a => "+a);
		System.out.println("z => "+z);
	}
}
