package com.controller.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.uesr.BookService;
import com.service.uesr.CategoryService;
import com.model.*;

@WebServlet("/HomeBook")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			System.out.println("ban can nhap");
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			BookService list = new BookService();

			String pageParam = request.getParameter("page");
			int pageNumber = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

			// Số lượng sách mỗi trang
			int pageSize = 7;
			int offset = (pageNumber - 1) * pageSize;

			// Lấy danh sách sách với phân trang
			List<Books> listBook = list.getBooks(pageSize, offset);

			int totalBooks = list.getTotalBookCount();
			int totalPages = (int) Math.ceil((double) totalBooks / pageSize);
			request.getSession().setAttribute("Check", "HomeBook");
			// Gửi thông tin đến JSP
			request.setAttribute("listBooks", listBook);
			request.setAttribute("currentPage", pageNumber);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("totalBooks", totalBooks); 
System.out.println("đang nhap thanh cong");
			
			request.getRequestDispatcher("/view/HomeAdmin.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
