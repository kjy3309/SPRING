<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<h3>회원가입</h3>
		<form action="join" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td>
						<input type="text" name="id"/>
						<input type="button" id="overlay" value="ID 중복 체크"/><span id="idChkMSg"></span>
					</td>
				</tr>
				<tr>
					<td>PW</td>
					<td><input type="password" name="pw"/></td>
				</tr>
				<tr>
					<td>NAME</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age"/></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						남자: <input type="radio" name="gender" value="남"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						여자: <input type="radio" name="gender" value="여"/>
					</td>
				</tr>
				<tr>
					<td>EMAIL</td>
					<td><input type="email" name="email"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="회원가입"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script>
		var idChk = false;
	
		$("#overlay").click(function(){	
			var id = $("input[name='id']").val();
			$.ajax({
				type:"get",
				url:"overlay",
				data:{"id":id},
				dataType:"JSON",
				success:function(data){
					$('#idChkMSg').html(data.msg);
					if(data.idChk){
						idChk = true;
					} 
					console.log(idChk);
				},
				error:function(e){
					console.log(e);
				}
			});
		});
	</script>
</html>