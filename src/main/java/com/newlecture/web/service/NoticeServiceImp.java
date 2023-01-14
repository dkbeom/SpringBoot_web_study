package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.NoticeDao;
import com.newlecture.web.entity.Comment;
import com.newlecture.web.entity.Notice;
import com.newlecture.web.entity.NoticeView;

@Service("noticeService")
public class NoticeServiceImp implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	
	// 테스트용
//	@Override
//	public List<NoticeView> getl() {
//		return getl();
//	}

	// 처음 공지사항 리스트 페이지 들어가면 나오는 첫 페이지의 공지사항 목록 반환
	@Override
	public List<NoticeView> getViewList(boolean isAdmin) {
		return getViewList(1, "title", "", isAdmin);
	}

	// 공지사항 리스트 페이지에서 "제목", "작성자"를 검색하면 나오는 첫 페이지의 공지사항 목록 반환
	@Override
	public List<NoticeView> getViewList(String field, String query, boolean isAdmin) {
		return getViewList(1, field, query, isAdmin);
	}
	
	// 공지사항 리스트 페이지에서 "제목", "작성자"를 검색하고, 특정 페이지를 눌러서 나오는 공지사항 목록 반환
	@Override
	public List<NoticeView> getViewList(int page, String field, String query, boolean isAdmin) {
		
		int size = 10;
		int startNum = 10*(page-1)+1; // page 1->1 , 2->11, 3->21 ...
		int endNum = startNum+size-1;
		
		List<NoticeView> list = noticeDao.getViewList(startNum, endNum, field, query, isAdmin);
		
		return list;
	}

	// 처음 공지사항 리스트 페이지 들어가면 나오는 공지사항 갯수 반환
	@Override
	public int getCount(boolean isAdmin) {
		return getCount("title", "", isAdmin);
	}

	// 공지사항 리스트 페이지에서 "제목", "작성자"를 입력하면 나오는 공지사항 갯수 반환
	@Override
	public int getCount(String field, String query, boolean isAdmin) {
		return noticeDao.getCount(field, query, isAdmin);
	}
	
	// 특정 공지사항의 댓글 가져오기
	public List<Comment> getComment(int notice_id) {
		return noticeDao.getComment(notice_id);
	}
	
	// 댓글 추가하기
	public int insertComment(Comment comment) {
		return noticeDao.insertComment(comment);
	}
	
	// 댓글 삭제하기
	public int deleteComment(int comment_id) {
		return noticeDao.deleteComment(comment_id);
	}
	
	// 공지사항 들어갈 때마다 조회수 1씩 늘어남
	public int hitUp(int id) {
		
		int hit = noticeDao.getHit(id);
		
		return noticeDao.hitUp(id, hit+1);
	}

	// 공지사항 리스트 페이지에서 공지사항을 클릭하면 나오는 공지사항 하나 반환
	@Override
	public Notice getNotice(int id) {
		
		Notice notice = noticeDao.getNotice(id);
		
		return notice;
	}

	// 자세한 공지사항 페이지에서 다음글을 클릭하면 나오는 공지사항 반환
	@Override
	public Notice getNext(int id) {
		return noticeDao.getNext(id);
	}

	// 자세한 공지사항 페이지에서 이전글을 클릭하면 나오는 공지사항 반환
	@Override
	public Notice getPrev(int id) {
		return noticeDao.getPrev(id);
	}
	////////////////////////////////////////////테스트 완료 기준선////////////////////////////////////////////
	// 일괄 공개, 삭제할 공지사항 들을 업데이트하고, 업데이트한 전체 공지사항 갯수 반환
	@Override 
	public int updatePubAll(List<String> pubIds, List<String> closeIds) {
		
		int result = 0;
		result += noticeDao.updatePubAll(pubIds, closeIds);
		return result;
	}

	@Override
	public int deleteAll(String[] delIds) {
		return noticeDao.deleteAll(delIds);
	}

	// 자세한 공지사항 하나에 들어가서, 수정하고 수정한 갯수 반환
	@Override
	public int update(Notice notice) {
		return noticeDao.update(notice);
	}

	// 자세한 공지사항 하나에 들어가서, 삭제하고 삭제한 갯수 반환
	@Override
	public int delete(int id) {
		return noticeDao.delete(id);
	}

	// 공지사항 목록에서 글쓰기 버튼을 눌러서 나오는 글쓰기 기능에서 글쓰기 완료를 누르면, 공지사항이 입력되고 입력된 공지사항 갯수 반환
	@Override
	public int insert(Notice notice) {
		return noticeDao.insert(notice);
	}

}
