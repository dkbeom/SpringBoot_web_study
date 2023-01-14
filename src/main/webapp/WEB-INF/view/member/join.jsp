<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main>
	<h2 class="main title">회원가입 폼</h2>

	<div class="breadcrumb" style="margin-top: -20px;">
		<h3 class="hidden">경로</h3>
		<img src="/images/member/step2.png" alt="회원가입 1단계" />
	</div>


	<form id="form1" method="post" enctype="multipart/form-data">
		<fieldset>
			<legend class="hidden">회원정보</legend>
			<table class="table margin-top first">
				<tbody>
					<tr>
						<th><label>아이디</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="text" id="id-text" name="id" value="${member.id}"
							class="width-half" placeholder="영문과 숫자 2~20자 이내"
							pattern="^\w{2,20}$" /> <input class="btn-text btn-default"
							type="submit" id="id-check-button" name="id_check" value="중복확인"
							onclick="getScrollY();" />
							
							<c:if test="${idDuplicate eq '중복없음'}">
								<div>사용 가능한 아이디입니다.</div>
							</c:if> <c:if test="${idDuplicate eq '중복있음'}">
								<div>이미 존재하는 아이디입니다.</div>
							</c:if> <c:if test="${idDuplicate eq '빈문자안됨'}">
								<div>아이디는 빈문자가 될 수 없습니다.</div>
							</c:if>
						</td>
					</tr>
					<tr>
						<th><label>비밀번호</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="password" id="pwd" name="pwd" value="${member.pwd}"
							placeholder="비밀번호 입력" />
						</td>
					</tr>
					<tr>
						<th><label>비밀번호 확인</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="password" id="pwd2" name="pwd2" value="${pwd2}" />
						</td>
					</tr>
					<tr>
						<th><label>이름</label></th>
						<td colspan="3" class="text-align-left indent">
							<input class="width-half" type="text" id="name"
							name="name" value="${member.name}" />
						</td>
					</tr>
					<tr>
						<th><label>닉네임</label></th>
						<td colspan="3" class="text-align-left indent">
						
							<input type="text" class="width-half"
							id="nickname" name="nickname" value="${member.nickname}"
							placeholder="영문과 숫자 2~20자 이내" pattern="^\w{2,20}$" />

							<input type="submit" class="btn-text btn-default"
							id="id-check-button" name="nickname_check" value="중복확인"
							onclick="getScrollY();" />
							
							<c:if test="${nicknameDuplicate eq '중복없음'}">
								<div>사용 가능한 닉네임입니다.</div>
							</c:if>
							<c:if test="${nicknameDuplicate eq '중복있음'}">
								<div>이미 존재하는 닉네임입니다.</div>
							</c:if>
							<c:if test="${nicknameDuplicate eq '빈문자안됨'}">
								<div>닉네임은 빈문자가 될 수 없습니다.</div>
							</c:if>
							
						</td>
					</tr>
					<tr>
						<th><label>성별</label></th>
						<td colspan="3" class="text-align-left indent">
							<select class="width-half" id="gender" name="gender">
								<option value="">선택</option>
								<option value="남성"
									<c:if test="${member.gender eq '남성'}">selected</c:if>>남성</option>
								<option value="여성"
									<c:if test="${member.gender eq '여성'}">selected</c:if>>여성</option>
							</select>
						</td>
					</tr>
					<tr>
						<th><label>생년월일</label></th>
						<td colspan="3" class="text-align-left indent">
						
							<input type="date" id="birthday" name="birthday" value="${member.birthday}"
							class="width-half" placeholder="예) 2000-02-17" />
							<!-- <input name="y" type="text" class="width-small margin-hor" style="margin-left:0px;" />년
							<input name="m" type="text" class="width-small margin-hor" />월
                            <input name="d" type="text" class="width-small margin-hor" />일 -->
							
							<input type="radio" id="solar" name="isLunar" value="양력" class="vertical-middle margin-hor"
							<c:if test="${member.isLunar eq '양력'}">checked</c:if> />
							양력
							
							<input type="radio" id="lunar" name="isLunar" value="음력" class="vertical-middle margin-hor"
							<c:if test="${member.isLunar eq '음력'}">checked</c:if> />
							음력
							
						</td>
					</tr>
					<tr>
						<th><label>핸드폰번호</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="text" id="phone" name="phone" value="${member.phone}"
							class="width-half" pattern="^01[016789]-\d{3,4}-\d{4}$"
							placeholder="예) 010-1111-2222" />
						</td>
					</tr>
					<tr>
						<th><label>이메일</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="email" id="email" name="email" value="${member.email}"
							class="width-half" placeholder="예) user@newlecture.com" />
						</td>
					</tr>
					<tr>
						<th><label>상사 아이디</label></th>
						<td colspan="3" class="text-align-left indent">
							<input type="text" id="boss_id" name="boss_id" value="${member.boss_id}"
							class="width-half" placeholder="모르면 안적어도 됩니다^^" />
						</td>
					</tr>

					<tr>
						<td colspan="4">
							<input id="submit-Button" type="submit"
							name="btn" value="확인" style="height: 30px; margin: 20px;"
							class="btn-text btn-default" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" id="idDuplicate" name="idDuplicate" value="${idDuplicate}" />
							<input type="hidden" id="nicknameDuplicate" name="nicknameDuplicate" value="${nicknameDuplicate}" />
							<input type="hidden" name="scrollY" id="scrollY" />
							<input type="hidden" name="scrollNow" value="${scrollNow}" id="scrollNow" />
						</td>
					</tr>
				</tbody>
			</table>
		</fieldset>
	</form>
