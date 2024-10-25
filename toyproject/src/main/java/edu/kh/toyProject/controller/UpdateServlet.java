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

@WebServlet("/student/update")
public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdService service = new StdServiceImpl();
			Student std = service.detailStd(stdNo);
			
			String message = null;
			
			if(std == null) {
				message = "해당 번호의 학생이 없습니다.";
				req.setAttribute("message", message);
				resp.sendRedirect("/");
				return;
			} 
			
			String path = "/WEB-INF/views/update.jsp";
			
			req.setAttribute("student", std);
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
	        int result = service.updateStd(std);
	        
	        String message = "";
	        String path = "";
	        
	        if(result > 0) {
	        	message = "수정 성공";
	        	path = "/";
	        	
	        } else {
	        	message = "수정 실패";
	        	path = "/student/update?stdNo=" + stdNo; 
	        	
	        }
	        
	        req.getSession().setAttribute("message", message);
	        resp.sendRedirect(path);
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
