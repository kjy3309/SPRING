package kr.co.goodee.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodee.service.TeamService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired TeamService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "list";
	}
	
	// 리스트 보여주기
	@RequestMapping(value = "/listCall", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> listCall() {
		logger.info("리스트 요청");
		return service.listCall();
	}
	
	// 수정하기
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> update(@RequestParam HashMap<String, String> params) {
		logger.info("수정 요청");
		logger.info("params = "+params);
		
		return service.update(params);
	}
	
}
