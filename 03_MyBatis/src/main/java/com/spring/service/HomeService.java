package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.BbsDAO;
import com.spring.dto.BbsDTO;

@Service
public class HomeService {
	
	@Autowired BbsDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 리스트 요청
	public ArrayList<BbsDTO> list() {
		ArrayList<BbsDTO> list = dao.list();
		return list;
	}

	public void delete(String idx) {
		int success = dao.delete(idx);
		logger.info("delete : "+success);
	}

	public BbsDTO detail(String idx) {
		BbsDTO info = dao.detail(idx);
		return info;
	}

	public void write(HashMap<String, String> params) {
		String user_name = params.get("user_name");
		String subject = params.get("subject");
		String content = params.get("content");
		
		int success = dao.write(user_name, subject, content);
		logger.info("success : "+success);
	}

}
