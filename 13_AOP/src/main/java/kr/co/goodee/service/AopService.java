package kr.co.goodee.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AopService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void before() {
		logger.info("before 메서드 실행");
	}

	public void after(String str) {
		logger.info("after 메서드 실행 ("+str+")");
	}

	public String afterReturning() {
		logger.info("afterReturning 메서드 실행");
		return "afterReturning turn value";
	}

	public void afterThrowing() throws Exception {
		logger.info("afterThrowing 메서드 실행");
		throw new Exception("Test Exception"); 
		// 강제로 예외 발생
	}

	public void around(String string) {
		logger.info("around 메서드 실행, 인자값 = "+string);
		
	}

}
