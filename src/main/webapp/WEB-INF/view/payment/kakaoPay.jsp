<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>카카오페이 결제</title>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).bind('ready', function(){
		$('#apibtn').click(function(){
			$.ajax({
				url : '/payment/kakaoPay',
				dataType : 'json',
				type : 'POST',
				success : function(data){
					const url = data.next_redirect_pc_url;
					window.open(url);
				},
				error : function(error){
					alert(error);
				}
			});
		});
	});
</script>
</head>

<body>
	<div style="margin: 200px">
		<button id="apibtn">카카오페이 결제</button>
	</div>
</body>

</html>