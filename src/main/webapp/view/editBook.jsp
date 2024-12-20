<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Book</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f6fa;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
}

.form-container {
	width: 80%;
	max-width: 500px;
	background-color: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #5e72e4;
}

.form-group {
	margin-bottom: 15px;
}

label {
	display: block;
	margin-bottom: 5px;
	color: #333;
}

input[type="text"], input[type="number"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #e9ecef;
	border-radius: 5px;
	font-size: 16px;
}

.form-actions {
	text-align: center;
}

.btn {
	padding: 10px 20px;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.btn-submit {
	background-color: #4CAF50;
}

.btn-cancel {
	background-color: #f44336;
	margin-left: 10px;
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
	<div class="form-container">
		<h2>Add New Book</h2>
		<form action="/demo/editBook" method="post"
			enctype="multipart/form-data">
			<!-- Book details form fields -->
			<div class="form-group">
				<label for="title">Mã Sách</label> <input type="number" id="bookid"
					name="bookid" value="${kk.bookid}" required>
			</div>
			<div class="form-group">
				<label for="title">Title</label> <input type="text" id="title"
					name="title" value="${kk.title}" required>
			</div>

			<div class="form-group">
				<label for="author">Author</label> <input type="text" id="author"
					name="author" value=" ${kk.author}" required>
			</div>

			<%-- <div class="form-group">
				<label>Thể loại</label> <select name="category" id="category"
					class="form-control"
					data-selected="${book.getCategory().getName()}" required>
					<!-- <option value="">Chọn 1 thể loại</option> -->
					<c:forEach items="${categoryList}" var="category">
						<option value="${Integer.toString(category.getId())}"
							<c:if test="${category.getId() == book.getCategory().getId()}">selected="true"</c:if>>${category.getName()}</option>
					</c:forEach>
				</select>
			</div> --%>
			 <div class="form-group">
				<label for="category">Category</label> <select class="form-control"
					id="category" name="category"
					data-selected="${kk.category.nameCategory }" required>
					<c:forEach items="${listcategory}" var="x">						
						<option value="${Integer.toString(x.categoryId)}"						
							<c:if test="${x.categoryId != kk.category.categoryId}">selected="true"</c:if>>${x.nameCategory}
						</option>
					</c:forEach>

				</select>
			</div>




			<%--  <div class="form-group">
				<label for="category">Category</label> <select class="form-control"
					id="category" name="category" required>
					<option value=" ${kk.category.categoryId}">${kk.category.nameCategory }</option>
					<c:forEach items="${listcategory}" var="x">
						<c:if test="${x.categoryId != kk.category.categoryId}">
							<option value="${x.categoryId}">${x.nameCategory}</option>
						</c:if>
					</c:forEach>

				</select>
			</div> 
 --%>
			<div class="form-group">
				<label for="year">Published Year</label> <input type="number"
					id="year" name="publishedyear" value="${kk.publishedYear}" required>
			</div>

			<div class="form-group">
				<label for="quantity">Quantity</label> <input type="number"
					id="quantity" name="quantity" value="${kk.quantity}" required>
			</div>
			
			<div class="form-group">
			<img src="/demo/image/${kk.image}" alt="Book Image"
					style="width: 50px; height: auto;"> 
				<label for="image">Book Image</label>
				 <input type="file" id="image"
					name="fileImage" accept="image/*" required>
			</div> 

			<div class="form-actions">
				<button type="submit" class="btn btn-submit">edit Book</button>
				<a href="/view/HomeBook" class="btn btn-cancel">Cancel</a>
			</div>
		</form>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>