package com.newlecture.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Service("memberService")
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberDao memberDao;

	@Override
	public Member login(String id, String password) {
		
		Member member = memberDao.getMember(id, password);

		return member;
	}

	@Override
	public int join(Member member) {

		int isJoin = memberDao.insertMember(member);

		return isJoin;
	}

	@Override
	public boolean checkIdDuplicate(String id) {

		boolean isIdDuplicate;
		
		// 해당 아이디를 가진 계정이 존재하지 않을 때
		if (memberDao.getIdById(id) == null) {
			isIdDuplicate = false;
		}
		// 해당 아이디를 가진 계정이 존재할 때
		else {
			isIdDuplicate = true;
		}

		return isIdDuplicate;
	}

	@Override
	public boolean checkNicknameDuplicate(String nickname) {

		boolean isNicknameDuplicate;

		// 해당 아이디를 가진 계정이 존재하지 않을 때
		if (memberDao.getIdByNickname(nickname) == null || memberDao.getIdByNickname(nickname).equals("")) {
			isNicknameDuplicate = false;
		}
		// 해당 아이디를 가진 계정이 존재할 때
		else {
			isNicknameDuplicate = true;
		}

		return isNicknameDuplicate;
	}

}
