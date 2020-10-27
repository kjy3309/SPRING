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
	<h3>평문을 암호화</h3>
	<form action="save">
		저장 : <input type="text" name="pass"/>
		<button>저장</button>
	</form>
	
	<h3>평문을 암호화 한 내용을 비교</h3>
	<form action="confirm">
		확인 : <input type="text" name="pass"/>
		<button>확인</button>
	</form>
	
	<h3>직접 만든 해쉬함수</h3>
	<form action="hash">
		확인 : <input type="text" name="pass"/>
		<button>확인</button>
	</form>
</body>
<script>

</script>
</html>