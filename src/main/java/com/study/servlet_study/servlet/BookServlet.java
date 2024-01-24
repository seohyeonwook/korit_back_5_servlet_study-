package com.study.servlet_study.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet_study.service.BookService;
import com.study.servlet_study.test.Author;
import com.study.servlet_study.test.Book;
import com.study.servlet_study.test.Publisher;
@WebServlet("/book")//여기 주소로 접속을하면 getset
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService;
       
    public BookServlet() {
        super();
        bookService = BookService.getinstance();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strBookId = request.getParameter("bookId");
		
		int bookId = 0;
		
		if(strBookId != null) {
			try {
				bookId = Integer.parseInt(strBookId);
			}catch(NumberFormatException e) {
				response.setContentType("text/plain");
				response.setStatus(400);
				response.getWriter().println("잘못된 형식의 데이터 입니다.");
				return;
			}
			
		}
		
		Book book = bookService.getBook(bookId);
		
		response.setContentType("text/plain");
		response.setStatus(200);
		response.getWriter().println(book);
		
		//서블릿 -> 서비스 -> 레파지토리 -> 데이터베이스 ->레파지토리 -> 서비스 ->서블릿
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName = request.getParameter("bookName");
		String authorName = request.getParameter("authorName");
		String pubilsherName = request.getParameter("publisherName");
		
		Book book = Book.builder()
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(pubilsherName).build())
				.build();
		boolean insertStatus = bookService.addBook(book);
		response.setContentType("text/plain");
		response.setStatus(201);
		response.getWriter().println(insertStatus);
		

	}

}
//도서등록 - 요청이니까 post -body