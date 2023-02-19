<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>

	#search-form label {
		border-right: 1px solid gray;
	}

	#shopping-menu {
		position: absolute;
		right: 150px;
		padding-top: 15px;
		display: flex;
	}
	
	#shopping-menu a {
		text-align: center;
		margin-right: 10px;
	}
	
	#shopping-menu a img {
		display: block;
		height: 30px;
		margin: 0 auto;
	}
	
	#shopping-menu span {
		font-family: "맑은 고딕", "고딕", sans-serif;
		font-size: 10px;
	}
</style>

<header id="header">

	<div class="content-container">
		<!-- ---------------------------<header>--------------------------------------- -->

		<h1 id="logo">
			<!-- <a href="/index.html"> -->
			<a href="/index">
				<img src="/images/myImages/shopping-day-purple.png" alt="뉴렉처 온라인"
				style="width: 182px; height: 35px;" />
			</a>
		</h1>

		<section>
			<!-- <h1 class="hidden">헤더</h1> -->

			<div class="sub-menu">

				<section id="search-form">
					<h1>검색 폼</h1>
					<form action="/course">
						<fieldset>
							<legend>검색필드</legend>
							<label>검색</label>
							<input type="text" name="q" value="" placeholder="찾고 싶은 상품을 검색해보세요!"/>
							<input type="submit" value="검색" />
						</fieldset>
					</form>
				</section>
				

				<div id="shopping-menu">
					<c:if test="${not empty loginSession and loginSession.code eq 0}">
						<a href="/item/regItem">
							<img src="/images/myImages/reg-item.png" alt="상품 등록" />
							<span>상품 등록</span>
						</a>
					</c:if>
					<a href="">
						<img src="/images/myImages/wish-list.png" alt="찜 리스트"
							id="wish-list"/>
						<span>찜 리스트</span>
					</a>
					<a href="">
						<img src="/images/myImages/shopping-cart.png" alt="장바구니"
							id="shopping-cart" />
						<span>장바구니</span>
					</a>
				</div>

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
							<c:if test="${empty loginSession}">
								<a href="/member/login">로그인</a>
							</c:if>
							<c:if test="${not empty loginSession}">
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