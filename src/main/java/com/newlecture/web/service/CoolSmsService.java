package com.newlecture.web.service;

public interface CoolSmsService {
	
	// 문자 보내기
	public void sendSms(String to, String text);
}
