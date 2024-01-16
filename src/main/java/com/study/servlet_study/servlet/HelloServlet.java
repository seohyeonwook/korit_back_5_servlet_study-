package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//클라이언트가 있고 서버한테 요청을 날린다 그럼 톰캣 서버로 간다 톰캣 서버에
	// 톰캣이 hello라는 주소를 찾아와서 doGet이있는지 찾는다 
	// doGet에 request,  response 객체 만든다
	//요청 주소를 먼저 확인하고 맞으면 필터가 있는지 확인한다 dofilter 들어가서 request,response들고온다
	//그리고 다운캐스팅 해서 인코딩 utf-8 실행하고 chin.doFilter 에 request,response 
	//가지고 다운캐스팅해서 다시 doGet 에 들어간다
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet은 오버라이드 된 메서드 매개변수 2개 받아옴
		//req(요청) resp(응답) wep 은 무조건 요청과 응답으로 이루어짐 둘 다 있어야한다
		System.out.println(request.getMethod());//request 가 가지고있는 메서드를 가지고 오겠다
		System.out.println(request.getRequestURL());// 경로를 전체 다 가지고 옴
		System.out.println(request.getRequestURI());// 서버주소는 떼고 가져옴
		//요청을 날리는건 클라이언트에서 -> GET요청 할거야(request)
		//response(응답)은 서버가 해준다
		System.out.println(response.getStatus());//정상적인 요청이면 서버에서 200을 준다
		
		response.setContentType("text/plain");//응답을 해줄때 데이터 타입을 정해주는거
		
		
		System.out.println(response.getContentType());//컨텐츠 타입이 NULL; ==응답된 데이터가 없다
		response.getWriter().println("hello");//응답을 줄 때 사용되는 코드
		
		System.out.println("요청이 들어옴!");
		//주소창에서 요청(엔터치면)날리면 request method가 무조건 get 요청
	}

}
