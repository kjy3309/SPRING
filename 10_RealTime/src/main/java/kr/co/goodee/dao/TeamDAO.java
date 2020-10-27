package kr.co.goodee.dao;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.goodee.dto.TeamDTO;

public interface TeamDAO {

	ArrayList<TeamDTO> listCall();

	int update(HashMap<String, String> params);

}
