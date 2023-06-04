//package com.shincha.ddh.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.shincha.ddh.util.JwtUtil;
//
//@Component
//public class JwtInterceptor implements HandlerInterceptor {
//	private static final String HEADER_AUTH = "access-token";
//
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		// 세션 중복 막기 && 토큰이 들어왔을 때 한 번 가능한지 확인하는 용도
//		if (request.getMethod().equals("OPTIONS")) {
//			return true;
//		}
//		String token = request.getHeader("access-token");
//		System.out.println("get token = " + token);
//		if (token != null) {
//			jwtUtil.valid(token);
//			return true;
//
//		}
//		throw new Exception("유효하지 않은 접근입니다.");
//		
//		return true;
//	}
//
//}
