package kr.co.goodee.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.goodee.service.LoginService;

@Controller
public class HomeController {
	
	@Autowired LoginService service;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		return "login";
	}
	
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(Model model, HttpSession session, @RequestParam HashMap<String, String> params) {
		logger.info("params : "+params);
		System.out.println(params);
		String page = "redirect:/";
		String msg = "로그인에 실패 했습니다.";
		
		if(service.login(params) > 0) {
			page = "result";
			msg = "로그인에 성공 했습니다.";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		mav.setViewName(page);
		
		return mav;
	}
	*/
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, RedirectAttributes rAttr, @RequestParam HashMap<String, String> params) {
		logger.info("params : "+params);
		System.out.println(params);
		String page = "redirect:/";
		String msg = "로그인에 실패 했습니다.";
		
		if(service.login(params) > 0) {
			page = "redirect:/result";
			msg = "로그인에 성공 했습니다.";
		}
		
		//RedirectAttribute 에 넣으면 redirect 시 기존 model 에 값을 넣은 것처럼 사용 가능
		rAttr.addFlashAttribute("msg", msg);
		
		return page;
	}
	
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public String result(Model model) {
		
		return "result";
	}
}
