package kr.co.goodee.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;

@Service
public class BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardDAO dao;
	
	@Value("#{config['Globals.root']}") String root;
	private String fullpath = null;
	
	public ArrayList<BoardDTO> list() {
		return dao.list();
	}

	public String upload(HttpSession session, MultipartFile file) {
		
		HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
		
		// 1. 원본명 뽑고.
		String oriFileName = file.getOriginalFilename();
		// 2. 새로바꿀 파일이름 만들어주고
		String newFileName = System.currentTimeMillis()+oriFileName.substring(oriFileName.lastIndexOf("."));
		fullpath = root + "upload/";
		// 3. 바꾼이름으로 저장한다음
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(fullpath+newFileName);
			Files.write(path, bytes);
			
			fileList.put(newFileName, oriFileName);
			session.setAttribute("fileList", fileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return "photo/"+newFileName;
		
	}

	public HashMap<String, Object> del(String fileName, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		// 1. 파일 객체화 시켜주고 파일삭제
		String newFileName = fileName.split("/")[1];
		File file = new File(root+"upload/"+newFileName);
		int success = 0;
		
		if(file.exists()) {
			if(file.delete()) {
				success = 1;
			}
		} else {
			success = 1;
		}
		HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
		
		if(fileList.get(newFileName) != null){
			fileList.remove(newFileName);
		}
		session.setAttribute("fileList", fileList);
		result.put("success", success);
		return result;
	}

}
