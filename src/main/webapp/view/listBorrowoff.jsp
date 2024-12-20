<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Danh sách người mượn sách</title>
    <style>
        /* CSS để định dạng bảng */
    /* CSS để định dạng bảng */
.container {
    max-width: 100%; /* Đảm bảo khung rộng đầy đủ màn hình */
    padding: 0 20px; /* Thêm padding nếu cần tạo khoảng cách cho các phần tử */
}

.table-container {
    margin-top: 30px;
    margin-right: 0; /* Loại bỏ khoảng cách bên phải nếu có */
    margin-left: 0; /* Loại bỏ khoảng cách bên trái */
}

.table-container table {
    width: 100%; /* Đảm bảo bảng chiếm toàn bộ chiều rộng khung */
}

.header-container {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    align-items: center;
    width: 100%; /* Đảm bảo phần header chiếm toàn bộ chiều rộng */
}

.search-container input {
    width: 350px; /* Tăng chiều rộng của ô tìm kiếm */
}

table {
    border-collapse: collapse;
    width: 90%;
    margin-right: 50px
}

th, td {
    border: 1px solid #ddd;
    padding: 15px;
    text-align: center;
}

th {
    background-color: #007bff;
    color: white;
}

td {
    background-color: #f9f9f9;
}

.table-container {
    margin-top: 30px;
}

.table-title {
    font-size: 2rem;
    font-weight: bold;
    color: #007bff;
    margin-bottom: 25px;
}

.btn-custom {
    background-color: #28a745;
    color: white;
    border-radius: 5px;
    padding: 12px 25px;
}

.btn-custom:hover {
    background-color: #218838;
}

.card {
    border-radius: 10px;
}

/* CSS cho các phần tìm kiếm và nút thêm */
.header-container {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    align-items: center;
}

.search-container {
    display: flex;
    justify-content: flex-end;
    width: 70%;
}

.search-container input {
    width: 320px;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
}

.add-button-container {
    display: flex;
    justify-content: flex-start;
    width: 30%;
}

.add-button-container button {
    font-size: 1rem;
}

    </style>
</head>
<body>
    <div class="container mt-4">
        <div class="card shadow-sm">
            <div class="card-body">
                <h2 class="table-title text-center">Danh sách người mượn sách</h2>

                <!-- Phần đầu có nút thêm và thanh tìm kiếm -->
                <div class="header-container">
                    <div class="add-button-container">
                        <button class="  btn btn-custom mb-4" onclick="location.href='addBorrowOff'">Thêm người mượn mới</button>
                    </div>
                    <div class="search-container">
                        <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm theo tên người mượn hoặc mã sách" onkeyup="searchTable()">
                    </div>
                </div>

                <div class="table-container">
                    <table class="table table-bordered table-hover" id="borrowersTable">
                        <thead>
                            <tr>
                                <th>BorrowOffID</th>
                                <th>Fname</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Birth</th>
                                <th>BorrowDate</th>
                                <th>DueDate</th>
                                <th>BookID</th>
                                <th>Title</th>
                                <th>Namecategory</th>
                            </tr>
                        </thead>
                        <tbody>
                           <c:forEach items="${listBorrowOff}" var="x">
   
                            <tr>
                                <td>${x.borrowOffid}</td>
                                <td>${x.fname}</td>
                                <td>${x.phone }</td>
                                <td>${x.email}</td>
                                <td>${x.birth }</td>
                                <td>${x.borrowdate }</td>
                                <td>${x.duedate }</td>
                                <td>${x.book.bookid}</td>
                                <td>${x.book.title }</td>
                                <td>${x.book.category.nameCategory }</td>
                            </tr>
                            </c:forEach>
                          
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- JavaScript tìm kiếm -->
    <script>
        function searchTable() {
            let input = document.getElementById('searchInput');
            let filter = input.value.toLowerCase();
            let table = document.getElementById('borrowersTable');
            let rows = table.getElementsByTagName('tr');
            
            for (let i = 1; i < rows.length; i++) {
                let row = rows[i];
                let cells = row.getElementsByTagName('td');
                let match = false;
                
                for (let j = 0; j < cells.length; j++) {
                    if (cells[j]) {
                        let cellText = cells[j].textContent || cells[j].innerText;
                        if (cellText.toLowerCase().indexOf(filter) > -1) {
                            match = true;
                            break;
                        }
                    }
                }
                
                if (match) {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            }
        }
    </script>
</body>
</html>
