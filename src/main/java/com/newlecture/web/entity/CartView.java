package com.newlecture.web.entity;

public class CartView extends Cart {

	private String imageUUID;
	private String name;
	private int price;
	private double sum_score;
	private int num_score;
	private boolean pub;

	public CartView() {

	}

	public CartView(int id, int item_id, String member_id, int quantity, String imageUUID, String name, int price, double sum_score, int num_score, boolean pub) {
		super(id, item_id, member_id, quantity);

		this.imageUUID = imageUUID;
		this.name = name;
		this.price = price;
		this.sum_score = sum_score;
		this.num_score = num_score;
		this.pub = pub;
	}

	public String getImageUUID() {
		return imageUUID;
	}

	public void setImageUUID(String imageUUID) {
		this.imageUUID = imageUUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getSum_score() {
		return sum_score;
	}

	public void setSum_score(double sum_score) {
		this.sum_score = sum_score;
	}

	public int getNum_score() {
		return num_score;
	}

	public void setNum_score(int num_score) {
		this.num_score = num_score;
	}

	public boolean isPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "CartView [imageUUID=" + imageUUID + ", name=" + name + ", price=" + price + ", sum_score=" + sum_score
				+ ", num_score=" + num_score + ", pub=" + pub + "]";
	}
}
