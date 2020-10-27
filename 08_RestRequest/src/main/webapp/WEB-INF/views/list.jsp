<!-- ctrl+shift+R : 이름으로 리소스 찾기 -->
<!-- ctrl+shift+alt+L : 특정 구문이 있는 내용 찾기(퀵서치) -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	table{
		width: 600px;
	}

	table, th, td{
		border: 1px solid black;
		border-collapse: collapse;
	}
	
	th, td{
		padding: 5px;
	}
</style>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
<script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	게시물 갯수 : 
	<select id="pagePerNum">
		<option value="5">5</option>
		<option value="10">10</option>
		<option value="15">15</option>
		<option value="20">20</option>
	</select>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody id="boardList">
		<!-- 리스트 출력 -->
		</tbody>
		<tr>
			<td id="paging" colspan="5" style="text-align:center;">
				<div class="container">
					<nav arial-label="Page navigation" style="text-align:center">
						<ul class="pagination" id="pagination"></ul>
					</nav>
				</div>
			</td>
		</tr>
	</table>
</body>
<script>
	
	listCall(1);
	
	$('#pagePerNum').change(function(){ // 페이지당 갯수가 변경되면 다시 호출
		$('#pagination').twbsPagination('destroy');
		listCall(1);
	});

	function listCall(page){
		var url="list/";
		var ppn = $("#pagePerNum").val();
		
		url += ppn+"/"+page;
		
		console.log(url);
		$.ajax({
			url:url
			,type:'get'
			,dataType:'json'
			,success:function(data){
				console.log(data);
				listPrint(data.list); // 게시물 그리기
				//pagingPrint(data)// 페이징 처리(복잡한 방식)
				// 플러그인 사용
				$('#pagination').twbsPagination({
					startPage: data.currPage, // 시작 페이지
					totalPages: data.range, // 만들 수 있는 총 페이지 수
					visiblePages: 5, // 보여줄 페이지 수
					onPageClick:function(event, page){
						console.log(event);
						console.log(page);
						listCall(page);
					}
				});
			}
			,error:function(e){
				console.log(e);
			}
		});
	}
	
	function pagingPrint(data){
		// 총 만들 수 있는 페이지까지 찍는 일
		var start = 1; // 페이징 그룹 시작
		var end = 5;
		var content = "";
		
		// paging 은 5 단위로 보여준다.
		// 현재 페이지가 5를 넘어가면 이전 버튼이 생성
		if(data.currPage>5){
			// data.currPage 를 가지고 페이징 그룹의 start 와 end 를 구해야 한다.
			console.log(Math.ceil(data.currPage/ppn));
			end = Math.ceil(data.currPage/5)*5;
			start = end-4;
			
			content += " <a href='#' onclick='listCall("+(start-1)+")'>이전</a>";
		}
		
		// <이전 6 7 8 9 10 다음>
		for(var i = start; i<=end; i++){
			if(i<=data.range){
				if(i == data.currPage){
					content += " <b style='color:blue'>"+i+"</b>";
				}else{
					content += " <a href='#' onclick='listCall("+i+")'>"+i+"</a>";
				}
			}
			
		}
		
		// 다음
		if(end<data.range){
			content += " <a href='#' onclick='listCall("+(end+1)+")'>다음</a>";
		}
		
		// 다음 버튼은 언제 만드냐? -> 페이징 그룹이 마지막이 아닐 때. end 가 마지막 페이지(41) 보다 작으면 
		
		$('#paging').empty();
		$('#paging').append(content);
	}
	
	function listPrint(list){
		console.log(list);
		var content = "";
		list.forEach(function(item){
			content += "<tr>";
			content += "<td>"+item.idx+"</td>";
			content += "<td>"+item.subject+"</td>";
			//content += "<td>"+item.reg_Date+"</td>"; //miliseconds 단위로 표시됨
			var date = new Date(item.reg_Date);
			console.log(date.toLocaleDateString("ko-KR"));
			content += "<td>"+date.toLocaleDateString("ko-KR")+"</td>";
			content += "<td>"+item.user_name+"</td>";
			content += "<td>"+item.bHit+"</td>";
			content += "</tr>";
		});
		$('#boardList').empty();
		$('#boardList').append(content);
		var content = "";
	}
</script>
</html>