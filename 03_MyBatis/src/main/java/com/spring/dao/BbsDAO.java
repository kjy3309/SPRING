package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BbsDTO;

public interface BbsDAO {

	ArrayList<BbsDTO> list(); // 게시판 리스트 가져오기

	int delete(String idx); // 삭제하기

	BbsDTO detail(String idx); // 상세보기

	int write(String user_name, String subject, String content); // 글쓰기
	
}
