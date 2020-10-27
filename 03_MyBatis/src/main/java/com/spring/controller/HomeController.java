package com.spring.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BbsDTO;
import com.spring.service.HomeService;

@Controller
public class HomeController {
	
	@Autowired HomeService service; // 이렇게 사용하면 객체화가 필요없음.
	
	// 사용 클래스 명을 따로 적어줄 필요가 없다.
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("list 요청");
		ArrayList<BbsDTO> list = service.list();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam String idx) {
		logger.info("delete 요청 : "+idx);
		service.delete(idx);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam String idx) {
		logger.info("detail 요청 : "+idx);
		BbsDTO info =  service.detail(idx);
		
		model.addAttribute("info", info);
		
		return "detail";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm(Model model) {
		return "writeForm";
	}
	
	
	// method 가 맞지 않으면 405 에러 출력
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, @RequestParam HashMap<String, String> params) {
		logger.info("params : {}", params);
		service.write(params);
		return "redirect:/";
	}
	
}
