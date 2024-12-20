package com.controller.User;
import com.model.Users;
import com.service.uesr.UserService;
import com.util.OPTGenerator;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionIdListener;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
 
        // TODO Auto-generated constructor stub
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=UTF-8");
		String fname = request.getParameter("fname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		
		  
	        
	Users user = new Users();
		user.setEmail(email);
		user.setFname(fname);
		user.setPassword(password);
		user.setUsername(username);
		user.setPhone(phone);
		
		
		UserService creative = new UserService();
		boolean isCreated = creative.creativeUser(user);

		
		
        // Kiểm tra nếu tạo thành công
        if (isCreated) {
      	HttpSession session = request.getSession();
        	session.setAttribute(username, "tài khoản không đúng");
       	
            // Nếu thành công, chuyển đến trang login
        	System.out.println("tạo tài khoản thành công");
       	
        	request.getRequestDispatcher("/view/login.jsp").forward(request, response);
        } else {
        	HttpSession session = request.getSession();
        	session.setAttribute("register", "đăng kí thất bại");
      	System.out.println("đăng kí thất bại");
            // Nếu thất bại, quay lại trang đăng ký
        	  request.getRequestDispatcher("/view/register.jsp").forward(request, response);
        }
    }

}
