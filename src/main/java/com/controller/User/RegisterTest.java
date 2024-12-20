package com.controller.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Users;
import com.service.uesr.UserService;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Servlet implementation class RegisterTest
 */
@WebServlet("/RegisterTest")
public class RegisterTest extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String EMAIL = "your_email@gmail.com"; // Email bạn
    private static final String PASSWORD = "your_email_password"; // Mật khẩu email bạn

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String fname = request.getParameter("fname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");

        // Tạo mã OTP ngẫu nhiên
        String otp = generateOTP();

        // Tạo đối tượng người dùng
        Users user = new Users();
        user.setFname(fname);
        user.setEmail(email);
        user.setPassword(password); // Nên mã hóa mật khẩu
        user.setUsername(username);
        user.setPhone(phone);

        UserService userService = new UserService();

        try {
            // Lưu thông tin người dùng vào hệ thống
            if (userService.creativeUser(user)) {
                // Gửi OTP qua email
                sendEmail(email, otp);

                // Lưu OTP vào session
                request.getSession().setAttribute("otp", otp);
                request.getSession().setAttribute("email", email);

                // Chuyển hướng đến trang xác thực OTP
                response.sendRedirect("/view/verifyOTP.jsp");
            } else {
                response.getWriter().write("Tạo tài khoản thất bại, vui lòng thử lại.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Có lỗi xảy ra: " + e.getMessage());
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Tạo số ngẫu nhiên 6 chữ số
        return String.valueOf(otp);
    }

    private void sendEmail(String recipient, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Mã OTP của bạn");
        message.setText("Mã OTP để xác nhận đăng ký của bạn là: " + otp);

        Transport.send(message);
    }
}
