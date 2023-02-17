package com.newlecture.web.dao.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@SpringBootTest
//@ExtendWith(SpringExtension.class)
//@Import(MybatisMemberDao.class)
class MybatisNoticeDaoTest {

	@Autowired
	private MemberDao memberDao;
	
	@Test
	void test() {
//		System.out.println(noticeDao.getViewList(0, 1, "title", "이").get(0));
		
//		System.out.println(noticeDao.getCount("title", "이"));
		
//		System.out.println(noticeDao.getView(7));
		
//		System.out.println(noticeDao.getNext(66));
		
		
		String member = memberDao.getIdById("admin");
		System.out.println(member);
	}

}
