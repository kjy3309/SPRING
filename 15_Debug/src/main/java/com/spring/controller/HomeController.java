package com.spring.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.CrudService;

@Controller
public class HomeController {
	
	@Autowired CrudService service;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//리스트 보기
	@RequestMapping(value = "/")
	public ModelAndView home() {	
		return service.list();
	}
	
	//삭제
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("idx") String idx) {
		service.delete(idx);
		return "redirect:/";
	}
	
	//글쓰기 페이지 이동
	@RequestMapping(value = "/writeView")
	public String listView() {
		return "writeView";
	}
	
	//글쓰기
	@RequestMapping(value = "/write")
	public String write(@RequestParam HashMap<String, String> params) {
		String writer = params.get("user_name");
		//String writer = params.get("writer");
		String subject = params.get("subject");
		String content = params.get("content");
		service.write(writer,subject,content);
		return "redirect:/";
	}
	
	//상세보기 
	@RequestMapping(value = "/contentView")
	/*public ModelAndView contentView(@RequestParam("idx") String idx) {	*/
	public ModelAndView contentView(String idx) {	
		return service.contentView(Integer.parseInt(idx));
	}
	
	@RequestMapping(value = "/updateForm")
	/*public ModelAndView contentView(@RequestParam("idx") String idx) {	*/
	public ModelAndView updateForm(String idx) {	
		return service.updateForm(Integer.parseInt(idx));
	}
	
}
