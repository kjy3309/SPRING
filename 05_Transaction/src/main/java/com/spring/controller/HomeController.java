package com.spring.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dto.BoardDTO;
import com.spring.service.BoardService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(Model model, HttpSession session) {
		logger.info("게시판 리스트 호출");
		String id = (String) session.getAttribute("loginId");
		ModelAndView mav = null;
		if(id != null) { // 로그인 했을 경우 list 메서드 실행
			mav = service.list();
		}else { // 로그인 하지 않았을 경우 login.jsp 로 보낸다.
			mav = new ModelAndView();
			mav.setViewName("login");
			mav.addObject("msg", "로그인이 필요한 서비스입니다.");
		}
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam String idx) {
		logger.info("게시판 상세보기 호출 "+idx);
		return service.detail(idx);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
		logger.info(id+" / "+pw);
		int cnt = service.login(id,pw);
		//request.getSession().....
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/";
		if(cnt > 0) {
			session.setAttribute("loginId", id);
			page = "redirect:/list";
		}
		mav.setViewName(page);
		
		return mav;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Model model) {
		session.removeAttribute("loginId");
		model.addAttribute("msg", "로그아웃 되었습니다.");
		return "login";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}
	
	// dto로 파라메터를 받기위한 조건
	// @ModelAttribute 를 사용 할 것
	// view 에서 보내는 name 과 dto 내의 필드명이 일치 할 것
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public ModelAndView write(@ModelAttribute BoardDTO dto) {
		logger.info("글쓰기 데이터들 : "+ dto.getSubject());
		int result = service.write(dto);
		ModelAndView mav = new ModelAndView();
		String page = "writeForm";
		if(result>0) {
			page = "redirect:/list";
		}
		mav.setViewName(page);
		return mav;
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam String idx) {
		return service.updateDetail(idx);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam String subject, String content, String idx) {
		return service.update(subject, content, idx);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam String idx) {
		return service.delete(idx);
	}
}
