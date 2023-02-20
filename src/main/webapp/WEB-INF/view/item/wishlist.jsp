<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 등록</title>
</head>

<main>
	<div>
		<h2 class="main title">찜 리스트</h2>

		<form action="" method="POST">
			<table>
				<thead>
					<tr>
						<td colspan="">상품(${상품수})</td>
					</tr>
				</thead>
                	
				<tbody>
					<c:forEach var="n" items="${상품리스트}">
						<tr>
							<td>
								<input type="checkbox" name="" value="${n.id}" />
							</td>
							<td>
								<img src="" alt="" />
							</td>
							<td>
								${상품 이름}
							</td>
							<td>
								<button type="submit">장바구니 담기</button>
								<button type="submit">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		
	</div>
</main>

</html>