package com.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.uesr.UserService;
import com.util.EncodePassword;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		EncodePassword encodePassword = new EncodePassword();
		String newEncodePassword = encodePassword.toSHA1(password);
		UserService loginService = new UserService();

		String role = loginService.getUserRole(username);

		if (loginService.LoginService(username, newEncodePassword)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", role);
			System.out.println(role);

			if ("Admin".equals(role) || "Staff".equals(role) || "Manager".equals(role)) {
				response.sendRedirect("HomeBook");
			} else if ("User".equals(role)) {
				
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");

			} else {
				response.sendRedirect("Home.jsp");
			}

			System.out.println("Đăng nhập thành công");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("notlogin", "tài khoản hoặc mật khẩu không chính xác");

			request.getRequestDispatcher("/view/login.jsp").forward(request, response);
			System.out.println("Đăng nhập thất bại");
		}
	}
}
