package com.service.uesr;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.dao.connectData;

public class OtpService {
	 private static final String INSERT_OTP = "INSERT INTO OTP (email, otp, created_at) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE otp = ?, created_at = ?";
	    private static final String VERIFY_OTP = "SELECT otp, created_at FROM OTP WHERE email = ?";

	    public void saveOTP(String email, String otp) throws Exception {
	        try (Connection connection = connectData.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_OTP)) {
	            java.sql.Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
	            preparedStatement.setString(1, email);
	            preparedStatement.setString(2, otp);
	            preparedStatement.setTimestamp(3, currentTime);
	            preparedStatement.setString(4, otp); // Update OTP nếu email đã tồn tại
	            preparedStatement.setTimestamp(5, currentTime);
	            preparedStatement.executeUpdate();
	        }
	    }

	    public boolean verifyOTP(String email, String userOtp) throws Exception {
	        try (Connection connection =connectData.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(VERIFY_OTP)) {
	            preparedStatement.setString(1, email);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String storedOtp = resultSet.getString("otp");
	                    Timestamp createdAt = resultSet.getTimestamp("created_at");

	                    // Kiểm tra OTP có khớp và còn hiệu lực (5 phút)
	                    LocalDateTime expiryTime = createdAt.toLocalDateTime().plusMinutes(5);
	                    return userOtp.equals(storedOtp) && LocalDateTime.now().isBefore(expiryTime);
	                }
	            }
	        }
	        return false;
	    }
}
