<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>글쓰기 페이지</title>
		<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
	<style>
		table{
			width:100%;
		}
		
		table,td{
			border: 1px solid black;
			border-collapse: collapse;
			padding: 10px;
			text-align:center;
		}		
		
		input[type='text']{
			width: 100%;
		}		
		
		textarea{
			resize:none;
			width: 100%;
			height: 250px;
		}
	</style>
	</head>
	<body>		
		<form action="write" method="post">	
			<table>		
				<tr>
					<td>작성자</td>
					<td>
						<input name="user_name"  type="text"/>
					</td>
				</tr>
				<tr>
					<td >제목</td>
					<td>
						<input name="subject"  type="text"/>
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="저장"/>
						<input type="reset" value="취소"/>
						<input type="button" onclick="location.href='./'" value="리스트보기"/>
					</td>
				</tr>	
			</table>
		</form>
	</body>
	<script></script>
</html>