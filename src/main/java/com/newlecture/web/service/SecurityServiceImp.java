package com.newlecture.web.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.LoginForm;
import com.newlecture.web.entity.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@PropertySource("classpath:local.properties")
public class SecurityServiceImp {
	
	@Autowired
	private MemberDao memberDao;
	
	@Value("${JWT_SECRET_KEY}")
    private String SECRET_KEY;
    
    private static final long expTime = 2*1000*60;

    // 토큰 생성
    // 로그인 서비스 던질 때 같이 던져주면 됨
    public String createToken(LoginForm loginForm){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // String 형태의 키(key)를 byte로 만들어줌
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        // byte로 만든 키(key)를 알고리즘에 따라 암호화함
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        Gson gson = new Gson();
        Member member = memberDao.getMember(loginForm);
        Map<String, String> map = new HashMap<>();
        map.put("id", member.getId());
        map.put("nickname", member.getNickname());
        String jsonStr = gson.toJson(map);
        
        return Jwts.builder()
                .setSubject(jsonStr) // 토큰 내용에 멤버의 id, nickname을 담아줌 
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();
    }

    // 토큰 검증하는 메소드를 만들어서 boolean 타입으로 리턴하는 걸 만들어서 사용하면 됨
    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token) // 토큰을 넣어서, 클레임들을 구함
                .getBody();
         
         return claims.getSubject();
    }
}
