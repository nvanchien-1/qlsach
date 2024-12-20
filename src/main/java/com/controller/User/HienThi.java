package com.controller.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HienThi")
public class HienThi extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HienThi() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Set the response content type
        response.setContentType("text/html;charset=UTF-8");
        
        // Get form data
        String firstName = request.getParameter("ten_dau");
        String lastName = request.getParameter("ten_cuoi");
        String address = request.getParameter("dia_chi");
        
        // Output response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Thông tin đăng ký</h1>");
        out.println("<p>Tên Đầu: " + firstName + "</p>");
        out.println("<p>Tên Cuối: " + lastName + "</p>");
        out.println("<p>Địa Chỉ: " + address + "</p>");
        out.println("</body></html>");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
