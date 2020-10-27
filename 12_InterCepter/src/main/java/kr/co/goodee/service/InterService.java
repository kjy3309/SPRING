package kr.co.goodee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.InterDAO;

@Service
public class InterService {

	@Autowired InterDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 로그인 요청
	public String login(String id, String pw) {
		
		return dao.login(id, pw);
	}

}
