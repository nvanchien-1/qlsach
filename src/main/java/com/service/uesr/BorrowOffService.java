package com.service.uesr;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dao.connectData;
import com.model.Books;
import com.model.BorrowOff;
import com.model.Category;

public class BorrowOffService {
	public List<BorrowOff> getListBorrowOff() {
		List<BorrowOff> list = new ArrayList<>();

		// Câu truy vấn chính xác
		String query = "SELECT u.BorrowOffID, u.Fname, u.Phone, u.Email, u.Birth, u.BorrowDate, u.DueDate, "
				+ "b.BookID, b.Title, c.Name " + "FROM BorrowOff u " + "JOIN Books b ON u.BookID = b.BookID "
				+ "JOIN Categories c ON b.CategoryID = c.CategoryID";

		try (Connection con = connectData.getConnection();
				PreparedStatement stm = con.prepareStatement(query);
				ResultSet rs = stm.executeQuery()) {

			while (rs.next()) {

				Category category = new Category();
				category.setNameCategory(rs.getString("Name"));

				Books book = new Books();
				book.setBookid(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setCategory(category);

				BorrowOff borrowOff = new BorrowOff();
				borrowOff.setBorrowOffid(rs.getInt("BorrowOffID"));
				borrowOff.setFname(rs.getString("Fname"));
				borrowOff.setPhone(rs.getInt("Phone"));
				borrowOff.setEmail(rs.getString("Email"));

				// Xử lý các trường ngày tháng, kiểm tra NULL
				java.sql.Date birthSqlDate = rs.getDate("Birth");
				if (birthSqlDate != null) {
					borrowOff.setBirth(birthSqlDate.toLocalDate());
				}

				java.sql.Date borrowDateSql = rs.getDate("BorrowDate");
				if (borrowDateSql != null) {
					borrowOff.setBorrowdate(borrowDateSql.toLocalDate());
				}

				java.sql.Date dueDateSql = rs.getDate("DueDate");
				if (dueDateSql != null) {
					borrowOff.setDuedate(dueDateSql.toLocalDate());
				}

				borrowOff.setBook(book);

				list.add(borrowOff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public void addBorrowOff(BorrowOff borrowOff) {

		
		String checkQuantity = "SELECT Quantity FROM Books WHERE BookID = ?";
	
		String query = "INSERT INTO Borrowoff (BorrowOffID, Fname, Phone, Email, Birth, BorrowDate, DueDate, BookID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
		String updateQuantity = "UPDATE Books SET Quantity = Quantity - 1 WHERE BookID = ?";

		try (Connection con = connectData.getConnection()) {

			// 1. Kiểm tra số lượng sách
			int bookid = borrowOff.getBook().getBookid();
			int Quantity = 0;

			try (PreparedStatement checkStmt = con.prepareStatement(checkQuantity)) {
				checkStmt.setInt(1, bookid);

				try (ResultSet rs = checkStmt.executeQuery()) {
					if (rs.next()) {
						Quantity = rs.getInt("Quantity");
						if (Quantity <= 0) {
							System.out.println("Không thể mượn sách! Sách đã hết.");
							return;
						}
					} else {
						System.out.println("Không tìm thấy thông tin sách.");
						return;
					}
				}
			}

			
			try (PreparedStatement insertStmt = con.prepareStatement(query)) {
				insertStmt.setInt(1, borrowOff.getBorrowOffid());
				insertStmt.setString(2, borrowOff.getFname());
				insertStmt.setInt(3, borrowOff.getPhone());
				insertStmt.setString(4, borrowOff.getEmail());
				insertStmt.setDate(5, Date.valueOf(borrowOff.getBirth())); 
				insertStmt.setDate(6, Date.valueOf(borrowOff.getBorrowdate())); 
				insertStmt.setDate(7, Date.valueOf(borrowOff.getDuedate())); 
				insertStmt.setInt(8, bookid);

				int rowsAffected = insertStmt.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Thêm bản ghi mượn sách thành công.");
				} else {
					System.out.println("Thêm vào bảng BorrowOff thất bại.");
					return;
				}
			}

		
			try (PreparedStatement updateStmt = con.prepareStatement(updateQuantity)) {
				updateStmt.setInt(1, bookid);
				updateStmt.executeUpdate();
				System.out.println("Cập nhật số lượng sách thành công.");
			}

		} catch (Exception e) {
			// Xử lý lỗi ngoại lệ
			e.printStackTrace();
		}
	}
}