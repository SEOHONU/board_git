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
		try {
			if (cmd.equals("/select.messages")) {
				MessagesDAO dao = MessagesDAO.getInstance();
				System.out.println("select서블릿 MessageDAO 인스턴스 실행");
				List<MessagesDTO> result = dao.select();
				request.setAttribute("list", result);
				request.getRequestDispatcher("/inputForm.jsp").forward(request, response);
			} else if (cmd.equals("insert.messages")) {
				int id = Integer.parseInt(request.getParameter("id"));
				String writer = request.getParameter("writer");
				String message = request.getParameter("message");

				int result = MessagesDAO.getInstance().insert(writer, message);

				request.getRequestDispatcher("/select.messages.jsp").forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
