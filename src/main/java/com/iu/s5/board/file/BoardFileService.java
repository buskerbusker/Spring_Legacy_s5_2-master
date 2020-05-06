package com.iu.s5.board.file;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.util.FileSaver;

@Service
public class BoardFileService {
	@Autowired
	private BoardFileDAO boardFileDAO;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private FileSaver fileSaver;

	public BoardFileVO fileSelect(BoardFileVO boardFileVO) throws Exception {
		return boardFileDAO.fileSelect(boardFileVO);
	}

	public int fileDelete(BoardFileVO boardFileVO) throws Exception {

		boardFileVO = boardFileDAO.fileSelect(boardFileVO);
		int result = boardFileDAO.fileDelete(boardFileVO);

		String board = "noticeUpload";
		if (boardFileVO.getBoard() == 2) {
			board = "qnaUpload";
		}
		String path = servletContext.getRealPath("/resources/" + board);
		fileSaver.deleteFile(boardFileVO.getFileName(), path);

		return result;
	}
	
	public String fileInsert(MultipartFile files) throws Exception{
		String path = servletContext.getRealPath("/resources/summmer");
		System.out.println(path);
		path = fileSaver.saveByStream(files, path);
		path = servletContext.getContextPath()+"/resources/summer/" + path;
		return path;
	}
	
	public int fileDelete(String fileName) throws Exception{
		String path = servletContext.getRealPath("/resources/summer");
		return fileSaver.deleteFile(fileName, path);
	}

}
