package com.newlecture.web.entity;

public class LoginForm {
	
	private String loginid;
	private String loginpwd;
	
	public LoginForm() {
		
	}
	
	public LoginForm(String loginid, String loginpwd) {
		this.loginid = loginid;
		this.loginpwd = loginpwd;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
}
