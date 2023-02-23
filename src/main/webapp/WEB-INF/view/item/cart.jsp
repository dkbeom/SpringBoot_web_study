<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>

<style>
	table {
		width: 1000px;
		/* border: 2px dashed blue; */
	}
	
	table td {
		border: 1px dashed tomato;
		text-align: center;
	}
	
	#table-title {
		/* border: 1px dashed tomato; */
		font-size: 30px;
	}

	.item-image {
		max-width: 200px;
		height: 100px;
	}
	
	.quantity-box {
		border: 1px dashed green;
		display: flex;
	}
	
	.quantity-name {
		border: 1px dashed orange;
		margin-left: 20px;
		font-size: 15px;
	}
	
	.quantity-controller {
		display: flex;
	}
	
	.quantity-controller .quantity {
		width: 30px;
		text-align: center;
		margin-left: 30px;
		font-size: 20px;
	}
	
	.quantity-controller button {
		display: block;
		height: 20px;
	}
	
	.quantity-up img {
		height: 10px;
	}
	
	.quantity-down img {
		height: 10px;
	}
	
	.button-container {
		
	}
	
	.delete-button {
		display: block;
		width: 100px;
		margin: 10px auto;
	}
	
	#total-amount-box {
		height: 150px;
		font-size: 30px;
	}
	
	#total-amount {
		color: red;
		font-weight: 1000;
	}
	
	#order-buttons {
		border: 3px dashed red;
		width: 1000px;
		height: 200px;
		text-align: center;
		
	}
	
	#goShopping {
		width: 250px;
		height: 60px;
		margin: 70px 20px;
		border: 2px solid purple;
		cursor: pointer;
	}
	
	#goPayment {
		width: 250px;
		height: 60px;
		margin: 70px 20px;
		border: 2px solid skyblue;
		cursor: pointer;
	}
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).bind('ready', function(){
		
		function insertTotalAmount(){
			const priceArr = $('.price');
			const quantityArr = $('.quantity');
			let totalAmount = 0;
			for(let i = 0; i < priceArr.length; i++){
				totalAmount += Number(priceArr[i].innerText) * Number(quantityArr[i].value);
			}
			// 총 상품금액 수정
			$('#total-amount').text(totalAmount);
		}
		
		// 상품 수량 직접 입력
		$('.quantity').change(function(){
			// 가격 x 수량
			const amount = Number($(this).closest('td').prev('.price-box').children('.price').text()) * Number($(this).val());
			
			// 상품금액 수정
			$(this).closest('td').nextAll('.total-price').children('.amount').text(amount);
			
			// 총 상품금액 수정
			insertTotalAmount();
		});
		
		// 상품 수량 올리기
		$('.quantity-up').click(function(){
			
			// 가격 가져오기
			const price = Number($(this).closest('td').prev('.price-box').find('.price').text());
			
			// 수량 갯수 input 값 올리기
			$(this).parent('div').prev('.quantity').val(Number($(this).parent('div').prev('.quantity').val())+1);
			
			// 바뀐 수량 가져오기
			const quantity = Number($(this).parent('div').prev('.quantity').val());
			
			// 상품금액(가격 * 수량) 수정
			$(this).closest('td').siblings('.total-price').children('.amount').html(price*quantity);
			
			// 총 상품금액 수정
			insertTotalAmount();
		});
		
		// 상품 수량 내리기
		$('.quantity-down').click(function(){
			if(Number($(this).parent('div').prev('.quantity').val()) > 1){
				
				// 가격 가져오기
				const price = Number($(this).closest('td').prev('.price-box').find('.price').text());
				
				// 수량 갯수 input 값 올리기
				$(this).parent('div').prev('.quantity').val(Number($(this).parent('div').prev('.quantity').val())-1);
				
				// 바뀐 수량 가져오기
				const quantity = Number($(this).parent('div').prev('.quantity').val());
				
				// 상품금액(가격 * 수량) 수정
				$(this).closest('td').siblings('.total-price').children('.amount').html(price*quantity);
				
				// 총 상품금액 수정
				insertTotalAmount();
			}
		});
		
		
		
	});
</script>
</head>

<main>
	<div>
		<h2 class="main title">장바구니</h2>

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
						<td id="table-title" colspan="7">구매(${count})</td>
					</tr>
					<tr>
						<td colspan="2">
							<button name="deleteSelected" value="true">선택삭제</button>
						</td>
						<td colspan="4">
							상품정보
						</td>
						<td>
							상품금액
						</td>
					</tr>
				</thead>
				
				<!-- 총 상품금액을 저장할 변수 -->
				<c:set var="totalAmount" value="" />
				
				<tbody>
					<c:forEach var="c" items="${cartViewList}">
						<tr>
							<td>
								<input type="checkbox" name="select" value="${c.item_id}" />
							</td>
							<td>
								<img src="/file/display/${c.imageUUID}" alt="상품 이미지"
									class="item-image" />
							</td>
							<td>
								${c.name}
							</td>
							<td class="price-box">
								<span>가격:</span>
								<span class="price">${c.price}</span>
							</td>
							<td>
								<div class="quantity-box">
									<div class="quantity-name">수량: </div>
									<div class="quantity-controller">
	        							<input class="quantity" type="text" value="${c.quantity}"/>
					        			<div>
        									<button class="quantity-up" type="button">
	        									<img src="/images/myImages/arrow-up.png" alt="위쪽 화살표" />
        									</button>
        									<button class="quantity-down" type="button">
	        									<img src="/images/myImages/arrow-down.png" alt="아래쪽 화살표" />
        									</button>
        								</div>
        							</div>
								</div>
							</td>
							<td class="button-container">
								<button class="delete-button" type="submit" name="delete" value="${c.item_id}">삭제</button>
							</td>
							<td class="total-price">
								<span class="amount">${c.quantity * c.price}</span>
								<span>원</span>
							</td>
						</tr>
						<c:set var="totalAmount" value="${totalAmount + (c.quantity * c.price)}" />
					</c:forEach>
					<tr>
						<td id="total-amount-box" colspan="7">
							<span>총 주문금액</span>
							<span id="total-amount">${totalAmount}</span>
							<span>원</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div id="order-buttons">
			<button id="goShopping">계속 쇼핑하기</button>
			<button id="goPayment">구매하기</button>
		</div>
	</div>
</main>

</html>