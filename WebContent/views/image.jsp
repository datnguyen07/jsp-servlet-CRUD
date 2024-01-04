<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
        }

        header {
            background-color: #333;
            color: white;
            padding: 1em;
            text-align: center;
        }

        main {
            max-width: 800px;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #555;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        form {
            margin-top: 20px;
        }

        form label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }

        form input, form button {
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: calc(100% - 22px);
            box-sizing: border-box;
        }

        form input[type="text"], form input[type="email"] {
            width: 100%;
        }

        form button {
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
        }

        form button:hover {
            background-color: #555;
        }
        #search-container {
            text-align: center;
            display: flex;
            padding-left: 1239px;
        }
        #search-form {
        	display: flex;
        }
        .paging {
    text-align: center;
    margin-top: 20px;
}

.paging a {
    display: inline-block;
    padding: 8px 12px;
    margin: 0 5px;
    color: #333;
    text-decoration: none;
    border: 1px solid #ddd;
    border-radius: 4px;
    transition: background-color 0.3s;
}

.paging a:hover {
    background-color: #ddd;
}

.paging a.active {
    background-color: #4CAF50;
    color: white;
}
table img {
        max-width: 100px; /* Thiết lập chiều rộng tối đa là 100px */
        max-height: 100px; /* Thiết lập chiều cao tối đa là 100px */
        height: auto; /* Tự động điều chỉnh chiều cao để giữ tỷ lệ khung hình */
    }
    a.back {
        display: inline-block;
        padding: 8px 16px; /* Tuỳ chỉnh giữa nút và văn bản bên trong */
        text-decoration: none; /* Loại bỏ gạch chân mặc định của thẻ a */
        border: 1px solid #ccc; /* Viền của nút */
        border-radius: 4px; /* Độ cong góc của nút */
        background-color: #f0f0f0; /* Màu nền của nút */
        color: #333; /* Màu văn bản của nút */
        font-weight: bold; /* In đậm văn bản */
        cursor: pointer; /* Biến con trỏ thành kiểu tay khi di chuột qua */
        text-align: center; /* Căn giữa văn bản trong nút */
        transition: background-color 0.3s ease; /* Hiệu ứng mượt mà khi hover */
    }

    /* Hiệu ứng hover */
    a.back:hover {
        background-color: #ddd; /* Màu nền khi hover */
    }
    </style>
    <title>Address Book</title>
</head>
<body>
    <header>
        <h1>Address Book</h1>
    </header>
    <main>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Sample data -->
                <c:forEach items="${listImage}" var="p"> 
	                <tr>
	                    <td>${ p.id }</td>
						<td><img src="${ p.image }"></td>
	                    <td>
	                        <a href="image?sid=${ p.id }">Update</a>
	                        <a href="#" onclick="showMess(${p.id}, ${p.directorybook_id})">Delete</a>
	                    </td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>

        <form action="/AddressBook5/image" method="post">
        	<input type="number" id="id" name="id" style="display: none;" value="${ dir.id }">
        	
            <label for="directorybook_id">Directory Book Id:</label>
            <input type="text" id="directorybook_id" name="directorybook_id" value="${ dir.directorybook_id }" required>

            <label for="image">Image:</label>
            <input type="text" id="image" name="image" value="${ dir.image }" required>

            <button type="submit">Add/Update</button>
            <a class="back" href="/AddressBook5/home">Back</a>
        </form>
    </main>
    
</body>
<script>
	function showMess(id, directorybook_id){
		var option = confirm('Are you sure to delete');
		if(option === true){
			window.location.href = 'deleteImage?sid='+id + '&directorybook_id=' + directorybook_id;
		}
	}
</script>

</html>
