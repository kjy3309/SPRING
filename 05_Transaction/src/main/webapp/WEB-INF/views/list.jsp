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
		text-align: center;
	}
	
	div {
		margin: 10px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<c:if test="${sessionScope.loginId ne null}">
	<div>
		안녕하세요 ${sessionScope.loginId }님
		<button onclick="location.href='logout'">로그아웃</button>
	</div>
	<button onclick="location.href='writeForm'">글쓰기</button>
</c:if>
<body>
	<table>
		<tr>
			<th>글번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>조회수</th>
			<th>등록날짜</th>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.idx }</td>
				<td>${dto.user_name }</td>
				<td><a href="detail?idx=${dto.idx}">${dto.subject }</a></td>
				<td>${dto.bHit }</td>
				<td>${dto.reg_date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
<script>
	var msg = "${msg}";
	if(msg != ""){
		alert(msg);
	}
</script>
</html>