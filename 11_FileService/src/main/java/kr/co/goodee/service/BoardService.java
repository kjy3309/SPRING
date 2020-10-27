package kr.co.goodee.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.goodee.dao.BoardDAO;
import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.dto.FileDTO;

@Service
public class BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired BoardDAO dao;
	@Value("#{config['Globals.root']}") String root;
	private String fullpath = null;
	
	public void list(Model model) {
		logger.info("리스트 요청 서비스");
		ArrayList<BoardDTO> list = dao.list();
		logger.info("리스트 갯수 : "+list.size());
		model.addAttribute("list", list);
	}

	public ModelAndView upload(MultipartFile file, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		// 파일을 request 에서 뽑아오기
		fullpath = root+"upload/";
		
		// 파일명을 추출
		String fileName = file.getOriginalFilename();
		
		// 새로운 파일명 만들기
		String newFileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));
		
		// 파일명 바꾸기 + 저장하기 (java.nio <- java 7 부터 적용)
		try {
			byte[] bytes = file.getBytes(); // 바이너리를 추출
			Path path = Paths.get(fullpath+newFileName); // 저장 경로를 지정
			Files.write(path, bytes); // 파일 저장
			
			// 업로드 성공한 파일을 session 에 저장
			HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
			fileList.put(newFileName, fileName);
			logger.info("업로드 한 파일 갯수 : "+fileList.size());
			session.setAttribute("fileList", fileList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// DB 에 저장하기(X) <- 파일 업로드 과정에서 수시로 올리고 지우고 할거기 때문에...
		mav.addObject("path", "photo/"+newFileName); //업로드 성공한 파일 경로를 제공
		mav.setViewName("uploadForm" );
		
		return mav;
	}

	public HashMap<String, Object> fileDelete(String fileName, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int success = 0;
		// 1. session 에서 fileList 가져오기
		HashMap<String, String> fileList = (HashMap<String, String>) session.getAttribute("fileList");
		// 2. 실제 파일 삭제하기
		String delFileName = root+"upload"+fileName;
		logger.info("지울 파일 경로 = "+delFileName);
		File file = new File(delFileName);
		if(file.exists()) {
			if(file.delete()) { // 파일이 존재할 경우 삭제 처리 후 성공하면 1로 변경
				success = 1;
			}
		} else {
			logger.info("이미 삭제된 상황");
			success = 1;
		}
		// 3. fileList 에서 삭제한 파일 지우기
		if(fileList.get(fileName) != null) { // 파일명이 리스트에 존재하면
			fileList.remove(fileName); // 지운다.
			logger.info("삭제 후 남은 파일 갯수 : "+fileList.size());
		}
		// 4. session 에 fileList 넣기
		session.setAttribute("fileList", fileList);
		// 5. 성공 여부 변경하기
		
		
		result.put("success", success);
		return result;
	}

	@Transactional
	public ModelAndView write(HashMap<String, String> params, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String page = "redirect:/writeForm";
		BoardDTO bean = new BoardDTO(); // 빈을 사용해야만 방금 넣은 값의 키를 알 수 있다.
		
		// 1. bbs 테이블에 작성자, 제목, 내용 넣기
		bean.setSubject(params.get("subject"));
		bean.setContent(params.get("content"));
		bean.setUser_name(params.get("user_name"));
		
		HashMap<String, Object> fileList = (HashMap<String, Object>) session.getAttribute("fileList");
		
		if(dao.write(bean) == 1) { // generateKey 를 사용했기 때문에 쿼리 실행 완료 후 bean에 idx 값이 생긴다.
			// 2. photo 테이블에 파일이 소속된 idx, 원본 파일명, 새 파일명 넣기
			int size = fileList.size();
			logger.info("저장할 파일 수"+size);
			int idx = bean.getIdx();
			if(size>0) { // 업로드한 파일이 있다면...
				// idx, oriFileName, newFileName
				logger.info(idx+" 번 게시물에 소속된 파일 등록");
				for(String key : fileList.keySet()) {
					// oriFileName, newFileName, idx
					dao.writeFile(idx, (String)fileList.get(key), key);
				}
			}
			page = "redirect:/detail?idx="+idx; // 등록된 상세 페이지로 이동 (detail 이 만들어지기 전 까지는 404)
		} else {
			// 세션의 fileList 에 저장된 모든 파일을 삭제
			// 이후 fileList 내용도 삭제
			for(String fileName : fileList.keySet()) {
				File file = new File(root+"upload/"+fileName);
				boolean success = file.delete();
				logger.info(fileName+" 삭제 결과 = "+success);
			}
		}
		session.removeAttribute("fileList");
		mav.setViewName(page);
		return mav;
	}

	public ModelAndView detail(String idx) {
		ModelAndView mav = new ModelAndView();
		BoardDTO dto = dao.detail(idx);
		ArrayList<FileDTO> fileList = dao.fileList(idx);
		
		mav.addObject("fileList", fileList);
		mav.addObject("info", dto);
		mav.setViewName("detail");
		return mav;
	}

	public void download(String oriFileName, String newFileName, HttpServletResponse resp) {
		
		try {
			// 1. 파일 객체 생성
			Path path = Paths.get(root+"upload/"+newFileName);
			// 2. 파일을 읽어오기  
			byte[] file = Files.readAllBytes(path);
			// 3. response 파일을 싣기, 보낼 파일에 대한 예고
			oriFileName = URLEncoder.encode(oriFileName, "UTF-8");
			resp.setContentType("application/octet-stream");
			resp.setHeader("content-Disposition", "attachment;filename=\""+oriFileName+"\"");
			//resp.setHeader("content-Disposition", "inline");
			// 4. 보내기
			OutputStream os = resp.getOutputStream();
			BufferedOutputStream buf = new BufferedOutputStream(os); // 속도 향상
			buf.write(file);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
