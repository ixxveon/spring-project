package kr.co.spring_project.meetings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kr.co.spring_project.meetings.dto.ReqBoardDTO;
import kr.co.spring_project.meetings.service.MeetingsService;
import kr.co.spring_project.users.dto.ResLoginDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingsController {
	private final MeetingsService meetingsService;
	
	// 1. 지금 모임 만들기
	// - 눌렀을 때 form.html 화면 보여줘야 함 (GetMapping)
	@GetMapping("/create/form")
	public String createForm() {
		return "pages/form";
	}
	
	// 2. 화면 들어갔으니 폼 작성해야지(Post)
	@PostMapping("/create")
	public String create(@Valid ReqBoardDTO request, 
						BindingResult bindingResult, // 검증 결과 담는 상자
						HttpSession session,
						RedirectAttributes redirectAttributes
) {
		// 1. 로그인 체크
		ResLoginDTO loginUser = (ResLoginDTO) session.getAttribute("LOGIN_USER");
		
		if(loginUser == null) {
			redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
			return "redirect:/users/login/form";
		}
		
		// 2. 유효성 검증 실패 시 다시 폼으로 
		if (bindingResult.hasErrors()) {
			// 폼에서 th:errors / th:field 쓰고 있으면 이대로 리턴만 해도 에러 표시 가능
			return "pages/form";
		}
			
		// 로그인도 했고, 폼도 통과했으니 이제 이걸 Meetings로 만들어서 저장(Service이용)
		// 3. 저장
		meetingsService.create(request, loginUser.getId());
	 
		// 4. 검증 메시지 + redirect
		redirectAttributes.addFlashAttribute("success", "작성 완료!");
		return "redirect:/home";
	}
	
	

}
