package com.newlecture.web.other;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.newlecture.web.service.CoolSmsService;
import com.newlecture.web.service.CoolSmsServiceImp;

public class OtherTest {
	
	@Test
	public void test() {
		int a = 1;
		double b = 2;
		double result = a/b;
		System.out.println("계산 결과는 => "+result);
	}
}
