<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<header id="header">

		<div class="content-container">
			<!-- ---------------------------<header>--------------------------------------- -->

			<h1 id="logo">
				<!-- <a href="/index.html"> -->
				<a href="/index">
					<img src="/images/logo.png" alt="뉴렉처 온라인" />
				</a>
			</h1>

			<section>
				<h1 class="hidden">헤더</h1>

				<nav id="main-menu">
					<h1>메인메뉴</h1>
					<ul>
						<li><a href="/guide">학습가이드</a></li>
						<li><a href="/course">강좌선택</a></li>
						<li><a href="/answers/index">Answers</a></li>
					</ul>
				</nav>

				<div class="sub-menu">

					<section id="search-form">
						<h1>강좌검색 폼</h1>
						<form action="/course">
							<fieldset>
								<legend>과정검색필드</legend>
								<label>과정검색</label>
								<input type="text" name="q" value="" />
								<input type="submit" value="검색" />
							</fieldset>
						</form>
					</section>

					<nav id="acount-menu">
						<h1 class="hidden">회원메뉴</h1>
						<ul>
							<li>
								<c:if test="${not empty loginSession and loginSession.code eq '0'}">
									<a href="/index">관리자 HOME</a>
								</c:if>
								<c:if test="${not empty loginSession and loginSession.code ne '0'}">
									<a href="/index">일반 회원 HOME</a>
								</c:if>
								<c:if test="${empty loginSession}">
									<a href="/index">HOME</a>
								</c:if>
							</li>
							
							<li>
								<c:if test="${empty loginSession}" >
									<a href="/member/login">로그인</a>
								</c:if>
								<c:if test="${not empty loginSession}" >
									<a href="/member/logout">로그아웃</a>
								</c:if>
							</li>
							
							<li>
								<c:if test="${empty loginSession}">
									<a href="/member/agree">회원가입</a>
								</c:if>
							</li>
						</ul>
					</nav>

					<nav id="member-menu" class="linear-layout">
						<h1 class="hidden">고객메뉴</h1>
						<ul class="linear-layout">
							<li>
								<a href="/member/home">
									<img src="/images/txt-mypage.png" alt="마이페이지" />
								</a>
							</li>
							<li>
								<a href="/notice/list">
									<img src="/images/txt-customer.png" alt="고객센터" />
								</a>
							</li>
						</ul>
					</nav>

				</div>
			</section>

		</div>

	</header>