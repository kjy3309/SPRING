package com.spring.dao;

import java.util.ArrayList;

import com.spring.beans.BoardDTO;

public interface CrudInter {

	ArrayList<BoardDTO> list();

	int delete(int parseInt);

	void write(String writer, String subject, String content);
	
	void upHit(int idx);

	BoardDTO detail(int idx);

	void del(int parseInt);	

}
