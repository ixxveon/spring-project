package kr.co.spring_project.join.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.spring_project.join.dto.ReqJoinDTO;
import kr.co.spring_project.join.service.JoinRequestsService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinRequestsController {
	private final JoinRequestsService joinRequestsService;
	
	// 참여 신청 폼 화면 보여줌
	@GetMapping("/form")
	public String joinForm(@RequestParam(name="meetingId") Long meetingId, Model model) {
		
		// 모집상태 체크 (Open일때만 신청화면 보여짐)
		joinRequestsService.getStatusCheck(meetingId);
		
		model.addAttribute("meetingId", meetingId);
		model.addAttribute("status", "OPEN");
		
		return "pages/apply";
	}
	
	// 신청 저장
	@PostMapping
	public String joinRequests(ReqJoinDTO request, HttpSession session) {
		
		// 1. 로그인한 사용자인지
//		ResLoginDTO loginUser = (ResLoginDTO) session.getAttribute("LOGIN_USER");
//		
//		if (loginUser == null) {
//			return "redirect:/user/login/form";
//		}
		
		// 3. 정원이 남아 있는지
	    joinRequestsService.getMemberCount(request);
		
		// 4. 중복 신청 방지(이미 신청했으면 막기)
		
		// 5. 저장 대신 로그인
	
		 
		return "pages/list";
	}
}
