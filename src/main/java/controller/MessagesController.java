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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
	      response.setContentType("text/html; charset=utf8");
	      String cmd = request.getRequestURI();
	      try {
	    	  if(cmd.equals("/select.message")) {
	    	  MessagesDAO dao = MessagesDAO.getInstance();
	    	  System.out.println("select서블릿 MessageDAO 인스턴스 실행");
	    	  List<MessagesDTO> result = dao.select();
	    	  request.setAttribute("select", result);
	    	request.getRequestDispatcher("/");
	    			  }
	      }catch (Exception e) {
	    	  
	      }
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
