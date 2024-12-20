<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
        }

        .menu {
            width: 250px;
            font-size: 14px;
            margin: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        .menu-title {
            font-weight: bold;
            cursor: pointer;
            margin-bottom: 10px;
            color: #007bff;
        }

        .menu-title:hover {
            text-decoration: underline;
        }

        .menu-options {
            display: none; /* Ẩn mặc định */
            margin-top: 10px;
        }

        .menu-options select[multiple] {
            width: 100%;
            height: auto;
            padding: 8px;
            font-size: 14px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: white;
            color: #555;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
    <div class="menu">
        <!-- Danh mục 1 -->
        <div class="menu-title" onclick="toggleMenu('menu1-options')">THỊT, CÁ, TRỨNG, HẢI SẢN</div>
        <div class="menu-options" id="menu1-options">
            <select multiple>
                <option value="thit-heo">Thịt heo</option>
                <option value="thit-bo">Thịt bò</option>
                <option value="thit-ga">Thịt gà, vịt, chim</option>
                <option value="thit-so-che">Thịt sơ chế</option>
                <option value="ca-hai-san">Cá, hải sản</option>
                <option value="trung">Trứng gà, vịt, cút</option>
                <option value="mon-ngon">Món ngon mỗi ngày</option>
            </select>
        </div>
    </div>

    <div class="menu">
        <!-- Danh mục 2 -->
        <div class="menu-title" onclick="toggleMenu('menu2-options')">RAU, CỦ, NẤM, TRÁI CÂY</div>
        <div class="menu-options" id="menu2-options">
            <select multiple>
                <option value="rau">Rau</option>
                <option value="cu">Củ</option>
                <option value="nam">Nấm</option>
                <option value="trai-cay">Trái cây</option>
            </select>
        </div>
    </div>

    <div class="menu">
        <!-- Danh mục 3 -->
        <div class="menu-title" onclick="toggleMenu('menu3-options')">DẦU ĂN, NƯỚC CHẤM, GIA VỊ</div>
        <div class="menu-options" id="menu3-options">
            <select multiple>
                <option value="dau-an">Dầu ăn</option>
                <option value="nuoc-cham">Nước chấm</option>
                <option value="gia-vi">Gia vị</option>
            </select>
        </div>
    </div>

    <script>
        function toggleMenu(menuId) {
            const menuOptions = document.getElementById(menuId);
            const isDisplayed = menuOptions.style.display === 'block';
            // Hiện tất cả các mục con (toggle trạng thái)
            menuOptions.style.display = isDisplayed ? 'none' : 'block';
        }
    </script>
</body>
</html>
