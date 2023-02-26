package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.ReviewDao;
import com.newlecture.web.entity.Review;

@Transactional
@Service
public class ReviewServiceImp implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ItemService itemService;
	
	///////////////////////////////////////////////////////////////////////
	
	@Override
	public List<Review> getReviewList(int itemId) {
		return reviewDao.getReviewList(itemId);
	}

	@Override
	public boolean insertReview(Review review) {
		
		// 상품 DB에 있는 종합 평점에 점수 반영
		itemService.scoreItem(review.getItem_id(), review.getScore());
		
		return reviewDao.insertReview(review);
	}

	@Override
	public boolean deleteReview(Integer deleteReviewId) {
		
		// 리뷰 객체 얻어오기
		Review review = reviewDao.getReview(deleteReviewId);
		
		// 상품 DB에 있는 종합 평점에 점수 반영
		itemService.cancelScore(review.getItem_id(), review.getScore());
		
		return reviewDao.deleteReview(deleteReviewId);
	}
}
