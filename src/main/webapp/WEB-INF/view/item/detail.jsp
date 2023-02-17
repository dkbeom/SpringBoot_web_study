<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 등록</title>
<style>

	#body {
		
	}

	main {
		width: 1000px;
		margin: 0 auto;
	}
	
	.item-info {
		display: flex;
		width: 1050px;
		border: 2px solid tomato;
	}
	
	.item-img {
		width: 500px;
	}
	
	.item-img img {
		width: inherit;
	}
	
	.item-detail {
		
	}
	
	.item-detail-main {
		height: 450px;
	}
	
	.item-name-favorite-btn {
		display: flex;
		height: 50px;
	}
	
	.item-name {
		border: 1px solid blue;
		flex-grow: 10;
		font-size: 25px;
	}
	
	.favorite-btn img {
		width: 50px;
		border: 1px solid green;
	}
	
	.price {
		color: #A7383E;
		height: 50px;
		border: 1px solid purple;
		font-size: 25px;
	}
	
	.buy-quantity-menu {
		display: flex;
		height: 50px;
	}
	
	.quantity-controller {
		display: flex;
	}
	
	.quantity-controller .quantity {
		width: 60px;
		text-align: center;
		margin-left: 30px;
		font-size: 20px;
	}
	
	.quantity-controller button {
		display: block;
	}
	
	.quantity-up img {
		width: 20px;
	}
	
	.quantity-down img {
		width: 20px;
	}
	
	.buy-menu {
		display: flex;
	}
	
	.buy-menu button {
		margin-left: 40px;
		width: 150px;
	}
	
</style>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script>
	$(document).bind('ready', function(){
		
		
		// 찜 버튼 toggle
		$('.favorite-btn').click(function(){
			if($('.favorite-btn > img').attr('src') === '/images/myImages/empty-heart.png'){
				$('.favorite-btn > img').attr('src', '/images/myImages/full-heart.png');
			}else{
				$('.favorite-btn > img').attr('src', '/images/myImages/empty-heart.png');
			}
		});
		
		// 상품 수량 올리기
		$('.quantity-up').click(function(){
			$('.quantity').attr('value', Number($('.quantity').val())+1);
		});
		
		// 상품 수량 내리기
		$('.quantity-down').click(function(){
			if(Number($('.quantity').attr('value')) > 1){
				$('.quantity').attr('value', Number($('.quantity').val())-1);
			}
		});
		
	    $(".buy").click(function(){
	    	
	    	// 로그인 하지 않았는데, 구매하기 버튼을 누르면 안되게 막음
	    	if(${empty loginSession}){
	    		// 알림을 띄움
	    		alert("로그인이 필요한 서비스입니다");
				// 다음 이벤트를 막음
				return false;
	    	}
	    	
			// 주문 생성
			const order = {
		    		merchant_uid: 'merchant_' + new Date().getTime(),
		    		pg_provider: 'html5_inicis',
		    		pay_method: 'card',
		    		name: '${item.name}',
		    		amount: ${item.price} * Number($('.quantity').val()),
		    		buyer_email: '${loginSession.email}',
		    		buyer_name: '${loginSession.name}',
		    		buyer_tel: '${loginSession.phone}',
		    		buyer_addr: '서울특별시 강남구 삼성동',
		    		buyer_postcode: '123-456'
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
	            buyer_postcode: order.buyer_postcode,
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
	                        	item_name: '${item.name}',
	                        	item_total_price: order.amount
	                    	}
	                	}).done(function (data) {
	                  		switch(data.status){
	                  			case "vbankIssued":
	                  			// 가상계좌 발급 시 로직
	                  			break;
	                  		case "success":
		                  		// 결제 성공 시 로직
	                  			break;
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

	<div class="item-info">
		<div class="item-img">
			<img src="/file/display/${item.imageUUID}" alt="상품 이미지" />
		</div>
		
        <div class="item-detail">
        	<div class="item-detail-main">
        		<div class="item-name-favorite-btn">
        			<div class="item-name">${item.name}</div>
        			<button class="favorite-btn">
        				<img src="/images/myImages/empty-heart.png" alt="찜 버튼" />
        			</button>
        		</div>
        		<div class="price">${item.price}원</div>
        	</div>
        	
        	<div class="buy-quantity-menu">
        		<div class="quantity-controller">
        			<input class="quantity" type="text" value="1"/>
        			<div>
        				<button class="quantity-up">
        					<img src="/images/myImages/arrow-up.png" alt="위쪽 화살표" />
        				</button>
        				<button class="quantity-down">
        					<img src="/images/myImages/arrow-down.png" alt="아래쪽 화살표" />
        				</button>
        			</div>
        		</div>
        		<div class="buy-menu">
        			<button class="cart">장바구니</button>
        			<button class="buy">바로구매</button>
        		</div>
        	</div>
        </div>
	</div>
	
	<div class="review">
		
	</div>
	
</main>

</html>