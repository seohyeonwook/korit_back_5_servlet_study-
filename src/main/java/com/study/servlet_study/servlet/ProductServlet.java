package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿 클래스로, 웹 요청에 대한 처리를 담당합니다. 
// doGet 메서드는 GET 요청에 대한 처리를, doPost 메서드는 POST 
// 요청에 대한 처리를 담당합니다. /account 경로로 매핑되어 있습니다.
// GET 요청은 유저네임에 해당하는 계정을 조회하고, POST 요청은 요청으로부터
// 받은 정보를 사용하여 계정을 추가합니다.
// 4번

// ProductServlet 클래스는 ProductService를 사용하여 웹 요청에 대한 처리를 수행하고,
// 서블릿이 초기화될 때 ProductService의 인스턴스를 생성합니다

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String productName = getInitParameter("productName");
	String price = getInitParameter("price");
	String size = getInitParameter("size");
	String color = getInitParameter("color");
	}

}
