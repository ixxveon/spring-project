package kr.co.spring_project.meetings.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.co.spring_project.meetings.entity.Meetings;
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
	

}
