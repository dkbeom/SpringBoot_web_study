package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.ReviewDao;
import com.newlecture.web.entity.Review;

@Repository
public class MybatisReviewDao implements ReviewDao {

	// SqlSession에서 가져오는 Mapper를 담기 위함
	private ReviewDao mapper;

	// 생성자 주입
	@Autowired
	public MybatisReviewDao(SqlSession sqlSession) {
		mapper = sqlSession.getMapper(ReviewDao.class);
	}

	//////////////////////////////////////////////////////////
	
	@Override
	public List<Review> getReviewList(int itemId) {
		return mapper.getReviewList(itemId);
	}
	
	@Override
	public Review getReview(int id) {
		return mapper.getReview(id);
	}

	@Override
	public boolean insertReview(Review review) {
		return mapper.insertReview(review);
	}

	@Override
	public boolean deleteReview(Integer deleteReviewId) {
		return mapper.deleteReview(deleteReviewId);
	}
}
