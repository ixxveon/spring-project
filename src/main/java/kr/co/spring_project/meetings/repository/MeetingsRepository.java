package kr.co.spring_project.meetings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.spring_project.meetings.entity.Meetings;

public interface MeetingsRepository extends JpaRepository<Meetings, Long> {
	
	

}
