package com.spring.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.CrudInter;

@Service
public class CrudService{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private SqlSession sqlSession;
	
	CrudInter inter = null;
	
	//리스트 보기
	public ModelAndView list() {			
		ModelAndView mav = new ModelAndView();		
		inter = sqlSession.getMapper(CrudInter.class);			
		mav.addObject("list", inter.list());
		mav.setViewName("list");
		return mav;
	}
	
	//삭제
	public void delete(String idx) {
		inter = sqlSession.getMapper(CrudInter.class);	
		inter.del(Integer.parseInt(idx));
		//inter.delete(Integer.parseInt(idx));
	}
	
	//글쓰기
	public void write(String writer, String subject, String content) {
		inter = sqlSession.getMapper(CrudInter.class);			
		inter.write(writer,subject,content);		
	}

	//상세 보기
	public ModelAndView contentView(int idx) {
		ModelAndView mav = new ModelAndView();		
		inter = sqlSession.getMapper(CrudInter.class);
		inter.upHit(idx);
		mav.addObject("content", inter.detail(idx));
		mav.setViewName("contentView");
		return mav;
	}

	public ModelAndView updateForm(int idx) {
		ModelAndView mav = new ModelAndView();		
		inter = sqlSession.getMapper(CrudInter.class);
		mav.addObject("content", inter.detail(idx));
		mav.setViewName("updateForm");
		return mav;
	}
	


}











