package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.dto.CrudDTO;
import com.spring.service.CrudService;

public class CrudDAO {

	private static final Logger logger = LoggerFactory.getLogger(CrudDAO.class);	
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public CrudDAO() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void resClose() throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(ps != null) {
			ps.close();
		}
		if(conn != null) {
			conn.close();
		}
	}

	public ArrayList<CrudDTO> listView() {
		logger.info("DAO 실행");
		String sql = "SELECT idx, user_name, subject FROM bbs";
		ArrayList<CrudDTO> list = new ArrayList<CrudDTO>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int idx = rs.getInt("idx");
				String user_name = rs.getString("user_name");
				String subject = rs.getString("subject");
				CrudDTO dto = new CrudDTO(idx, user_name, subject);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public CrudDTO detail(String idx) {
		CrudDTO dto = new CrudDTO();
		String sql = "SELECT * FROM bbs WHERE idx=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, idx);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				// idx, user_name, subject, content
				dto.setIdx(rs.getInt("idx"));
				dto.setUser_name(rs.getString("user_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	public int write(HashMap<String, String> params) throws SQLException {
		String sql = "INSERT INTO bbs(idx, user_name, subject, content, bHit) VALUES (bbs_seq.NEXTVAL,?,?,?,0)";
		int success = 0;
		ps = conn.prepareStatement(sql);
		ps.setString(1, params.get("user_name"));
		ps.setString(2, params.get("subject"));
		ps.setString(3, params.get("content"));
		
		success = ps.executeUpdate();
		
		return success;
	}

}
