package com.spring.dao;

import java.util.ArrayList;

import com.spring.dto.BoardBean;

public interface BoardInter {
	
	ArrayList<BoardBean> listCall(int start, int end);//리스트 불러 오기

	int allCount();//전체 게시물 수

}
