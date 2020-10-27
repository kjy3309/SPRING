package com.spring.controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	String hash = "";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	// 암호화
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public String save(Model model, @RequestParam String pass) {
		logger.info("pass = "+pass);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		hash = encoder.encode(pass);
		logger.info("hash = "+hash);
		return "home";
	}
	
	
	// 평문을 암호화한 내용과 비교
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public String confirm(Model model, @RequestParam String pass) {
		logger.info("pass = "+pass);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		boolean success = encoder.matches(pass, hash);
		logger.info("일치 여부 = "+success);
		return "home";
	}
	
	@RequestMapping(value = "/hash", method = RequestMethod.GET)
	public String hash(Model model, @RequestParam String pass) {
		logger.info("평문 : "+pass);
		logger.info("Hash = "+getHash(pass));
		return "home";
	}
	
	// 해시 함수 만들기
	private String getHash(String txt) {
		String result = "";
		
		// java 에서 사용하는 해시 알고리즘
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			// 문장을 바이트 단위 배열로 만들어 암호화
			byte[] byteArr = digest.digest(txt.getBytes(StandardCharsets.UTF_8));
			// 숫자 형태의 바이트를 16진수로 치환 후 문자열로 변경
			result = new String (Hex.encode(byteArr));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
