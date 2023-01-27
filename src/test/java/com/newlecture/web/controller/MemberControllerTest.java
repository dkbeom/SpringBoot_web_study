package com.newlecture.web.controller;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.mockito.BDDMockito.given; // 이거 왜 자동(ctrl+shift+o)이 안돼?
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.newlecture.web.controller.member.MemberController;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

// 해당 컨트롤러 메소드 없애버렸음(안될거임)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MemberService memberService;
	
	@Test
	@DisplayName("멤버 정보 가져오는거 테스트")
	void getTest() throws Exception {
		
		// [given]
		// Mock 객체가 특정상황에서 해야하는 행위를 정의하는 메소드
		given(memberService.login("newlec1","1234"))
		.willReturn(new Member("newlec1", "1234", "붬뒁권", "abcdef",
							   "남성", "1983-03-31", "양력",
							   "010-1577-1577", "email@email.com", null, 1));
		
		// 아이디 지정
		String memberName = "붬뒁권";
		
		// [when & then]
		// REST API 테스트
		mockMvc.perform(
				get("/member/login/"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.name", startsWith(memberName)))
				.andExpect(jsonPath("$.gender").exists())
				.andDo(print());
		
		verify(memberService).login("newlec1", "1234");
	}
}
