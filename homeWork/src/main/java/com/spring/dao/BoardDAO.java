package com.spring.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.spring.dto.BoardDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list();

	int write(HashMap<String, String> map);

	BoardDTO detail(String idx);

	void upHit(String idx);

	int del(String idx);

	void update(HashMap<String, String> map);

}
