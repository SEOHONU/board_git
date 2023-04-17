package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;
import dto.MessagesDTO;


@WebServlet("*.messages")
public class MessagesController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=utf8");
		String cmd = request.getRequestURI();
		MessagesDAO dao = MessagesDAO.getInstance();
		try {
			if (cmd.equals("/select.messages")) {
				List<MessagesDTO> result = dao.select();
				request.setAttribute("list", result);
				request.getRequestDispatcher("/messages/select.jsp").forward(request, response);
			}
			else if(cmd.equals("/update.messages")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				int result = MessagesDAO.getInstance().insert(writer, message);
				response.sendRedirect("/select.messages");
			}
			else if(cmd.equals("/insert.messages")){
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");
				int result = MessagesDAO.getInstance().insert(writer, message);
				response.sendRedirect("/select.messages");
			}
			else if(cmd.equals("/delete.messages")) {
				int id = Integer.parseInt(request.getParameter("id"));
				int result = dao.delete(id);
				response.sendRedirect("/index.jsp"); 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
