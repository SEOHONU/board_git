package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessagesDAO;


@WebServlet("*.messages")
public class MessagesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd=request.getRequestURI();
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html; charset=utf8");


		if(cmd.equals("/update.messages")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");

				MessagesDAO dao = MessagesDAO.getInstance();
				int result = dao.updateMessage(id, writer, message);
				request.getRequestDispatcher("/select.messages?id="+id).forward(request,response);
			}catch (Exception e ) {
				e.printStackTrace();
				response.sendRedirect("error.html");
			}
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
