package com.service.uesr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.connectData;
import com.model.*;

public class BookService {

	public List<Books> GetListBook() {
		List<Books> List = new ArrayList<>();
		String query = "select b.BookID, b.Title, b.Author, b.PublishedYear, b.Quantity, c.Name " + "from books as b "
				+ "join categories as c on b.CategoryID = c.CategoryID " + "ORDER BY b.BookID ";

		try {
			Connection conn = connectData.getConnection();
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				// Tạo đối tượng Category từ NameCategory
				Category category = new Category();
				category.setNameCategory(rs.getString("Name"));

				// Khai báo và khởi tạo đối tượng Books trong vòng lặp
				Books books = new Books();
				books.setBookid(rs.getInt("BookID")); // Sử dụng setBookID thay vì setbookID
				books.setTitle(rs.getString("Title"));
				books.setAuthor(rs.getString("Author"));
				books.setPublishedYear(rs.getInt("PublishedYear"));
				books.setQuantity(rs.getInt("Quantity"));
				books.setCategory(category);

				// Thêm đối tượng Books vào danh sách
				List.add(books);
			}
		} catch (Exception e) {
			e.printStackTrace(); // In lỗi nếu có vấn đề xảy ra
		}

		return List;
	}

	public void addBook(Books book) {
		String query = "INSERT INTO Books (BookID,Title, Author, PublishedYear, Quantity, CategoryID, Image) Value(?, ?, ?, ?, ?, ?,?) ";

		try {
			Connection con = connectData.getConnection();
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, book.getBookid());
			stm.setString(2, book.getTitle());
			stm.setString(3, book.getAuthor());

			stm.setInt(6, book.getCategory().getCategoryId());

			stm.setInt(4, book.getPublishedYear());
			stm.setInt(5, book.getQuantity());
			stm.setString(7, book.getImage());
			int rowsAffected = stm.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("thêm thành công");
			} else {
				System.out.println(" thêm thất bạn");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void DeleteBook(String bookid) {
		String query = "DELETE FROM books WHERE BookID = ?";
		try {
			Connection con = connectData.getConnection();
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, bookid);

			int affectedRows = stm.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("xoa thanh cong");

			} else {

				System.out.println("xoa that bai");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//	 public Books getBookId(String bookid) {
//		    String query = "SELECT * FROM books WHERE  = ?";
//		    try (
//		        Connection con = connectData.getConnection();
//		        PreparedStatement stm = con.prepareStatement(query)
//		    ) {
//		        // Thiết lập giá trị tham số
//		        stm.setString(1, bookid);
//		        
//		        // Thực thi truy vấn
//		        ResultSet rs = stm.executeQuery();
//
//		        // Xử lý kết quả
//		        if (rs.next()) {
//		            Category category = new Category();
//		            category.setNameCategory(rs.getString("Name"));
//
//		            // Tạo đối tượng Books từ dữ liệu truy vấn
//		            Books book = new Books();
//		            book.setBookid(rs.getInt("BookID"));
//		            book.setTitle(rs.getString("Title"));
//		            book.setAuthor(rs.getString("Author"));
//		            book.setPublishedYear(rs.getInt("PublishedYear"));
//		            book.setQuantity(rs.getInt("Quantity"));
//		            book.setCategory(category);
//
//		            return book;
//		        }
//
//		    } catch (Exception e) {
//		        e.printStackTrace();
//		    }
//
//		    return null; // Trả về null nếu không tìm thấy sách
//		}

	public Books getBookById(String bookId) {
		
		String query = "SELECT b.BookID, b.Title, b.Author, b.PublishedYear, b.Quantity, c.Name , b.Image "
				+ "FROM books AS b " + "JOIN categories AS c ON b.CategoryID = c.CategoryID " + "WHERE b.BookID = ? ";
		try (Connection conn = connectData.getConnection(); PreparedStatement stm = conn.prepareStatement(query)) {

			stm.setString(1, bookId); // Set the value for the parameter

			ResultSet rs = stm.executeQuery(); // Execute the query

			if (rs.next()) {
				// Khởi tạo đối tượng Category từ NameCategory
				Category category = new Category();
				category.setNameCategory(rs.getString("Name"));

				// Khởi tạo đối tượng Books
				Books book = new Books();
				book.setBookid(rs.getInt("BookID"));
				book.setTitle(rs.getString("Title"));
				book.setAuthor(rs.getString("Author"));
				book.setPublishedYear(rs.getInt("PublishedYear"));
				book.setQuantity(rs.getInt("Quantity"));
				book.setCategory(category); // Gán đối tượng Category vào Books
				book.setImage(rs.getString("Image"));
				return book; // Trả về đối tượng Books đã được khởi tạo đầy đủ
			}

		} catch (Exception e) {
			e.printStackTrace(); // In lỗi nếu có vấn đề xảy ra
		}

		return null; // Trả về null nếu không tìm thấy cuốn sách
	}

	public void editBook(int bookid, String title, String author, int publishedYear, int quantity, Category category , String image) {
		String query = "UPDATE `ql_sach`.`books` set `Title` = ?, `Author` = ?, `PublishedYear` = ?, `Quantity` = ?, `CategoryID` = ? ,`Image` = ?  WHERE (`BookID` = ?)";

		try {
			Connection con = connectData.getConnection();
			PreparedStatement stm = con.prepareStatement(query);

			stm.setString(1, title);
			stm.setString(2, author);
			stm.setInt(3, publishedYear);
			stm.setInt(4, quantity);
			stm.setInt(5, category.getCategoryId());
			stm.setString(6, image);
			stm.setInt(7, bookid);

			int affectedRows = stm.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("xua thanh cong");

			} else {

				System.out.println("sua that bai that bai");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Phương thức lấy tổng số sách
	public int getTotalBookCount() {
		int count = 0;
		String query = "SELECT COUNT(*) FROM books"; // Giả sử bảng sách là 'books'

		try (Connection conn = connectData.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) { // Sửa
																														// ở
																														// đây
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1); // Lấy tổng số sách
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Books> getBooks(int pageSize, int offset) {
		List<Books> booksList = new ArrayList<>();
		String query = "SELECT b.BookID, b.Title, b.Author, b.PublishedYear, b.Quantity, c.Name , b.Image "
				+ "FROM books AS b " + "JOIN categories AS c ON b.CategoryID = c.CategoryID " + "ORDER BY b.BookID "
				+ "LIMIT ? OFFSET ?";

		try (Connection conn = connectData.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

			// Set the LIMIT and OFFSET values
			stmt.setInt(1, pageSize); // pageSize for LIMIT
			stmt.setInt(2, offset); // offset for OFFSET

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Tạo đối tượng Category từ NameCategory
				Category category = new Category();
				category.setNameCategory(rs.getString("Name"));

				// Khai báo và khởi tạo đối tượng Books trong vòng lặp
				Books books = new Books();
				books.setBookid(rs.getInt("BookID")); // Sử dụng setBookID thay vì setbookID
				books.setTitle(rs.getString("Title"));
				books.setAuthor(rs.getString("Author"));
				books.setPublishedYear(rs.getInt("PublishedYear"));
				books.setQuantity(rs.getInt("Quantity"));
				books.setCategory(category);
				books.setImage(rs.getString("Image"));

				booksList.add(books);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booksList;
	}
}
