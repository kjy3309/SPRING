<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style></style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	${msg }
	<form action="list" method="get">
		<fieldset>
			<select name="opt">
				<option value="id">아이디</option>
				<option value="name">이름</option>
				<option value="email">이메일</option>
			</select>
			<input type="text" name="keyword" placehorder="검색어를 입력 하세요"/>
			<button>search</button>
		</fieldset>
	</form>
</body>
<script>

</script>
</html>