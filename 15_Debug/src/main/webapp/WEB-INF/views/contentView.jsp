<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>새로운 페이지</title>
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
	</style>	
	</head>
	<body>
		<table>
			<tr>
				<td>글번호</td>
				<td>${content.idx }</td>
				<td>작성자</td>
				<td>${content.user_name }</td>
				<td>조회수</td>
				<td>${content.bHit }</td>
				<td>작성일</td>
				<td>${content.reg_date }</td>
			</tr>
			<tr>
				<td >제목</td>
				<td colspan="7">${content.subject }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="7">
				${content.content }								
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<input type="button" onclick="home()" value="목록"/>
					<input type="button" onclick="update()" value="수정"/>
					<input type="button" onclick="del()" value="삭제"/>
				</td>
			</tr>	
		</table>
	</body>
	<script>
	function home(){
		location.href="./";
	}

	function update(){
		location.href="updateForm?idx="+${content.idx };
	}
	
	function del(idx){
		location.href="delete?idx="+${content.idx };
	}	
	</script>
</html>