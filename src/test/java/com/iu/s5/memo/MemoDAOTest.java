package com.iu.s5.memo;

import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;

public class MemoDAOTest extends AbstractTestCase {

	@Autowired
	private MemoDAO memoDAO;
	
	@Test
	public void test() throws Exception {
		for(int i=0;i<100;i++) {
			MemoVO memoVO = new MemoVO();
			memoVO.setWriter("writer"+i);
			memoVO.setContents("contents"+i);
			memoDAO.memoInsert(memoVO);
		}
	}

}
