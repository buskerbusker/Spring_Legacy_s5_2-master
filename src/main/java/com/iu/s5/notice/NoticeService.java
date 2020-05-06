package com.iu.s5.notice;

import java.awt.peer.ListPeer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iu.s5.board.BoardService;
import com.iu.s5.board.BoardVO;
import com.iu.s5.board.file.BoardFileDAO;
import com.iu.s5.board.file.BoardFileVO;
import com.iu.s5.util.FileSaver;
import com.iu.s5.util.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private NoticeDAO noticeDAO;
	@Autowired
	private FileSaver fileSaver;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private BoardFileDAO boardFileDAO;

	@Override
	public List<BoardVO> boardList(Pager pager) throws Exception {

		pager.makeRow();
		long totalCount = noticeDAO.boardCount(pager);
		pager.makePage(totalCount);

		return noticeDAO.boardList(pager);
	}

	@Override
	public BoardVO boardSelect(long num) throws Exception {
		// TODO Auto-generated method stub
		noticeDAO.hitUpdate(num);
		return noticeDAO.boardSelect(num);
	}

	@Override
	public int boardWrite(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub

		String path = servletContext.getRealPath("/resources/noticeUpload");
		System.out.println(path);

		// sequence 번호 받기
		boardVO.setNum(noticeDAO.boardNum());
		// notice table insert
		int result = noticeDAO.boardWrite(boardVO);

		for (MultipartFile file : files) {
			if (file.getSize() > 0) {
				BoardFileVO boardFileVO = new BoardFileVO();
				String fileName = fileSaver.saveByTransfer(file, path);
				boardFileVO.setNum(boardVO.getNum());
				boardFileVO.setFileName(fileName);
				boardFileVO.setOriName(file.getOriginalFilename());
				boardFileVO.setBoard(1);
				boardFileDAO.fileInsert(boardFileVO);
			}
		}
		return result;
	}

	@Override
	public int boardUpdate(BoardVO boardVO, MultipartFile[] files) throws Exception {
		// TODO Auto-generated method stub

		// HDD file save
		String path = servletContext.getRealPath("/resources/noticeUpload");
		System.out.println(path);
		int result = noticeDAO.boardUpdate(boardVO);

		for (MultipartFile multipartFile : files) {
			if (multipartFile.getSize() > 0) {
				String fileName = fileSaver.saveByUtils(multipartFile, path);
				BoardFileVO boardFileVO = new BoardFileVO();
				boardFileVO.setNum(boardVO.getNum());
				boardFileVO.setFileName(fileName);
				boardFileVO.setOriName(multipartFile.getOriginalFilename());
				boardFileVO.setBoard(1);
				result = boardFileDAO.fileInsert(boardFileVO);
			}
		}

		return result;
	}

	@Override
	public int boardDelete(long num) throws Exception {
		List<BoardFileVO> list = boardFileDAO.fileList(num);

		// 1. hdd의 파일삭제
		String path = servletContext.getRealPath("/resources/noticeUpload");
		System.out.println(path);
		for (BoardFileVO boardFileVO : list) {
			fileSaver.deleteFile(boardFileVO.getFileName(), path);
		}
		// 2. db에서 삭제
		boardFileDAO.fileDeleteAll(num);
		return noticeDAO.boardDelete(num);

	}

}
