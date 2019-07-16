package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dto.CardDTO;

public class CardDAO {

	private DataSource dataFactory;
	
	// 싱글톤 패턴(lazy holder) https://jeong-pro.tistory.com/86 참고
	private CardDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static class LazyHolder {
		public static final CardDAO INSTANCE = new CardDAO();
	}
	
	public static CardDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	// 싱글톤 여기까지!
	
	public CardDTO getLastInsertCard(Connection con)
	{
		PreparedStatement pstmt = null;
		ResultSet rs;	
		
		CardDTO dto = null;
		
		try {
			// 커넥션을 가져온다.
			
			String query = "select * from Card where num = LAST_INSERT_ID()";
			
			pstmt = con.prepareStatement(query);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당하는 카드리스트가 있는 경우
				
				int num2 = rs.getInt("num");
				String title2 = rs.getString("title");
				String content2 = rs.getString("content");
				int listnum2 = rs.getInt("listnum");
				
				dto = new CardDTO(num2, title2, content2, listnum2);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public CardDTO insert(String title, String content, int listnum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		CardDTO dto = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "insert into Card (title, content, listnum) values(?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, listnum);
			
			// 쿼리문 실행
			// pstmt.executeUpdate()는
			// Insert, update, delete, create, drop할때 사용 
			int n = pstmt.executeUpdate();
			
			dto = getLastInsertCard(con);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void delete(int cardNum){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "delete from Card where num=?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setInt(1, cardNum);
			
			// 쿼리문 실행
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 카드 불러오기
	public CardDTO retrieve(int cardNum){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		CardDTO dto = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "select * from Card where num=?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setInt(1, cardNum);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당하는 카드리스트가 있는 경우
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int listnum = rs.getInt("listnum");
				
				dto = new CardDTO(num, title, content, listnum);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	// 카드 수정
	public void update(int num, String title, String content) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "update Card set title = ?, content = ?  where num=?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			// 쿼리문 실행
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 카드 불러오기
	public ArrayList<CardDTO> list (int cardListNum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		ArrayList<CardDTO> list = new ArrayList<CardDTO>();
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select * from Card where listnum=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, cardListNum);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 해당하는 카드리스트가 있는 경우
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int listnum = rs.getInt("listnum");
				
				CardDTO dto = new CardDTO(num, title, content, listnum);
				list.add(dto);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
