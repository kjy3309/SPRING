<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">		
		<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>    
		<!-- <script src="resources/js/jquery.twbsPagination.js" type="text/javascript"></script> -->
		<style>
			b{
				color:red;
			}

			table{
				width:100%;
			}
			
			table, td, th{
				border : 1px solid;
				border-collapse : collapse;
				padding: 5px;
			}
			
			#paging{
				text-align: center;
			}
		</style>
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
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
			</thead>
			<tbody id="list">			
				<!-- 리스트가 출력될 영역 -->
			</tbody>
			<tr>
				<td colspan="6" id="paging">	
				<!-- 	플러그인 사용	(twbsPagination)			
					<div class="container">					
									
						<nav aria-label="Page navigation" style="text-align:center">
							<ul class="pagination" id="pagination"></ul>
						</nav>					
					</div>
				-->	
				</td>
			</tr>
		</table>		
	</body>
	<script>
	var showPage = 1;//기본 페이지
	var url;
	var data;
	
	listCall(showPage);//리스트 호출
		
	$("#pagePerNum").change(function(){//페이지당 보여줄 갯수를 변경 하면 바로 리스트를 다시 호출 해 온다.
		listCall(showPage);
	});
	
	function listCall(page){
		
		url="./list"
		data={};
		data.page = page;
		data.cnt = $("#pagePerNum").val();		
		/*	@pathVariable 방식은 각자 구현 해 보자
		url += "/"+$("#pagePerNum").val();
		url += "/"+page;
		console.log(url);
		*/
		ajaxCall(url,data);
	}
	
	function ajaxCall(reqUrl, reqData){
		console.log(reqUrl, reqData);
		$.ajax({
			url:reqUrl,
			type:"post",
			data:reqData,
			dataType:"json",
			success:function(d){	
				console.log("총 보여줄 페이지 : "+d.range);
				showPage = d.currPage;
				listPrint(d.list);
				printPaging(d.range);//플러그인 미사용 페이징 그리기
				
				/*플러그인 사용
				$('#pagination').twbsPagination({
					startPage: d.currPage,	//시작 페이지
					totalPages: d.range,	//총 페이지 갯수
					visiblePages: 5,	//보여줄 페이지 수
					onPageClick: function (event, page) {
						console.log(event);
						console.log(page);
						listCall(page);
					}
				});
				*/
			},
			error:function(e){
				console.log(e);
			}
		});
	}
	
	//리스트 그리기
	function listPrint(list){			
		var content="";					
		for(var i=0; i<list.length; i++){
			content+="<tr>";
			content+="<td>"+list[i].idx+"</td>";
			content+="<td>";
			if(list[i].fileName !=null){
				content +="<img width='15px' src='resources/img/default.png'/>";
			}
			content+="<a href='./detail?idx="+list[i].idx+"'>"
					+list[i].subject+"</a></td>";
			content+="<td>"+list[i].user_name+"</td>";
			/* 만약 아래 내용이 milisconds 로 나타난다면 ...
			content+="<td>"+list[i].reg_date+"</td>";
			*/
			var date = new Date(list[i].reg_date);
			//console.log(date.toLocaleDateString("ko-KR"));//en-US
			
			content+="<td>"+date.toLocaleDateString("ko-KR")+"</td>";	
			content+="<td>"+list[i].bHit+"</td>";;	
			content+="</tr>";
		}						
		$("#list").empty();
		$("#list").append(content);			
	}
	
	// 일반 페이징 방식		
	function printPaging(pageNum){
		console.log("생성 가능 페이지 :"+pageNum );
		console.log("현재 페이지 :"+showPage);
		
		$("#paging").empty();
		//1 2 3 4 5
		var start = 1;	//페이지 시작
		var end = 5;	//페이지 끝

		var content = "";
		
		if(showPage >5){//현재 페이지가 5를 넘었을 경우
			//단순히 4씩 더하면 뒷 페이지가 한칸씩 뒤로 물러나기만 한다.
			//소숫점을 올리고 * 5(페이징 단위가 넘어가는 시점)
			end = Math.ceil(showPage/5)*5;
			start = end-4;
			content +="<a href='#' onclick='listCall("+(start-1)+")'>이전</a> | ";
		}	
		
		//페이징 부분
		for(var i=start; i<=end;i++){
			if(i<=pageNum){//i 는 절대 생성 가능 페이지 보다 클 수 없다.
				if(showPage ==i){//선택된 페이지 일 경우
					content +="<b>"+i+"</b>";
				}else{
					content += " <a href='#' onclick='listCall("+i+")'>"
					+i+"</a> "
				}					
			}			
		}
		
		//마지막 페이지가 전체 페이지 수 보다 적으면 다음 링크 생성
		if(end<pageNum){
			content +=" | <a href='#' onclick='listCall("+(end+1)+")'>다음</a> "
		}
		
		$("#paging").append(content);
		
	}
	
	
	
	
	</script>
</html>