</main>
<script>
	const submitButton = document.querySelector("#submit-Button");
	const id_text = document.querySelector("#id-text");
	const pwd = document.querySelector("#pwd");
	const pwd2 = document.querySelector("#pwd2");
	const name = document.querySelector("#name");
	const nickname = document.querySelector("#nickname");
	const gender = document.querySelector("#gender");
	const birthday = document.querySelector("#birthday");
	const isLunar = document.getElementsByName("isLunar");
	const phone = document.querySelector("#phone");
	const email = document.querySelector("#email");
	const boss_id = document.querySelector("#boss_id");
	const idDuplicate = document.querySelector("#idDuplicate");
	const nicknameDuplicate = document.querySelector("#nicknameDuplicate");
	
	// submit 버튼들 누르면(onclick) 발생하는 이벤트
	function getScrollY() {
		// 현재 스크롤 Y좌표 저장
		document.querySelector("#scrollY").value = window.scrollY;
	}
	
	// 화면이 새로 로드될 때
	window.addEventListener('load', (e) => {
		const scrollNow = document.querySelector("#scrollNow").value;
		window.scrollTo(0, scrollNow);
	})
	
	submitButton.onclick = () => {
		// 현재 스크롤 Y좌표 저장
		document.querySelector("#scrollY").value = window.scrollY;
		
		// 정보 하나라도 빼먹었을 때
		if (id_text.value === "" || pwd.value === "" || pwd2.value === "" ||
			name.value === "" || nickname.value === "" || gender.value === "" ||
			birthday.value === "" || (isLunar[0].checked === false && isLunar[1].checked === false) ||
			phone.value === "" || email.value === "") {
			alert("입력하지 않은 회원정보가 있습니다.");
			
			// 버튼을 눌러서, 페이지가 넘어가는 것을 막아줌
			e.preventDefault();
		}
		// 비밀번호와 비밀번호확인이 같지 않을 때
		else if (pwd.value !== pwd2.value) {
			alert("비밀번호와 비밀번호 확인란이 일치하지 않습니다.");
			
			// 버튼을 눌러서, 페이지가 넘어가는 것을 막아줌
			e.preventDefault();
		}
		// 비밀번호와 비밀번호확인 하나라도 아무것도 안적었을 때
		else if (pwd.value === "" || pwd2.value === "") {
			alert("비밀번호를 적어주세요.");
			
			// 버튼을 눌러서, 페이지가 넘어가는 것을 막아줌
			e.preventDefault();
		}
		// 아이디 중복검사 안했을 때
		else if (idDuplicate.value !== "중복없음") {
			alert("아이디 중복확인을 마쳐주세요.");
			
			// 버튼을 눌러서, 페이지가 넘어가는 것을 막아줌
			e.preventDefault();
		}
		// 닉네임 중복검사 안했을 때
		else if (nicknameDuplicate.value !== "중복없음") {
			alert("닉네임 중복확인을 마쳐주세요.");
			
			// 버튼을 눌러서, 페이지가 넘어가는 것을 막아줌
			e.preventDefault();
		}
	}
</script>



