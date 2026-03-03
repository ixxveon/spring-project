package kr.co.spring_project.meetings.service;

import kr.co.spring_project.meetings.dto.ReqBoardDTO;


public interface MeetingsService {
	
	public void create(ReqBoardDTO request, Long userId);
	
	

}
