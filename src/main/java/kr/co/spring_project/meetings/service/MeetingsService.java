package kr.co.spring_project.meetings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.spring_project.meetings.dto.CreateRequestDTO;
import kr.co.spring_project.meetings.dto.DetailResponseDTO;
import kr.co.spring_project.meetings.dto.UpdateRequestDTO;
import kr.co.spring_project.meetings.entity.Meetings;
import kr.co.spring_project.meetings.entity.MeetingsStatus;
import kr.co.spring_project.meetings.repository.MeetingsRepository;


@Service
@Transactional(readOnly = true)
public class MeetingsService {
	
	private final MeetingsRepository meetingsRepository;
	
	public MeetingsService(kr.co.spring_project.meetings.repository.MeetingsRepository meetingsRepository) {
		this.meetingsRepository = meetingsRepository;
	}
	
	public List<Meetings> list() {
		return meetingsRepository.findAllByOrderByCreatedAtDesc();
	}

	public DetailResponseDTO detail(Long id) {
		return null;
	}

	public Long create(CreateRequestDTO dto) {
		Meetings meetings = new Meetings();
		
		meetings.setTitle(dto.getTitle());
		meetings.setCategory(dto.getCategory());
		meetings.setRegion(dto.getRegion());
		meetings.setMeetingStart(dto.getMeetingsStart());
		meetings.setMeetingEnd(dto.getMeetingsEnd());
		meetings.setCapacity(dto.getCapacity());
		meetings.setContent(dto.getContent());
		
		meetings.setStatus(MeetingsStatus.OPEN);
		
		meetingsRepository.save(meetings);
		return meetings.getId();
	}

	public void update(Long id, UpdateRequestDTO dto) {
			
	}

	public void delete(Long id) {
	
		
	}
	
	

}
