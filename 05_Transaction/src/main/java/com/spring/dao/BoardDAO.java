package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.dto.BoardDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list(); // 게시판 불러오기

	BoardDTO detail(String idx); // 게시판 상세보기

	void bHit(String idx); // 조회수 올리기

	int login(String id, String pw); // 로그인

	int write(BoardDTO dto); // 글쓰기

	int update(String subject, String content, String idx);

	int delete(String idx);

}
