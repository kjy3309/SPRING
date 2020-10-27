<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<link rel="stylesheet" href="css/common.css" type="text/css">
</head>
<body>
	<form action="write" method="post">
		<table>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name" value="${sessionScope.loginId}" readonly/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="저장"/></td>
			</tr>
		</table>
	</form>
</body>
<script></script>
</html>