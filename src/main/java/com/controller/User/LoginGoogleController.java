package com.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.GoogleAccount;
import com.service.uesr.UserService;
import com.util.GoogleLogin;
import com.util.GoogleRegister;

/**
 * Servlet implementation class LoginGoogleController
 */
@WebServlet("/loginGoogle")
public class LoginGoogleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginGoogleController() {
    }
	protected void processRequest(HttpServletRequest request, HttpServletResponse Response)
			throws ServletException, IOException {
		String codes = request.getParameter("code");
		System.out.println(codes);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			System.out.println("Không tìm thấy code từ Google.");
			response.getWriter().write("Không tìm thấy code từ Google.");
			return;
		}

		try {
			GoogleLogin gg = new GoogleLogin();
			String accessToken = gg.getToken(code);
			System.out.println("Access Token: " + accessToken);

			GoogleAccount acc = gg.getUserInfo(accessToken);
			System.out.println("Thông tin người dùng:");
			System.out.println("ID: " + acc.getId());
			System.out.println("Email: " + acc.getEmail());
			System.out.println("Tên: " + acc.getName());
			System.out.println("Email xác thực: " + acc.isVerified_email());

			UserService check = new UserService();
			String role = check.getUserRolegg(acc.getId());
			if(check.LoginGoogle(acc.getId())) {
				HttpSession session = request.getSession();
				session.setAttribute("username", acc.getName());
				System.out.println(role);

				if ("Admin".equals(role)) {
					response.sendRedirect("HomeBook"); // Admin chuyển hướng tới HomeBook
				} else {
					response.sendRedirect("Home.jsp"); // Người dùng thường chuyển hướng tới home
				}

				System.out.println("Đăng nhập thành công");
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("notlogin", "tài khoản hoặc mật khẩu không chính xác");

				request.getRequestDispatcher("/view/login.jsp").forward(request, response);
				System.out.println("Đăng nhập thất bại");
			
			
			session.setAttribute("username", acc.getName());}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Đã xảy ra lỗi: " + e.getMessage());
		}
	
	}
	
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
