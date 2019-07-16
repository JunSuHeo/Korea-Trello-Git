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
import com.dto.CardListDTO;

public class CardListDAO {

	private DataSource dataFactory;
	
	// 싱글톤 패턴(lazy holder) https://jeong-pro.tistory.com/86 참고
	private CardListDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static class LazyHolder {
		public static final CardListDAO INSTANCE = new CardListDAO();
	}
	
	public static CardListDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	// 싱글톤 여기까지!
	
	// 카드 리스트 추가
	public void insert(String title, int boardnum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "insert into CardList (title, boardnum) values(?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setString(1, title);
			pstmt.setInt(2, boardnum);
			
			// 쿼리문 실행
			// pstmt.executeUpdate()는
			// Insert, update, delete, create, drop할때 사용 
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
	
	// 카드리스트 삭제
	public void delete(int cardListNum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "delete from CardList where num = ?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setInt(1, cardListNum);
			
			// 쿼리문 실행
			// pstmt.executeUpdate()는
			// Insert, update, delete, create, drop할때 사용 
			int n = pstmt.executeUpdate();
			
			// 카드리스트 안에 있는 모든 카드 삭제
			ArrayList<CardDTO> list = CardDAO.getInstance().list(cardListNum);
		
			for(CardDTO dto : list) {
				CardDAO.getInstance().delete(dto.getNum());
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
	}
	
	// 카드리스트에 카드 추가
	public void insertCard(int cardListNum, int cardNum, String cardTitle){
		CardDAO.getInstance().insert(cardTitle, "", cardListNum);
	}
	
	// 카드리스트에 카드 삭제
	// Waring : 차라리 CardDAO.getInstance().delete(cardNum)를 쓰는것이 좋습니다.
	public void deleteCard(int cardListNum, int cardNum){
		CardDAO.getInstance().delete(cardNum);
	}
	
	// 게시판에 있는 카드 리스트 모두 구하기
	public ArrayList<CardListDTO> list(int boardnum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		ArrayList<CardListDTO> list = new ArrayList<CardListDTO>();
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select * from CardList where boardnum=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, boardnum);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 해당하는 카드리스트가 있는 경우
				int num = rs.getInt("num");
				String title = rs.getString("title");
				
				ArrayList<CardDTO> cards = CardDAO.getInstance().list(num);
				
				CardListDTO dto = new CardListDTO(num, title, boardnum, cards);
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
	
	// 카드 리스트 검색
	public CardListDTO retrieve(int cardListNum) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		CardListDTO dto = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select * from CardList where num=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, cardListNum);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당하는 카드리스트가 있는 경우
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				int boardnum = rs.getInt("boardnum");
				
				ArrayList<CardDTO> cards = CardDAO.getInstance().list(num);
				
				dto = new CardListDTO(num, title, boardnum, cards);
				
			}else { // 해당하는 카드리스트가 없음.
				
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
}
