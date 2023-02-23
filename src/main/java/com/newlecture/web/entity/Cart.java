package com.newlecture.web.entity;

public class Cart {
	
	private int id;
	private int item_id;
	private String member_id;
	private int quantity;
	
	public Cart() {
		
	}

	public Cart(int id, int item_id, String member_id, int quantity) {
		this.id = id;
		this.item_id = item_id;
		this.member_id = member_id;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", item_id=" + item_id + ", member_id=" + member_id + ", quantity=" + quantity + "]";
	}
}
