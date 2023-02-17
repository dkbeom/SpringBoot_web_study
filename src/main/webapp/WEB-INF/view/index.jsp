<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

	.banner1 {
		border-bottom: 2px solid green;
		overflow: hidden;
		text-align: center;
	}
	
	.banner1 img {
		cursor: pointer;
		width: 100%;
	}

	main {
		text-align: center;
	}

	.items-table {
		display: inline-block;
		/* border: 3px dashed blue; */
	}

	.items-table .items-row {
		/* border: 1px dashed red; */
		width: 960px;
	}

	.items-table .items-row .item {
		padding: 20px;
		border: 2px solid green;
		width: 240px;
		text-align: center;
		cursor: pointer;
	}
	
	.items-table .items-row .item .item-image {
		width: 180px;
		height: 200px;
	}
	
	.items-table .items-row .item .item-info-wrap {
		
	}
	
	.items-table .items-row .item .item-info-wrap .item-name {
		font-size: 20px;
		color: black;
	}
	
	.items-table .items-row .item .item-info-wrap .item-price {
		font-size: 30px;
		color: #A7383E;
	}
	
	.items-table .items-row .item .item-info-wrap .item-review {
		font-size: 15px;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).bind('ready', function(){
		$('.item').hover(function(){
			
		});
	});
</script>

<!-- --------------------------- <body> --------------------------------------- -->

<!-- content 부분 -->

<div id="visual" class="">
	<div class="content-container">
	
		<ul class="banner">
			<li class="banner1">
				<a href="customer/event/1">
					<!-- <img src="/images/myImages/index_banner.png" data-id="1" style="cursor: pointer; height: 100%;" /> -->
					<img src="/images/myImages/visual-logo.png" data-id="1" />
				</a>
			</li>
		</ul>
		
	</div>
</div>
<!-- ----- 공지사항 줄 ------------------------------------------------------------------------------ -->
<div id="information">
	<div class="content-container">
	
		<!------------------ 배너 아래줄에서 왼쪽 섹션 ------------------>
		<section>
			<h1 class="title">안내</h1>
			<div class="margin-top">
				ㅇㅇㅇㅇㅇ
			</div>
		</section>
		
		<!------------------ 배너 아래줄에서 가운데 섹션 ------------------>
		
		<section class="course-info">
			<h1 class="title text-center">뭘 넣어야 할지</h1>
			<ul class="list">
				<li>ㅋㅋㅋ</li>
			</ul>
		</section>
		
		<!------------------ 배너 아래줄에서 오른쪽 섹션 ------------------>
		
		<section class="notice">
			<h1 class="title">
				<a href="${loginSession.code == '0' ? 'admin/board/notice/list' : 'customer/notice/list'}">
					공지사항
				</a>
			</h1>
			
			<ul class="list margin-top">
				<c:forEach var="n" items="${noticeList}" begin="0" end="4">
					<li>
						<span class="notice-title">
							<c:if test="${loginSession.code == '0'}">
								<a href="admin/board/notice/detail?id=${n.id}">${n.title}</a>
							</c:if>
							<c:if test="${loginSession.code != '0'}">
								<a href="customer/notice/detail?id=${n.id}">${n.title}</a>
							</c:if>
						</span>
						<span>
							<fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate}" />
						</span>
					</li>
				</c:forEach>
			</ul>
		</section>
	</div>
</div>

<!-------- 상품 목록 --------------------------------------------------------------------------------------------------------- -->
<main id="course">
	<table class="items-table">
		<!-- numOfItem = 0 일때 상황 따로 만들어줘야함 -->
		<c:forEach var="row" begin="0" end="${(numOfItem-1)/4}">
			<!-- 상품 목록에서 한 줄(상품 4개씩 한줄) -->
			<tr class="content-container items-row">
			
				<c:forEach var="i" begin="${(row*4)}" end="${(row*4)+3}">
					<!-- 상품 한개 -->
					<c:if test="${i lt numOfItem}">
					<td class="item" onclick="location.href='/item/detail?id=${itemList[i].id}';">
						<img class="item-image" src="/file/display/${itemList[i].imageUUID}" alt="상품" />
						<div class="item-info-wrap">
							<div class="item-name">
								<span>${itemList[i].name}</span>
							</div>
							<div class="item-price">
								<span>${itemList[i].price}원</span>
							</div>
							<div class="item-review">
								<span>별점 3.5</span>
							</div>
						</div>
					</td>
					</c:if>
				</c:forEach>
				
			</tr>
		</c:forEach>
	</table>
</main>