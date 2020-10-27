package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired BoardDAO dao;

	public ArrayList<BoardDTO> list() {
		return dao.list();
	}

	public int write(HashMap<String, String> map) {
		return dao.write(map);
	}

	public BoardDTO detail(String idx) {
		dao.upHit(idx);
		return dao.detail(idx);
	}

	public int del(String idx) {
		return dao.del(idx);
	}

	public void update(HashMap<String, String> map) {
		dao.update(map);
	}

}
