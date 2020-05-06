package com.iu.s5.notice;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;
import com.iu.s5.board.BoardVO;

public class NoticeDAOTest extends AbstractTestCase {

	@Autowired
	private NoticeDAO noticeDAO;

	@Test
	public void daoTest()throws Exception{
		this.boardWriteTest();
	}

	
	public void daoIsnull() {
		assertNotNull(noticeDAO);
	}
	
	
	public void boardWriteTest()throws Exception {
		String writer="";
		String title="";
		String contents="";
		for(int i=0;i<150;i++) {
			NoticeVO noticeVO = new NoticeVO();
			if(i%3==0) {
				writer="iu";
				title="Alert";
				contents="Samsung";
			}else if(i%3==1) {
				writer="choa";
				title="computer";
				contents="apple";
			}else {
				writer="suji";
				title="os";
				contents="linux";
			}
			noticeVO.setTitle(title+i);
			noticeVO.setWriter(writer);
			noticeVO.setContents(contents+i);
			int result = noticeDAO.boardWrite(noticeVO);
			if(i==50 || i==100) {
				Thread.sleep(1000);
			}
		}
	}
	
	
	public void boardDeleteTest()throws Exception{
		int result = noticeDAO.boardDelete(1);
		assertNotEquals(0, result);
	}
	
	
	public BoardVO boardSelectTest()throws Exception{
		BoardVO boardVO = noticeDAO.boardSelect(30);
		return boardVO;
	}
	
	/*
	 * public List<BoardVO> boardListTest()throws Exception{ return
	 * noticeDAO.boardList(); }
	 */
}
