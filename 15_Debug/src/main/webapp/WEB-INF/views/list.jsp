<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="resources/favicon.ico" type="image/x-icon"/>
		<title>리스트 페이지</title>
		<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
		<style>
			table{
				width:100%;
			}
			table, td{
				border: 1px solid;
				border-collapse: collapse;
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<table>
			<tr>
				<td colspan="6">
					<input onclick="location.href='writeView'" type="button" value="글쓰기"/>
				</td>
			</tr>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>이름</td>
				<td>날짜</td>
				<td>조회</td>
				<td>삭제</td>
			</tr>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.idx}</td>
					<td>
						<a href="./contentView?idx=${dto.idx}">
							${dto.subject}
						</a>
					</td>
					<td>${dto.user_name}</td>
					<td>${dto.reg_date}</td>
					<td>${dto.bHit}</td>
					<td><a href="./delete?idx=${dto.idx}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
	<script></script>
</html>







