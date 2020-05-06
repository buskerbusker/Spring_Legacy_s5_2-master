package com.iu.s5.member;

import com.iu.s5.member.memberFile.MemberFileVO;

public class MemberVO {
	
	private String id;
	private String pw;
	private String name;
	private int age;
	private String email;
	private String phone;
	private MemberFileVO memberFileVO;
	
	
	
	public MemberFileVO getMemberFileVO() {
		return memberFileVO;
	}
	public void setMemberFileVO(MemberFileVO memberFileVO) {
		this.memberFileVO = memberFileVO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
