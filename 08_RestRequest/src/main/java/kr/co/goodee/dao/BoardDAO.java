package kr.co.goodee.dao;

import java.util.ArrayList;

import kr.co.goodee.dto.BoardDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list(int start, int end); // 게시물 가져오기

	int allCount(); // 전체 갯수 가져오기

}
