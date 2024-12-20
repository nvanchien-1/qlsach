package com.controller.User;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BorrowOff;
import com.service.uesr.BorrowOffService;

@WebServlet("/listBorrowOff")
public class listBorrowOffController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
		} else {BorrowOffService list = new BorrowOffService();
	List<BorrowOff> listBorrowOff= list.getListBorrowOff();
	request.setAttribute("listBorrowOff", listBorrowOff);
	request.getRequestDispatcher("/view/listBorrowoff.jsp").forward(request, response);
	
	
	
	}}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
