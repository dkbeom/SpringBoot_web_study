package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Review;

public interface ReviewService {

	List<Review> getReviewList(int itemId);

	boolean insertReview(Review review);

	boolean deleteReview(Integer deleteReviewId);
}
