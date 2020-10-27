package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.service.BoardService;

@RestController
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public HashMap<String, Object> home() {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "잘못된 사용 요청입니다. 사용자 API 를 참조하세요.");
		
		return result;
	}
	
	/*
	 resultful = URL 을 보고 요청에 대한 내용을 짐작할 수 있어야 하는 것
	 url 의 인자 값이 어떤 의미를 내포하는지 사전에 약속이 되어있어야 한다.
	 해당 약속을 명시한 문서를 우리는 api 문서라고 부른다.
	 API : 사용 설명서
	 
	 [DOMAIN]/(인증키)/xml/CardSubwayStatsNew/1/5/20151101
	 
    KEY	String(필수)	인증키	OpenAPI 에서 발급된 인증키
	TYPE	String(필수)	요청파일타입	xml : xml, xml파일 : xmlf, 엑셀파일 : xls, json파일 : json
	SERVICE	String(필수)	서비스명	CardSubwayStatsNew
	START_INDEX	INTEGER(필수)	요청시작위치	정수 입력 (페이징 시작번호 입니다 : 데이터 행 시작번호)
	END_INDEX	INTEGER(필수)	요청종료위치	정수 입력 (페이징 끝번호 입니다 : 데이터 행 끝번호)
	USE_DT	STRING(필수)	사용일자	YYYYMMDD 형식의 문자열
	
	[DOMAIN]/(인증키)/json/CardSubwayStatsNew/1/50/20200812
	=> json 형태로 YYYYMMDD 날짜의 CardSubwayStatsNew 데이터를 1부터 50까지 요청?
	
	 */
	
	
	//list/{페이지당 보여줄 리스트 수}/{몇 페이지 볼건지}
	@RequestMapping(value = "/list/{pagePerCnt}/{page}", method = RequestMethod.GET)
	public HashMap<String, Object> list(@PathVariable int pagePerCnt, @PathVariable int page) {
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		logger.info("pagePerCnt : "+pagePerCnt);
		logger.info("page : "+page);
		
		return service.list(page, pagePerCnt);
	}
	
	//restController 는 무조건 response 로 응답하므로 특정 view 로 보내기가 어렵다.
	// 이 경우 ModelAndView 를 사용하면 원하는 뷰로 이동이 가능하다.
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("list");
		
		return mav;
	}
	
}
