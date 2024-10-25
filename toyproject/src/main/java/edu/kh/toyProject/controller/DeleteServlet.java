package edu.kh.toyProject.controller;

import java.io.IOException;

import edu.kh.toyProject.service.StdService;
import edu.kh.toyProject.service.StdServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student/delete")
public class DeleteServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int stdNo = Integer.parseInt(req.getParameter("stdNo"));
			
			StdService service = new StdServiceImpl();
			
			int result = service.deleteStd(stdNo);
			String message = "";
			String path = "";
			
			if(result > 0) {
				message = "해당 학생이 삭제되었습니다.";
				path = "/";
				
			} else {
				message = "해당 학생이 존재하지 않습니다.";
				path="/student/detail?stdNo=" + stdNo;
				
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
}
