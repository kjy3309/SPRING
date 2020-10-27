package com.spring.controller;

import java.text.DateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.MemberService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired MemberService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public ModelAndView join(@RequestParam HashMap<String, String> params) {
		logger.info("params : {}",params);
		return service.join(params);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam HashMap<String, String> params) {
		logger.info("params : {}",params);
		return service.list(params);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam HashMap<String, String> params) {
		logger.info("params : {}",params);
		return service.update(params);
	}
	
	@RequestMapping(value = "/multi", method = RequestMethod.POST)
	public ModelAndView multi(@RequestParam(value="userName") String[] userName) {
		
		for (String name : userName) {
			logger.info("params : {}",name);
		}
		
		return service.multi(userName);
	}
	
}
