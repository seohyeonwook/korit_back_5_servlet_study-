package com.study.servlet_study.service;

import com.study.servlet_study.repository.BookRepository;
import com.study.servlet_study.test.Book;

public class BookService {
	private static BookService instance;
	private BookRepository bookRepository;
	
	private BookService() {
		bookRepository = BookRepository.getInstance();
	}
	
	public static BookService getinstance() {
		if(instance == null) {
			instance = new BookService();
		
		}
		return instance;
	}
	//--------------------------------------------------------------
	
	
	public boolean addBook(Book book) {
		return bookRepository.saveBook(book) > 0;
	}
	
	public Book getBook(int bookId) {
		return bookRepository.findBookByBookId(bookId);
	}

}
