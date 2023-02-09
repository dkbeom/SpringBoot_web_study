package com.newlecture.web.other;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.newlecture.web.service.CoolSmsService;
import com.newlecture.web.service.CoolSmsServiceImp;

@ExtendWith(SpringExtension.class)
@Import(CoolSmsServiceImp.class)
public class OtherTest {
	
	@Autowired
	CoolSmsService c;
	
	@Test
	public void test() {
		c.sendSms("01075207537", "test 문자 ㄱㄱ");
	}
}
