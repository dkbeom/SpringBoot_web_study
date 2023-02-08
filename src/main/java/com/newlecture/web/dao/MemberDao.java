package com.newlecture.web.dao;

import com.newlecture.web.entity.Member;

// MyBatis가 @Mapper 를 읽고, 이 인터페이스의 구현체를 만들어서 IoC 컨테이너에 담아줌
//@Mapper
public interface MemberDao {
	
	Member getMember(String id, String password);
	
	int insertMember(Member member);
	
	String getIdById(String id);
	
	String getIdByNickname(String nickname);

	boolean findByIdAndUpdate(String id, String vbank_num, String vbank_date, String vbank_name);
}
