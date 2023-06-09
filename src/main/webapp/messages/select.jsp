<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Insert title here</title>
            <script src="https://code.jquery.com/jquery-3.6.4.js"></script>
        </head>

        <body>
        <form action="/index.jsp">
            <table border="1" style="margin: auto;">
                <tr>
                    <th>아이디</th>
                    <th>작성자</th>
                    <th>메세지</th>
                </tr>
                <c:forEach var="list" items="${list}">
                    <tr>
                        <td>${list.id}</td>
                        <td>${list.writer}</td>
                        <td>${list.message}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="3"><button>뒤로가기</button></td>
                </tr>
            </table>
            </form>
        </body>

        </html>
