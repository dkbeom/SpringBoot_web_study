<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>물품 등록</title>

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).bind('ready', function(){
		
		// type="file"인 input에 이미지 파일을 넣었을 때
		$('#item-image').change(preview);
		
		// 이미지 미리보기
		function preview(e){
			
			const file = e.target.files[0];
			
			if(isImageFile(file) === true && isOverSize(file) === false){
				const reader = new FileReader();
				reader.onload = function(e) {
					$('#item-image').parent('td').parent('tr')
					.after('<tr><td><img /></td></tr>')
					.next().children('td').children('img')
					.attr('src', e.target.result);
				}
				reader.readAsDataURL(file);
			}
		}
		
		// 확장자가 이미지 파일인지 확인
		function isImageFile(file){

			const ext = file.name.split('.').pop().toLowerCase(); // 파일명에서 확장자를 가져온다. 

			return ($.inArray(ext, ['jpg', 'jpeg', 'gif', 'png']) === -1) ? false : true;
		}
		
		// 파일의 최대 사이즈 확인
		function isOverSize(file){

			const maxSize = 10 * 1024 * 1024; // 10MB로 제한 

			return (file.size > maxSize) ? true : false;
		}
	});
</script>
</head>

<main>
	<div>

                <h2 class="main title">상품 등록</h2>

                <div class="breadcrumb">
                    <h3 class="hidden">breadlet</h3>
                    <ul>
                        <li>home</li>
                        <li>상품 관리</li>
                        <li>상품 등록</li>
                    </ul>
                </div>

                <form method="post" enctype="multipart/form-data">
                    <div class="margin-top first">
                        <h3 class="hidden">상품 등록</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>상품명</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="name" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>상품 가격</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="price" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>상품 이미지</th>
                                    <td colspan="3" class="text-align-left text-indent">
                                    	<input id="item-image" type="file" name="file" />
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" class="text-align-right">
                                    	<input class="vertical-align" type="checkbox" id="open" name="pub" value="true" checked />
                                    	<label for="open" class="margin-left">바로공개</label>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="등록" />
                        <a class="btn-text btn-cancel" href="/index">취소</a>
                    </div>
                </form>
</div>
</main>

</html>