package edu.kh.toyProject.dao;

import static edu.kh.toyProject.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.toyProject.dto.Student;

public class StdDAOImpl implements StdDAO{
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public StdDAOImpl() {
		try {
			
			String filePath = StdDAOImpl.class.getResource("/xml/sql.xml").getPath();
			
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			System.out.println("sql.xml 로드 중 예외발생");
			e.printStackTrace();
			
		}
		
	}
	

	@Override
	public List<Student> stdList(Connection conn) throws Exception {
		
		List<Student> stdList = new ArrayList<Student>();
		
		try {
			String sql = prop.getProperty("stdList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Student std = Student.builder()
						.stdNo(rs.getInt("STD_NO"))
						.stdName(rs.getString("STD_NAME"))
						.stdAge(rs.getInt("STD_AGE"))
						.stdGender(rs.getString("STD_GENDER"))
						.stdScore(rs.getString("STD_SCORE"))
						.build();
				
				stdList.add(std);
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return stdList;
	}


	@Override
	public Student detailStd(Connection conn, int stdNo) throws Exception {
		
		Student std = null;
		
		try {
			String sql = prop.getProperty("detailStd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, stdNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				std = Student.builder()
						.stdNo(rs.getInt("STD_NO"))
						.stdName(rs.getString("STD_NAME"))
						.stdAge(rs.getInt("STD_AGE"))
						.stdGender(rs.getString("STD_GENDER"))
						.stdScore(rs.getString("STD_SCORE"))
						.build();
				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return std;
	}


	@Override
	public int deleteStd(Connection conn, int stdNo) throws Exception {
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteStd");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, stdNo);
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	@Override
	public int insertStd(Connection conn, Student std) throws Exception {
		
		int result = 0;
		
		try {
			String sql = """
					INSERT INTO TB_STUDENT
					VALUES(?, ?, ?, ?, ?)
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, std.getStdNo());
			pstmt.setString(2, std.getStdName());
			pstmt.setInt(3, std.getStdAge());
			pstmt.setString(4, std.getStdGender());
			pstmt.setString(5, std.getStdScore());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	@Override
	public int updateStd(Connection conn, Student std) throws Exception {
		
		int result = 0;
		
		try {
			String sql = """
					UPDATE TB_STUDENT
					SET
						STD_NAME = ?,
						STD_AGE = ?,
						STD_GENDER = ?,
						STD_SCORE =?
					WHERE STD_NO = ?
					""";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, std.getStdName());
			pstmt.setInt(2, std.getStdAge());
			pstmt.setString(3, std.getStdGender());
			pstmt.setString(4, std.getStdScore());
			pstmt.setInt(5, std.getStdNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
