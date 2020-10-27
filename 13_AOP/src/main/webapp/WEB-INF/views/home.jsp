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
	<h3>AOP(Aspect Orient Programming)</h3>
	<p>관점 지향 프로그래밍은 "특정 작업" 의 그 시점을 포함한 전, 후에 해야할 일을 지정할 수 있다.</p>
	<p>그래서 특정 메서드 실행 전 후에 반복적으로 해야할 일이 있을 때 유용하다.</p>
	<ul>
		<li><a href='before'>@Before - 특정 메서드 실행 전에 실행</a></li>
		<li><a href='after'>@After - 특정 메서드 실행 후 실행</a></li>
		<li><a href='afterReturning'>@AfterReturning - 특정 메서드 결과 반환 후 실행, 메서드 결과값도 가로채온다.</a></li>
		<li><a href='afterThrowing'>@AfterThrowing - 특정 메서드가 예외 발생 한 후에 실행</a></li>
		<li><a href='around'>@Around - 특정 메서드 실행을 중심으로 전/후에 실행</a></li>
	</ul>
</body>
<script>

</script>
</html>