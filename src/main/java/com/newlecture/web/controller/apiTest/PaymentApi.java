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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Order;
import com.newlecture.web.service.MemberService;
import com.newlecture.web.service.OrderService;

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
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	

	@GetMapping("kakaoPay")
	public String kakaoPayPage() {
		return "payment.kakaoPay";
	}

	@PostMapping("kakaoPay")
	@ResponseBody
	public String kakaoPay(String aoi, String boi) {
		System.out.println("aoi는 => " + aoi);
		System.out.println("boi는 => " + boi);
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
			parameter.put("cid", "TC0ONETIME");
			parameter.put("partner_order_id", "partner_order_id");
			parameter.put("partner_user_id", "partner_user_id");
			parameter.put("item_name", "초코파이");
			parameter.put("quantity", 3); // 상품 수량
			parameter.put("total_amount", 1000); // 상품 총액
			parameter.put("tax_free_amount", 0);
			parameter.put("approval_url", "http://localhost:8080/index");
			parameter.put("cancel_url", "http://localhost:8080/index");
			parameter.put("fail_url", "http://localhost:8080/index");

			// Map에 저장한걸 문자열로 변환
			String param = parameter.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
					.collect(Collectors.joining("&"));
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
			if (result == 200) {
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
	public String iamportPage(HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginSession");
		// 로그인한 상태가 아니라면
//		if (loginMember == null) {
//			return "redirect:/index";
//		}
		
		return "payment.iamport";
	}
	
	// 주문 레코드 삽입
	@PostMapping("iamport/insertOrder")
	@ResponseBody
	public boolean iamportInsertOrder(Order order, HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginSession");
		
		// 로그인한 상태가 아니라면
//		if (loginMember == null) {
//			return "redirect:/index";
//		}
		
		boolean insert = orderService.insertOrder(order);
		
		return insert;
	}

	// 결제 성공 후에, 주문 레코드(DB)의 값과 실제 결제 정보를 비교해서 검증
	@PostMapping("iamport/complete")
	@ResponseBody
	// public String iamport(String imp_uid, String merchant_uid) {
	public String iamport(String imp_uid, String merchant_uid, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");
		
		// 로그인한 상태가 아니라면
//		if (loginMember == null) {
//			return "redirect:/index";
//		}
		
		try {
			// 액세스 토큰(access token) 발급 받기
			/////////////////////////////////////////////////////////////
			// 출력 //

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
			// 입력 //

			// 결과 코드(200만 에러가 아님)
			int result = http.getResponseCode();

			// 바이트 기반으로, 외부에서 데이터를 입력받는 객체(서버로부터 입력)
			InputStream in;
			// 에러가 안났을 때
			if (result == 200) {
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
			String rsp = bufferedReader.readLine();
			// json 문자열을 Map 으로 변환
			Map<String, Object> map = gson.fromJson(rsp, Map.class);
			Map<String, String> response = (Map<String, String>) map.get("response");
			String access_token = response.get("access_token");

			// imp_uid로 아임포트 서버에서 결제 정보 조회
			//////////////////////////////////////////////////////////////
			// 출력 //

			URL url2 = new URL("https://api.iamport.kr/payments/" + imp_uid);

			HttpURLConnection http2 = (HttpURLConnection) url2.openConnection();

			http2.setRequestMethod("GET");
			http2.setRequestProperty("Authorization", access_token);
			http2.setDoOutput(true); // 서버에 보내줄게 있는지 없는지(setDoOutput의 기본값은 false)

			//////////////////////////////////////////////////////////////
			// 입력 //

			// 결과 코드(200만 에러가 아님)
			int result2 = http2.getResponseCode();

			// 바이트 기반으로, 외부에서 데이터를 입력받는 객체(서버로부터 입력)
			InputStream in2;
			// 에러가 안났을 때
			if (result2 == 200) {
				in2 = http2.getInputStream();
			}
			// 에러가 났을 때
			else {
				in2 = http2.getErrorStream();
			}
			InputStreamReader reader2 = new InputStreamReader(in2);

			// 형변환이 되게 해주는 객체
			BufferedReader bufferedReader2 = new BufferedReader(reader2);

			// 문자열로 형변환을 해줌
			String rsp2 = bufferedReader2.readLine();
			
			// json 문자열을 Map 으로 변환
			Map<String, Object> map2 = gson.fromJson(rsp2, Map.class);
			Map<String, Object> response2 = (Map<String, Object>) map2.get("response");
			
			// 결제정보 DB(가맹점 uid로 구분됨)에서 결제되어야 하는 금액 조회
			Order order = orderService.findById((String)response2.get("merchant_uid"));
			int amountToBePaid = order.getAmount();
			
			//////////////////////////////// 결제 검증하기 ////////////////////////////////
			// 결제 정보에서의 "실제 결제된 금액"과, DB에서의 "결제되어야 하는 금액" 같은지 비교
			int amount = (int)Math.round((double)response2.get("amount")); // 실제 결제된 금액
			String status = (String)response2.get("status");
			
			if(amount == amountToBePaid) {
				
				// 실제 결제 정보를 이용해서 Order 객체 만들기
				Order actualOrder = new Order(
						(String)response2.get("merchant_uid"),
						(String)response2.get("pg_provider"),
						(String)response2.get("pay_method"),			
						(String)response2.get("name"),
						amount,
						(String)response2.get("buyer_email"),
						(String)response2.get("buyer_name"),
						(String)response2.get("buyer_tel"),
						(String)response2.get("buyer_addr"),
						(String)response2.get("buyer_postcode")
				);
				
				// DB에 결제 정보 저장
				orderService.findByIdAndUpdate(merchant_uid, actualOrder);
				// 결제 상태
				switch(status) {
					case "ready": // 가상계좌 발급
						
						// 결제정보 DB에 가상계좌 발급 정보 저장
						String vbank_num = (String)response2.get("vbank_num");
						String vbank_date = (String)response2.get("vbank_date");
						String vbank_name = (String)response2.get("vbank_name");
						memberService.findByIdAndUpdate(loginMember.getId(), vbank_num, vbank_date, vbank_name);
						
						// 가상계좌 발급 안내 문자메시지 전송
						// SMS.send()
						
						// 응답 객체에 값 넣기
						// status: "success", message: "일반 결제 성공"
						
						break;
					case "paid": // 결제 완료
						
						// 응답 객체에 값 넣기
						// status: "success", message: "일반 결제 성공"
						
						break;
				}
			} else { // 결제금액 불일치(위/변조된 결제)
				//throw {status:"forgery", message:"위조된 결제시도"};
			}
			
			return "";

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "{\"result\":\"NO\"}";
	}
}
