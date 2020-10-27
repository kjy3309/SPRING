package kr.co.goodee.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;

@Service
public class BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardDAO dao;

	//리스트 보기
	public HashMap<String, Object> list(int page, int pagePerCnt) {
		
		// 몇번부터 몇번까지 가져올 것인가?
		// page : 1페이지 1 ~ ?
		// pagePerCnt : 5
		// page = 1, pagePerCnt = 5 === 1~5 까지 보여달라..
		int end = page * pagePerCnt;
		int start = end-pagePerCnt+1; //3page = 15까지..
		
		// 현재 만들 수 있는 페이지 수 (ex. 페이지당 5개)
		// 20 -- > 4page
		// 15 -- > 3paqe
		// 21 -- > 5page
		int totalCnt = dao.allCount();// 전체 게시물 수
		logger.info("전체 게시물 수 : "+totalCnt);
		int range = totalCnt%pagePerCnt > 0 ? Math.round((totalCnt/pagePerCnt)+1) : Math.round((totalCnt/pagePerCnt));
		
		logger.info(range+"page"); // 만들 수 있는 페이지
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("range", range);
		map.put("totalCnt", totalCnt);
		map.put("currPage", page);
		map.put("list", dao.list(start, end));
		
				
		return map;
	}

}
