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
		font-size: 20px;
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
	
	.quantity::-webkit-outer-spin-button,
	.quantity::-webkit-inner-spin-button {
		-webkit-appearance: none;
    	-moz-appearance: none;
    	appearance: none;
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
	
	.goShopping {
		width: 250px;
		height: 60px;
		margin: 70px 20px;
		border: 2px solid purple;
		cursor: pointer;
	}
	
	.goPayment {
		width: 250px;
		height: 60px;
		margin: 70px 20px;
		border: 2px solid skyblue;
		cursor: pointer;
	}
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
	$(document).bind('ready', function(){
		
		// 페이지 로딩 직후, 총 상품금액 표시
		insertTotalAmount();
		
		// 총 상품금액 insert하는 함수
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
		
		
		// 상품 수량이 바뀔 때
		$('.quantity').on('propertychange change keyup paste input', function(){
			
			// 유효성 검사를 위한 정규표현식
			const quantityCheck = /[1-9][0-9]*/;
			// 입력한 값이 유효성 검사에 맞지 않은 경우
			if(!quantityCheck.test($(this).val())){
				$(this).val('');
				return false;
			}
			
			console.log($(this).val());
			
			// DB에 있는 Cart 수량 수정
			$.ajax({
				url: '/item/updateCartQuantity',
				method: 'GET',
				data: {
					itemId: Number($(this).prev('.itemId').val()),
					memberId: '${loginSession.id}',
					quantity: Number($(this).val())
				},
				success: function(){
					console.log('상품 수량 수정 성공');
				},
				error: function(){
					console.log('상품 수량 수정 실패');
				}
			});
			
			// 가격 * 수량
			const amount = Number($(this).closest('td').prev('.price-box').children('.price').text()) * Number($(this).val());
			
			// 상품금액 수정
			$(this).closest('td').nextAll('.total-price').children('.amount').text(amount);
			
			// 총 상품금액 수정
			insertTotalAmount();
		});
		
		
		// 수량 조절 버튼(위,아래) 누를 때마다 실행되는 함수
		function quantityControll(c){
			// 가격 가져오기
			const price = Number($(this).closest('td').prev('.price-box').find('.price').text());
			
			// 수량 갯수 input 값 올리기
			$(this).parent('div').prev('.quantity').val(Number($(this).parent('div').prev('.quantity').val())+c);
			
			// 바뀐 수량 가져오기
			const quantity = Number($(this).parent('div').prev('.quantity').val());
			
			// 상품금액(가격 * 수량) 수정
			$(this).closest('td').siblings('.total-price').children('.amount').html(price*quantity);
			
			// 총 상품금액 수정
			insertTotalAmount();
			
			// DB에 있는 Cart 수량 수정
			$.ajax({
				url: '/item/updateCartQuantity',
				method: 'GET',
				data: {
					itemId: Number($(this).closest('.quantity-controller').children('.itemId').val()),
					memberId: '${loginSession.id}',
					quantity: Number($(this).closest('.quantity-controller').children('.quantity').val())
				},
				success: function(){
					console.log('상품 수량 수정 성공');
				},
				error: function(){
					console.log('상품 수량 수정 실패');
				}
			});
		}
		
		// 상품 수량 올리기
		$('.quantity-up').click(function(){
			quantityControll.call(this, 1);
		});
		
		// 상품 수량 내리기
		$('.quantity-down').click(function(){
			if(Number($(this).parent('div').prev('.quantity').val()) > 1){
				quantityControll.call(this, -1);
			}
		});
		
		// 구매하기 버튼을 눌렀을 때
	    $('.goPayment').click(function(){
	    	
	    	// 로그인 하지 않았는데, 구매하기 버튼을 누르면 안되게 막음
	    	if(${empty loginSession}){
	    		// 알림을 띄움
	    		alert("로그인이 필요한 서비스입니다");
				// 다음 이벤트를 막음
				return false;
	    	}
	    	
	    	// 상품 이름 합치기
	    	let itemName = '';
	    	$('.item-name').each(function(index, item){
	    		itemName += item.innerHTML.trim()+' ';
	    	});
	    	
			// 주문 생성
			const order = {
		    		merchant_uid: 'merchant_' + new Date().getTime(),
		    		pg_provider: 'html5_inicis',
		    		pay_method: 'card',
		    		name: itemName,
		    		amount: Number($('#total-amount').text()),
		    		buyer_email: '${loginSession.email}',
		    		buyer_name: '${loginSession.name}',
		    		buyer_tel: '${loginSession.phone}',
		    		buyer_addr: '${loginSession.address}'
		    	};
	    	// 주문 레코드 추가를 위한 요청
	    	$.ajax({
	    		url: '/payment/iamport/insertOrder',
	    		data: order,
	    		method: 'POST',
	    		success: function(data){
	    			if(data){
	    				console.log('주문 레코드 추가 성공');
	    			}
	    		},
	    		error: function(data){
	    			if(!data){
	    				console.log('주문 레코드 추가 실패');
	    			}
	    		}
	    	}).done(function(){
	    	
	        var IMP = window.IMP; // 생략가능
	        IMP.init('imp60582554'); 
	        // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
	        // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
	        
	        // 결제창 호출
	        // IMP.request_pay(param, callback함수)
	        IMP.request_pay({
	            pg: order.pg_provider, // version 1.1.0부터 지원.
	            /* 
	                'kakao':카카오페이, 
	                'html5_inicis':이니시스(웹표준결제)
	                'nice':나이스페이
	                'jtnet':제이티넷
	                'uplus':LG유플러스
	                'danal':다날
	                'payco':페이코
	                'syrup':시럽페이
	                'paypal':페이팔
	            */
	            pay_method: order.pay_method,
	            /* 
	                'samsung':삼성페이, 
	                'card':신용카드, 
	                'trans':실시간계좌이체,
	                'vbank':가상계좌,
	                'phone':휴대폰소액결제 
	            */
	            merchant_uid: order.merchant_uid,
	            /* 
	                merchant_uid에 경우 
	                https://docs.iamport.kr/implementation/payment
	                위에 url에 따라가면 넣을 수 있는 방법이 있다.
	             */
	            name: order.name,
	            //결제창에서 보여질 이름
	            amount: order.amount,
	            //가격 
	            buyer_email: order.buyer_email,
	            buyer_name: order.buyer_name,
	            buyer_tel: order.buyer_tel,
	            buyer_addr: order.buyer_addr,
	            m_redirect_url: 'http://localhost:8080/payment/iamport',
	            /*  
	                모바일 결제시,
	                결제가 끝나고 랜딩되는 URL을 지정 
	                (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐) 
	            */
	        },	// 결제 후에 실행되는 콜백함수(결제 후, 결제 정보를 검증함)
	        	function (rsp) {
	            	if (rsp.success) {
	            		// jQuery로 HTTP 요청
	               		$.ajax({
	               	    	url: "/payment/iamport/complete", // 예: https://www.myservice.com/payments/complete
	                    	method: "POST",
	                    	//headers: { "Content-Type": "application/x-www-form-urlencoded" },
	                    	data: {
	                        	imp_uid: rsp.imp_uid,
	                        	merchant_uid: rsp.merchant_uid,
	                        	item_name: itemName,
	                        	item_total_price: order.amount
	                    	}
	                	});
	            	
	              	 	var msg = '결제가 완료되었습니다.';
	                	msg += '\n고유ID : ' + rsp.imp_uid;
	                	msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	                	msg += '\n결제 금액 : ' + rsp.paid_amount;
	                	msg += '\n카드 승인번호 : ' + rsp.apply_num;
	                	
	            	} else {
		                var msg = '결제에 실패하였습니다.';
	                	msg += '\n에러내용 : ' + rsp.error_msg;
	            	}
	            	alert(msg);
	        	});
	    	}); // 주문 레코드 추가를 위한 요청 ajax의 .done() 함수의 끝
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
							<td class="item-name">
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
										<input class="itemId" type="hidden" value="${c.item_id}" />
	        							<input class="quantity" type="number" value="${c.quantity}" min="1" />
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
					</c:forEach>
					<tr>
						<td id="total-amount-box" colspan="7">
							<span>총 주문금액</span>
							<span id="total-amount"></span>
							<span>원</span>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div id="order-buttons">
			<button class="goShopping" onclick="location.href='/index'">계속 쇼핑하기</button>
			<button class="goPayment">구매하기</button>
		</div>
		
		<button class="btn">버튼</button>
	</div>
</main>

</html>