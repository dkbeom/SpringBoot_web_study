package com.newlecture.web.controller.security;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.newlecture.web.entity.LoginForm;
import com.newlecture.web.service.SecurityServiceImp;

@RestController
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private SecurityServiceImp securityService;

	@GetMapping("/create/token")
	public Map<String, Object> createToken(@ModelAttribute LoginForm loginForm) {

		String token = securityService.createToken(loginForm);

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("token", token);

		return map;
	}

	// 사용자 인증이 필요한 메소드마다 요청 헤더에 token을 넣어, 검증해줘야 됨
	// 특정 사용자 정보로 뭔가를 할 때는, securityService.getSubject(token)을 이용해 특정 사용자를 특정해서 뭔가를 하면 됨
	@GetMapping("/get/subject")
	public Map<String, Object> getIdAndNickname(@RequestHeader(value="Authorization") String token) {

		String subject = securityService.getSubject(token);

		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(subject, Map.class);
		
		System.out.println("사용자의 닉네임 => "+map.get("nickname"));

		return map;
	}
}
