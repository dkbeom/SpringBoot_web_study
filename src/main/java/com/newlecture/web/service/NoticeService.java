package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

public interface NoticeService {
	
	// 테스트용
//	public List<NoticeView> getl();
	
	// 페이지를 요청할 때
	List<NoticeView> getViewList(boolean isAdmin);

	// 검색을 요청할 때
	List<NoticeView> getViewList(String field, String query, boolean isAdmin);

	// 페이지를 요청할 때
	List<NoticeView> getViewList(int page, String field, String query, boolean isAdmin);
	
	// 공지사항 갯수 반환 요청할 때
	int getCount(boolean isAdmin);
	int getCount(String field, String query, boolean isAdmin);
	
	// 특정 공지사항의 댓글 가져오기
	List<Comment> getComment(int notice_id);
	// 댓글 추가하기
	int insertComment(Comment comment);
	// 댓글 삭제하기
	int deleteComment(int comment_id);
	
	// 공지사항 들어갈 때마다 조회수 1씩 늘어남
	int hitUp(int id);

	// 자세한 공지사항 페이지를 요청할 때
	Notice getNotice(int id);
	Notice getNext(int id);
	Notice getPrev(int id);

	// 일괄공개를 요청할 때(공개/비공개 transaction을 맞추기 위해 동시에 작업)
	int updatePubAll(List<String> pubIds, List<String> closeIds);

	// 일괄삭제를 요청할 때
	int deleteAll(String[] delIds);

	// 자세한 공지사항 페이지에서 "수정" 버튼 눌러서, 수정 페이지에서 수정할 때
	int update(Notice notice);
	
	// 자세한 공지사항 페이지에서 "삭제" 버튼 눌러서, 삭제 페이지에서 삭제할 때
	int delete(int id);
	
	// 공지사항 목록 페이지에서 "글쓰기" 버튼 눌러서, 글쓰기 페이지에서 글을 쓰고 등록할 때
	int insert(Notice notice);
}
