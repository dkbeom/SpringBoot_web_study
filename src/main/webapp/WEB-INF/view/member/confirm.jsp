<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main>
	<h2 class="main title">가입확인</h2>

	<div class="breadcrumb" style="margin-top: -20px;">
		<h3 class="hidden">경로</h3>
		<img src="/images/member/step3.png" alt="회원가입 3단계" />
	</div>

	<div class="margin-top first"
		style="background: url('/images/member/bg-login.png') no-repeat center; height: 277px;">
		<div class="text-align-center">
			<img style="margin-left: 170px; margin-top: 70px;"
				alt="뉴렉처의 회원가입이 완료되었습니다."
				src="/images/member/txt-join-welcome.png">
		</div>
		<div class="text-align-center">
			<a class="btn"
				style="margin-left: 170px; margin-top: 50px; display: inline-block; width: 83px; height: 53px; background: url('../images/member/btn-login.png') no-repeat center;"
				href="login">메인페이지로 이동합니다.</a>
		</div>
	</div>
</main>