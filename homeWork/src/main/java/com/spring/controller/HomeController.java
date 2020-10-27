package com.spring.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.MemberDTO;
import com.spring.service.BoardService;
import com.spring.service.MemberService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired MemberService service;
	@Autowired BoardService bService;
	@Autowired HttpConnUtil conn;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value = "/withDraw", method = RequestMethod.GET)
	public String withDraw(Model model, HttpSession session) {
		String id = (String) session.getAttribute("loginId");
		if(service.withDraw(id) > 0) {
			model.addAttribute("msg", "회원 탈퇴가 정상적으로 처리되었어요");
		}
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		return "index";
	}
	
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET)
	public String joinForm() {
		return "joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Model model, @RequestParam HashMap<String, String> map ) {
		
		int success = service.join(map);
		String page = "joinForm";
		
		if(success > 0) {
			model.addAttribute("msg","회원 가입에 성공했습니다.");
			page = "index";
		} else {
			model.addAttribute("msg","회원 가입에 실패했습니다.");
		}
		
		return page;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, HttpSession session, @RequestParam String id, String pw) {
		
		String page = "index";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptPw = service.login(id);
		
		if(encoder.matches(pw, encryptPw)) {
			session.setAttribute("loginId", id);
			page = "list";
			model.addAttribute("list", bService.list());
		} else {
			model.addAttribute("msg", "ID 또는 비밀번호를 확인해주세요.");
		}
		
		return page;
	}
	// 스케쥴러는 한번 시작하면 서비스를 내릴 때 까지 멈출수가 없다.
	// 0에서 시작해서 5씩 증가
	//@Scheduled(cron = "0/5 * * * * MON-FRI")
	// XML 방식과 annotation 방식은 혼용 할 수 없다.
	
	
}
