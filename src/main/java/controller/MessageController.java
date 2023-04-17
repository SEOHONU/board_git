package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;

@WebServlet("*.message")
public class MessageController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");

		String cmd = request.getRequestURI();
	
		try {
			if(cmd.equals("insert.message")){
			String id = request.getParameter("id");
			String writer = request.getParameter("writer");
			String message = request.getParameter("message");
			
			int result = MessageDAO.getInstance().insert(writer, message);
			
			request.getRequestDispatcher(/select.message).forward(request, response);
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
