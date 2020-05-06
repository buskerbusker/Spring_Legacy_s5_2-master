package com.iu.s5.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.s5.board.file.BoardFileVO;

@Component
public class FileDown extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		BoardFileVO boardFileVO = (BoardFileVO) model.get("file");
		String fileNmae = boardFileVO.getFileName();
		String path = "";
		if (boardFileVO.getBoard() == 1) {
			path = "/resources/noticeUpload";
		} else {
			path = "/resources/qndUpload";
		}
		path = request.getSession().getServletContext().getRealPath(path);

		// 1. HDD에서 파일을 읽을 준비
		File file = new File(path, fileNmae);

		// 2. 파일의 크기
		response.setContentLength((int) file.length());

		// 3. 다운로드시 파일 이름 인코딩
		String downName = URLEncoder.encode(boardFileVO.getOriName(), "UTF-8");

		// 4. header 설정
		response.setHeader("Content-disposition", "attachment;filename=\"" + downName + "\"");
		response.setHeader("Content-transfer-Encoding", "binary");

		// 5. Stream 연결 후 전송
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();

		FileCopyUtils.copy(fi, os);

		os.close();
		fi.close();

	}

}
