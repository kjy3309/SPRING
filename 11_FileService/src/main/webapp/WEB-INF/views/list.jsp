<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<style>
	table, td, th{
		border : 1px solid;
		border-collapse : collapse;
		padding: 5px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>리스트</h3>
	<button onclick="location.href='writeForm'">글쓰기</button>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bbs">
				<tr>
					<td>${bbs.idx }</td>
					<td><a href="detail?idx=${bbs.idx }" >${bbs.subject }</a></td>
					<td>${bbs.user_name }</td>
					<td>${bbs.reg_date }</td>
					<td>${bbs.bHit }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script>

</script>
</html>