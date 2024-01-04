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
    </style>
    <title>Address Book</title>
</head>
<body>
    <header>
        <h1>Address Book</h1>
    </header>
    <div id="search-container">
        <form id="search-form" action="/AddressBook5/home" method="get">
            <input type="text" name="txtSearch" placeholder="Enter keyword...">
            <button type="submit">Search</button>
        </form>
    </div>
    <main>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <!-- Sample data -->
                <c:forEach items="${list}" var="p"> 
	                <tr>
	                    <td>${ p.id }</td>
						<td>${ p.address }</td>
						<td>${ p.phone }</td>
						<td>${ p.email }</td>
	                    <td>
	                        <a href="home?sid=${ p.id }">Update</a>
	                        <a href="#" onclick="showMess(${p.id})">Delete</a>
	                        <a href="/AddressBook5/image?aid=${ p.id }">Image</a>
	                    </td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>

        <form action="/AddressBook5/home" method="post">
        	<input type="number" id="id" name="id" style="display: none;" value="${ dir.id }">
        	
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${ dir.address }" required>

            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="${ dir.phone }" required>
            
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="${ dir.email }" required>

            <button type="submit">Add/Update</button>
        </form>
    </main>
    
    <div class="paging">
    	<c:forEach begin="1" end="${end}" var="i">
    		<a href="/AddressBook5/home?index=${i}&txtSearch=${txtSearch}">${i}</a>
    	</c:forEach>
    </div>
</body>
<script>
	function showMess(id){
		var option = confirm('Are you sure to delete');
		if(option === true){
			window.location.href = 'delete?sid='+id;
		}
	}
</script>
</html>
