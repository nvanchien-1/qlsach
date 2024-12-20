package com.controller.User;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Books;
import com.model.BorrowOff;
import com.model.Category;

import com.service.uesr.BookService;
import com.service.uesr.BorrowOffService;
import com.service.uesr.CategoryService;

/**
 * Servlet implementation class BorrowOffController
 */
@WebServlet("/addBorrowOff")
public class BorrowOffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
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

			// Gửi thông tin đến JSP
			request.setAttribute("listBookss", listBook);
			request.setAttribute("currentPage", pageNumber);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("totalBooks", totalBooks); // Thêm tổng số sách

			request.getRequestDispatcher("/view/BorrowOff.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int borrowOffid = Integer.parseInt(request.getParameter("borrowOffid"));
		String fname = request.getParameter("fname");
		String email = request.getParameter("email");
		int phone = Integer.parseInt(request.getParameter("phone"));
		String bookid = request.getParameter("bookid");
		LocalDate birth = LocalDate.parse(request.getParameter("birth"));
		LocalDate borrowdate = LocalDate.parse(request.getParameter("borrowdate"));
		LocalDate duedate = LocalDate.parse(request.getParameter("duedate"));

		BookService getid = new BookService();
		Books book = getid.getBookById(bookid);

		BorrowOff borrowOff = new BorrowOff();
		borrowOff.setBorrowOffid(borrowOffid);
		borrowOff.setFname(fname);
		borrowOff.setEmail(email);
		borrowOff.setPhone(phone);
		borrowOff.setBook(book);
		borrowOff.setBirth(birth);
		borrowOff.setBorrowdate(borrowdate);
		borrowOff.setDuedate(duedate);

		// Gọi service để thêm BorrowOff
		BorrowOffService borrowOffService = new BorrowOffService();
		borrowOffService.addBorrowOff(borrowOff);
		System.out.println("BookID: " + bookid);
		response.getWriter().append("Thêm thành công");
		
		
	}

}
