<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lập Phiếu Mượn</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        .card {
            border: none;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #6c63ff;
        }

        .btn {
            background-color: #6c63ff;
            color: #fff;
        }

        .btn:hover {
            background-color: #5751d4;
        }

        .table th,
        .table td {
            vertical-align: middle;
        }
        .pagination-controls {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.pagination-controls a {
    padding: 10px 20px;
    margin: 0 5px;
    background-color: #3498db;
    color: white;
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.3s;
}

.pagination-controls a:hover {
    background-color: #2980b9;
}

.pagination-controls .active {
    background-color: #2c3e50;
}

.pagination-controls .prev-button,
.pagination-controls .next-button {
    background-color: #1abc9c;
    padding: 10px 15px;
    border-radius: 5px;
}

.pagination-controls .prev-button:hover,
.pagination-controls .next-button:hover {
    background-color: #16a085;
}
    </style>
</head>

<body>
    <div class="container py-4">
        <div class="row">
            <!-- Form Section -->
         <div class="col-lg-5 mb-4">
    <div class="card p-4 shadow-sm border-light">
        <h5 class="mb-4 text-center">Lập Phiếu Mượn</h5>
        <form action = "/demo/addBorrowOff" method="post" >
           
            <div class="row mb-5">
                <div class="col-6">
                    <label for="soThe" class="form-label">Số thẻ</label>
                    <input type="text" id="soThe" class="form-control" name="borrowOffid" required>
                </div>
                <div class="col-6">
                    <label for="hoTen" class="form-label">Họ tên</label>
                    <input type="text" id="hoTen" class="form-control" name="fname" required>
                </div>
            </div>
            <!-- SĐT và Email -->
            <div class="row mb-5">
                <div class="col-6">
                    <label for="sdt" class="form-label">SĐT</label>
                    <input type="text" id="sdt" class="form-control" name="phone" required>
                </div>
                <div class="col-6">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" id="email" class="form-control" name="email" required>
                </div>
            </div>
            <!-- Mã sách và Ngày Sinh -->
            <div class="row mb-5">
                <div class="col-6">
                    <label for="maSach" class="form-label">Mã sách</label>
                    <input type="text" id="maSach" class="form-control" name="bookid" required >
                </div>
                <div class="col-6">
                    <label for="ngaySinh" class="form-label">Ngày Sinh</label>
                    <input type="date" id="ngaySinh" class="form-control" name="birth" required >
                </div>
            </div>
            <!-- Ngày mượn và Ngày trả -->
            <div class="row mb-5">
                <div class="col-6">
                    <label for="ngayMuon" class="form-label">Ngày mượn</label>
                    <input type="date" id="ngayMuon" class="form-control" name="borrowdate" required >
                </div>
                <div class="col-6">
                    <label for="ngayTra" class="form-label">Ngày dự kiến trả</label>
                    <input type="date" id="ngayTra" class="form-control" name="duedate" required >
                </div>
            </div>
            <!-- Nút Xác nhận -->
            <div class="d-flex justify-content-center mt-5">
                <button type="submit" class="btn btn-success btn-lg" onclick="showSuccessMessage()">Xác nhận</button>
            </div>
        </form>
    </div>
</div>

         


<!-- Success Message Modal -->
<div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="successModalLabel">Thành công</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Phiếu mượn đã được xác nhận thành công!
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
            </div>
        </div>
    </div>
</div>




            <!-- Table Section -->
    <div class="col-lg-7 mb-4">
    <div class="card p-4">
        <!-- Header and Search Bar -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="mb-0">Thông tin sách</h3>
            <input type="text" id="searchBar" class="form-control w-50" placeholder="Tìm kiếm sách..." onkeyup="searchTable()">
        </div>
        
        <!-- Table -->
        <table class="table table-hover align-middle" id="bookTable">
            <thead>
                <tr>
                    <th>Mã Sách</th>
                    <th>Tên Sách</th>
                    <th>thể loại </th>
                    <th>Tác Giả</th>                    
                   
                </tr>
            </thead>
            <c:forEach items="${listBookss}" var="x">
            <tbody>
                
          
                <tr>
                    <td>${x.bookid}</td>
                    <td>${x.title}</td>
                    <td>${x.category.nameCategory }</td>
                    <td>${x.author}</td>
               
                </tr>
                 
            </tbody>
             </c:forEach>
        </table>
        <div class="pagination-controls">
        <c:if test="${currentPage > 1}">
            <a href="addBorrowOff?page=${currentPage - 1}" class="prev-button">Previous</a>
        </c:if>
        
        <c:forEach begin="1" end="${totalPages}" var="i">
            <a href="addBorrowOff?page=${i}" class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
        
        <c:if test="${currentPage < totalPages}">
            <a href="addBorrowOff?page=${currentPage + 1}" class="next-button">Next</a>
        </c:if>
    </div>
    </div>
</div>


        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
