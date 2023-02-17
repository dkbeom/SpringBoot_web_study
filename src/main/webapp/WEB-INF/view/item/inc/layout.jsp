<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>상품</title>

	<link href="/css/admin/layout.css" type="text/css" rel="stylesheet" />
	<style>
		#visual .content-container {
			height: inherit;
			display: flex;
			align-items: center;
			background: url("/images/mypage/visual.png") no-repeat center;
		}
	</style>
	<script defer src="/js/notice.js"></script>
</head>

<body>
	<!-- header 부분 -->

	<tiles:insertAttribute name="header" />

	<!-- --------------------------- <visual> --------------------------------------- -->
	<!-- visual 부분 -->
	<%-- <tiles:insertAttribute name="visual" /> --%>

	<!-- --------------------------- <body> --------------------------------------- -->
	<div id="body">
		<div class="clearfix">

			<!-- --------------------------- aside --------------------------------------- -->
			<!-- aside 부분 -->

			<%-- <tiles:insertAttribute name="aside" /> --%>

			<!-- --------------------------- main --------------------------------------- -->

			<tiles:insertAttribute name="main" />




		</div>
	</div>

	<!-- ------------------- <footer> --------------------------------------- -->

	<tiles:insertAttribute name="footer" />


</body>

</html>