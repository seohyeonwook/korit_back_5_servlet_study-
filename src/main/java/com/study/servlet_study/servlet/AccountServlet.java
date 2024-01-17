package com.study.servlet_study.servlet;//4번째
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.entity.Account;
import com.study.servlet_study.service.AccountService;

@WebServlet("/account")//요청주소랑 맞춰줘얗
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService;
       
    public AccountServlet() {
        super();
        accountService = AccountService.getInstance(); // AccountService의 인스턴스를 가져와 초기화
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		// 사용자로부터 받은 유저네임을 사용하여 AccountService를 통해 계정 정보를 가져옴
		Account account = accountService.getAccount(username);
		
		// 응답 상태를 200으로 설정하고, 계정 정보를 출력
		response.setStatus(200);
		response.getWriter().println(account);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자로부터 받은 파라미터 값을 변수에 저장
		String username = request.getParameter("username");//이렇게 하지말고 그냥 객체로 하나 묶자 -> entity
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// 받은 정보로 Account 객체를 생성 (빌더 패턴 사용)
		Account account = Account.builder()//어카운트로 하나로 묶음
				.username(username)
				.password(password)
				.name(name)
				.email(email)
				.build();
		// AccountService를 통해 계정 정보를 추가하고, 결과를 받아옴
			int body = accountService.addAccount(account);
			
			 // 응답 상태를 201(Created)으로 설정하고, 결과를 출력
			response.setStatus(201);
			response.getWriter().println(body);
		
		
	}

}
