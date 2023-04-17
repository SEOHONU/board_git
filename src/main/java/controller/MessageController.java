package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDAO;
import dto.MessagesDTO;

@WebServlet("*.message")
public class MessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String cmd = request.getRequestURI();
		try {
			MessageDAO dao = MessageDAO.getInstance();
			if(cmd.equals("/insert.message")) {
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				dao.insertMessage(new MessagesDTO(0,writer,message));
				response.sendRedirect("/index.jsp");
			}else if(cmd.equals("/select.message")) {

			}else if(cmd.equals("/delete.message")) {
				response.sendRedirect("/select.message");
			}else if(cmd.equals("/update.message")) {
				response.sendRedirect("/select.message");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
