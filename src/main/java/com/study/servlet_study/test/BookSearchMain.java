package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.study.servlet_study.config.DBConnectionMgr;

public class BookSearchMain {
	
	public static void main(String[] args) {
		
		//검색할 도서명을 입력하셈>> 글
		
		//도서명/저자명/출판사
		
		Scanner scanner = new Scanner(System.in);
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		
		System.out.println("검색할 도서명을 입력하셈>>>>>");
		
		
		
	}

}
