<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
  src="https://code.jquery.com/jquery-3.6.4.js"></script>
</head>
<body>
<table border=1 align=center>
      <tr>
         <th colspan=3>Message List</th>
      </tr>

      <tr>
         <th>ID</th>
         <th>WRITER</th>
         <th>MESSAGE</th>
      </tr>

      <c:forEach var="i" items="${list}">
         <tr>
            <td>${i.id}</td>
            <td>${i.writer}</td>
            <td>${i.message}</td>
         </tr>
      </c:forEach>

      <tr>
         <td colspan=3 align=center><a href="index.html">back</a></td>
      </tr>

   </table>
</body>
</html>