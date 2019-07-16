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

import com.dto.BoardDTO;

public class BoardDAO {

	private DataSource dataFactory;
	
	// 싱글톤 패턴(lazy holder) https://jeong-pro.tistory.com/86 참고
	private BoardDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static class LazyHolder {
		public static final BoardDAO INSTANCE = new BoardDAO();
	}
	
	public static BoardDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	// 싱글톤 여기까지!
	
	public void insert(String boardName, String[] idList, String adminId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String s_idList = ';'+String.join(":;", idList)+':';
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "insert into Board (board_name, id_list, made_id) value(?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setString(1, boardName);
			pstmt.setString(2, s_idList);
			pstmt.setString(3, adminId);
			
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
	
	public void delete(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "delete from Board where num=?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setInt(1, num);
			
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
	
	// 게시판 불러오기
	public BoardDTO retrieve(int num){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		BoardDTO dto = null;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "select * from Board where num=?";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setInt(1, num);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 해당하는 게시판이 있는 경우
				
				String boardName = rs.getString("board_name");
				String s_idList = rs.getString("id_list");
				String adminId = rs.getString("admin_id");
				
				String[] temp = s_idList.split(";");
				String[] idList = new String[temp.length-1];
				
				for(int i = 0; i < idList.length; i++) {
					idList[i] = temp[i+1].substring(0,temp[i+1].length()-1);
				}
						
				dto = new BoardDTO(num, boardName, idList, adminId);
				
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
	
	// 게시판에 유저 추가
	public void inviteUser(int num, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select * from Board where num=? and id_list like ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, "%;" + userId + ":%");
			
			rs  = pstmt.executeQuery();
			
			if(rs.next())
			{
				//이미 있음
			}else {
				
				// 쿼리문
				query = "update Board set id_list = concat(id_list, ?) where num=?";
				
				pstmt = con.prepareStatement(query);
				
				// 쿼리문에 값 입력
				pstmt.setString(1, ';'+userId+':');
				pstmt.setInt(2, num);
				
				// 쿼리문 실행
				int n = pstmt.executeUpdate();
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
	
	// 카드 불러오기
	public ArrayList<BoardDTO> list(String userId) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select * from Board where id_list like ?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, "%;" + userId + ":%");
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // 해당하는 카드리스트가 있는 경우
				
				int num = rs.getInt("num");
				String boardName = rs.getString("board_name");
				String s_idList = rs.getString("id_list");
				String adminId = rs.getString("made_id");
				
				String[] temp = s_idList.split(";");
				String[] idList = new String[temp.length-1];
				
				for(int i = 0; i < idList.length; i++) {
					idList[i] = temp[i+1].substring(0,temp[i+1].length()-1);
				}
						
				BoardDTO dto = new BoardDTO(num, boardName, idList, adminId);
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
