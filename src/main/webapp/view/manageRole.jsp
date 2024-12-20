<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Quản Lý Quyền Tài Khoản</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f1f1f1;
}

.title {
	text-align: center;
	font-size: 24px;
	margin-bottom: 20px;
}

form {
	display: inline;
}
</style>
</head>
<body>
	<div class="title">Quản Lý Quyền Tài Khoản</div>



	<!-- Hiển thị dữ liệu trong bảng -->
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Email</th>
				<th>Tên</th>
				<th>Chức Năng</th>
			</tr>
		</thead>
		<tbody>
		   
			<c:set var="counter" value="1" scope="page" />
			<c:forEach var="x" items="${listUser}">
				<tr>
					<td>${counter}</td> 
					<td>${x.email}</td>
					<td>${x.fname}</td>
					<td>${x.role.roleName}</td>
				</tr>
				<c:set var="counter" value="${counter + 1}" /> 
			</c:forEach>
			
			
			<c:forEach var="x" items="${listUsergg}">
				<tr>
					<td>${counter}</td>
					<td>${x.email}</td>
					<td>${x.name}</td>
					<td>${x.role.roleName}</td>
				</tr>
				<c:set var="counter" value="${counter + 1}" /> 
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
