package com.controller.User;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.uesr.BookService;

import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;

/**
 * Servlet implementation class deleteBookController
 */
@WebServlet("/deleteBook")
public class deleteBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String bookid = request.getParameter("xid");
			System.out.println(bookid);
			BookService delete = new BookService();

			delete.DeleteBook(bookid);
			response.sendRedirect("HomeBook");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
