package kr.co.spring_project.meetings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.spring_project.meetings.dto.CreateRequestDTO;
import kr.co.spring_project.meetings.dto.UpdateRequestDTO;
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
	
	@GetMapping("/new")
	public String createForm(Model model) {
		model.addAttribute("createRequestDTO", new CreateRequestDTO());
		return "meetings/form";
	}
	
	@GetMapping("/{id}")
	public String detail(@PathVariable Long id, Model model) {
		model.addAttribute("meetings",meetingsService.detail(id));
		return "meetings/detail";
	}
	
	@GetMapping("/{id}/edit")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("meetings", meetingsService.detail(id));
		return "meetings/edit";
	}
	
	@PostMapping
	public String create(@ModelAttribute CreateRequestDTO dto) {
		Long id = meetingsService.create(dto);
		return "redirect:/meetings/" + id;
	}
	
	@PostMapping("/{id}/edit")
	public String update(@PathVariable Long id,
			             @ModelAttribute UpdateRequestDTO dto) {
		meetingsService.update(id, dto);
		return "redirect:/meeting/" + id;
	}
	
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id) {
		meetingsService.delete(id);
		return "redirect:/meeting";
	}
	
	


	

}	
	


