<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		
		table.board{
			width: 100%;
		}
		
		input[type='text']{
			width: 100%;
		}
		
		#editable{
			text-align: left;
			width: 98%;
			height: 500px;
			border: 1px solid gray;
			padding: 5px;
			overflow: auto;
		}
		
	</style>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form action="write" method="post">
		<table class="board">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="user_name" value=""/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<div id='editable' contenteditable="true"></div>
					<input id="content" type="hidden" name="content" value="">
				</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td>
					<input type="button" onclick="fileUp()" value="파일 업로드"/>
					<div id="files"></div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" onclick="save()" value="저장"/></td>
			</tr>
		</table>
	</form>
</body>
<script>

	function fileUp(){ // 파일 업로드 새창 띄우기
		window.open('uploadForm','파일 업로드','width=400, height=100');
	}
	
	function del(elem){
		var fileName = elem.id;
		console.log(fileName);
		$.ajax({
			url:'del'
			,type:'get'
			,data:{'fileName':fileName}
			,dataType:'json'
			,success: function(d){
				console.log(d);
				if(d.success >0){
					$(elem).prev().remove(); // 클릭한 버튼 기준으로 앞에 있는놈 삭제
					$(elem).remove(); // 클릭한 버튼 삭제
				}
			}
			,error: function(e){
				console.log(e);
			}
		});
	}

</script>
</html>