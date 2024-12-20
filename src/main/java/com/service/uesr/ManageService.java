package com.service.uesr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.connectData;
import com.model.Books;
import com.model.Category;
import com.model.GoogleAccount;
import com.model.Role;
import com.model.Users;

public class ManageService {

	public List<GoogleAccount> getUsersGG() {
		List<GoogleAccount> userList = new ArrayList<>();
		String query = "SELECT  b.email, b.name, c.RoleName " + "FROM googleusers AS b "
				+ "JOIN role AS c ON b.RoleId = c.RoleId ";

		try {
			Connection conn = connectData.getConnection();
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {

				Role namerole = new Role();
				namerole.setRoleName(rs.getString("RoleName"));

				GoogleAccount user = new GoogleAccount();
				user.setEmail(rs.getString("email"));
				user.setName(rs.getString("name"));
				user.setRole(namerole);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<Users> getUsers() {
		List<Users> userList = new ArrayList<>();
		String query = "SELECT  b.Email, b.Name, c.RoleName " + "FROM users AS b "
				+ "JOIN role AS c ON b.RoleId = c.RoleId ";

		try {
			Connection conn = connectData.getConnection();
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				Role namerole = new Role();
				namerole.setRoleName(rs.getString("RoleName"));
				Users user = new Users();
				user.setEmail(rs.getString("Email"));
				user.setFname(rs.getString("Name"));
				user.setRole(namerole);

				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

}
