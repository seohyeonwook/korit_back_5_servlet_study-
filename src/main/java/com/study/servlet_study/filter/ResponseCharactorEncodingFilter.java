package com.study.servlet_study.filter;

import java.io.IOException;
import java.net.http.HttpResponse;

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


@WebFilter("/*")//모든 요청주소 적용 의미/*
//Filter는 전처리와 후처리가 있다
//클라이언트가 서버에 요청하면 
//전처리 필터를 거치면 doGet으로 가고 doGet을 거치면 후처리 필터로간다
public class ResponseCharactorEncodingFilter extends HttpFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//Http안붙으면 무조건 다운캐스팅 해서 써야함
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//다운캐스팅해라 다운캐스팅은 자식클래스가 있어야함 자식에서 업캐스팅돼서 다시 다운캐스팅 되는거임
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
	
		httpResponse.setCharacterEncoding("utf-8");
		//전처리 영역
		chain.doFilter(request, response);//최종필터를 호출하고 최종필터는 doGet을 호출한다
		//후처리 영역
//		httpResponse.getWriter().println("무조건 후처리함");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
