package kr.co.goodee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.BoardDAO;

@Service
public class ScheduleService {
	
	@Autowired BoardDAO dao;

	// 스케쥴러는 한번 시작하면 서비스를 내릴 때 까지 멈출수가 없다.
	// 0에서 시작해서 5씩 증가
	//@Scheduled(cron = "0/5 * * * * MON-FRI")
	// XML 방식과 annotation 방식은 혼용 할 수 없다.
	public void loop5() {
		System.out.println("5초 마다 찍는다.");
	}
	
	//@Scheduled(fixedDelay = 5000) // 5초 후
	//@Scheduled(fixedRate = 5000) // 5초 단위로 실행
	public void loop() {
		System.out.println("5초 마다 찍는다.");
	}
	
	@Scheduled(cron = "0/5 * * * * MON-FRI")
	public void memberCount() {
		int count = dao.memberCount();
		//System.out.println("게시글 수"+count);
		
		System.out.println("\""+"큰따옴표 사용"+"\"");
	}
}
