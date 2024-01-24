package com.study.servlet_study.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.test.Author;
import com.study.servlet_study.test.Book;
import com.study.servlet_study.test.Publisher;

public class BookRepository {

	//0번 싱글톤 만들기
	private static BookRepository instance;
	private DBConnectionMgr pool;//2 게속 사용해야해서 위로 뺴둠
	
	private BookRepository() {//
		pool = DBConnectionMgr.getInstance();//3
	}
	public static BookRepository getInstance() {
		if(instance == null) {
			instance = new BookRepository();
		}
		return instance;
	}
	
	
	public int saveBook(Book book) {//3
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {//4 = insertmain에 만들어둔 try3개 가지고온다
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1, book.getAuthor().getAuthorName());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
		
			if(rs.next()) {
				
				book.getAuthor().setAuthorId(rs.getInt(1));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
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
		return 1;
	}
		//-----------------------------------------------------------------------요청

	public Book findBookByBookId(int bookId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book findBook = null;
		
		try {
			con = pool.getConnection();
			String sql ="select * from book_view where book_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookId);//물음표중에서 첫번째 물음표에 북아이디 넣어라
			rs = pstmt.executeQuery();
			
		
			
			if (rs.next()) {
			    findBook = Book.builder()
			            .bookId(rs.getInt(1))
			            .bookName(rs.getString(2))
			            .author(Author.builder()
			                    .authorId(rs.getInt(3))
			                    .authorName(rs.getString(4))
			                    .build())
			            .publisher(Publisher.builder()
			                    .publisherId(rs.getInt(5))
			                    .publisherName(rs.getString(6))
			                    .build())
			            .build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return findBook;
		
	}
	
}


