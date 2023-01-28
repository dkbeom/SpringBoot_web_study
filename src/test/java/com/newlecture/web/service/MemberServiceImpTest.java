package com.newlecture.web.service;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
//@Import({MemberServiceImp.class, MemberDao.class})
//Bean을 사용하지 않는, @InjectMocks & @Mock을 쓰니까, @Import가 지금은 필요가 없다
class MemberServiceImpTest {

	@Mock
	MemberDao memberDao;
	
	@InjectMocks
	MemberServiceImp service;
	
	@Test // 미완성(자세한 값들 넣어줘야함)
	void loginTest() {
//		System.out.println(service.getViewList("title", "이").get(0));
//		System.out.println(service.getCount("title", "이"));
		
		// [given]
		// 주입하고 있는 객체를 세팅
		Mockito.when(memberDao.getMember("dragon", "222"))
			.thenReturn(new Member("id456", "222", "이름456", "nickname456",
								"남성", "1992-04-21", null,
								"010-9876-5432", null, "bossName", 1));
		
		// [when]
		// 주입하고 있는 객체가 세팅됐으니, Service 객체의 메소드 바로 실행
		Member member = service.login("dragon", "222");
		
		// [then]
		// DAO에서 가져온 entity가 제대로 왔는지 내가 예상한 정보와 비교하며 체크
		Assertions.assertEquals(member.getId(), "id456");
		Assertions.assertEquals(member.getName(), "이름456");
		Assertions.assertEquals(member.getGender(), "남성");
		
		// verify
		// memberDao 객체의 getMember 메소드가 "id", "pw" 매개변수로 실행됐는지 체크
		verify(memberDao).getMember("id456", "222");
	}

}
