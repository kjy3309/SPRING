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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.goodee.service.BoardService;

@Controller
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardService service;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", service.list());
		return "list";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm(HttpSession session) {
		HashMap<String, Object> fileList = new HashMap<String, Object>();
		session.setAttribute("fileList", fileList);
		return "writeForm";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(HttpSession session) {
		return "writeForm";
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public String uploadForm() {
		return "uploadForm";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpSession session, MultipartFile file, Model model) {
		model.addAttribute("path", service.upload(session, file));
		return "uploadForm";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> del(@RequestParam String fileName, HttpSession session, Model model) {
		logger.info("삭제 요청"+fileName);
		return service.del(fileName, session);
		
	}
}
