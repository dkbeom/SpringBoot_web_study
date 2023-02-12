package com.newlecture.web.service;

import java.util.List;

import com.newlecture.web.entity.Item;

public interface ItemService {

	// 상품 객체 리스트 가져오기(관리자가 아니면, 공개된 상품의 갯수만)
	List<Item> getItemList(boolean isAdmin);
	List<Item> getItemList(String query, boolean isAdmin);
	
	// 상품 갯수 반환(관리자가 아니면, 공개된 상품의 갯수만)
	int getCount(boolean isAdmin);
	int getCount(String query, boolean isAdmin);
	
	// 아이디로 상품 객체 가져오기
	Item getItem(int id);

	// 상품 등록하기
	boolean regItem(Item item);

	// 상품 수정하기
	boolean updateItem(Item item);

	// 상품 삭제하기
	boolean deleteItem(int id);

	// 상품 일괄 삭제하기
	boolean deleteAllItem(int[] ids);

	// 상품 일괄 공개하기(또는 숨기기)
	boolean updatePubAllItem(int[] pubIds, int[] closeIds);

	// 상품 평점 부여하기(평점 누적됨)
	boolean scoreItem(int id, int score);
}
