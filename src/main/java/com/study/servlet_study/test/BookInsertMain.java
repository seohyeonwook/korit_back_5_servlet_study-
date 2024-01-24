package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;

public class BookInsertMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		System.out.println("도서명 >>>>");
		bookName = scanner.nextLine();
		System.out.println("저자명 >>>>");
		authorName = scanner.nextLine();
		System.out.println("출판사 >>>>");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder()//book객체 만들어
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())//한번에 넣기 앞에서는 따로넣음
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";//name이 물음표에 들어가서 변한다
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//insert할때만 매개변수로 넣어줌 Statement.RETURN_GENERATED_KEYS
			//keys = author테이블에 insert 할때 id값 가져오려고
			
			pstmt.setString(1, book.getAuthor().getAuthorName());
			pstmt.executeUpdate();
			//insert /update /delete = executeUpdate 
			ResultSet rs = pstmt.getGeneratedKeys();
			//1에 김준일이 들어갔는데 김준이를 넣으면 2번이라는 키값이 필요하다 insert등록하기위해서
			//어서랑 버ㅍ블리셔 아이디 키값이 필요하다 getGeneratedKeys 이거쓰면 집어넣은 값에 id값을 가져온다
		
			if(rs.next()) {
				//while 안돌리는 이유는 "insert into author_tb values (0 ?)"에 데이터값 하나만 넣기 떄문에
				book.getAuthor().setAuthorId(rs.getInt(1));
				//북은 이미 만들어져 있고 여기 안에 들어있는 get어서에서 set어서id를 넣어준다 방금만들어진 key값
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);//이거 두개만 freeConnection rs 는나가면 안쓸거라서
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			pstmt.setString(1, book.getPublisher().getPublisherName());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
		
			if(rs.next()) {
				
				book.getPublisher().setPublisherId(rs.getInt(1));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?,?,?)"; //밑에 1,2,3
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			pstmt.executeUpdate();
			 
			ResultSet rs = pstmt.getGeneratedKeys();
			
		
			if(rs.next()) {
				
				book.setBookId(rs.getInt(1));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		System.out.println("추가된 도서 정보");
		System.out.println(book);
		
	}

}
