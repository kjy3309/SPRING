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
	${msg}
	<table>
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
		</tr>
		<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.id }</td>
				<td>${dto.name }</td>
				<td>${dto.age }</td>
				<td>${dto.gender }</td>
			</tr>
		</c:forEach>
	</table>
	
	<fieldset>
		<form action="update" method="post">
			<p>수정 ID : <input type="text" name="id"/></p>
			<p>수정 pw : <input type="text" name="pw"/></p>
			<p>수정 name : <input type="text" name="name"/></p>
			<p>수정 email : <input type="text" name="email"/></p>
			<button>수정요청</button>
		</form>
	</fieldset>
	
	<fieldset>
		<form action="multi" method="post">
			<p>
				이름이 : <input type="text" name="userName"/>
				이거나 : <input type="text" name="userName"/> 인 회원 찾기
			</p>
			<button>검색</button>
		</form>
	</fieldset>
</body>
<script>

</script>
</html>