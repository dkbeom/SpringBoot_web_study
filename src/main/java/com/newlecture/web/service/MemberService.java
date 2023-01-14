package com.newlecture.web.service;

import com.newlecture.web.entity.Member;

public interface MemberService {
	
	// 로그인
	Member login(String id, String password);
	
	// 회원가입
	int join(Member member);
	
	// 아이디 중복체크
	boolean checkIdDuplicate(String id);
	
	// 닉네임 중복체크
	boolean checkNicknameDuplicate(String nickname);
}