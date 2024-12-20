package com.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.FacebookAccount;
import com.model.GoogleAccount;
import com.util.FacebookLogin;
import com.util.GoogleRegister;

/**
 * Servlet implementation class LoginFacebookController
 */
@WebServlet("/ResisterFacebook")
public class LoginFacebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginFacebookController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String code = request.getParameter("code");
		FacebookLogin fb = new FacebookLogin();
		String accessToken = fb.getToken(code);
		FacebookAccount acc = fb.getUserInfo(accessToken);
	}

}
