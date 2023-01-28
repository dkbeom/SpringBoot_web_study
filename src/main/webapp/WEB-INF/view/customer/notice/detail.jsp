<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<main>
	<h2 class="main title">공지사항</h2>

	<div class="breadcrumb">
		<h3 class="hidden">breadlet</h3>
		<ul>
			<li>home</li>
			<li>고객센터</li>
			<li>공지사항</li>
		</ul>
	</div>

	<div class="margin-top first">
		<h3 class="hidden">공지사항 내용</h3>
		<table class="table">
			<tbody>
				<tr>
					<th>제목</th>
					<td class="text-align-left text-indent text-strong text-orange"
						colspan="3">${n.title}</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td class="text-align-left text-indent" colspan="3"><fmt:formatDate
							pattern="yyyy-MM-dd" value="${n.regdate}" /></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${n.writer_id}</td>
					<th>조회수</th>
					<td>${n.hit}</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3" style="text-align: left; text-indent: 10px;">
						<a href="images/${n.fileUUID}">${fileName}</a>
					</td>
				</tr>
				<tr class="content">
					<td colspan="4">${n.content}</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div class="margin-top text-align-center">
		<a class="btn btn-list" href="list">목록</a>
		<c:if test="${not empty loginSession and loginSession.code ne '0'}">
			<button id="commentButton" class="btn-text btn-default">댓글쓰기 open</button>
		</c:if>
	</div>

	<form action="?id=${param.id}" method="post">
		<input type="hidden" name="noticeId" value="${param.id}" />
		<input type="hidden" name="commentWriter" value="${loginSession.nickname}" />
		<table class="table" style="margin-top: 30px; border-collapse: collapse;">
			<tbody>
				<!----------------------------------------- 기존 댓글란 ----------------------------------------->
				<c:forEach var="c" items="${cmt}">
					<tr>
						<td style="width: 100px;">${c.writer_id}</td>
						<td class="expand" style="text-align: left; padding: 10px 30px;">${c.content}</td>
						<td style="width: 150px;">
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${c.regdate}" />
						</td>
					</tr>
				</c:forEach>
				<!----------------------------------------- 댓글 쓰기 ----------------------------------------->
				<c:if test="${not empty loginSession and loginSession.code ne '0'}">
					<tr id="writeComment" style="height:40px; border-top:2px solid skyblue; display:none;">
						<th style="width: 100px;">${loginSession.nickname}</th>
						<th class="expand">
							<input type="text" name="content" placeholder="댓글 추가..." style="width: 100%; height: 30px;" />
						</th>
						<th style="width: 150px;">
							<input type="submit" class="btn-text btn-default" value="게시" />
						</th>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>

	<div class="margin-top">
		<table class="table border-top-default">
			<tbody>
				<tr>
					<th>다음글</th>
					<td colspan="3" class="text-align-left text-indent"><a
						href="detail?id=${next.id}">${next.title}</a></td>
				</tr>
				<tr>
					<th>이전글</th>
					<td colspan="3" class="text-align-left text-indent"><a
						class="text-blue text-strong" href="detail?id=${prev.id}">${prev.title}</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</main>