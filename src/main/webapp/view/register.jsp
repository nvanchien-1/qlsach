<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form Đăng Ký</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Thêm Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
}

.form-container {
	background-color: #fff;
	padding: 10px 20px;
	/* Tăng padding để form dài hơn, padding trên/bottom nhiều hơn */
	border-radius: 8px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	margin-top: 10px;
	width: 100%; /* Đảm bảo chiều rộng của form sẽ linh động */
	max-width: 500px; /* Đặt chiều rộng tối đa cho form */
	margin-left: auto;
	margin-right: auto; /* Căn giữa form */ . form-title { color : #333;
	font-weight: bold;
	text-transform: uppercase;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
	border-color: #004085;
}

.btn-facebook {
	background-color: #4267B2;
	border-color: #4267B2;
	color: white;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 16px;
}

.btn-facebook:hover {
	background-color: #365899;
	border-color: #365899;
}

.btn-facebook i {
	margin-right: 8px; /* Khoảng cách giữa biểu tượng và chữ */
}

.mb-3 {
	margin-bottom: 20px;
}

.mb-3.confirmPassword {
	margin-bottom: 50px; /* Tăng khoảng cách cho phần xác nhận mật khẩu */
}

.d-grid.mb-3 {
	margin-top: 20px;
	/* Tạo khoảng cách giữa phần nhập mật khẩu và nút đăng ký */
}
</style>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="form-container">
					<h2 class="text-center form-title my-3">Đăng Ký</h2>
					<div class="mb-1">
						<%
						String register = (String) session.getAttribute("register");
						if (register != null) {
						%>
						<p class="text-danger text-center mb-3"><%=register%></p>
						<%
						session.removeAttribute("register");
						}
						%>
					</div>
					<form action="/demo/register" method="POST">
						<!-- Họ và tên -->
						<div class="mb-2">
							<label for="fullName" class="form-label">Họ và tên</label> <input
								type="text" class="form-control" id="fname" name="fname"
								placeholder="Nhập họ và tên của bạn">
						</div>
						<div class="mb-2">
							<label for="phone" class="form-label">Phone</label> <input
								type="text" class="form-control" id="phone" name="phone"
								placeholder="Nhập SĐT của bạn">
						</div>

						<!-- Email -->

						<div class="mb-2">
							<label for="email" class="form-label">Email</label> <input
								type="email" class="form-control" id="email" name="email"
								placeholder="Nhập email của bạn">
						</div>
						<div class="mb-2">
							<label for="username" class="form-label">Tài Khoản</label> <input
								type="text" class="form-control" id="username" name="username"
								placeholder="Nhập tài khoản của bạn">
						</div>

						<!-- Mật khẩu -->
						<div class="mb-2">
							<label for="password" class="form-label">Mật khẩu</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="Nhập mật khẩu">
						</div>

						<!-- Xác nhận mật khẩu -->
						<div class="mb-2 confirmPassword ">
							<label for="confirmPassword" class="form-label">Xác nhận
								mật khẩu</label> <input type="password" class="form-control"
								id="confirmPassword" placeholder="Nhập lại mật khẩu"
								oninput="checkPasswordMatch()">
							<div id="passwordError" class="text-danger mt-1"></div>
							<!-- Div để hiển thị lỗi -->
						</div>

						<!-- Nút đăng ký -->
						<div class="d-grid mb-2">
							<button type="submit" class="btn btn-primary">Đăng Ký</button>
						</div>
						<div class="text-center mb-2">Hoặc</div>

						<!-- Nút đăng ký với Facebook -->
						<div class="d-grid">
							<a
								href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid

&redirect_uri=http://localhost:8001/demo/registerGoogle

&response_type=code

&client_id=475118769346-demmlpa8qhr39fn1alo345oss4ml6scr.apps.googleusercontent.com

&approval_prompt=force"
								class="btn btn-facebook"  class="fab fa-facebook-f"  > Đăng Ký với google </a>
								
							
						</div>
						
						

					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	<script>
		function checkPasswordMatch() {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmPassword").value;
			var passwordError = document.getElementById("passwordError");

			if (password !== confirmPassword) {
				passwordError.textContent = "Mật khẩu và xác nhận mật khẩu không khớp.";
			} else {
				passwordError.textContent = ""; // Xóa thông báo nếu mật khẩu khớp
			}
		}
	</script>




</body>
</html>
