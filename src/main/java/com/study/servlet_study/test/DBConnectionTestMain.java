package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.servlet_study.config.DBConnectionMgr;

public class DBConnectionTestMain {
	public static void main(String[] args) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();//getInstance라는 메서드 호출 비스듬하게 누워있으면  static
																						// 똑바로서있으면 일반 멤버 변수
		//getConnection 일반 메서드 는 생성해야지만 사용가능 싱글톤으로 객체 생성 후에 사용가능		
		//
		 Connection con = null;//인터페이스를 규ㅜ현한 크랫
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		
		
		try {//예외처리 하는이유 = 실행하다가 예외가 뜨면 강제로 꺼지는 데 그걸 방지하기 위해서
			con = pool.getConnection();//얘만 호출해주면 db랑 연결해준다
			//커넥션이라는 객체에 db랑 자바랑 연결되어있음
			String name = "junil";
			String sql = "select * from author_tb where author_name = ?"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);//물음표순서,이름
			
			rs = pstmt.executeQuery();//ctrl+enter 한거임
			
			List<Author> authorList = new ArrayList<>();
			
			while(rs.next()) {//다음이 있으면 또내려간다
				authorList.add(Author.builder()
						.authorId(rs.getInt(1))
						.authorName(rs.getString(2))
						.build());

				//while(rs.next()) {
					//System.out.println("id: " + rs.getInt(1));//컬럼 첫번째 id
					//System.out.println("name: "+ rs.getString(2));//컬럼 두번째 name까지하고 while로돌아가서 next
				//
			}
			
			authorList.forEach(author -> System.out.println(author));//()객체 주소 
			
//			for(Author author : authorList) {
//				System.out.println(author);//위아래 같은그 3개
//			}
//			
//			for(int i = 0; i < authorList.size(); i++) {
//				Author author = authorList.get(i);
//				System.out.println(author);
//			}
			
			
			
			
		
	} catch(Exception e) {//예외가 일어났을때 예외를 catch하는거 그래서 예외를 처리하겠다
		e.printStackTrace();
		 
	} finally {// pool에서 빌렸으니 반납해야한다.
		pool.freeConnection(con, pstmt, rs);//지역메서드 안에 있어서 밖으로 뺀다음 null값을 줘서 finally처리함
	}

}
}
	
