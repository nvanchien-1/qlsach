package com.controller.User;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("//verifyOTP")
public class VerifyOTP extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userOtp = request.getParameter("otp"); // OTP người dùng nhập
        String email = (String) request.getSession().getAttribute("email"); // Lấy email từ session

        if (email == null || userOtp == null) {
            response.getWriter().write("Yêu cầu nhập đầy đủ thông tin.");
            return;
        }

        String sessionOtp = (String) request.getSession().getAttribute("otp"); // OTP đã gửi qua email và lưu trong session

        if (sessionOtp != null && sessionOtp.equals(userOtp)) {
            // OTP hợp lệ, chuyển sang trang thành công
            HttpSession session = request.getSession();
            session.setAttribute("otpVerified", true); // Đánh dấu OTP đã được xác thực

            response.getWriter().write("OTP xác thực thành công! Bạn có thể đăng nhập.");
            // Bạn có thể chuyển hướng tới trang login hoặc thực hiện các thao tác khác tại đây
            response.sendRedirect("/view/login.jsp");
        } else {
            // OTP không hợp lệ
            response.getWriter().write("Mã OTP không chính xác hoặc đã hết hạn.");
            request.getRequestDispatcher("/view/otp.jsp").forward(request, response);
        }
    }


    private void saveToDatabase(String username, String email, String password) {
        // Giả lập kết nối database và lưu dữ liệu
        // Sử dụng JDBC thực tế để kết nối cơ sở dữ liệu
        System.out.println("Saved to DB: " + username + ", " + email);
    }
}
