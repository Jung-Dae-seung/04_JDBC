package edu.kh.toyProject.service;

import java.sql.Connection;
import java.util.List;

import static edu.kh.toyProject.common.JDBCTemplate.*;

import edu.kh.toyProject.dao.StdDAO;
import edu.kh.toyProject.dao.StdDAOImpl;
import edu.kh.toyProject.dto.Student;

public class StdServiceImpl implements StdService{
	
	private StdDAO dao = new StdDAOImpl();

	@Override
	public List<Student> stdList() throws Exception {
		
		Connection conn = getConnection();
		
		List<Student> stdList = dao.stdList(conn);
		
		close(conn);
		
		return stdList;
	}

	@Override
	public Student detailStd(int stdNo) throws Exception {
		
		Connection conn = getConnection();
		
		Student std = dao.detailStd(conn, stdNo);
		
		close(conn);
		
		return std;
	}

}
