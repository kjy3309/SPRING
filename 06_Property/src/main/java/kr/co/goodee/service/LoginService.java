package kr.co.goodee.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.LoginDAO;


@Service
public class LoginService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired LoginDAO dao;
	@Value("#{config['manager.id']}") String adminId;
	@Value("#{config['manager.pw']}") String adminPw;
	@Value("#{config['manager.msg']}") String adminMsg;
	
	public int login(HashMap<String, String> params) {
		
		int cnt = 0;
		
		if(!params.get("mode").equals("admin")) {
			cnt = dao.login(params);
		} else {
			logger.info(adminId+" / "+adminPw+" / "+adminMsg);
			if(params.get("id").equals(adminId) && params.get("pw").equals(adminPw)) {
				cnt = 1;
			}
		}
		logger.info("cnt : "+cnt);
		return cnt;
	}

}
