package com.study.servlet_study.filter;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class RequestCharactorEncodingFilter extends HttpFilter implements Filter {
       

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//HttpServletResponse httpResponse = (HttpServletResponse) response; 후처리들 필요없음
		
		String[] methods = new String[] {"POST", "PUT" };//바로 값 주입
		System.out.println(httpRequest.getMethod());
		if(Arrays.asList(methods).contains(httpRequest.getMethod().toUpperCase())) {
			httpRequest.setCharacterEncoding("utf-8");
		}
		
		
		//httpRequest.setCharacterEncoding("utf-8");//걍 이거 써도 됨.
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
