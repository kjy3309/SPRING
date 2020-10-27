package kr.co.goodee.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.TeamDAO;
import kr.co.goodee.dto.TeamDTO;

@Service
public class TeamService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired TeamDAO dao;

	public HashMap<String, Object> listCall() {
		logger.info("리스트 요청 서비스");
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("list", dao.listCall());
		
		return result;
	}

	public HashMap<String, Object> update(HashMap<String, String> params) {
		logger.info("수정 요청 서비스");
		
		int success = dao.update(params);
		logger.info("수정 요청 서비스 = "+success);
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("success", success);
		
		return result;
	}
}
