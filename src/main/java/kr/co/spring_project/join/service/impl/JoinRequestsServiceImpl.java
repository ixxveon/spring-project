package kr.co.spring_project.join.service.impl;

import org.springframework.stereotype.Service;

import kr.co.spring_project.join.dto.ReqJoinDTO;
import kr.co.spring_project.join.service.JoinRequestsService;
import kr.co.spring_project.meetings.entity.Meetings;
import kr.co.spring_project.meetings.repository.MeetingsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinRequestsServiceImpl implements JoinRequestsService {
	private final MeetingsRepository meetingsRepository;
	
	// 모임상태 체크
	@Override
	public void getStatusCheck(Long meetingId) {
		
		Meetings meetings = meetingsRepository.findById(meetingId).orElse(null);
		if(meetings == null) {
			System.out.println("존재하지 않는 모임입니다.");
		}
		
		if(!"OPEN".equals(meetings.getStatus())) {
			System.out.println("마감된 모집글입니다.");
		}
		
		
	}
	
	// 인원 체크
	@Override
	public void getMemberCount(ReqJoinDTO request) {
		/*
		 * Meetings meetings = meetingsRepository.findBy; if(meetings.capacity -
		 * request.getMemberCount();
		 */
	}
}
