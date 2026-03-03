package kr.co.spring_project.meetings.service.impl;

import org.springframework.stereotype.Service;

import kr.co.spring_project.meetings.dto.ReqBoardDTO;
import kr.co.spring_project.meetings.entity.Meetings;
import kr.co.spring_project.meetings.repository.MeetingsRepository;
import kr.co.spring_project.meetings.service.MeetingsService;
import kr.co.spring_project.users.entity.Users;
import kr.co.spring_project.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingsServiceImpl implements MeetingsService {
	private final UsersRepository usersRepository;
	private final MeetingsRepository meetingsRepository;
	@Override
	public void create(ReqBoardDTO request, Long userId) {
		// 1. userId로 작성자(Users) 엔티티 조회
		Users writer = usersRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다.")) ;

		// 2. DTO 값이 “말이 되는지” 추가 체크 (예: 시작시간 < 종료시간)
		if (request.getMeetingStart() != null && request.getMeetingEnd() != null) {
			if(!request.getMeetingEnd().isAfter(request.getMeetingStart())) {
				throw new IllegalArgumentException("종료 시간은 시작 시간보다 이후여야 합니다.");
			}
		}

		// 3. DTO → Meetings 엔티티로 변환
		Meetings meetings = Meetings.builder()
							.writer(writer) 
							.title(request.getTitle())
							.category(request.getCategory())
							.region(request.getRegion())
							.content(request.getContent())
							.meetingStart(request.getMeetingStart())
							.meetingEnd(request.getMeetingEnd())
							.capacity(request.getCapacity())
							.build();

		// 4. repository.save()
		meetingsRepository.save(meetings);

		
	}
}
