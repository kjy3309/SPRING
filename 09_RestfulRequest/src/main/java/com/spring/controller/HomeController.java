package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.BoardService;

@RestController
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired BoardService board;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HashMap<String, Object> index(@RequestParam Map<String, String> params) {			
		HashMap<String, Object> map = new HashMap<String, Object>();		
		logger.info("알 수 없는 url");
		map.put("msg", "잘못된 요청 입니다. 사용자 API 를 참조 하세요!");
		return map;
	}
	
	/*
	restful 에 대한 기본은 어떠한 형태로 데이터를 요청 할 것이며
	어떠한 형태로 응답할 것이라는 것에 대한 약속 이다.
	예)domain/[약속1]/[약속2]/[약속3]/...
	일반적으로 restful 방식은 url 방식으로 데이터를 요청 하므로 데이터 조회에 국한하여 사용 한다.
	*/
	
	//list/페이지당 보여줄 리스트 수/볼 페이지
	@RequestMapping(value="/listSub/{pagePerCnt}/{page}")
	public HashMap<String, Object> listSub(@PathVariable int page, @PathVariable int pagePerCnt) {
		//@PathVariable 은 요청 경로의 특정 부분을 변수에 담는 기능이다.
		HashMap<String, Object> map = new HashMap<String, Object>();				
		logger.info(pagePerCnt+"/"+page);
		map.put("list", board.list(page, pagePerCnt));				
		return map;
	}
	
	//restController 에서 페이지 이동시 ModelAndView 를 사용 하자
	//String  사용시 문자열이 그대로 response 로 출력이 된다.
	@RequestMapping(value="/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		return mav;
	}
	
	@RequestMapping(value="/list")
	public HashMap<String, Object> list(@RequestParam Map<String, String> params) {
		String page = params.get("page");
		String pagePerCnt = params.get("cnt");	
		return board.pagingList(Integer.parseInt(page), Integer.parseInt(pagePerCnt));
	}
	
}
