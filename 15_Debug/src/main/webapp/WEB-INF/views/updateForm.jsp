<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<form action="../update" method="post">
		<table>
			<tr><th>번호</th><td><input type="text" name="idx" value="${info.idx }" readonly></td></tr>
			<tr><th>작성자</th><td>${info.user_name }</td></tr>
			<tr><th>제목</th><td><input type="text" name="subject" value="${info.subject }"></td></tr>
			<tr><th>내용</th><td><input type="text" name="content" value="${info.content }"></td></tr>
		</table>
		<a href="../list">목록보기</a>
		<button>수정하기</button>
	</form>
</body>
<script>

</script>
</html>