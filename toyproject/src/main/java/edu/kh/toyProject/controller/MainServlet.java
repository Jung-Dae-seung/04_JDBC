package edu.kh.toyProject.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.toyProject.dto.Student;
import edu.kh.toyProject.service.StdService;
import edu.kh.toyProject.service.StdServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			StdService service = new StdServiceImpl();
			List<Student> stdList = service.stdList();
			
			req.setAttribute("studentList", stdList);
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
