<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>
	<form action="/insert.message" method="get">
		<input type="text" name="writer"><br>
		<input type="text" name="message"><br>
		<input type="submit">
	</form>
</body>
</html>