package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Repository("noticeDao")
public class MybatisNoticeDao implements NoticeDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private NoticeDao mapper;
	
	// 생성자 주입
	@Autowired
	public MybatisNoticeDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(NoticeDao.class);
	}
	
//	테스트용
//	@Override
//	public List<NoticeView> getl(){
//		return mapper.getl();
//	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<NoticeView> getViewList(int startNum, int endNum, String field, String query, boolean isAdmin) {
		
		return mapper.getViewList(startNum, endNum, field, query, isAdmin);
	}

	@Override
	public int getCount(String field, String query, boolean isAdmin) {
		
		return mapper.getCount(field, query, isAdmin);
	}
	
	@Override
	public List<Comment> getComment(int notice_id) {
		return mapper.getComment(notice_id);
	}
	
	@Override
	public int insertComment(Comment comment) {
		return mapper.insertComment(comment);
	}
	
	@Override
	public int deleteComment(int comment_id) {
		return mapper.deleteComment(comment_id);
	}
	
	@Override
	public int getHit(int id) {
		return mapper.getHit(id);
	}
	
	@Override
	public int hitUp(int id, int hit) {
		return mapper.hitUp(id, hit);
	}

	@Override
	public Notice getNotice(int id) {
		return mapper.getNotice(id);
	}
	
	@Override
	public Notice getNext(int id) {
		return mapper.getNext(id);
	}

	@Override
	public Notice getPrev(int id) {
		return mapper.getPrev(id);
	}

	@Override
	public int update(Notice notice) {
		return mapper.update(notice);
	}

	@Override
	public int insert(Notice notice) {
		return mapper.insert(notice);
	}

	@Override
	public int delete(int id) {
		return mapper.delete(id);
	}

	@Override
	public int deleteAll(String[] delIds) {
		return mapper.deleteAll(delIds);
	}

	@Override
	public int updatePubAll(List<String> pubIds, List<String> closeIds) {
		return mapper.updatePubAll(pubIds, closeIds);
	}
	
}
