package kr.co.spring_project.meetings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.spring_project.meetings.entity.Meetings;

public interface MeetingsRepository extends JpaRepository<Meetings, Long> {
	List<Meetings> findAllByOrderByCreatedAtDesc();
	

}
