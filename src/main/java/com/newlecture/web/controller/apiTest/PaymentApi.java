package com.newlecture.web.controller.apiTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/payment/")
@PropertySource("classpath:local.properties")
public class PaymentApi {
	
	@Value("${kakaoPay_admin_key}")
	private String kakaoPayAdminKey;
	
	@Value("${iamport_imp_key}")
	private String iamportImpKey;
	
	@Value("${iamport_imp_secret}")
	private String iamportImpSecret;

	
	@GetMapping("kakaoPay")
	public String kakaoPayPage() {
		return "payment.kakaoPay";
	}
	
	@PostMapping("kakaoPay")
	@ResponseBody
	public String kakaoPay() {
		
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			
			// HttpURLConnection : 서버 연결을 해주는 객체
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			// Request 헤더 만들기
			http.setRequestMethod("POST");
			http.setRequestProperty("Authorization", kakaoPayAdminKey); // Admin 키
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			http.setDoOutput(true); // 서버에 보내줄게 있는지 없는지(setDoOutput의 기본값은 false)
			// setDoInput은 기본값이 true이기 때문에 생략

			// QueryString 같은 형식으로 파라미터를 작성해줌
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("cid","TC0ONETIME");
			parameter.put("partner_order_id","partner_order_id");
			parameter.put("partner_user_id","partner_user_id");
			parameter.put("item_name","초코파이");
			parameter.put("quantity",3); // 상품 수량
			parameter.put("total_amount",1000); // 상품 총액
			parameter.put("tax_free_amount",0);
			parameter.put("approval_url","http://localhost:8080/index");
			parameter.put("cancel_url","http://localhost:8080/index");
			parameter.put("fail_url","http://localhost:8080/index");
			
			// Map에 저장한걸 문자열로 변환
			String param = parameter.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue()).collect(Collectors.joining("&"));
			System.out.println("파라미터는 => " + param);
			
			// 바이트 기반으로, 외부로 데이터 출력하는 객체(서버로 출력)
			OutputStream out = http.getOutputStream();
//			// OutputStream의 자식 클래스
//			// 파라미터를 바이트 형식으로 서버에 전달하기 위해서 만듬
//			DataOutputStream dataOut = new DataOutputStream(out);
//			dataOut.writeBytes(param);
//			// flush(스트림을 비우면서(데이터를 서버로 출력하면서))를 하면서 close
//			dataOut.close();
			
			PrintWriter pWriter = new PrintWriter(out);
			pWriter.write(param);
			pWriter.close();
			
//			BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(out));
//			bWriter.write(param);
//			bWriter.close();
			
			//////////////////////////////////////////////////////////////
			
			// 결과 코드(200만 에러가 아님)
			int result = http.getResponseCode();
			
			// 바이트 기반으로, 외부에서 데이터를 입력받는 객체(서버로부터 입력)
			InputStream in;
			// 에러가 안났을 때
			if(result == 200) {
				in = http.getInputStream();
			}
			// 에러가 났을 때
			else {
				in = http.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(in);
			
			// 형변환이 되게 해주는 객체
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			// 문자열로 형변환을 해줌
			return bufferedReader.readLine();
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return "{\"result\":\"NO\"}";
	}
	
	@GetMapping("iamport")
	public String iamportPage() {
		return "payment.iamport";
	}
	
	@PostMapping("iamport")
	@ResponseBody
	public String imp(String imp_uid, String merchant_uid) {
		
		try {
			// 액세스 토큰(access token) 발급 받기
			URL url = new URL("https://api.iamport.kr/users/getToken");
			// HttpURLConnection : 서버 연결을 해주는 객체
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			// Request 헤더 만들기
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type", "application/json");
			http.setDoOutput(true); // 서버에 보내줄게 있는지 없는지(setDoOutput의 기본값은 false)
			// setDoInput은 기본값이 true이기 때문에 생략
			
			Gson gson = new Gson();
			JsonObject jsonObj = new JsonObject();
			jsonObj.addProperty("imp_key", iamportImpKey);
			jsonObj.addProperty("imp_secret", iamportImpSecret);
			String jsonStr = gson.toJson(jsonObj);
			
			// 바이트 기반으로, 외부로 데이터 출력하는 객체(서버로 출력)
			OutputStream out = http.getOutputStream();
			PrintWriter pWriter = new PrintWriter(out);
			pWriter.write(jsonStr);
			pWriter.close();
			
			
			//////////////////////////////////////////////////////////////
			
			// 결과 코드(200만 에러가 아님)
			int result = http.getResponseCode();
			
			// 바이트 기반으로, 외부에서 데이터를 입력받는 객체(서버로부터 입력)
			InputStream in;
			// 에러가 안났을 때
			if(result == 200) {
				in = http.getInputStream();
			}
			// 에러가 났을 때
			else {
				in = http.getErrorStream();
			}
			InputStreamReader reader = new InputStreamReader(in);
			
			// 형변환이 되게 해주는 객체
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			// 문자열로 형변환을 해줌
			System.out.println("서버에서 읽어들인 문자열 => "+bufferedReader.readLine());
			return bufferedReader.readLine();
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "{\"result\":\"NO\"}";
	}
}
