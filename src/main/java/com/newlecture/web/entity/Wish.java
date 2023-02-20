package com.newlecture.web.entity;

public class Wish {
	private int id;
	private int item_id;
	private String item_name;
	private String member_id;
	
	public Wish() {
		
	}
	
	public Wish(int id, int item_id, String item_name, String member_id) {
		this.id = id;
		this.item_id = item_id;
		this.item_name = item_name;
		this.member_id = member_id;
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

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "Wish [id=" + id + ", item_id=" + item_id + ", item_name=" + item_name + ", member_id=" + member_id
				+ "]";
	}
}
