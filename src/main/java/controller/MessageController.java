package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;

@WebServlet("*.messages")
public class MessageController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String cmd = request.getRequestURI();
		
		try {
		if(cmd.equals("/delete.messages")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			MessageDAO dao = MessageDAO.getInstance();
			int result = dao.delete(id);
			response.sendRedirect("/index.jsp"); 
			
			
		}
	
		}catch (Exception e) {
			e.printStackTrace(); 
			response.sendRedirect("/error.jsp"); 
		}
	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
