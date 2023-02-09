package com.newlecture.web.service;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@PropertySource("classpath:local.properties")
public class CoolSmsServiceImp implements CoolSmsService {

	@Value("${coolsms_api_key}")
	private String api_key;

	@Value("${coolsms_api_secret}")
	private String api_secret;

	@Value("${calling_number}")
	private String callingNumber; // 발신번호

	@Override
	public void sendSms(String to, String text) {

		Message coolsms = new Message(api_key, api_secret);

		HashMap<String, String> params = new HashMap<String, String>();

		params.put("to", to); // 수신번호
		params.put("from", callingNumber); // 발신번호
		params.put("text", text); // 문자내용
		params.put("type", "sms"); // 문자 타입

		try {
			// 보내기 & 전송결과받기
			JSONObject result = coolsms.send(params);
			
			System.out.println(result.toString());

			if ((int) result.get("success_count") > 0) {
				// 메시지 보내기 성공 및 전송결과 출력
				System.out.println("성공");
				System.out.println("group_id: "+result.get("group_id")); // 그룹아이디
				System.out.println("success_count: "+result.get("success_count")); // 메시지아이디
				System.out.println("error_count: "+result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
			} else {
				// 메시지 보내기 실패
				System.out.println("실패");
				System.out.println("group_id: "+result.get("group_id")); // 그룹아이디
				System.out.println("success_count: "+result.get("success_count")); // 메시지아이디
				System.out.println("error_count: "+result.get("error_count")); // 여러개 보낼시 오류난 메시지 수
			}
		} catch (CoolsmsException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}

}
