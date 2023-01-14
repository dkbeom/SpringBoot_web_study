package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

// MyBatis가 @Mapper 를 읽고, 이 인터페이스의 구현체를 만들어서 IoC 컨테이너에 담아줌
//@Mapper
public interface NoticeDao {

//	List<NoticeView> getl();
	
	List<NoticeView> getViewList(int startNum, int endNum, String field, String query, boolean isAdmin);
	int getCount(String field, String query, boolean isAdmin);
	
	List<Comment> getComment(int notice_id);
	int insertComment(Comment comment);
	int deleteComment(int comment_id);
	
	int getHit(int id);
	int hitUp(int id, int hit);
	
	Notice getNotice(int id);
	Notice getNext(int id);
	Notice getPrev(int id);

	int update(Notice notice);
	int insert(Notice notice);	
	int delete(int id);
	
	int deleteAll(String[] delIds);
	int updatePubAll(List<String> pubIds, List<String> closeIds);
}
