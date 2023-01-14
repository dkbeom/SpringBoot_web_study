package com.newlecture.web.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.newlecture.web.entity.NoticeView;

@AutoConfigureTestDatabase(replace = Replace.NONE)
// @MybatisTest 는 Mybatis의 @Mapper 를 사용할 때, 사용하는 것
@MybatisTest
class NoticeDaoTest {

	@Autowired
	private NoticeDao noticeDao;
	
	@Test
	void test() {
		List<NoticeView> list = noticeDao.getViewList(0, 1, null, null, true);
		
		System.out.println(list.get(0));
	}

}
