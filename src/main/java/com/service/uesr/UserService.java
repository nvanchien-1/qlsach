package com.service.uesr;

import com.dao.*;
import com.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64.Encoder;

import com.dao.connectData;
import com.model.GoogleAccount;
import com.model.Users;

public class UserService {
//    public void CreativeUser(User1 user) {     
//        // Tiến hành kết nối và lưu vào cơ sở dữ liệu
//        try (Connection conn = connectData.getConnection()) {
//        	String CheckEmail = "SELECT COUNT(*) FROM user WHERE email = ?";
//            String query = "INSERT INTO user (email, password, fname) VALUES (?, ?, ?)";
//            try(PreparedStatement checkemail = conn.prepareStatement(CheckEmail)){
//            checkemail.setString(1,user.getEmail());
//            try(ResultSet rs = checkemail.executeQuery() ){
//            	if(rs.next()&& rs.getInt(1)>0) {
//            		System.out.println("email đã tồn tại ");
//            		return;
//            	}
//            	
//            }
//            
//            }
//            try (PreparedStatement ct = conn.prepareStatement(query)) {
//                ct.setString(1, user.getEmail());
//                ct.setString(2, user.getPassword());
//                ct.setString(3, user.getFname());
//
//                int affectedRows = ct.executeUpdate();
//                if (affectedRows > 0) {
//                    System.out.println("Thêm thành công");
//                } else {
//                    System.out.println(" thêm thất bại");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Thêm thất bại");
//        }
//    }

	public boolean creativeUser(Users user) {
		// Tiến hành kết nối và lưu vào cơ sở dữ liệu
		try (Connection conn = connectData.getConnection()) {
			String checkEmail = "SELECT COUNT(*) FROM users WHERE Email = ?";
			String checkUsername = "SELECT COUNT(*) FROM users WHERE Username = ?";
			String insertUsers = "INSERT INTO users (Email, Password, Name,Username,Phone) VALUES (?, ?, ?,?,?)";

			// check email
			try (PreparedStatement checkEmailStmt = conn.prepareStatement(checkEmail)) {
				checkEmailStmt.setString(1, user.getEmail());
				try (ResultSet rs = checkEmailStmt.executeQuery()) {
					if (rs.next() && rs.getInt(1) > 0) {
						System.out.println("Email đã tồn tại.");
						return false; // Trả về false nếu email đã tồn tại
					}
				}
			}
// check usename
			try (PreparedStatement checkUsernameStmt = conn.prepareStatement(checkUsername)) {
				checkUsernameStmt.setString(1, user.getUsername());
				try (ResultSet rs = checkUsernameStmt.executeQuery()) {
					if (rs.next() && rs.getInt(1) > 0) {
						System.out.println("tai khoan da ton tai");
						return false;
					}
				}
			}

			// Thực hiện thêm người dùng mới vào cơ sở dữ liệu
			try (PreparedStatement insertUser = conn.prepareStatement(insertUsers)) {
				insertUser.setString(1, user.getEmail());

//            	BCryptPasswordEncoder bCryptPasswordEncoder  = new BCryptPasswordEncoder();
//            	 String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());   

				EncodePassword encodePassword = new EncodePassword();
				String passwword = encodePassword.toSHA1(user.getPassword());

				insertUser.setString(2, passwword);
				insertUser.setString(3, user.getFname());
				insertUser.setString(4, user.getUsername());
				insertUser.setString(5, user.getPhone());
				int affectedRows = insertUser.executeUpdate();
				if (affectedRows > 0) {
					System.out.println("Thêm thành công.");
					return true; // Trả về true nếu thêm thành công
				} else {
					System.out.println("Thêm thất bại.");
					return false; // Trả về false nếu thêm thất bại
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Lỗi khi kết nối cơ sở dữ liệu hoặc thao tác với cơ sở dữ liệu.");
			return false; // Trả về false khi có lỗi xảy ra
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Đã xảy ra lỗi không xác định.");
			return false; // Trả về false khi có lỗi không xác định
		}
	}
// Kiểm tra phần login

	public boolean LoginService(String Username, String Password) {
		String query = "SELECT COUNT(*) FROM users WHERE Username = ? and Password = ?";

		try {
			Connection conn = connectData.getConnection();
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, Username);
			stm.setString(2, Password);
			try (ResultSet rs = stm.executeQuery()) {
				return rs.next() && rs.getInt(1) > 0;

			}

		} catch (Exception e) {

		}

		return false;
	}

	public String getUserRole(String username) {
		String query = "SELECT r.RoleName " + "FROM users AS u " + "JOIN role AS r ON u.RoleId = r.RoleId "
				+ "WHERE u.Username = ?";
		String roleName = null;

		try (Connection conn = connectData.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

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
	public String getUserRolegg(String id) {
		String query = "SELECT r.RoleName " + "FROM googleusers AS u " + "JOIN role AS r ON u.RoleId = r.RoleId "
				+ "WHERE u.id = ?";
		String roleName = null;

		try (Connection conn = connectData.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, id);

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

	public void saveGoogleUser(GoogleAccount account) throws SQLException {
		String sql = "INSERT INTO GoogleUsers (id, email, name, verified_email) VALUES (?, ?, ?, ?)";

		try (Connection conn = connectData.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setString(1, account.getId());
			statement.setString(2, account.getEmail());
			statement.setString(3, account.getName());
			statement.setBoolean(4, account.isVerified_email());

			statement.executeUpdate();
		}
	}

	public boolean LoginGoogle(String id) {
		String query = "SELECT COUNT(*) FROM googleusers WHERE id = ? ";

		try {
			Connection conn = connectData.getConnection();
			PreparedStatement stm = conn.prepareStatement(query);
			stm.setString(1, id);

			try (ResultSet rs = stm.executeQuery()) {
				return rs.next() && rs.getInt(1) > 0;

			}

		} catch (Exception e) {

		}

		return false;
	}

}
