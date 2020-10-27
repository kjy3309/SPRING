package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.BoardInter;
import com.spring.dto.BoardBean;

@Service
public class BoardService {
	
	@Autowired BoardInter inter;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	//리스트 보기
	public ArrayList<BoardBean> list(int page, int pagePerCnt) {
		int end = page * pagePerCnt;
		int start = end - pagePerCnt+1;	
		return inter.listCall(start,end);	
	}

	public HashMap<String, Object> pagingList(int page, int pagePerCnt) {
		//리스트/현재페이지/최대 만들수 있는 페이지수
		HashMap<String, Object> json = new HashMap<String, Object>();
		
		logger.info("현재 페이지 : {}",page);
		logger.info("페이지당 보여줄 수  : {}",pagePerCnt);		

		int allCnt = inter.allCount();//전체 게시물 수
		//만들 수 있는 최대 페이지
		//총갯수:21, 페이지당 보여줄 수:5 이때 만들 페이지는???
		int range = allCnt%pagePerCnt>0?
				Math.round(allCnt/pagePerCnt)+1
				:Math.round(allCnt/pagePerCnt);
		
		/*
			25개 게시물에서 5개 5페이지 였다가 20개로 변경 하면 5 페이지 내용이 없으므로...
			현재페이지가 생성 가능 페이지 보다 크면 생성 가능 페이지 만큼으로 변경 해야 한다.
			요청 페이지가 생성 가능 페이지 보다 클 경우 생성 가능 페이지로 적용
		*/						
		if(page>range) {
			page = range;
		}
		
		int end = page * pagePerCnt;
		int start = end - pagePerCnt+1;
				
		json.put("currPage",page);
		json.put("range", range);
		json.put("list", inter.listCall(start,end));		

		return json;
	}

}
