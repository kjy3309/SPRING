<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
		<style>
			table, td{
				border : 1px solid black;
				border-collapse: collapse;
			}
			td{
				padding: 5px;
				text-align:center;
			}
		</style>
</head>
<body>
	<h2>회원 관리 로그인</h2>
	<form action="login" method="post">
		<table>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<td>PW</td>
				<td><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="login"/>
					<input type="button" value="회원가입" onclick="location.href='joinForm'"/>
				</td>
			</tr>
		</table>
	</form>	
</body>
<script>
	var msg = "${msg}";	
	if(msg != ""){
		alert(msg);
	}
</script>
</html>