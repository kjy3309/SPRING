package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.BoardDAO;
import com.spring.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired BoardDAO dao;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public ModelAndView list() {
		
		ArrayList<BoardDTO> list = dao.list();
		logger.info("size : "+list.size());
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("list");
		
		return mav;
	}

	
	/* 
	   옵션은 줘도 되고 안줘도 된다. 
	  READ_COMMITTED : 다른 트랜잭션에서 COMMIT 된 데이터만 읽어올 수 있게 하는 Level (기본 옵션)
	  READ_UNCOMMITTED : 다른 트랜잭션에서 COMMIT 되지 않은 데이터도 읽을 수 있는 Level
	  REPEATABLE_READ : 조회중인 데이터를 다른 트랜잭션에서 UPDATE 하지 못하도록 막는 Level
	  SERIALIZABLE : 한 트랜잭션의 작업 내용이 COMMIT 될 때 까지 다른 트랜잭션은 CRUD 할 수 없다.
	*/
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public ModelAndView detail(String idx) {
		dao.bHit(idx); // 조회수 올리기
		BoardDTO detail = dao.detail(idx);
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", detail);
		mav.setViewName("detail");
		
		return mav;
	}


	public int login(String id, String pw) {
		
		int cnt = dao.login(id,pw);
		logger.info("회원 여부 = "+cnt);
		
		return cnt;
	}


	public int write(BoardDTO dto) {
		int result = dao.write(dto);
		return result;
	}


	public ModelAndView updateDetail(String idx) {
		BoardDTO detail = dao.detail(idx);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("info", detail);
		mav.setViewName("updateForm");
		
		return mav;
	}


	public ModelAndView update(String subject, String content, String idx) {
		int result = dao.update(subject, content, idx);
		ModelAndView mav = new ModelAndView();
		if(result > 0) {
			mav.setViewName("redirect:/list");
		} else {
			mav.setViewName("updateForm");
			mav.addObject("msg", "수정에 실패했습니다.");
		}
		return mav;
	}


	public ModelAndView delete(String idx) {
		int result = dao.delete(idx);
		String page = "redirect:/detail?idx="+idx;
		
		if(result>0) {
			page = "redirect:/list";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(page);
			
		return mav;
	}

}
