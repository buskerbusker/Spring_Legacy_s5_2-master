package com.iu.s5.member;

import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.iu.s5.AbstractTestCase;

public class MemberDAOTest extends AbstractTestCase{

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void memberJoinTest() throws Exception {
		Random random = new Random();
		
		for(int i=0;i<100;i++)  {
			String id="";
			String pw="";
			String name="";
			String phone="010";
			String email="";
			int age=0;
			MemberVO memberVO = new MemberVO();
			if(i<30) {
				id="iu"+i;
				pw="iu"+i;
				name="iu"+i;
				email="iu"+i+"@naver.com";
			}else if(i<60) {
				id="choa"+i;
				pw="choa"+i;
				name="choa"+i;
				email="choa"+i+"@google.com";
			}else {
				id="suji"+i;
				pw="suji"+i;
				name="suji"+i;
				email="suji"+i+"@daum.net";
			}
			
			phone=phone+i+i+i+i+i+i+i+i;
			age = random.nextInt(100)+1;
			
			memberVO.setId(id);
			memberVO.setPw(pw);
			memberVO.setName(name);
			memberVO.setEmail(email);
			memberVO.setPhone(phone);
			memberVO.setAge(age);
			memberDAO.memberJoin(memberVO);
			if(i%10==0) {
				Thread.sleep(1000);
			}
		}
		
		
	}

}
