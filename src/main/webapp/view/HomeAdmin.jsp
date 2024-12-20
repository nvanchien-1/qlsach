<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dashboard - Book Management</title>
<style>
/* Tổng quan */
html, body {
	font-family: 'Roboto', Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	overflow: hidden;
	max-width: 100%;
	background-color: #f4f6fa;
	color: #333;
}

a {
	text-decoration: none;
	color: inherit;
}

/* Sidebar */
.sidebar {
	width: 240px;
	background-color: #2c3e50;
	color: #ecf0f1;
	display: flex;
	flex-direction: column;
	align-items: center;
	padding: 20px 0;
	box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.sidebar h2 {
	margin: 0 0 20px;
	font-size: 1.4em;
	letter-spacing: 1px;
}

.sidebar a {
	color: #bdc3c7;
	font-size: 1em;
	padding: 12px 20px;
	margin: 10px 0;
	width: 90%;
	text-align: center;
	border-radius: 8px;
	transition: all 0.3s;
}

.sidebar a:hover {
	background-color: #34495e;
	color: #ecf0f1;
	box-shadow: 0 3px 5px rgba(0, 0, 0, 0.2);
}

/* Main Content */
.main-content {
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	padding: 20px;
	background-color: #ecf0f1;
	overflow-y: auto;
}

/* Navbar */
.navbar {
	width: 100%;
	background-color: #2c3e50;
	color: #ecf0f1;
	padding: 10px 20px;
	display: flex;
	align-items: center;
	justify-content: space-between;
	border-radius: 10px;
	box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
}

.navbar h1 {
	font-size: 1.5em;
	margin: 0;
}

.navbar .nav-right {
	font-size: 1em;
	display: flex;
	align-items: center;
}

.navbar .nav-right span {
	margin-right: 10px;
	color: #bdc3c7;
}

/* Table Container */
.table-container {
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.1);
	padding: 20px;
	overflow-x: auto;
}

.table-container .header-row h2 {
	font-size: 1.4em;
	margin: 0 0 15px;
	color: #2c3e50;
	border-bottom: 2px solid #f4f6fa;
	padding-bottom: 8px;
}

.header-row {
	display: flex;
	justify-content: space-between; /* Căn hai phần tử sang hai phía */
	align-items: center; /* Căn giữa theo trục dọc */
	padding: 10px; /* Khoảng cách giữa các phần tử */
	border-bottom: 1px solid #ccc; /* Tùy chọn, nếu muốn kẻ đường dưới */
}

h2 {
	margin: 0; /* Loại bỏ khoảng cách mặc định */
	font-size: 24px;
	font-weight: bold;
}

.nav-right {
	font-size: 16px;
	color: #555; /* Màu chữ */
}

/* Table */
table {
	width: 100%;
	border-collapse: collapse;
	font-size: 0.95em;
}

thead {
	background-color: #2c3e50; /* Chỉnh màu nền */
	color: #2c3e50; /* Màu chữ */
}

th, td {
	padding: 12px 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	font-weight: bold;
	background-color: #f9fbfd;
	color: #34495e;
	text-transform: uppercase;
	font-size: 0.9em;
}

tbody tr:hover {
	background-color: #f2f4f6;
}

.action-button {
	padding: 6px 12px;
	font-size: 0.85em;
	color: #ffffff;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background 0.3s;
}

.edit-button {
	background-color: #27ae60;
}

.edit-button:hover {
	background-color: #229954;
}

.delete-button {
	background-color: #e74c3c;
}

.delete-button:hover {
	background-color: #c0392b;
}

.add-button {
	background-color: #2980b9;
	color: #ffffff;
	padding: 8px 15px;
	border: none;
	border-radius: 4px;
	font-size: 0.9em;
	cursor: pointer;
	transition: background 0.3s;
}

.add-button:hover {
	background-color: #21618c;
}

/* Responsive */
@media ( max-width : 768px) {
	.sidebar {
		width: 200px;
	}
	.navbar h1 {
		font-size: 1.2em;
	}
	th, td {
		padding: 8px;
	}
	.action-button {
		font-size: 0.8em;
	}
}
/* Pagination Styling */
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

.pagination-controls .prev-button, .pagination-controls .next-button {
	background-color: #1abc9c;
	padding: 10px 15px;
	border-radius: 5px;
}

.pagination-controls .prev-button:hover, .pagination-controls .next-button:hover
	{
	background-color: #16a085;
}

img {
	max-width: 100%;
	height: auto;
	display: block;
	object-fit: cover;
}
</style>
</head>
<body>
	<div class="sidebar">
		<h2>Thư Viện Sách</h2>
		<a href="HomeBook">Books</a> 
		<a href="/demo/addbook">Thêm Sách</a> 
		<a href="listBorrowOff">BorrowOff</a> 
		<a href="addBorrowOff">loan</a>
		<c:if test="${sessionScope.role == 'Manager'}">
				<a href="ListManager">List Manage</a>
			</c:if>
		 <a href="Logout">Logout</a>
	</div>

	<%-- 	<div class="main-content">
		<div class="navbar">
			<h1>Book Management</h1>
			<c:if test="${sessionScope.username != null}">
				<div class="nav-right">
					<span>Welcome, ${sessionScope.username}!</span>
				</div>
			</c:if>
		</div>
 --%>
	<div class="table-container">
		<div class="header-row">
			<h2>Manage Books</h2>
			<c:if test="${sessionScope.username != null}">
				<div class="nav-right">
					<span>Welcome, ${sessionScope.username}!</span>
				</div>
			</c:if>
		</div>

		<table>
			<thead>
				<tr>
					<th>BookId</th>
					<th>Image</th>
					<th>Title</th>
					<th>Author</th>
					<th>Category</th>
					<th>Published Year</th>
					<th>Quantity</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<c:forEach items="${listBooks}" var="x">
				<tbody>
					<tr>
						<td>${x.bookid}</td>
						<td><img src="/demo/image/${x.image}" alt="Book Image"
							style="width: 50px; height: auto;"></td>
						<td>${x.title}</td>
						<td>${x.author}</td>
						<td>${x.category.nameCategory}</td>
						<td>${x.publishedYear}</td>
						<td>${x.quantity}</td>
						<td><button class="action-button edit-button"
								onclick="location.href='editBook?xid=${x.bookid}'">Edit</button></td>
						<td><button class="action-button delete-button"
								onclick="location.href='deleteBook?xid=${x.bookid}'">Delete</button></td>
					</tr>

				</tbody>
			</c:forEach>
		</table>
		<div class="pagination-controls">
			<c:if test="${currentPage > 1}">
				<a href="HomeBook?page=${currentPage - 1}" class="prev-button">Previous</a>
			</c:if>

			<c:forEach begin="1" end="${totalPages}" var="i">
				<a href="HomeBook?page=${i}"
					class="page-link ${i == currentPage ? 'active' : ''}">${i}</a>
			</c:forEach>

			<c:if test="${currentPage < totalPages}">
				<a href="HomeBook?page=${currentPage + 1}" class="next-button">Next</a>
			</c:if>
		</div>
	</div>
</body>
</html>
