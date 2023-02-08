package com.newlecture.web.entity;

import java.time.LocalDate;

public class Member {
	
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String gender;
	private String birthday;
	private String isLunar;
	private String phone;
	private LocalDate regdate;
	private String email;
	private int age;
	private String boss_id;
	private Integer code;
	// 가상계좌
	private String vbank_num;  // 가상계좌 입금계좌번호
	private String vbank_date; // 가상계좌 입금기한
	private String vbank_name; // 가상계좌 은행명

	public Member() {
		
	}

	public Member(String id, String pwd, String name, String nickname,
					String gender, String birthday, String isLunar,
					String phone, String email, String boss_id, Integer code) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.gender = gender;
		this.birthday = birthday;
		this.isLunar = isLunar;
		this.phone = phone;
		//this.regdate = regdate;
		this.email = email;
		//this.age = age;
		this.boss_id = boss_id;
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getIsLunar() {
		return isLunar;
	}

	public void setIsLunar(String isLunar) {
		this.isLunar = isLunar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getRegdate() {
		return regdate;
	}

	public void setRegdate(LocalDate regdate) {
		this.regdate = regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBoss_id() {
		return boss_id;
	}

	public void setBoss_id(String boss_id) {
		this.boss_id = boss_id;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getVbank_num() {
		return vbank_num;
	}

	public void setVbank_num(String vbank_num) {
		this.vbank_num = vbank_num;
	}

	public String getVbank_date() {
		return vbank_date;
	}

	public void setVbank_date(String vbank_date) {
		this.vbank_date = vbank_date;
	}

	public String getVbank_name() {
		return vbank_name;
	}

	public void setVbank_name(String vbank_name) {
		this.vbank_name = vbank_name;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickname=" + nickname + ", gender=" + gender
				+ ", birthday=" + birthday + ", isLunar=" + isLunar + ", phone=" + phone + ", regdate=" + regdate
				+ ", email=" + email + ", age=" + age + ", boss_id=" + boss_id + ", code=" + code + ", vbank_num="
				+ vbank_num + ", vbank_date=" + vbank_date + ", vbank_name=" + vbank_name + "]";
	}
}
