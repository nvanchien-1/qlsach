package com.controller.User;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.model.Books;
import com.model.Category;
import com.service.uesr.BookService;
import com.service.uesr.CategoryService;

/**
 * Servlet implementation class editBookController
 */
@MultipartConfig
@WebServlet("/editBook")
public class editBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
		} else {
			String id = request.getParameter("xid");
			BookService getidbook = new BookService();
			Books book = getidbook.getBookById(id);
			request.setAttribute("kk", book);

			CategoryService list = new CategoryService();
			List<Category> listcategory = list.getAllCategory();

			request.setAttribute("listcategory", listcategory);
			request.getRequestDispatcher("/view/editBook.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		int bookid = Integer.parseInt(request.getParameter("bookid"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		Integer categoryid = Integer.parseInt(request.getParameter("category"));
		int publishedyear = Integer.parseInt(request.getParameter("publishedyear"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		String savePath = "D:\\JAVAONHA\\demo\\src\\main\\webapp\\image"; // Đường dẫn cụ thể
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
		}

		// Xử lý file upload
		Part file = request.getPart("fileImage");
		String fileName = extractfilename(file);
		file.write(savePath + File.separator + fileName);

		BookService edit = new BookService();
		CategoryService getId = new CategoryService();
		Category category = getId.getCategoryById(categoryid);

		edit.editBook(bookid, title, author, publishedyear, quantity, category, fileName);
		response.sendRedirect("HomeBook");

	}

	private String extractfilename(Part file) {
		String cd = file.getHeader("content-disposition");
		String[] items = cd.split(";");
		for (String string : items) {
			if (string.trim().startsWith("filename")) {
				return string.substring(string.indexOf("=") + 2, string.length() - 1);
			}
		}
		return "";
	}

}
