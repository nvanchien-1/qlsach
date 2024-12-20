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
import com.model.GoogleAccount;
import com.model.Users;
import com.service.uesr.BorrowOffService;
import com.service.uesr.ManageService;

/**
 * Servlet implementation class ListManagerController
 */
@WebServlet("/ListManager")
public class ListManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListManagerController() {
        super();}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("username") == null) {
			String errorString = "Bạn cần đăng nhập trước";
			request.setAttribute("errorString", errorString);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/view/login.jsp");
			dispatcher.forward(request, response);
		} else {
			ManageService list = new ManageService();
		
	List<Users> listUser= list.getUsers();
	
	List<GoogleAccount> listUsergg= list.getUsersGG();
	
	request.setAttribute("listUser", listUser);
	request.setAttribute("listUsergg", listUsergg);
	request.getRequestDispatcher("/view/manageRole.jsp").forward(request, response);
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
