package com.study.servlet_study.test;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;

public class BookSearchMain {
	
	public static void main(String[] args) {
		
		//검색할 도서명을 입력하셈>> 글
		
		//도서명/저자명/출판사
		
		Scanner scanner = new Scanner(System.in);
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		List<Book> BookList = new ArrayList<>();// while 정보들이 여기 담긴다
		//사용이유 -  유동적인 갯수를 담기위해 공간이 정해져있지 않음
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		
		System.out.println("검색할 도서명을 입력하셈>>>>>");
		String bookName = scanner.nextLine();
		try { 
			con = pool.getConnection(); 
			String sql = "select\r\n"
					+ "	bt.book_id,\r\n"	    //1번
					+ "	bt.book_name,\r\n"      //2번
					+ "    bt.author_id,\r\n"   //3번
					+ "    at.author_name,\r\n" //4번
					+ "    bt.publisher_id,\r\n"//5번
					+ "    pt.publisher_name\r\n"//6번
					+ "from book_tb bt\r\n"
					+ "left outer join author_tb at on(at.author_id = bt.author_id)#\r\n"
					+ "left outer join publisher_tb pt on(pt.publisher_id = bt.publisher_id)\r\n"
					+ "where book_name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + bookName + "%");
			
			rs = pstmt.executeQuery();
			
			
			
			
			while(rs.next()) {
				Author author = Author.builder() //객체 생성해서 리스트에 넣어줘야한다
						.authorId(rs.getInt(3))
						.authorName(rs.getString(4))
						.build();
				Publisher publisher = Publisher.builder()
						.publisherId(rs.getInt(5))
						.publisherName(rs.getString(6))
						.build();
				Book book = Book.builder()
						.bookId(rs.getInt(1))
						.bookName(rs.getString(2))
						.author(author)
						.publisher(publisher)
						.build();
				
				BookList.add(book);
						
			}
				
			
			}catch (Exception e) {
			}finally {
				pool.freeConnection(con, pstmt, rs);
			}
		System.out.println("도서명 / 저자명 / 출판사");
		
		for(Book book : BookList) {
			System.out.println(book.getBookName() + "/" + book.getAuthor().getAuthorName() + "/"
					+book.getPublisher().getPublisherName());
			
		}
		
		
	}

}
