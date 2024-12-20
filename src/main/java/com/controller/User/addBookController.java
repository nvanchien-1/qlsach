package com.controller.User;


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

import java.io.File;

/**
 * Servlet implementation class addBookController
 */
@MultipartConfig
@WebServlet("/addbook")
public class addBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false) == null || request.getSession().getAttribute("username") == null) {
			System.out.println("can dnag nhap");
		    String errorString = "Bạn cần đăng nhập trước";
		    request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
		    return; // Đảm bảo không tiếp tục xử lý khi đã redirect
		}

		try {
		    CategoryService categoryService = new CategoryService();
		    List<Category> listCategory = categoryService.getAllCategory();

		    // Chỉ cần lưu thông tin cần thiết vào session nếu thực sự cần
		    request.getSession().setAttribute("Check", "addbook");
		    request.setAttribute("listcategory", listCategory);
		    request.getRequestDispatcher("/view/addBook.jsp").forward(request, response);
		} catch (Exception e) {
		    e.printStackTrace(); // Hoặc dùng logger để ghi log
		   
		}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		System.out.println(bookid);
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

		BookService add = new BookService();
		CategoryService getId = new CategoryService();
		Category category = getId.getCategoryById(categoryid);

		Books book = new Books();
		book.setBookid(bookid);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublishedYear(publishedyear);
		book.setQuantity(quantity);
		book.setCategory(category);
		book.setImage(fileName);
		add.addBook(book);

		response.getWriter().append("Thêm sách thành công");
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
//Category categorys = new Category();
//categorys.setNameCategory(category);
// biết lỗi r 
// viết 1 hàm tìm category theo id nhận đươc sau đó book.setCategory(bỏ category mới tìm bằng id vào đây) hỉu ko
