<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang Chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom styles */
        .container {
    margin:0 50px 0 0px ;
}
        .navbar {
            background-color: #5186b0;
             padding: 0;
        }
        .navbar-brand{
         padding: 0;
        
        }
   .navbar-brand img {
    height: 60px; /* Chiều cao logo */
     width: 100px;
  
  object-fit: cover; /* Tự động căn chỉnh chiều rộng */
}

        .navbar-nav .nav-link {
            color: white;
            font-weight: bold;
        }
        .navbar-nav .nav-link:hover {
            color: #ffdd57;
        }
        .btn-login {
            color: white;
            margin-right: 10px;
        }
        .btn-register {
            background-color: #ff8c00;
            color: white;
        }
        .announcement {
            background-color: #ff8c00;
            color: white;
            padding: 8px 0;
            text-align: center;
            font-weight: bold;
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg">
        <div class="container ">
            <!-- Logo -->
            <a class="navbar-brand " href="#" style="margin-right: 100px;">
                <img src="/demo/image/logo.png" alt="Logo" style="width: 150px; height: auto;"> 
            </a>

            <!-- Search box -->
            <form class="d-flex mx-3">
                <input class="form-control me-3" type="search" placeholder="Tìm kiếm" aria-label="Search">
            </form>

            <!-- Navbar links -->
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Học Bài</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Hỏi Bài</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Kiểm Tra</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">ĐGNL</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Thi Đấu</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Bài Viết
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Chủ đề 1</a></li>
                            <li><a class="dropdown-item" href="#">Chủ đề 2</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Trợ Giúp</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Về OLM</a>
                    </li>
                </ul>

               	<c:if test="${sessionScope.username != null}">
				<div class="d-flex">
					<span>Welcome, ${sessionScope.username}!</span>
					
				</div>
				<div class="d-flex">
					 <a href="Logout">Logout</a>
					
				</div>
				
				
			</c:if>
               <c:if test="${sessionScope.username == null}">
				<div class="d-flex">
                    <a href="/demo/view/login.jsp" class="btn btn-login">Đăng nhập</a>
                    <a href="/demo/view/register.jsp" class="btn btn-register">Đăng ký</a>
                </div>
				
			</c:if>
                
            </div>
        </div>
    </nav>

    <!-- Announcement bar -->
    <div class="announcement">
        Tham gia cuộc thi "Nhà giáo sáng tạo" ẵm giải thưởng với tổng giá trị lên đến 10 triệu VNĐ
    </div>
    

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
