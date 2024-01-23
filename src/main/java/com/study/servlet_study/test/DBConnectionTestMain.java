package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;

public class DBConnectionTestMain {
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();//getInstance라는 메서드 호출 비스듬하게 누워있으면  static
																						// 똑바로서있으면 일반 멤버 변수
		//getConnection 일반 메서드 는 생성해야지만 사용가능 싱글톤으로 객체 생성 후에 사용가능		
		//
		 Connection con = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		
		
		try {//예외처리 하는이유 = 실행하다가 예외가 뜨면 강제로 꺼지는 데 그걸 방지하기 위해서
			//try 안쪽 - 예외가 일어날수 있는 녀석들 넣는다
			con = pool.getConnection();
			//커넥션이라는 객체에 db랑 자바랑 연결되어있음
			String sql = "select * from author_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();//ctrl+enter 한거임
			
			List<Author> authorList = new ArrayList<>();
			
			while(rs.next()) {
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());

				//while(rs.next()) {//다음이 있으면 또내려간다
					//System.out.println("id: " + rs.getInt(1));//컬럼 첫번째 id
					//System.out.println("name: "+ rs.getString(2));//컬럼 두번째 name까지하고 while로돌아가서 next
				//
			}
			
			authorList.forEach(author -> System.out.println(author));
			
			
			
			
		
	} catch(Exception e) {//예외가 일어났을때 예외를 catch하는거 그래서 예외를 처리하겠다
		e.printStackTrace();
		 
	} finally {// pool에서 빌렸으니 반납해야한다.
		pool.freeConnection(con, pstmt, rs);//지역메서드 안에 있어서 밖으로 뺀다음 null값을 줘서 finally처리함
	}

}
}
	
