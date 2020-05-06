package com.iu.s5.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iu.s5.member.MemberVO;

@Component
public class MemberInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean check = false;
		Object obj = request.getSession().getAttribute("member");
		if (obj != null) {
			check = true;
			System.out.println("멤버 페이지");
		} else {
			System.out.println("멤버 페이지x");
			response.sendRedirect("./memberLogin");
		}
		return check;
	}
}
