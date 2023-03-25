package com.newlecture.web.controller.member;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.web.entity.LoginForm;
import com.newlecture.web.entity.Member;
import com.newlecture.web.service.MemberService;

@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberService service;

	/////////////////////////////////////////////////////////////////////////

	// 로그인 창에 진입할 때
	@GetMapping("login")
	public String login(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		return "member.login";
	}

	// 로그인 정보를 입력하고 난 후
	@PostMapping("login")
	public String login(@ModelAttribute LoginForm loginForm, HttpSession session, BindingResult bindingResult,
			Model model) {

		// loginForm에 타입 오류가 발생할 경우
		if (bindingResult.hasErrors()) {
			return "redirect:login";
		}

		// 로그인 폼에서 아이디, 비밀번호를 꺼내서 login을 시킴
		Member loginMember = service.login(loginForm);

		// 로그인 실패(id, pw 에 맞는 Member가 없음)
		if (loginMember == null) {
			return "redirect:login";
		}

		// 로그인 실패가 아니라면, 로그인 성공!!

		// 세션에 Member 정보 저장
		session.setAttribute("loginSession", loginMember);

		System.out.println("loginMember의 id는 => " + loginMember.getId());

		return "redirect:/index";
	}

	/////////////////////////////////////////////////////////////////////////

	// 로그아웃할 때
	@GetMapping("logout")
	public String logout(HttpServletRequest request) {

		// 세션이 있으면 있는걸 반환, 없으면 null을 반환
		HttpSession session = request.getSession(false);

		if (session != null) {
			// 모든 세션을 삭제
			session.invalidate();
		}

		return "redirect:/index";
	}

	/////////////////////////////////////////////////////////////////////////

	// 약관동의 창에 진입할 때
	@GetMapping("agree")
	public String agree(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		return "member.agree";
	}

	// 약관동의 창에 진입할 때
	@PostMapping("agree")
	public String agree(String agree, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		// 약관에 동의했다면, 회원가입 창으로 넘어감
		if (agree != null && agree.equals("true")) {
			return "redirect:join";
		}
		// 약관에 동의하지 않았다면, 약관동의 창으로 다시 옴
		else {
			return "redirect:agree";
		}

	}

	/////////////////////////////////////////////////////////////////////////

	// 회원가입 창에 진입할 때
	@GetMapping("join")
	public String join(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		return "member.join";
	}

	// 회원가입 정보를 입력하고 난 후
	@PostMapping("join")
	public String join(String id, String id_check, String pwd, String pwd2, String name, String nickname,
			String nickname_check, String gender, String birthday, String isLunar, String phone, String email,
			String boss_id, String address,String btn, String idDuplicate, String nicknameDuplicate, String scrollY,
			Model model, HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		// LocalDate, 이건 그냥 생년월일로 현재 나이 계산하는데에 쓰인다
		LocalDate birthday_ = null;
		// 생년월일이 입력이 되었을 때
		if (birthday != null && !birthday.equals("")) {
			// 날짜 포맷 설정
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// String -> LocalDate
			birthday_ = LocalDate.parse(birthday, formatter);
		}

		// 페이지 새로고침 이후, 이미 써놨던 정보들이 지워지지 않게 유지시켜 주려고 담는 entity
		Member member = new Member(id, pwd, name, nickname, gender, birthday, isLunar, phone, email, boss_id, address, 1);
		model.addAttribute("member", member);
		model.addAttribute("pwd2", pwd2);

		// 아이디 중복확인할 때
		if (id_check != null && id_check.equals("중복확인")) {

			// 아이디 중복이 없을 때
			if (!service.checkIdDuplicate(id) && !id.equals("")) {
				// 아이디 중복이 없을 때, 뷰에서 중복없다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", "중복없음");
				model.addAttribute("nicknameDuplicate", nicknameDuplicate);
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
			// 아이디를 빈문자를 입력했을 때
			else if (id.equals("")) {
				// 아이디를 빈문자로 입력했을 때, 빈문자 안된다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", "빈문자안됨");
				model.addAttribute("nicknameDuplicate", nicknameDuplicate);
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
			// 아이디 중복이 있을 때
			else {
				// 아이디 중복이 있을 때, 뷰에서 중복있다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", "중복있음");
				model.addAttribute("nicknameDuplicate", nicknameDuplicate);
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
		}
		// 닉네임 중복확인할 때
		else if (nickname_check != null && nickname_check.equals("중복확인")) {

			// 닉네임 중복이 없을 때
			if (!service.checkNicknameDuplicate(nickname) && !nickname.equals("")) {
				// 닉네임 중복이 없을 때, 뷰에서 중복없다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", idDuplicate);
				model.addAttribute("nicknameDuplicate", "중복없음");
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
			// 닉네임을 빈문자를 입력했을 때
			else if (nickname.equals("")) {
				// 닉네임을 빈문자로 입력했을 때, 빈문자 안된다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", idDuplicate);
				model.addAttribute("nicknameDuplicate", "빈문자안됨");
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
			// 닉네임 중복이 있을 때
			else {
				// 닉네임 중복이 있을 때, 뷰에서 중복있다는 창을 띄우기 위해
				model.addAttribute("idDuplicate", idDuplicate);
				model.addAttribute("nicknameDuplicate", "중복있음");
				// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
				model.addAttribute("scrollNow", scrollY);
				return "member.join";
			}
		}
		// 회원가입 확인 버튼을 누를 때
		else if (btn != null && btn.equals("확인")) {

			// 중복확인 현황을 유지하기 위해
			model.addAttribute("idDuplicate", idDuplicate);
			model.addAttribute("nicknameDuplicate", nicknameDuplicate);
			// 스크롤을 유지하기 위해 넘기는 스크롤 Y좌표
			model.addAttribute("scrollNow", scrollY);

			// 모든 조건을 충족할 때(아이디, 닉네임 중복검사 마치고, 비밀번호와 비밀번호확인도 일치한 상태)
			if (idDuplicate.equals("중복없음") && nicknameDuplicate.equals("중복없음") && pwd.equals(pwd2)) {
				// 나이 계산
				LocalDate now = LocalDate.now();
				int age = now.getYear() - birthday_.getYear() + 1;

				// 계산한 나이 member에 넣어주기
				member.setAge(age);

				// 회원가입
				service.join(member);

				// 회원가입 완료 URL로 진입
				return "redirect:confirm";
			}

			return "member.join";
		}
		// 이 곳에 온다면 뭔가 잘못 클릭한 것
		return "redirect:join";
	}

	/////////////////////////////////////////////////////////////////////////

	// 회원가입 완료 창에 진입할 때
	@RequestMapping("confirm")
	public String confirm(HttpSession session) {

		Member loginMember = (Member) session.getAttribute("loginSession");

		// 로그인한 상태라면, /index 로 이동
		if (loginMember != null) {
			return "redirect:/index";
		}

		return "member.confirm";
	}
}