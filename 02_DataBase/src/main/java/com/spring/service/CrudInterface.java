package com.spring.service;

import java.util.HashMap;

import org.springframework.ui.Model;


// 인터페이스 사용 이유? -> 필수로 사용할 메서드를 강제화 시킬 경우(규격)
public interface CrudInterface {
	
	public void listView(Model model); // 리스트 보기
	
	public void write(HashMap<String, String> params); // 글 쓰기
	
	public void contentView(Model model); // 상세 보기
	
	public void modify(Model model); // 수정 하기
	
	public void delete(Model model); // 삭제 하기
}
