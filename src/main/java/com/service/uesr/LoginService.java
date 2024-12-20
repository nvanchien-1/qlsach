package com.service.uesr;
import com.dao.*;
import com.model.Role;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService  {
	private static final long serialVersionUID = 1L;
       
	
//	    public boolean authenticate(String username, String password) {
//	        // Giả sử username và password đúng là "admin" và "12345"
//	        return "admin".equals(username) && "12345".equals(password);
//	    }
	
	public boolean LoginUser(String username, String password) {
		
	    String query = "SELECT COUNT(*) FROM users WHERE Username = ? AND password = ?";
	    
	    try (Connection conn = connectData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	         
	        stmt.setString(1, username); 
	        stmt.setString(2, password); 
	        
	        try (ResultSet rs = stmt.executeQuery()) {
	            return rs.next() && rs.getInt(1) > 0; 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public String getUserRole(String username) {
	    String query = "SELECT r.RoleName " +
	                   "FROM users AS u " +
	                   "JOIN role AS r ON u.RoleId = r.RoleId " +
	                   "WHERE u.Username = ?";
	    String roleName = null;

	    try (Connection conn = connectData.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	       
	        stmt.setString(1, username);

	      
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	             
	                roleName = rs.getString("RoleName");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return roleName; // Trả về tên vai trò hoặc null nếu không tìm thấy
	}

		
		
		
	

}
