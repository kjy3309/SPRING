package kr.co.goodee.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.goodee.dto.UserInfo;

@RestController // ResponseBody 를 안써도된다.(Spring 4.2 이하에서는 못쓴다... jackson lib를 통해 사용함)
@RequestMapping(value="/rest") // rest 로 시작되는 놈은 이 컨트롤러로 와라
public class AjaxController {
	
	//ResponseEntity 는 사용하기 불편할 수 있지만 서버 상태를 보여줄 수 있다.
	@RequestMapping(value="/list")
	public ResponseEntity<ArrayList<String>> list(){
		ArrayList<String> list = new ArrayList<String>();
		list.add("하나");
		list.add("둘");
		list.add("셋");
		
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public HashMap<String, String> map() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("하나", "1");
		map.put("둘", "2");
		map.put("셋", "3");
		return map;
	}
	
	@RequestMapping(value = "/object", method = RequestMethod.GET)
	public UserInfo obj() {
		UserInfo info = new UserInfo();
		info.setAge(30);
		info.setGender("male");
		info.setId("admin");
		info.setName("jhone");
		info.setSuccess(true);
		
		return info;
	}
}
