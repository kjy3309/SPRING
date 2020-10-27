package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.goodee.service.AopService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired AopService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/before", method = RequestMethod.GET)
	public String before(Model model) {
		logger.info("before 요청");
		// 가로채기 -> 메서드 실행 전 처리 -> 원래 메서드 실행
		
		service.before();
		return "home";
	}
	@RequestMapping(value = "/after", method = RequestMethod.GET)
	public String after(Model model) {
		logger.info("after 요청");
		service.after("arg test");
		return "home";
	}
	@RequestMapping(value = "/afterReturning", method = RequestMethod.GET)
	public String afterReturning(Model model) {
		// 가로채기 -> 실행, 실행 후 처리 -> 반환 값 가로채기
		logger.info("afterReturning 요청");
		logger.info(service.afterReturning());;
		return "home";
	}
	@RequestMapping(value = "/afterThrowing", method = RequestMethod.GET)
	public String afterThrowing(Model model) {
		logger.info("afterThrowing 요청");
		try {
			// 가로채기 -> 실행 -> 실행 후 처리 -> 예외 가로채기 -> 예외 발생
			service.afterThrowing();
		} catch (Exception e) {
			logger.info("서비스로 부터 온 예외 메시지 : "+e.toString());
		}
		
		return "home";
	}
	@RequestMapping(value = "/around", method = RequestMethod.GET)
	public String around(Model model) {
		logger.info("around 요청");
		// 메서드 실행 요청 -> 가로채기 -> 실행 전 처리 -> 실행 -> 실행 후 처리
		service.around("김중열");
		return "home";
	}
}
