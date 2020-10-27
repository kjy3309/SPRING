package com.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller // 이 class 는 컨트롤러 이다.
public class HomeController {
	
	// 로그찍는 설정임
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// "/" 로 들어왔을 경우 home 이라는 메서드 실행
	// GET 방식만을 받는다.
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) { // model 은 request 의 attribute 역할
		
		// TRACE < DEBUG < INFO < WARN < ERROR < FATAL 으로 로그 등급을 둘 수 있다.
		// 로그 설정에서 특정 등급 이하의 로그는 찍지 않도록 설정 가능
		logger.info("메인 페이지 접속 시도");
		
		model.addAttribute("serverTime", "hello" ); // request.setAttribute("serverTime", "hello");
		return "home"; // requestDispatcher 가 필요없다.
	}
	
	@RequestMapping(value = "/member") // 모든 형태의 요청을 받는다. (GET, POST, PUT, DELETE, ...) 보안상 권장X
	public ModelAndView member() {	
		//modelAndView = model+view
		ModelAndView mav = new ModelAndView();
		mav.addObject("serverTime", "member");
		mav.setViewName("home");
		// modelAndView 를 사용하면 service 에서도 담을 값이나 갈 곳을 지정할 수 있다.
		
		return mav;
	}
	
}
