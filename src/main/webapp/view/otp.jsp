<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhập Mã OTP</title>
</head>
<body>
    <h2>Xác Nhận OTP</h2>
    <form action="verifyOTP" method="post">
        <label for="otp">Nhập mã OTP:</label>
        <input type="text" id="otp" name="otp" required>
        <button type="submit">Xác nhận</button>
    </form>
</body>
</html>