package edu.kh.toyProject.controller;

import java.io.IOException;

import edu.kh.toyProject.dto.Student;
import edu.kh.toyProject.service.StdService;
import edu.kh.toyProject.service.StdServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/insert")
public class InsertServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String path = "/WEB-INF/views/insert.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
	        String stdName = req.getParameter("stdName");
	        int stdAge = Integer.parseInt(req.getParameter("stdAge"));
	        String stdGender = req.getParameter("stdGender");
	        String stdScore = req.getParameter("stdScore");
	        
	        Student std = new Student(stdNo, stdName, stdAge, stdGender, stdScore);
	        
	        StdService service = new StdServiceImpl();
	        int result = service.insertStd(std);
	        
	        String message = "";
	        String path = "";
	        
	        if(result > 0) {
	        	message = "학생이 추가되었습니다.";
	        	path = "/";
	        	
	        } else {
	        	message = "학생 추가가 실패되었습니다.";
	        	path = "/student/insert";
	        	
	        }
	        
	        req.getSession().setAttribute("message", message);
	        resp.sendRedirect(path);
	        		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
