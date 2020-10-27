package com.spring.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.spring.controller.HomeController;
import com.spring.dao.CrudDAO;
import com.spring.dto.CrudDTO;

@Service
public class CrudService implements CrudInterface {

	private static final Logger logger = LoggerFactory.getLogger(CrudService.class);	
	
	@Override
	public void listView(Model model) {
		logger.info("서비스 호출");
		CrudDAO dao = new CrudDAO();
		try {
			ArrayList<CrudDTO> list =  dao.listView();
			dao.resClose();
			model.addAttribute("list", list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void write(HashMap<String, String> params) {
		logger.info("글쓰기");
		CrudDAO dao = new CrudDAO();
		try {
			int success = dao.write(params);
			dao.resClose();
			logger.info("글쓰기 성공 여부 : "+success);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contentView(Model model) {
		logger.info("상세보기 서비스");
		// model 에 Controller 에서 넣은 파라메터 값을 뽑아옴 -> 비효율적
		Map<String, Object> map = model.asMap();
		String idx = (String) map.get("idx");
		logger.info("idx = "+idx);
		
		try {
			CrudDAO dao = new CrudDAO();
			CrudDTO dto = dao.detail(idx);
			model.addAttribute("info", dto);
			dao.resClose();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modify(Model model) {

	}

	@Override
	public void delete(Model model) {

	}

}
