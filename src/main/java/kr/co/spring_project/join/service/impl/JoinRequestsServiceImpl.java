package kr.co.spring_project.join.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import kr.co.spring_project.join.dto.ReqJoinDTO;
import kr.co.spring_project.join.dto.ResJoinDTO;
import kr.co.spring_project.join.entity.JoinRequests;
import kr.co.spring_project.join.repository.JoinRequestsRepository;
import kr.co.spring_project.join.service.JoinRequestsService;
import kr.co.spring_project.meetings.entity.Meetings;
import kr.co.spring_project.meetings.repository.MeetingsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinRequestsServiceImpl implements JoinRequestsService {
	private final MeetingsRepository meetingsRepository;
	private final JoinRequestsRepository joinRequestsRepository;
	
	// 모임상태 체크
	@Override
	public Meetings getStatusCheck(Long meetingId) {
		// 1. 존재하는 모임인지
		Meetings meeting = meetingsRepository.findById(meetingId).orElse(null);

		System.out.println("조회된 meetingId = " + meetingId);
		System.out.println("DB status = [" + (meeting == null ? "null" : meeting.getStatus()) + "]");
	
		if(meeting == null) {
		    System.out.println("모집글이 존재하지 않습니다.");
		    return null;
		}
		
		// 2. 모임상태가 OPEN인지 확인!!
		Object statusObj = meeting.getStatus();
        String status = (statusObj == null) ? null : statusObj.toString(); // Enum이면 "OPEN" 같은 문자열로 변환됨

        if (status == null || !"OPEN".equalsIgnoreCase(status.trim())) {
            System.out.println("마감된 모집글입니다.");
            return null;
        }
		
		
		return meeting;
		
		
	}
	
	// 신청 가능 여부 판단
	@Override
	public void apply(ReqJoinDTO request, HttpSession session) {
		// 1. 로그인한 유저인지 체크 (Session?)
		Long userId = (Long) session.getAttribute("LOGIN_USER");
		if (userId == null) {
            System.out.println("로그인이 필요합니다.");
            return;
        }
		
		// 2. 존재하는 모임인지
		// 3. 모집상태가 open인지 체크
		// 위 로직과 같아서 위 메서드 재사용가능할듯
		Meetings meeting = getStatusCheck(request.getMeetingId());
		if (meeting == null) {
			// getStatusCheck에서 이미 이유 출력했으니 여기서는 종료만
	        return;
	    }
		
		// 4. 정원이 남아 있는지 체크
		if(meeting.getCapacity() != null && request.getMemberCount() > meeting.getCapacity()) {
			System.out.println("로그인이 필요합니다.");
			return;
		}
		
		// 5. 중복 신청 방지(?)                             존재하는지 이 모임에 이 유저가
		boolean alreadyApplied = joinRequestsRepository.existsByMeeting_IdAndUserId(request.getMeetingId(), userId);
		if(alreadyApplied) {
			 System.out.println("이미 신청한 모임입니다.");
	         return;
		}
		
		// 6. 신청 생성 Entity로 변환해서 저장
		JoinRequests joinRequest = JoinRequests.builder()
							      .meeting(meeting)
							      .userId(userId)
							      .memberCount(request.getMemberCount())
							      .phoneNumber(request.getPhoneNumber())
							      .message(request.getMessage())
							      .status("PENDING") // PENDING 대기중 APPROVED 승인됨 REJECTED 거절됨
							      .build();
		
		// 7. 저장
		joinRequestsRepository.save(joinRequest);
		System.out.println("신청 저장 완료");
	}
	
	@Override
	public List<ResJoinDTO> getAll() {
		List<JoinRequests> entityList = joinRequestsRepository.findAllByOrderByIdDesc();
		List<ResJoinDTO> dtoList = new ArrayList<>();
		
		for(JoinRequests j : entityList) {
			ResJoinDTO response = ResJoinDTO.builder()
								  .id(j.getId())
								  .userId(j.getUserId())
								  .meetingId(j.getMeeting().getId())
								  .status(j.getStatus())
								  .createdAt(j.getCreatedAt())
								  .build();
			dtoList.add(response);
			
		}
		
		return dtoList;
	
	}
}
