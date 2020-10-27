package kr.co.goodee.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.goodee.service.InterService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired InterService service;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("로그인 페이지 이동 요청");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, @RequestParam String pw, HttpSession session, Model model) {
		logger.info("로그인 요청 = "+id+"/"+pw);
		String userId = service.login(id, pw);
		logger.info("해당 아이디가 존재하는가? "+userId);
		
		if(userId == null) {
			return "redirect:/";
		}else {
			session.setAttribute("loginId", userId);
		}
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("로그아웃 요청");
		session.removeAttribute("loginId");
		return "redirect:/";
	}
	
	// list 요청을 하면 list.jsp 로 이동
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		logger.info("리스트 요청 컨트롤러");
		return "list";
	}
	
	// detail 요청을 하면 detail.jsp 로 이동
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail() {
		logger.info("상세보기 요청 컨트롤러");
		return "detail";
	}
	
}
