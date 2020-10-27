<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style>
			table, th, td{
				border: 1px solid black;
				border-collapse: collapse;
				padding: 5px 10px;
			}
		</style>
		<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	</head>
<body>
	<button onclick="location.href='writeForm'">글쓰기</button>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>삭제</th>
		</tr>
		<c:forEach items="${list }" var="dto">
			<tr>
				<td>${dto.idx }</td>
				<td><a href="detail?idx=${dto.idx}">${dto.subject }</td>
				<td>${dto.user_name }</td>
				<td><a href="delete?idx=${dto.idx}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>

</script>
</html>