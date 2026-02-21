package kr.co.spring_project.join.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.spring_project.join.dto.ReqJoinDTO;
import kr.co.spring_project.join.dto.ResJoinDTO;
import kr.co.spring_project.join.service.JoinRequestsService;
import kr.co.spring_project.meetings.entity.Meetings;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinRequestsController {
	private final JoinRequestsService joinRequestsService;
	
	// 참여 신청 폼 화면 보여줌
	@GetMapping("/form")
	public String joinForm(@RequestParam(name = "meetingId") Long meetingId, Model model) {
		
		// 모집상태 체크 (Open일때만 신청폼 열림)
		Meetings meeting = joinRequestsService.getStatusCheck(meetingId);
		
		// 신청 ㄴㄴ
		if(meeting == null) {
	        model.addAttribute("msg", "마감된 모집글입니다.");
	        return "pages/apply";
	    }

	    // 신청 가능
		model.addAttribute("meetingId", meetingId);
	    return "pages/form";
		
	}
	
	// 신청 저장
	@PostMapping("/apply")
	public String joinRequests(ReqJoinDTO request, HttpSession session) {

	    joinRequestsService.apply(request, session);
		
	    // 저장 끝났으면 GET으로 보내서 목록을 조회하게 해야 함
		return "redirect:/join/list";
	}
	
	// 신청 목록 화면
	@GetMapping("/list")
	public String JoinList(Model model, HttpSession session) {
	
		// 전체 목록 보게 할까..?
		List<ResJoinDTO> list = joinRequestsService.getAll();
		
		model.addAttribute("list", list);
		return "pages/list";
	}
}
