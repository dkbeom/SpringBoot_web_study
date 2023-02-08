package com.newlecture.web.dao.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Repository("memberDao")
public class MybatisMemberDao implements MemberDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private MemberDao mapper;
	
	// 생성자 주입
	@Autowired
	public MybatisMemberDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(MemberDao.class);
	}

	////////////////////////////////////////////////////////////////
	
	@Override
	public Member getMember(String id, String password) {
		return mapper.getMember(id, password);
	}

	@Override
	public int insertMember(Member member) {
		return mapper.insertMember(member);
	}
	
	@Override
	public String getIdById(String id) {
		return mapper.getIdById(id);
	}
	
	@Override
	public String getIdByNickname(String nickname) {
		return mapper.getIdByNickname(nickname);
	}

	@Override
	public boolean findByIdAndUpdate(String id, String vbank_num, String vbank_date, String vbank_name) {
		return mapper.findByIdAndUpdate(id, vbank_num, vbank_date, vbank_name);
	}
}
