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
import com.util.GoogleRegister;

/**
 * Servlet implementation class LoginGoogle
 */
@WebServlet("/registerGoogle")
public class RegisterGoogleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterGoogleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse Response)
			throws ServletException, IOException {
		String codes = request.getParameter("code");
		System.out.println(codes);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			System.out.println("Không tìm thấy code từ Google.");
			response.getWriter().write("Không tìm thấy code từ Google.");
			return;
		}

		try {
			GoogleRegister gg = new GoogleRegister();
			String accessToken = gg.getToken(code);
			System.out.println("Access Token: " + accessToken);

			GoogleAccount acc = gg.getUserInfo(accessToken);
			System.out.println("Thông tin người dùng:");
			System.out.println("ID: " + acc.getId());
			System.out.println("Email: " + acc.getEmail());
			System.out.println("Tên: " + acc.getName());
			System.out.println("Email xác thực: " + acc.isVerified_email());
			UserService creativeUsergg = new UserService();
			creativeUsergg.saveGoogleUser(acc);
			HttpSession session = request.getSession();
			session.setAttribute("username", acc.getName());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write("Đã xảy ra lỗi: " + e.getMessage());
		}
		response.sendRedirect("Home.jsp");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
