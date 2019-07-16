package com.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	
	private DataSource dataFactory;
	
	// 싱글톤 패턴(lazy holder) https://jeong-pro.tistory.com/86 참고
	private UserDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
			
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static class LazyHolder {
		public static final UserDAO INSTANCE = new UserDAO();
	}
	
	public static UserDAO getInstance() {
		return LazyHolder.INSTANCE;
	}
	// 싱글톤 여기까지!
	
	// 비밀번호 MD5 해시 처리 (출처: https://offbyone.tistory.com/286 [쉬고 싶은 개발자])
	public static String encodePassword(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			return byteToHexString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String byteToHexString(byte[] data) {
	    StringBuilder sb = new StringBuilder();
	    for(byte b : data) {
	        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
	// MD5 여기까지!
	
	// 유저 정보 추가
	public void insert(String id, String password, String userName) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String encodedPwd = encodePassword(password);
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			// 쿼리문
			String query = "insert into User (id, password, username) values(?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			// 쿼리문에 값 입력
			pstmt.setString(1, id);
			pstmt.setString(2, encodedPwd);
			pstmt.setString(3, userName);
			
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
	
	// 로그인 체크
	public int loginCheck(String id, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		String encodedPwd = encodePassword(password);
		
		int returnVal = -1;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select password from User where id=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 입력된 아이디에 해당하는 비번이 있는 경우
				
				String dbPwd = rs.getString("password");// DB에 기록된 password
				
				if(dbPwd.equals(encodedPwd)) {
					returnVal = 1;	// 입력된 비번이 맟는 경우!
				}else {
					returnVal = 0;	// 비번이 틀린경우
				}
				
			}else { // 입력된 아이디에 해당하는 비번이 없는 경우
				returnVal = -1;
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
		
		return returnVal;
	}
	
	// 아이디 중복 체크
	public int idCheck(String id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		
		int returnVal = -1;
		
		try {
			// 커넥션을 가져온다.
			con = dataFactory.getConnection();
			
			String query = "select id from User where id=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			
			// pstmt.executeQuery()는
			// select 할때 사용
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // 입력된 아이디에 해당하는 비번이 있는 경우
				returnVal = 1;
			}else { // 입력된 아이디에 해당하는 비번이 없는 경우
				returnVal = -1;
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
		
		return returnVal;
	}
}
