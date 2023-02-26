package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Review;

public interface ReviewDao {

	List<Review> getReviewList(int itemId);
	
	Review getReview(int id);

	boolean insertReview(Review review);

	boolean deleteReview(Integer deleteReviewId);
}
