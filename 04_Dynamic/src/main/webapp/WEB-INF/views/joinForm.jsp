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
	<form action="join" method="post">
		<p>ID : <input type="text" name="user_id"/></p>
		<p>PW : <input type="password" name="user_pw"/></p>
		<p>NAME : <input type="text" name="user_name"/></p>
		<p>AGE : <input type="text" name="user_age"/></p>
		<p>GENDER : <input type="text" name="user_gender"/></p>
		<p>EMAIL : <input type="text" name="user_email"/></p>
		<button>가입</button>
	</form>
</body>
<script>

</script>
</html>