<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실시간 테이블</title>
<style>
	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
		text-align: center;
	}
	
	th, td{
		padding: 5px;
	}
	
	input[type='text']{
		background-color: lightgray;
		color: #808080;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<h3>실시간 테이블</h3>
	<table>
		<thead>
			<th>No</th>
			<th>Leader</th>
			<th>MEMBER1</th>
			<th>MEMBER2</th>
			<th>MEMBER3</th>
			<th>MEMBER4</th>
			<th>MEMBER5</th>
		</thead>
		<tbody id="list">
			<!-- server 에서 받아온 리스트 그리기 -->
		</tbody>
	</table>
</body>
<script>
	listCall(); // 초기 리스트 불러오기
	
	$('table').focusin(function(e){
		// e.target 은 이벤트가 발생한 대상 요소를 의미한다.
		e.target.style.backgroundColor="white";
		e.target.style.color="black"
	});
	
	$('table').focusout(function(e){
		// e.target 은 이벤트가 발생한 대상 요소를 의미한다.
		console.log(e);
		
		e.target.style.backgroundColor="lightgray";
		e.target.style.color="#808080"
		
		// 저장 요청
		if(e.target.defaultValue != e.target.value){ // 값이 변경되었을 경우만 수정 요청을 보낸다.
			save(e.target);
		}
	});
	
	function save(elem){
		// num 이 2인 leader 컬럼을 '홍길동' 으로 바꾸려면?
		// UPDATE teams SET leader='홍길동' WHERE num=2 
		// 바꿔줄 이름, 팀 번호, 어떤 컬럼을 바꿀것인지?		
		var str = elem.className;
		var params = {};
		
		var teamNum = str.substring(4,5); // 팀 번호
		var member = str.substring(6,str.length); // 컬럼명
		
		params.num = teamNum;
		params.val = elem.value;
		params.col = member;
		
		
		$.ajax({
			type:'get'
			,url:'update'
			,dataType:'json'
			,data:params
			,success:function(d){
				if(d.success<1){
					alert('수정에 실패했어요');
				}
				listCall();
			}
			,error:function(e){
				console.log(e);
			}
		});
		
	}

	function listCall(){
		$.ajax({
			type:'get'
			,url:'listCall'
			,dataType:'json'
			,success:function(d){
				drawList(d.list);
			}
			,error:function(e){
				console.log(e);
			}
		});
	}
	
	function drawList(list){
		var content = "";
		for(var i=0; i<list.length;i++){
			content += "<tr>";
			content += "<td>"+list[i].num+"</td>";
			content += "<td><input class='num_"+(i+1)+" leader' type='text' value='"+list[i].leader+"'/></td>";
			content += "<td><input class='num_"+(i+1)+" member1' type='text' value='"+list[i].member1+"'/></td>";
			content += "<td><input class='num_"+(i+1)+" member2' type='text' value='"+list[i].member2+"'/></td>";
			content += "<td><input class='num_"+(i+1)+" member3' type='text' value='"+list[i].member3+"'/></td>";
			content += "<td><input class='num_"+(i+1)+" member4' type='text' value='"+list[i].member4+"'/></td>";
			content += "<td><input class='num_"+(i+1)+" member5' type='text' value='"+list[i].member5+"'/></td>";
			content += "</tr>";
		}
		
		$('#list').empty();
		$('#list').append(content);
	}
</script>
</html>