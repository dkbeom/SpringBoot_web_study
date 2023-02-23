package com.newlecture.web.entity;

public class Order {
	private String merchant_uid;
	private String pg_provider;
	private String pay_method;
	private String name; // 상품명
	private int amount;
	private String buyer_email;
	private String buyer_name;
	private String buyer_tel;
	private String buyer_addr;
	
	public Order() {
	}
	
	public Order(String merchant_uid, String pg_provider, String pay_method, String name, int amount,
			String buyer_email, String buyer_name, String buyer_tel, String buyer_addr) {
		this.merchant_uid = merchant_uid;
		this.pg_provider = pg_provider;
		this.pay_method = pay_method;
		this.name = name;
		this.amount = amount;
		this.buyer_email = buyer_email;
		this.buyer_name = buyer_name;
		this.buyer_tel = buyer_tel;
		this.buyer_addr = buyer_addr;
	}

	public String getMerchant_uid() {
		return merchant_uid;
	}

	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}

	public String getPg_provider() {
		return pg_provider;
	}

	public void setPg_provider(String pg_provider) {
		this.pg_provider = pg_provider;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getBuyer_tel() {
		return buyer_tel;
	}

	public void setBuyer_tel(String buyer_tel) {
		this.buyer_tel = buyer_tel;
	}

	public String getBuyer_addr() {
		return buyer_addr;
	}

	public void setBuyer_addr(String buyer_addr) {
		this.buyer_addr = buyer_addr;
	}

	@Override
	public String toString() {
		return "Order [merchant_uid=" + merchant_uid + ", pg_provider=" + pg_provider + ", pay_method=" + pay_method
				+ ", name=" + name + ", amount=" + amount + ", buyer_email=" + buyer_email + ", buyer_name="
				+ buyer_name + ", buyer_tel=" + buyer_tel + ", buyer_addr=" + buyer_addr + "]";
	}
}
