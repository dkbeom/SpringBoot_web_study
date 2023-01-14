<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<main class="main">
	<h2 class="main title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">경로</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>

	<div class="search-form margin-top first align-right">
		<h3 class="hidden">공지사항 검색폼</h3>
		<form class="table-form">
			<fieldset>
				<legend class="hidden">공지사항 검색 필드</legend>
				<label class="hidden">검색분류</label>
				<select name="f">
					<option value="title" <c:if test="${param.f == 'title'}">selected</c:if> >제목</option>
					<option value="writer_id" <c:if test="${param.f == 'writer_id'}">selected</c:if> >작성자</option>
				</select>
				<label class="hidden">검색어</label>
				<input type="text" name="q" value="" />
				<input class="btn btn-search" type="submit" value="검색" />
			</fieldset>
		</form>
	</div>

	<div class="notice margin-top">
		<h3 class="hidden">공지사항 목록</h3>
		<table class="table">
			<thead>
				<tr>
					<th class="w60">번호</th>
					<th class="expand">제목</th>
					<th class="w100">작성자</th>
					<th class="w100">작성일</th>
					<th class="w60">조회수</th>
					<th class="w60">댓글수</th>
				</tr>
			</thead>
			<tbody>

				<!-- 공지사항 목록(forEach문 반복) -->
				<c:forEach var="n" items="${list}">
					<tr>
						<td>${n.id}</td>
						<td class="title indent text-align-left"><a href="detail?id=${n.id}">${n.title}</a></td>
						<td>${n.writer_id}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate}"/></td>
						<td>${n.hit}</td>
						<td>${n.cmt_count}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<!------------------------------ page 관련 변수 ------------------------------>
	<!-- 현재 주변 page 번호 매길 때 가장 앞에 있는 page 번호 -->
	<c:set var="startPage" value="${page-(page-1)%5}" />
	<!-- 전체 page 중에 마지막 page 번호 -->
	<c:set var="lastPage" value="${fn:substringBefore( (count-count%10)/10+1, '.' )}" />

	<div class="indexer margin-top align-right">
		<h3 class="hidden">현재 페이지</h3>
		<div>
			<span class="text-orange text-strong">${page}</span> / ${lastPage} pages
		</div>
	</div>

	<div class="margin-top align-center pager">

		<!-- 이전 페이지 버튼 -->
		<div>
			<c:if test="${page-1 lt 1}">
				<span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
			</c:if>
			<c:if test="${page-1 ge 1}">
				<a class="btn btn-prev" href="?p=${page-1}&f=${param.f}&q=${param.q}">이전</a>
			</c:if>
		</div>
		
		<!-- 현재 주변 page 번호 -->
		<c:forEach var="i" begin="0" end="4">
			<c:if test="${startPage+i le lastPage}">
				<ul class="-list- center">
					<li><a class="-text- bold ${page == startPage+i ? 'orange' : ''}" href="?p=${startPage+i}&f=${param.f}&q=${param.q}">${startPage+i}</a></li>
				</ul>
			</c:if>
		</c:forEach>
		
		<!-- 다음 페이지 버튼 -->
		<div>
			<c:if test="${page+1 gt lastPage}">
				<span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
			</c:if>
			<c:if test="${page+1 le lastPage}">
				<a class="btn btn-next" href="?p=${page+1}&f=${param.f}&q=${param.q}">다음</a>
			</c:if>
		</div>

	</div>
</main>