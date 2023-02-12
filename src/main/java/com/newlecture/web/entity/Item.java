package com.newlecture.web.entity;

public class Item {
	
	private int id;
	private String imageUUID;
	private String name;
	private int price;
	private double sum_score;
	private double num_score;
	private boolean pub;
	
	public Item() {
		
	}
	
	public Item(int id, String imageUUID, String name, int price, double sum_score, double num_score, boolean pub) {
		this.id = id;
		this.imageUUID = imageUUID;
		this.name = name;
		this.price = price;
		this.sum_score = sum_score;
		this.num_score = num_score;
		this.pub = pub;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getSum_Score() {
		return sum_score;
	}
	public void setSum_Score(double sum_score) {
		this.sum_score = sum_score;
	}
	public double getNum_Score() {
		return num_score;
	}
	public void setNum_Score(double num_score) {
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
		return "Item [id=" + id + ", imageUUID=" + imageUUID + ", name=" + name + ", price=" + price + ", sum_score="
				+ sum_score + ", num_score=" + num_score + ", pub=" + pub + "]";
	}
}
