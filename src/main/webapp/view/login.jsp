<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Form Đăng Nhập</title>
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
	padding: 40px 30px;
	/* Tăng padding để form dài hơn, padding trên/bottom nhiều hơn */
	border-radius: 8px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	margin-top: 100px;
	width: 100%; /* Đảm bảo chiều rộng của form sẽ linh động */
	max-width: 500px; /* Đặt chiều rộng tối đa cho form */
	margin-left: auto;
	margin-right: auto; /* Căn giữa form */
}

.form-title {
	color: #333;
	font-weight: bold;
	text-transform: uppercase;
	margin-bottom: 20px;
}

.btn-primary, .btn-facebook {
	font-size: 16px;
	padding: 10px;
	border-radius: 5px;
	transition: all 0.3s ease;
}

.btn-primary {
	background-color: #007bff;
	border-color: #007bff;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.btn-facebook {
	background-color: #4267B2;
	border-color: #4267B2;
	color: white;
	display: flex;
	align-items: center;
	justify-content: center;
}

.btn-facebook:hover {
	background-color: #365899;
}

.btn-facebook i {
	margin-right: 8px;
}

.text-center {
	margin-top: 10px;
}

.mb-3 {
	margin-bottom: 20px;
}

.mb-3.password {
	margin-bottom: 60px; /* Tăng khoảng cách cho phần xác nhận mật khẩu */
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
					<h2 class="text-center form-title">Đăng Nhập</h2>
					<form action="/demo/login" method="POST">
						<div class="mb-3">
							<%
							String notlogin = (String) session.getAttribute("notlogin"); // Retrieve the attribute
							if (notlogin != null) {
							%>
							<p class="text-danger text-center mb-3"><%=notlogin%></p>
							<%
							session.removeAttribute("notlogin"); // Remove the attribute after displaying it
							}
							%>

						</div>


						<div class="mb-3">
							<label for="username" class="form-label">Tài Khoản</label> <input
								type="text" class="form-control" id="username" name="username"
								placeholder="Nhập tài khoản của bạn">
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">Mật khẩu</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="Nhập mật khẩu">
						</div>
						<div class="text-end mb-3">
							<span>Chưa có tài khoản? <a href="/demo/view/register.jsp">Đăng
									ký ngay</a></span>
						</div>
						<div class="d-grid mb-3">
							<button type="submit" class="btn btn-primary">Đăng Nhập</button>
						</div>
						<div class="text-center mb-3">Hoặc</div>
						<div class="d-grid mb-3">
							<a
								href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid

&redirect_uri=http://localhost:8001/demo/loginGoogle

&response_type=code

&client_id=475118769346-demmlpa8qhr39fn1alo345oss4ml6scr.apps.googleusercontent.com

&approval_prompt=force"
								class="btn btn-facebook"> Đăng nhập với google </a>
						</div>
						<!-- Nút đăng nhập với Facebook -->
						<div class="d-grid mb-3">
							<a
								href="https://www.facebook.com/v21.0/dialog/oauth?
  client_id=1105639381200632
  &redirect_uri=http://localhost:8001/demo/LoginFacebook" class="btn btn-facebook" >Đăng Nhập với Facebook</a>
								 
						
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
