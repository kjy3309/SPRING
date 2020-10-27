package com.spring.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.BoardDTO;
import com.spring.dto.MemberDTO;
import com.spring.service.BoardService;
import com.spring.service.MemberService;

@RestController
public class AjaxController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired MemberService mService;
	
	@RequestMapping(value="/overlay")
	public MemberDTO overlay(@RequestParam String id){
		
		int result = mService.overlay(id);
		MemberDTO dto = new MemberDTO();
		
		if(result > 0) {
			dto.setMsg("중복된 ID 입니다.");
			dto.setIdChk(false);
		} else {
			dto.setMsg("사용 가능한 ID 입니다!");
			dto.setIdChk(true);
		}
		
		return dto;
	}
	
}
