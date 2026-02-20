package kr.co.spring_project.meetings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.spring_project.meetings.service.MeetingsService;

@Controller
@RequestMapping("/meeting")
public class MeetingsController {
	
	private final MeetingsService meetingsService;
	
	public MeetingsController(MeetingsService meetingsService) {
		this.meetingsService = meetingsService;
		
	}
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("meetings", meetingsService.list());
		return "meetings/list";
	}

}
