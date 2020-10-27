package com.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.BoardDTO;
import com.spring.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService bService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("list", bService.list());
		return "list";
	}
	
	@RequestMapping(value = "/writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "writeForm";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(Model model, @RequestParam HashMap<String, String> map) {
		String page = "writeForm";
		
		if(bService.write(map) > 0){
			model.addAttribute("msg", "글이 작성되었습니다");
			page = "list";
			model.addAttribute("list", bService.list());
		}
		return page;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Model model, @RequestParam String idx) {
		model.addAttribute("bbs", bService.detail(idx));
		return "detail";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(Model model, @RequestParam String idx) {
		if(bService.del(idx) > 0) {
			model.addAttribute("msg", "글이 삭제되었어요");
			model.addAttribute("list", bService.list());
		}
		return "list";
	}
	
	@RequestMapping(value = "/updateForm", method = RequestMethod.GET)
	public String updateForm(Model model, @RequestParam String idx) {
		model.addAttribute("bbs", bService.detail(idx));
		return "updateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, @RequestParam HashMap<String, String> map) {
		bService.update(map);
		model.addAttribute("msg", "수정에 성공했어요");
		model.addAttribute("bbs", bService.detail(map.get("idx")));
		return "detail";
	}
}
