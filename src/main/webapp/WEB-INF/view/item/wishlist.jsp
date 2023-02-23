<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찜 리스트</title>
<style>
	table {
		width: 1000px;
		/* border: 2px dashed blue; */
	}
	
	table td {
		border: 1px dashed tomato;
	}
	
	#table-title {
		/* border: 1px dashed tomato; */
		font-size: 30px;
		text-align: center;
	}

	#item-image {
		max-width: 200px;
		height: 100px;
	}
	
	#button-container {
		
	}
	
	.cart-button {
		display: block;
		width: 100px;
		margin: 10px auto;
	}
	
	.delete-button {
		display: block;
		width: 100px;
		margin: 10px auto;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).bind('ready', function(){
		
		// 장바구니 담기 버튼을 눌렀을 때
		$('.cart-button').click(function(){
			$.ajax({
				url: '/item/putCart',
				method: 'POST',
				data: {
						item_id: this.value,
						member_id: '${loginSession.id}'
					},
				success: function(){
					alert('장바구니 담기 성공')
				},
				error: function(){
					alert('장바구니 담기 오류');
				}
			});
		});
		
	});
</script>

</head>

<main>
	<div>
		<h2 class="main title">찜 리스트</h2>

		<form action="" method="POST">
			<table>
				<colgroup>
					<col style="width: 30px;" />
					<col style="width: 200px;" />
					<col style="font-size: 100px;" />
					<col style="width: 150px;" />
				</colgroup>
				
				<thead>
					<tr>
						<td id="table-title" colspan="4">상품(${count})</td>
					</tr>
					<tr>
						<td colspan="4">
							<button name="deleteSelected" value="true">선택삭제</button>
						</td>
					</tr>
				</thead>
                	
				<tbody>
					<c:forEach var="w" items="${wishViewList}">
						<tr>
							<td>
								<input type="checkbox" name="select" value="${w.item_id}" />
							</td>
							<td>
								<img src="/file/display/${w.imageUUID}" alt="상품 이미지"
									id="item-image" />
							</td>
							<td>
								${w.name}
							</td>
							<td id="button-container">
								<button class="cart-button" type="button" value="${w.item_id}">장바구니 담기</button>
								<button class="delete-button" type="submit" name="delete" value="${w.item_id}">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		
	</div>
</main>

</html>