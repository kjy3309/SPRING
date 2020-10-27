package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodee.dto.UserInfo;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String> list() {
		// @ResponseBody 를 사용하면 response 에 하던 기존 작업이 사라진다.
		// @ResponseBody 를 사용하면 java의 자료구조를 클라이언트로 내보낼 수 있다.
		logger.info("리스트 요청");
		ArrayList<String> list = new ArrayList<String>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		
		return list;
	}
	
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, String> map() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("하나", "1");
		map.put("둘", "2");
		map.put("셋", "3");
		return map;
	}
	
	@RequestMapping(value = "/object", method = RequestMethod.GET)
	public @ResponseBody UserInfo obj() {
		UserInfo info = new UserInfo();
		info.setAge(30);
		info.setGender("male");
		info.setId("admin");
		info.setName("jhone");
		info.setSuccess(true);
		
		return info;
	}
	
}
